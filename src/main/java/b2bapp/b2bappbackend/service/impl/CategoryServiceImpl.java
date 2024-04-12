package b2bapp.b2bappbackend.service.impl;

import b2bapp.b2bappbackend.entity.CategoryEntity;
import b2bapp.b2bappbackend.exception.category.CategoryAlreadyExistsException;
import b2bapp.b2bappbackend.exception.category.CategoryNotFoundByIdException;
import b2bapp.b2bappbackend.repository.CategoryRepo;
import b2bapp.b2bappbackend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo categoryRepo;

    @Autowired
    public CategoryServiceImpl(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public CategoryEntity createCategory(CategoryEntity category) throws CategoryAlreadyExistsException{
        if(categoryRepo.findCategoryEntitiesByName(category.getName()) != null) {
            throw new CategoryAlreadyExistsException("Эта категория уже существует");
        }
        return categoryRepo.save(category);
    }

    @Override
    public void deleteCategory(Long categoryId) throws CategoryNotFoundByIdException{
        CategoryEntity category = categoryRepo.findById(categoryId).orElse(null);

        if(categoryId == null) {
            throw new CategoryNotFoundByIdException("Такой категории не существует");
        }

        categoryRepo.deleteById(categoryId);
    }

    @Override
    public List<CategoryEntity> getAllCategories() {
        return categoryRepo.findAll();
    }

    @Override
    public CategoryEntity getOneCategory(Long categoryId) throws CategoryNotFoundByIdException {
        CategoryEntity category = categoryRepo.findById(categoryId).orElse(null);

        if(category == null) {
            throw new CategoryNotFoundByIdException("Категории с таким ID не существует");
        }

        return category;
    }
}
