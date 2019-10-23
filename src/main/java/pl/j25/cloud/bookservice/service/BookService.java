package pl.j25.cloud.bookservice.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.j25.cloud.bookservice.mapper.BookMapper;
import pl.j25.cloud.bookservice.model.Book;
import pl.j25.cloud.bookservice.model.dto.CreateBookRequest;
import pl.j25.cloud.bookservice.model.dto.UpdateBookRequest;
import pl.j25.cloud.bookservice.repository.BookRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper bookMapper;

    public Book getById(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            return book;
        }
        throw new EntityNotFoundException("Nie znaleziono encji");
    }

    public Book createBookFromDto(CreateBookRequest createBookRequest) {
        Book book = bookMapper.createBookFromDto(createBookRequest);
        return bookRepository.save(book);
    }

    public List<Book> getByTitleOrAuthor(String title, String author) {
        if (author != null && !author.isEmpty() && title != null && !title.isEmpty()) {
            return bookRepository.findByAuthorContainingOrTitleContaining(author, title);
        }
        if (author != null && !author.isEmpty()) {
            return bookRepository.findByAuthorContaining(author);
        }
        if (title != null && !title.isEmpty()) {
            return bookRepository.findByTitleLike(title);
        }
        return new ArrayList<>();
    }

    public void updateBookFromDto(UpdateBookRequest bookToEdit) {
        Optional<Book> optionalBook = bookRepository.findById(bookToEdit.getId());
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();

            if (bookToEdit.getNumberOfAvailableCopies() != null) {
                book.setNumberOfAvailableCopies(bookToEdit.getNumberOfAvailableCopies());
            }
            bookRepository.save(book);
            return;
        }
        throw new EntityNotFoundException("Book, id not found: " + bookToEdit.getId());
    }
}
