package TheShoeBox.TheShoeBox.repository;

import TheShoeBox.TheShoeBox.model.entity.OrderEntity;
import TheShoeBox.TheShoeBox.model.view.OrderViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findAll();
}
