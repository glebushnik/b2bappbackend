package b2bapp.b2bappbackend.service;


import b2bapp.b2bappbackend.entity.CategoryEntity;
import b2bapp.b2bappbackend.entity.SubcategoryEntity;
import b2bapp.b2bappbackend.exception.subcategory.SubcategoryAlreadyExistsException;
import b2bapp.b2bappbackend.exception.subcategory.SubcategoryNotFoundByIdException;

import java.util.List;

public interface SubcategoryService {
    SubcategoryEntity createSubCategory(SubcategoryEntity subcategoryId) throws SubcategoryAlreadyExistsException;
    void deleteSubCategory(Long subcategoryId) throws SubcategoryNotFoundByIdException;
    List<SubcategoryEntity> getAllSubCategories();

    SubcategoryEntity getOneSubCategory(Long subcategoryId) throws SubcategoryNotFoundByIdException;
}
