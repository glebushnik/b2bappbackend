package b2bapp.b2bappbackend.repository;

import b2bapp.b2bappbackend.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepo extends JpaRepository<ReviewEntity, Long> {
}
