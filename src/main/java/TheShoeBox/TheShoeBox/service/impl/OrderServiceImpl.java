package TheShoeBox.TheShoeBox.service.impl;

import TheShoeBox.TheShoeBox.model.entity.OrderEntity;
import TheShoeBox.TheShoeBox.model.entity.ShoeEntity;
import TheShoeBox.TheShoeBox.model.entity.UserEntity;
import TheShoeBox.TheShoeBox.model.service.OrderServiceModel;
import TheShoeBox.TheShoeBox.model.view.OrderViewModel;
import TheShoeBox.TheShoeBox.model.view.ShoeViewModel;
import TheShoeBox.TheShoeBox.model.view.UserViewModel;
import TheShoeBox.TheShoeBox.repository.OrderRepository;
import TheShoeBox.TheShoeBox.service.OrderService;
import TheShoeBox.TheShoeBox.service.ShoeService;
import TheShoeBox.TheShoeBox.service.UserEntityService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final UserEntityService userEntityService;
    private final ShoeService shoeService;


    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper, UserEntityService userEntityService, ShoeService shoeService) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.userEntityService = userEntityService;
        this.shoeService = shoeService;
    }


    @Override
    public List<OrderViewModel> findAllOrders() {
        return orderRepository.findAll().stream().map(o -> modelMapper.map(o, OrderViewModel.class)).collect(Collectors.toList());
    }

    @Override
    public OrderServiceModel addOrder(Long productId, String email) {
        UserViewModel buyer = modelMapper.map(userEntityService.findUserByEmail(email), UserViewModel.class);
        ShoeViewModel shoe = shoeService.findById(productId);
        UserEntity creator = shoe.getCreator();

        OrderEntity order = new OrderEntity()
                .setBrandAndModelProduct(shoe.getBrand() + " " + shoe.getModel())
                .setImageUrl(shoe.getImageUrl())
                .setPrice(shoe.getPrice())
                .setSellerId(creator.getId())
                .setSellerFullName(creator.getFirstName() + " " + creator.getLastName())
                .setBuyerId(buyer.getId())
                .setBuyerFullName(buyer.getFirstname() + " " + buyer.getLastname())
                .setProductId(productId);

        shoeService.deleteOffer(shoe.getId());
        OrderEntity save = orderRepository.save(order);

        return modelMapper.map(save, OrderServiceModel.class);
    }

    @Override
    public void deleteAll() {
        orderRepository.deleteAll();
    }

    @Override
    public List<OrderViewModel> getAllOrders() {
        return orderRepository.findAll().stream().map(u -> modelMapper.map(u, OrderViewModel.class)).collect(Collectors.toList());
    }
}
