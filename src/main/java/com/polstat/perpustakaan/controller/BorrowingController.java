package com.polstat.perpustakaan.controller;

import com.polstat.perpustakaan.dto.BorrowingDto;
import com.polstat.perpustakaan.service.BorrowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/borrowings")
public class BorrowingController {

    @Autowired
    private BorrowingService borrowingService;

    // 1. CREATE (Borrow)
    @PostMapping("/borrow")
    public ResponseEntity<BorrowingDto> borrowBook(@RequestParam Long memberId, @RequestParam Long bookId) {
        return ResponseEntity.ok(borrowingService.borrowBook(memberId, bookId));
    }

    // 2. UPDATE (Return)
    @PutMapping("/return/{borrowingId}")
    public ResponseEntity<BorrowingDto> returnBook(@PathVariable Long borrowingId) {
        return ResponseEntity.ok(borrowingService.returnBook(borrowingId));
    }

    // 3. READ all borrowing records
    @GetMapping
    public ResponseEntity<List<BorrowingDto>> getAllBorrowings() {
        return ResponseEntity.ok(borrowingService.getAllBorrowings());
    }

    // 4. READ borrowing record by ID
    @GetMapping("/{id}")
    public ResponseEntity<BorrowingDto> getBorrowingById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(borrowingService.getBorrowingById(id));
    }
}