package com.patika.Library.Management.System.API.api;

import com.patika.Library.Management.System.API.business.abstracts.IAuthorService;
import com.patika.Library.Management.System.API.core.config.modelMapper.IModelMapperService;
import com.patika.Library.Management.System.API.core.result.Result;
import com.patika.Library.Management.System.API.core.result.ResultData;
import com.patika.Library.Management.System.API.core.utils.Msg;
import com.patika.Library.Management.System.API.core.utils.ResultHelper;
import com.patika.Library.Management.System.API.dto.request.author.AuthorSaveRequest;
import com.patika.Library.Management.System.API.dto.request.author.AuthorUpdateRequest;
import com.patika.Library.Management.System.API.dto.response.AuthorReponse;
import com.patika.Library.Management.System.API.dto.response.CursorResponse;
import com.patika.Library.Management.System.API.entities.Author;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/authors")
public class AuthorController {

    private final IAuthorService authorService;
    private final IModelMapperService modelMapper;

    public AuthorController(IAuthorService authorService, IModelMapperService modelMapper) {
        this.authorService = authorService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AuthorReponse> save(@Valid @RequestBody AuthorSaveRequest authorSaveRequest) {
        Author author = this.modelMapper.forRequest().map(authorSaveRequest, Author.class);
        this.authorService.save(author);
        return ResultHelper.created(this.modelMapper.forResponse().map(author, AuthorReponse.class));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AuthorReponse> get(@PathVariable("id") int id) {
        Author author = this.authorService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(author, AuthorReponse.class));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AuthorReponse> update(@Valid @RequestBody AuthorUpdateRequest authorUpdateRequest) {
        Author author = this.modelMapper.forRequest().map(authorUpdateRequest, Author.class);
        this.authorService.update(author);
        return ResultHelper.success(this.modelMapper.forResponse().map(author, AuthorReponse.class));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AuthorReponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "2") int pageSize
    ) {
        Page<Author> authorPage = this.authorService.cursor(page, pageSize);
        Page<AuthorReponse> authorResponsePage = authorPage
                .map(authors -> this.modelMapper.forResponse().map(authors, AuthorReponse.class));

        return ResultHelper.cursor(authorResponsePage);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id) {
        this.authorService.delete(id);
        return new Result(true, Msg.SUCCESS, "200");
    }
}
