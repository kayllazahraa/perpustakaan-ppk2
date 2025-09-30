package com.polstat.perpustakaan.service;

import com.polstat.perpustakaan.dto.BorrowingDto;
import java.util.List;

public interface BorrowingService {
    BorrowingDto borrowBook(Long memberId, Long bookId);
    BorrowingDto returnBook(Long borrowingId);
    List<BorrowingDto> getAllBorrowings();
    BorrowingDto getBorrowingById(Long id);
}