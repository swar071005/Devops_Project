package com.devops.project.service;

import com.devops.project.model.Book;
import com.devops.project.model.BookIssue;
import com.devops.project.repository.BookIssueRepository;
import com.devops.project.repository.BookRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class IssueService {
    private final BookIssueRepository issueRepository;
    private final BookRepository bookRepository;

    public IssueService(BookIssueRepository issueRepository, BookRepository bookRepository) {
        this.issueRepository = issueRepository;
        this.bookRepository = bookRepository;
    }

    public List<BookIssue> activeIssues() {
        return issueRepository.findByStatusOrderByIssueDateDesc("ISSUED");
    }

    public List<BookIssue> allIssues() {
        return issueRepository.findAll();
    }

    public long issuedCount() {
        return issueRepository.findByStatusOrderByIssueDateDesc("ISSUED").size();
    }

    public BookIssue issue(Long bookId, String studentName) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        if (book.getAvailableQuantity() <= 0) {
            throw new IllegalStateException("No copies available for this book.");
        }

        book.setAvailableQuantity(book.getAvailableQuantity() - 1);
        bookRepository.save(book);

        BookIssue issue = new BookIssue();
        issue.setBook(book);
        issue.setStudentName(studentName);
        issue.setIssueDate(LocalDate.now());
        issue.setStatus("ISSUED");
        return issueRepository.save(issue);
    }

    public void returnBook(Long issueId) {
        BookIssue issue = issueRepository.findById(issueId)
                .orElseThrow(() -> new IllegalArgumentException("Issue record not found"));

        if (!"ISSUED".equals(issue.getStatus())) return;

        issue.setStatus("RETURNED");
        issue.setReturnDate(LocalDate.now());

        Book book = issue.getBook();
        book.setAvailableQuantity(Math.min(book.getQuantity(), book.getAvailableQuantity() + 1));

        bookRepository.save(book);
        issueRepository.save(issue);
    }
}
