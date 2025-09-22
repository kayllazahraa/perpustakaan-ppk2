package com.polstat.perpustakaan.repository;

import com.polstat.perpustakaan.entity.Book;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long>{
    // method untuk mencari buku berdasarkan keyword di title, author, atau description
    List<Book> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String title, String author, String description);
}