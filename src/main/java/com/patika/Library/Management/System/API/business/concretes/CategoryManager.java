package com.patika.Library.Management.System.API.business.concretes;

import com.patika.Library.Management.System.API.business.abstracts.ICategoryService;
import com.patika.Library.Management.System.API.core.config.exception.NotFoundException;
import com.patika.Library.Management.System.API.core.utils.Msg;
import com.patika.Library.Management.System.API.dao.CategoryRepository;
import com.patika.Library.Management.System.API.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
public class CategoryManager implements ICategoryService {

    private final CategoryRepository categoryRepo;

    public CategoryManager(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Category save(Category category) {
        return this.categoryRepo.save(category);
    }

    @Override
    public Category get(int id) {
        return this.categoryRepo.findById(id).orElseThrow(()->new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Category update(Category category) {
        this.get(category.getId());
        return this.categoryRepo.save(category);
    }

    @Override
    public String delete(int id) {
        Category category = this.get(id);
        if (!category.getBooks().isEmpty()) {
            return "Bu kategoriye ait kitaplar var, silinemez.";
        }else{
            this.categoryRepo.delete(this.get(id));
            return "Kategori Silindi";
        }
    }

    @Override
    public Page<Category> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return this.categoryRepo.findAll(pageable);
    }

    @Override
    public List<Category> getAllByIds(List<Integer> id) {
        List<Category> categories = this.categoryRepo.findAllById(id);

        if (categories.size() != id.size()) {
            throw new NotFoundException("Bazı kategori ID'leri geçersiz: " + id);
        }

        return categories;
    }



}
