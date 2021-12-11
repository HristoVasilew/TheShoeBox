package TheShoeBox.TheShoeBox.service;

import TheShoeBox.TheShoeBox.model.entity.OrderEntity;
import TheShoeBox.TheShoeBox.model.service.OrderServiceModel;
import TheShoeBox.TheShoeBox.model.view.OrderViewModel;

import java.util.List;

public interface OrderService {
    List<OrderViewModel> findAllOrders();

    OrderServiceModel addOrder(Long productId, String buyer);

    void deleteAll();

    List<OrderViewModel> getAllOrders();

    List<OrderViewModel> findAllUserOrder(Long id);

    OrderEntity findById(Long id);

    void shipping(Long id);

    void cancelOrder(Long id);
}
