package TheShoeBox.TheShoeBox.service;

import TheShoeBox.TheShoeBox.model.service.OrderServiceModel;
import TheShoeBox.TheShoeBox.model.view.OrderViewModel;

import java.util.List;

public interface OrderService {
    List<OrderViewModel> findAllOrders();

    OrderServiceModel addOrder(Long productId, String buyer);
}
