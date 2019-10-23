package pl.j25.cloud.bookservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.j25.cloud.bookservice.model.Book;
import pl.j25.cloud.bookservice.model.dto.CreateBookRequest;
import pl.j25.cloud.bookservice.model.dto.UpdateBookRequest;
import pl.j25.cloud.bookservice.service.BookService;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @PutMapping
    private Book putBook(@RequestBody CreateBookRequest createBookRequest) {
        return bookService.createBookFromDto(createBookRequest);

    }

    @PostMapping
    private void editBook(@RequestBody UpdateBookRequest updateBookRequest) {
        bookService.updateBookFromDto(updateBookRequest);
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable(name = "id") Long id) {
        return bookService.getById(id);
    }

    @GetMapping("/getBy/")
    public List<Book> getBookByTitleOrAuthor(@RequestParam(name = "title") String title,
                                             @RequestParam(name = "author") String author) {
        return bookService.getByTitleOrAuthor(title, author);

    }
}
