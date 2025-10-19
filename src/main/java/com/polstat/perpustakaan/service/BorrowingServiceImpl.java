package com.polstat.perpustakaan.service;

import com.polstat.perpustakaan.dto.BorrowingDto;
import com.polstat.perpustakaan.entity.Book;
import com.polstat.perpustakaan.entity.Borrowing;
import com.polstat.perpustakaan.entity.Member;
import com.polstat.perpustakaan.mapper.BorrowingMapper;
import com.polstat.perpustakaan.repository.BookRepository;
import com.polstat.perpustakaan.repository.BorrowingRepository;
import com.polstat.perpustakaan.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class BorrowingServiceImpl implements BorrowingService {

    @Autowired
    private BorrowingRepository borrowingRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public BorrowingDto borrowBook(Long memberId, Long bookId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        Borrowing borrowing = Borrowing.builder()
                .memberId(member)
                .book(book)
                .borrowDate(new Date())
                .status(Borrowing.BorrowingStatus.BORROWED)
                .build();

        borrowing = borrowingRepository.save(borrowing);
        return BorrowingMapper.mapToBorrowingDto(borrowing);
    }

    @Override
    public BorrowingDto returnBook(Long borrowingId) {
        Borrowing borrowing = borrowingRepository.findById(borrowingId)
                .orElseThrow(() -> new RuntimeException("Borrowing record not found"));

        borrowing.setReturnDate(new Date());
        borrowing.setStatus(Borrowing.BorrowingStatus.RETURNED);

        long diffInMillies = Math.abs(borrowing.getReturnDate().getTime() - borrowing.getBorrowDate().getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        if (diff > 7) {
            borrowing.setLateDays((int) (diff - 7));
        } else {
            borrowing.setLateDays(0);
        }

        borrowing = borrowingRepository.save(borrowing);
        return BorrowingMapper.mapToBorrowingDto(borrowing);
    }

    @Override
    public List<BorrowingDto> getAllBorrowings() {
        return borrowingRepository.findAll().stream()
                .map(BorrowingMapper::mapToBorrowingDto)
                .collect(Collectors.toList());
    }

    @Override
    public BorrowingDto getBorrowingById(Long id) {
        Borrowing borrowing = borrowingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Borrowing record not found with id: " + id));
        return BorrowingMapper.mapToBorrowingDto(borrowing);
    }
}