package com.polstat.perpustakaan.service;

import com.polstat.perpustakaan.dto.BookDto;
import java.util.List;

public interface BookService {
    public void createBook(BookDto bookDto);
    public List<BookDto> getBooks();
    // menambahkan deklarasi method untuk search books
    public List<BookDto> searchBooks(String keyword);
}