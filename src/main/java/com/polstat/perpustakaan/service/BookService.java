package com.polstat.perpustakaan.service;

import com.polstat.perpustakaan.dto.BookDto;
import java.util.List;

public interface BookService {
    BookDto createBook(BookDto bookDto); // createBook sekarang me-return DTO
    List<BookDto> getBooks();
    BookDto getBookById(Long id); // Method baru
    BookDto updateBook(Long id, BookDto bookDto); // Method baru
    void deleteBook(Long id); // Method baru
    List<BookDto> searchBooks(String keyword);
}