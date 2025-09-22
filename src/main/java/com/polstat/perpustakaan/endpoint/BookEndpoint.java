package com.polstat.perpustakaan.endpoint;

import com.polstat.perpustakaan.gen.AddBookRequest;
import com.polstat.perpustakaan.gen.AddBookResponse;
import com.polstat.perpustakaan.gen.BookDto;
import com.polstat.perpustakaan.gen.GetAllBooksRequest;
import com.polstat.perpustakaan.gen.GetAllBooksResponse;
import com.polstat.perpustakaan.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import java.util.List;
import java.util.stream.Collectors;

@Endpoint
public class BookEndpoint {
    private static final String NAMESPACE_URI = "http://www.polstat.com/perpustakaan/gen";

    @Autowired
    private BookService bookService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addBookRequest")
    @ResponsePayload
    public AddBookResponse addBook(@RequestPayload AddBookRequest request) {
        AddBookResponse response = new AddBookResponse();
        com.polstat.perpustakaan.dto.BookDto bookDto = new com.polstat.perpustakaan.dto.BookDto();
        bookDto.setTitle(request.getBook().getTitle());
        bookDto.setAuthor(request.getBook().getAuthor());
        bookDto.setDescription(request.getBook().getDescription());
        bookService.createBook(bookDto);
        response.setBook(request.getBook());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllBooksRequest")
    @ResponsePayload
    public GetAllBooksResponse getAllBooks(@RequestPayload GetAllBooksRequest request) {
        GetAllBooksResponse response = new GetAllBooksResponse();
        List<com.polstat.perpustakaan.dto.BookDto> bookDtos = bookService.getBooks();

        List<BookDto> books = bookDtos.stream()
                .map(this::convertToGeneratedBookDto)
                .collect(Collectors.toList());

        response.getBook().addAll(books);
        return response;
    }

    private BookDto convertToGeneratedBookDto(com.polstat.perpustakaan.dto.BookDto bookDto) {
        BookDto generatedBookDto = new BookDto();
        generatedBookDto.setId(bookDto.getId());
        generatedBookDto.setTitle(bookDto.getTitle());
        generatedBookDto.setAuthor(bookDto.getAuthor());
        generatedBookDto.setDescription(bookDto.getDescription());
        return generatedBookDto;
    }
}
