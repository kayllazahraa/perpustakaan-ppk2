package com.polstat.perpustakaan.mapper;

import com.polstat.perpustakaan.dto.BorrowingDto;
import com.polstat.perpustakaan.entity.Borrowing;

public class BorrowingMapper {

    public static BorrowingDto mapToBorrowingDto(Borrowing borrowing) {
        return BorrowingDto.builder()
                .id(borrowing.getId())
                .memberId(borrowing.getMemberId().getId())
                .bookId(borrowing.getBook().getId())
                .borrowDate(borrowing.getBorrowDate())
                .returnDate(borrowing.getReturnDate())
                .status(borrowing.getStatus())
                .lateDays(borrowing.getLateDays())
                .build();
    }
}