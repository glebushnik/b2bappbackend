package b2bapp.b2bappbackend.repository;

import b2bapp.b2bappbackend.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepo extends JpaRepository<CompanyEntity, Long> {
}
