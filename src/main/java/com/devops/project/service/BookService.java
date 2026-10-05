package com.devops.project.service;

import com.devops.project.model.Book;
import com.devops.project.repository.BookRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {
    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public List<Book> findAll() { return repository.findAll(); }

    public List<Book> search(String q) {
        if (q == null || q.isBlank()) return findAll();
        return repository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(q, q);
    }

    public Book findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Book not found"));
    }

    public Book save(Book book) {
        if (book.getId() == null) {
            book.setAvailableQuantity(book.getQuantity());
        } else {
            Book old = findById(book.getId());
            int issued = old.getQuantity() - old.getAvailableQuantity();
            book.setAvailableQuantity(Math.max(0, book.getQuantity() - issued));
        }
        return repository.save(book);
    }

    public void delete(Long id) { repository.deleteById(id); }

    public long totalBooks() { return repository.count(); }

    public int totalCopies() {
        return findAll().stream().mapToInt(Book::getQuantity).sum();
    }

    public int availableCopies() {
        return findAll().stream().mapToInt(Book::getAvailableQuantity).sum();
    }
}
