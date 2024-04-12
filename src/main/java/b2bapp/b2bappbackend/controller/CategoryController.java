package b2bapp.b2bappbackend.controller;

import b2bapp.b2bappbackend.entity.CategoryEntity;
import b2bapp.b2bappbackend.exception.category.CategoryAlreadyExistsException;
import b2bapp.b2bappbackend.exception.category.CategoryNotFoundByIdException;
import b2bapp.b2bappbackend.service.CategoryService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final Logger logger = (Logger) LoggerFactory.getLogger(CategoryController.class);
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/new")
    public ResponseEntity createNewCategory(@RequestBody CategoryEntity category){
        try{
            logger.info("Created a category.");
            return ResponseEntity.ok().body(categoryService.createCategory(category));
        } catch (CategoryAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity getAllCategories(){
        try{
            logger.info("Got all categories.");
            List<CategoryEntity> categories = categoryService.getAllCategories();
            return ResponseEntity.ok().body(categories);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity getOneCategory(@PathVariable Long categoryId) {
        try{
            logger.info(String.format("Got category with ID : %d", categoryId));
            return ResponseEntity.ok().body(categoryService.getOneCategory(categoryId));
        } catch (CategoryNotFoundByIdException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity deleteCategoryById(@PathVariable Long categoryId) {
        try {
            logger.info(String.format("Deleted category with ID : %d",categoryId));
            categoryService.deleteCategory(categoryId);
            return ResponseEntity.ok().body(String.format("Вы успешно удалили категорию с ID : %d", categoryId));
        }catch (CategoryNotFoundByIdException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
