package b2bapp.b2bappbackend.service;

import b2bapp.b2bappbackend.entity.CategoryEntity;
import b2bapp.b2bappbackend.exception.category.CategoryAlreadyExistsException;
import b2bapp.b2bappbackend.exception.category.CategoryNotFoundByIdException;

import java.util.List;

public interface CategoryService {
    CategoryEntity createCategory(CategoryEntity category) throws CategoryAlreadyExistsException;
    void deleteCategory(Long categoryId) throws CategoryNotFoundByIdException;

    List<CategoryEntity> getAllCategories();

    CategoryEntity getOneCategory(Long categoryId) throws CategoryNotFoundByIdException;
}
