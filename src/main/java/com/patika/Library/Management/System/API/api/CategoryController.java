package com.patika.Library.Management.System.API.api;

import com.patika.Library.Management.System.API.business.abstracts.ICategoryService;
import com.patika.Library.Management.System.API.core.config.modelMapper.IModelMapperService;
import com.patika.Library.Management.System.API.core.result.ResultData;
import com.patika.Library.Management.System.API.core.utils.ResultHelper;
import com.patika.Library.Management.System.API.dto.request.category.CategorySaveRequest;
import com.patika.Library.Management.System.API.dto.request.category.CategoryUpdateRequest;
import com.patika.Library.Management.System.API.dto.response.CategoryResponse;
import com.patika.Library.Management.System.API.dto.response.CursorResponse;
import com.patika.Library.Management.System.API.entities.Category;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/categories")
public class CategoryController {
    private final ICategoryService categoryService;
    private final IModelMapperService modelMapper;

    public CategoryController(ICategoryService categoryService, IModelMapperService modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<CategoryResponse> save(@Valid @RequestBody CategorySaveRequest saveRequestRequest){
        Category category = this.modelMapper.forRequest().map(saveRequestRequest,Category.class);
        this.categoryService.save(category);
        return ResultHelper.created(this.modelMapper.forResponse().map(category, CategoryResponse.class));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CategoryResponse> get(@PathVariable("id")int id){
        Category category = this.categoryService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(category, CategoryResponse.class));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CategoryResponse> update(@Valid @RequestBody CategoryUpdateRequest categoryUpdateRequest){
        Category category = this.modelMapper.forRequest().map(categoryUpdateRequest,Category.class);
        this.categoryService.save(category);
        return ResultHelper.success(this.modelMapper.forResponse().map(category, CategoryResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String delete(@PathVariable("id")int id){
        return this.categoryService.delete(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<CategoryResponse>> cursor(
            @RequestParam(name = "page",required = false,defaultValue = "0")int page,
            @RequestParam(name = "pageSize",required = false,defaultValue = "3")int pageSize
    ){
        Page<Category> categoryPage = this.categoryService.cursor(page,pageSize);
        Page<CategoryResponse> categoryResponcePage = categoryPage
                .map(category -> this.modelMapper.forResponse().map(category, CategoryResponse.class));
        return ResultHelper.cursor(categoryResponcePage);
    }
}
