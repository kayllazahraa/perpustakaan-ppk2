package com.polstat.perpustakaan.dto;

import com.polstat.perpustakaan.entity.Borrowing;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BorrowingDto {
    private Long id;
    private Long memberId;
    private Long bookId;
    private Date borrowDate;
    private Date returnDate;
    private Borrowing.BorrowingStatus status;
    private int lateDays;
}