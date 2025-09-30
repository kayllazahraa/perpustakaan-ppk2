package com.polstat.perpustakaan.controller;

import com.polstat.perpustakaan.dto.BorrowingDto;
import com.polstat.perpustakaan.service.BorrowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/borrowings")
public class BorrowingController {

    @Autowired
    private BorrowingService borrowingService;

    @PostMapping("/borrow")
    public ResponseEntity<BorrowingDto> borrowBook(@RequestParam Long memberId, @RequestParam Long bookId) {
        return ResponseEntity.ok(borrowingService.borrowBook(memberId, bookId));
    }

    @PutMapping("/return/{borrowingId}")
    public ResponseEntity<BorrowingDto> returnBook(@PathVariable Long borrowingId) {
        return ResponseEntity.ok(borrowingService.returnBook(borrowingId));
    }
}