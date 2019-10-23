package pl.j25.cloud.bookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.j25.cloud.bookservice.model.Book;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByAuthorContainingOrTitleContaining(String title, String author);

    List<Book> findByAuthorContaining(String author);

    List<Book> findByTitleLike(String title);
}
