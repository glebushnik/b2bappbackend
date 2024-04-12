package b2bapp.b2bappbackend.repository;

import b2bapp.b2bappbackend.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<CategoryEntity, Long> {
    public CategoryRepo findCategoryEntitiesByName(String categoryName);
}
