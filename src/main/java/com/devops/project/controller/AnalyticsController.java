// package com.devops.project.controller;

// import com.devops.project.model.Book;
// import com.devops.project.repository.BookRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;

// import java.util.List;
// import java.util.Map;
// import java.util.stream.Collectors;

// @Controller
// public class AnalyticsController {

//     @Autowired
//     private BookRepository bookRepository;

//     @GetMapping("/analytics")
//     public String showAnalytics(Model model) {
//         List<Book> books = bookRepository.findAll();

//         int totalTitles = books.size();
//         int totalCopies = books.stream()
//                 .mapToInt(b -> b.getQuantity() != null ? b.getQuantity() : 0)
//                 .sum();
//         int availableCopies = books.stream()
//                 .mapToInt(b -> b.getAvailableQuantity() != null ? b.getAvailableQuantity() : 0)
//                 .sum();
//         int issuedCopies = Math.max(0, totalCopies - availableCopies);
//         double utilizationRate = totalCopies > 0 ? ((double) issuedCopies / totalCopies) * 100 : 0.0;

//         // Group book titles by Category
//         Map<String, Long> categoryCountMap = books.stream()
//                 .collect(Collectors.groupingBy(
//                         b -> (b.getCategory() != null && !b.getCategory().isBlank()) ? b.getCategory() : "Uncategorized",
//                         Collectors.counting()
//                 ));

//         // Group total stock copies by Category
//         Map<String, Integer> categoryStockMap = books.stream()
//                 .collect(Collectors.groupingBy(
//                         b -> (b.getCategory() != null && !b.getCategory().isBlank()) ? b.getCategory() : "Uncategorized",
//                         Collectors.summingInt(b -> b.getQuantity() != null ? b.getQuantity() : 0)
//                 ));

//         model.addAttribute("totalTitles", totalTitles);
//         model.addAttribute("totalCopies", totalCopies);
//         model.addAttribute("availableCopies", availableCopies);
//         model.addAttribute("issuedCopies", issuedCopies);
//         model.addAttribute("utilizationRate", String.format("%.1f", utilizationRate));

//         model.addAttribute("categoryLabels", categoryCountMap.keySet().stream().toList());
//         model.addAttribute("categoryCounts", categoryCountMap.values().stream().toList());
//         model.addAttribute("categoryStock", categoryStockMap.values().stream().toList());
//         model.addAttribute("categoryStockMap", categoryStockMap);

//         return "analytics";
//     }
// }
