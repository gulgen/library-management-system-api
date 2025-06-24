package com.patika.Library.Management.System.API.business.concretes;

import com.patika.Library.Management.System.API.business.abstracts.IBookBorrowingService;
import com.patika.Library.Management.System.API.business.abstracts.IBookService;
import com.patika.Library.Management.System.API.core.config.exception.BookBorrowingExistException;
import com.patika.Library.Management.System.API.core.config.exception.BookUnavailableException;
import com.patika.Library.Management.System.API.core.config.exception.NotFoundException;
import com.patika.Library.Management.System.API.core.config.modelMapper.IModelMapperService;
import com.patika.Library.Management.System.API.core.utils.Msg;
import com.patika.Library.Management.System.API.dao.BookBorrowingRepository;
import com.patika.Library.Management.System.API.dto.request.bookborrowing.BookBorrowingSaveRequest;
import com.patika.Library.Management.System.API.dto.request.bookborrowing.BookBorrowingUpdateRequest;
import com.patika.Library.Management.System.API.dto.response.BookBorrowingResponse;
import com.patika.Library.Management.System.API.entities.Book;
import com.patika.Library.Management.System.API.entities.BookBorrowing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

@Service
public class BookBorrowingManager implements IBookBorrowingService {
    private final BookBorrowingRepository borrowRepo;
    private final IModelMapperService modelMapper;
    private final IBookService bookService;

    public BookBorrowingManager(BookBorrowingRepository borrowRepo, IModelMapperService modelMapper, IBookService bookService) {
        this.borrowRepo = borrowRepo;
        this.modelMapper = modelMapper;
        this.bookService = bookService;
    }

    @Override
    public BookBorrowingResponse get(int id) {
        BookBorrowing borrow = this.borrowRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        return getBorrowResponse(borrow);
    }

    @Override
    public BookBorrowingResponse update(BookBorrowingUpdateRequest borrowUpdateRequest) {
        BookBorrowing existing = this.borrowRepo.findById(borrowUpdateRequest.getId())
                .orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));

        BookBorrowing borrowing = this.modelMapper.forRequest().map(borrowUpdateRequest, BookBorrowing.class);
        borrowing.setId(existing.getId());
        this.borrowRepo.save(borrowing);
        return getBorrowResponse(borrowing);
    }


    @Override
    public String delete(int id) {
        BookBorrowing borrowing = this.borrowRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        if (borrowing.getReturnDate().isAfter(LocalDate.now())){
            return "Kitabın iade süresi henüz dolmamış, silinemez.";
        }else{
            this.borrowRepo.delete(borrowing);
        }
        return "Kiralama silindi.";
    }

    @Override
    public BookBorrowingResponse save(BookBorrowingSaveRequest bookBorrowingSaveRequest) {

        Book book = this.bookService.get((int) bookBorrowingSaveRequest.getBookId());
        validateBookAvailability(book);

        BookBorrowing borrow = new BookBorrowing();
        borrow.setBorrowerName(bookBorrowingSaveRequest.getBorrowerName());
        borrow.setBorrowingDate(bookBorrowingSaveRequest.getBorrowingDate());
        borrow.setReturnDate(bookBorrowingSaveRequest.getReturnDate());
        borrow.setBook(book);

        this.borrowRepo.save(borrow);

        return getBorrowResponse(borrow);
    }

    public BookBorrowingResponse getBorrowResponse(BookBorrowing borrowing){
        BookBorrowingResponse borrow = new BookBorrowingResponse();

        borrow.setBorrowerName(borrowing.getBorrowerName());
        borrow.setBorrowingDate(borrowing.getBorrowingDate());
        borrow.setReturnDate(borrowing.getReturnDate());
        borrow.setBookName(borrowing.getBook().getName());
        borrow.setId(borrowing.getId());

        return borrow;
    }

    private void validateBookAvailability(Book book) {
        if (this.borrowRepo.existsById(book.getId())) {
            throw new BookBorrowingExistException("Kitap zaten kiralanmış.");
        }

        if (book.getStock() <= 0) {
            throw new BookUnavailableException("Kitabın stoğu tükenmiş.");
        }
    }

    @Override
    public Page<BookBorrowing> cursor(int page, int pageSize) {
        Pageable pageable = (Pageable) PageRequest.of(page,pageSize);
        return this.borrowRepo.findAll(pageable);
    }
}
