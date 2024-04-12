package b2bapp.b2bappbackend.controller;

import b2bapp.b2bappbackend.entity.SubcategoryEntity;
import b2bapp.b2bappbackend.exception.subcategory.SubcategoryAlreadyExistsException;
import b2bapp.b2bappbackend.exception.subcategory.SubcategoryNotFoundByIdException;
import b2bapp.b2bappbackend.service.SubcategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subcategories")
public class SubCategoryController {

    private final SubcategoryService subcategoryService;
    private final Logger logger = (Logger) LoggerFactory.getLogger(SubCategoryController.class);

    @Autowired
    public SubCategoryController(SubcategoryService subcategoryService) {
        this.subcategoryService = subcategoryService;
    }

    @PostMapping("/new")
    public ResponseEntity createNewSubcategory(@RequestBody SubcategoryEntity subcategory) {
        try{
            logger.info("Created new subcategory.");
            return ResponseEntity.ok().body(subcategoryService.createSubCategory(subcategory));
        } catch (SubcategoryAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity getAllSubcategories() {
        try {
            logger.info("Got all subcategories.");
            List<SubcategoryEntity> subcategoryEntityList = subcategoryService.getAllSubCategories();
            return ResponseEntity.ok().body(subcategoryEntityList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{subcategoryId}")
    public ResponseEntity getSubcategoryById(@PathVariable Long subcategoryId) {
        try {
            logger.info(String.format("Got subcategory with ID : %d", subcategoryId));
            return ResponseEntity.ok(subcategoryService.getOneSubCategory(subcategoryId));
        } catch (SubcategoryNotFoundByIdException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{subcategoryId}")
    public ResponseEntity deleteSubCategoryById(@PathVariable Long subcategoryId) {
        try {
            logger.info(String.format("Deleted subcategory with ID : %d", subcategoryId));
            subcategoryService.deleteSubCategory(subcategoryId);
            return ResponseEntity.ok().body(String.format("Вы успешно удалили подкатегорию с ID : %d", subcategoryId));
        } catch (SubcategoryNotFoundByIdException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
