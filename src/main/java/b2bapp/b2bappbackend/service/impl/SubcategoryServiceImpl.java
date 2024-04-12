package b2bapp.b2bappbackend.service.impl;

import b2bapp.b2bappbackend.entity.SubcategoryEntity;
import b2bapp.b2bappbackend.exception.subcategory.SubcategoryAlreadyExistsException;
import b2bapp.b2bappbackend.exception.subcategory.SubcategoryNotFoundByIdException;
import b2bapp.b2bappbackend.repository.SubcategoryRepo;
import b2bapp.b2bappbackend.service.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubcategoryServiceImpl implements SubcategoryService {
    private final SubcategoryRepo subcategoryRepo;

    @Autowired
    public SubcategoryServiceImpl(SubcategoryRepo subcategoryRepo) {
        this.subcategoryRepo = subcategoryRepo;
    }

    @Override
    public SubcategoryEntity createSubCategory(SubcategoryEntity subcategory) throws SubcategoryAlreadyExistsException {
        if(subcategoryRepo.findSubcategoryEntitiesByName(subcategory.getName())!=null) {
            throw new SubcategoryAlreadyExistsException("Подкатегория уже существует.");
        }

        return subcategoryRepo.save(subcategory);
    }

    @Override
    public void deleteSubCategory(Long subcategoryId) throws SubcategoryNotFoundByIdException {
        SubcategoryEntity subcategory = subcategoryRepo.findById(subcategoryId).orElse(null);
        if(subcategory == null) {
            throw new SubcategoryNotFoundByIdException("Подкатегории с таким ID не существует.");
        }

        subcategoryRepo.deleteById(subcategoryId);
    }

    @Override
    public List<SubcategoryEntity> getAllSubCategories() {
        return subcategoryRepo.findAll();
    }

    @Override
    public SubcategoryEntity getOneSubCategory(Long subcategoryId) throws SubcategoryNotFoundByIdException {
        SubcategoryEntity subcategory = subcategoryRepo.findById(subcategoryId).orElse(null);
        if(subcategory == null) {
            throw new SubcategoryNotFoundByIdException("Подкатегории с таким ID не существует.");
        }

        return subcategory;
    }
}
