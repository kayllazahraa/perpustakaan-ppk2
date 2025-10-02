package com.polstat.perpustakaan.controller;

import com.polstat.perpustakaan.dto.BookDto;
import com.polstat.perpustakaan.service.BookService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class BookGraphqlController {
    private final BookService bookService;

    @Autowired
    public BookGraphqlController(BookService bookService) {
        this.bookService = bookService;
    }

    @QueryMapping
    public List<BookDto> books() {
        return bookService.getBooks();
    }

    @QueryMapping
    public BookDto bookById(@Argument Long id) {
        return bookService.getBookById(id);
    }

    @MutationMapping
    public BookDto createBook(@Argument String title, @Argument String description, @Argument String author) {
        BookDto bookDto = BookDto.builder()
                .title(title)
                .description(description)
                .author(author)
                .build();
        return bookService.createBook(bookDto);
    }

    @MutationMapping
    public BookDto updateBook(@Argument Long id, @Argument String title, @Argument String description, @Argument String author) {
        BookDto bookDto = BookDto.builder()
                .title(title)
                .author(author)
                .description(description)
                .build();
        return bookService.updateBook(id, bookDto);
    }

    @MutationMapping
    public BookDto deleteBook(@Argument Long id) {
        BookDto bookToDelete = bookService.getBookById(id);

        bookService.deleteBook(id);
        return bookToDelete;
    }
}