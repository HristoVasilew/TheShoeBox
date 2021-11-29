package TheShoeBox.TheShoeBox.repository;

import TheShoeBox.TheShoeBox.model.entity.ShoeCategoryEntity;
import TheShoeBox.TheShoeBox.model.entity.enums.ShoeCategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<ShoeCategoryEntity, Long> {
    ShoeCategoryEntity getCategoryEntityByName(ShoeCategoryEnum name);


    ShoeCategoryEntity findShoeCategoryEntityByName(ShoeCategoryEnum name);
}
