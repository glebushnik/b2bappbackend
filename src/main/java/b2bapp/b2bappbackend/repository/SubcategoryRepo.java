package b2bapp.b2bappbackend.repository;

import b2bapp.b2bappbackend.entity.SubcategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubcategoryRepo extends JpaRepository<SubcategoryEntity, Long> {
    public SubcategoryEntity findSubcategoryEntitiesByName(String subcategoryName);
}
