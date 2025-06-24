package com.patika.Library.Management.System.API.api;

import com.patika.Library.Management.System.API.business.abstracts.IBookBorrowingService;
import com.patika.Library.Management.System.API.core.config.modelMapper.IModelMapperService;
import com.patika.Library.Management.System.API.core.result.ResultData;
import com.patika.Library.Management.System.API.core.utils.ResultHelper;
import com.patika.Library.Management.System.API.dto.request.bookborrowing.BookBorrowingSaveRequest;
import com.patika.Library.Management.System.API.dto.request.bookborrowing.BookBorrowingUpdateRequest;
import com.patika.Library.Management.System.API.dto.response.BookBorrowingResponse;
import com.patika.Library.Management.System.API.dto.response.CursorResponse;
import com.patika.Library.Management.System.API.entities.BookBorrowing;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/borrows")
public class BookBorrowingController {

    private final IBookBorrowingService borrowService;
    private final IModelMapperService modelMapper;

    public BookBorrowingController(IBookBorrowingService borrowService, IModelMapperService modelMapper) {
        this.borrowService = borrowService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<BookBorrowingResponse> save(@Valid @RequestBody BookBorrowingSaveRequest borrowSaveRequest){
        BookBorrowingResponse borrowResponse = this.borrowService.save(borrowSaveRequest);
        return ResultHelper.created(borrowResponse);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<BookBorrowingResponse> get(@PathVariable("id") int id){
        return ResultHelper.success(this.borrowService.get(id));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<BookBorrowingResponse> update(@Valid @RequestBody BookBorrowingUpdateRequest bookBorrowingUpdateRequest){
        return ResultHelper.success(this.borrowService.update(bookBorrowingUpdateRequest));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String delete(@PathVariable("id") int id){
        return this.borrowService.delete(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<BookBorrowingResponse>> cursor(
            @RequestParam(name = "page", required = false,defaultValue = "0")int page,
            @RequestParam(name = "pageSize",required = false, defaultValue = "10")int pageSize
    ){
        Page<BookBorrowing> bookBorrowings = this.borrowService.cursor(page,pageSize);
        Page<BookBorrowingResponse> borrowResponsePage = bookBorrowings
                .map(borrowing -> this.modelMapper.forResponse().map(borrowing, BookBorrowingResponse.class));

        return ResultHelper.cursor(borrowResponsePage);
    }
}
