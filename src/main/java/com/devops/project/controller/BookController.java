package com.devops.project.controller;

import com.devops.project.model.Book;
import com.devops.project.service.BookService;
import com.devops.project.service.IssueService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class BookController {
    private final BookService bookService;
    private final IssueService issueService;

    public BookController(BookService bookService, IssueService issueService) {
        this.bookService = bookService;
        this.issueService = issueService;
    }

    @GetMapping("/")
    public String dashboard(Model model) {
        model.addAttribute("totalBooks", bookService.totalBooks());
        model.addAttribute("totalCopies", bookService.totalCopies());
        model.addAttribute("availableCopies", bookService.availableCopies());
        model.addAttribute("issuedCount", issueService.issuedCount());
        return "index";
    }

    @GetMapping("/books")
    public String books(@RequestParam(required = false) String q, Model model) {
        model.addAttribute("books", bookService.search(q));
        model.addAttribute("q", q == null ? "" : q);
        return "books";
    }

    @GetMapping("/books/new")
    public String newBook(Model model) {
        model.addAttribute("book", new Book());
        return "book-form";
    }

    @GetMapping("/books/edit/{id}")
    public String editBook(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.findById(id));
        return "book-form";
    }

    @PostMapping("/books/save")
    public String saveBook(@Valid @ModelAttribute Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "book-form";
        }
        bookService.save(book);
        return "redirect:/books?success=Book+saved+successfully";
    }

    @GetMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.delete(id);
        return "redirect:/books?success=Book+deleted";
    }

    @GetMapping("/issue")
    public String issuePage(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "issue";
    }

    @PostMapping("/issue")
    public String issue(@RequestParam Long bookId, @RequestParam String studentName, Model model) {
        try {
            issueService.issue(bookId, studentName);
            return "redirect:/issues?success=Book+issued+successfully";
        } catch (RuntimeException ex) {
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("books", bookService.findAll());
            return "issue";
        }
    }

    @GetMapping("/issues")
    public String issues(Model model) {
        model.addAttribute("issues", issueService.allIssues());
        return "issues";
    }

    @PostMapping("/return/{id}")
    public String returnBook(@PathVariable Long id) {
        issueService.returnBook(id);
        return "redirect:/issues?success=Book+returned+successfully";
    }

    @GetMapping("/analytics")
    public String showAnalytics(Model model) {
        List<Book> books = bookService.findAll();

        int totalTitles = books.size();
        int totalCopies = books.stream()
                .mapToInt(b -> b.getQuantity() != null ? b.getQuantity() : 0)
                .sum();
        int availableCopies = books.stream()
                .mapToInt(b -> b.getAvailableQuantity() != null ? b.getAvailableQuantity() : 0)
                .sum();
        int issuedCopies = Math.max(0, totalCopies - availableCopies);
        double utilizationRate = totalCopies > 0 ? ((double) issuedCopies / totalCopies) * 100 : 0.0;

        Map<String, Long> categoryCountMap = books.stream()
                .collect(Collectors.groupingBy(
                        b -> (b.getCategory() != null && !b.getCategory().isBlank()) ? b.getCategory() : "Uncategorized",
                        Collectors.counting()
                ));

        Map<String, Integer> categoryStockMap = books.stream()
                .collect(Collectors.groupingBy(
                        b -> (b.getCategory() != null && !b.getCategory().isBlank()) ? b.getCategory() : "Uncategorized",
                        Collectors.summingInt(b -> b.getQuantity() != null ? b.getQuantity() : 0)
                ));

        model.addAttribute("totalTitles", totalTitles);
        model.addAttribute("totalCopies", totalCopies);
        model.addAttribute("availableCopies", availableCopies);
        model.addAttribute("issuedCopies", issuedCopies);
        model.addAttribute("utilizationRate", String.format("%.1f", utilizationRate));

        model.addAttribute("categoryLabels", categoryCountMap.keySet().stream().toList());
        model.addAttribute("categoryCounts", categoryCountMap.values().stream().toList());
        model.addAttribute("categoryStock", categoryStockMap.values().stream().toList());
        model.addAttribute("categoryStockMap", categoryStockMap);

        return "analytics";
    }
}
