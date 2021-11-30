package TheShoeBox.TheShoeBox.repository;

import TheShoeBox.TheShoeBox.model.entity.ShoeConditionEntity;
import TheShoeBox.TheShoeBox.model.entity.enums.ConditionEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoeConditionRepository extends JpaRepository<ShoeConditionEntity, Long> {

    ShoeConditionEntity findShoeConditionEntityByName(ConditionEnum name);

}
