package TheShoeBox.TheShoeBox.service.impl;

import TheShoeBox.TheShoeBox.exeptions.ObjectNotFoundException;
import TheShoeBox.TheShoeBox.model.entity.OrderEntity;
import TheShoeBox.TheShoeBox.model.entity.ShoeEntity;
import TheShoeBox.TheShoeBox.model.entity.UserEntity;
import TheShoeBox.TheShoeBox.model.service.OrderServiceModel;
import TheShoeBox.TheShoeBox.model.view.OrderViewModel;
import TheShoeBox.TheShoeBox.model.view.UserViewModel;
import TheShoeBox.TheShoeBox.repository.OrderRepository;
import TheShoeBox.TheShoeBox.service.OrderService;
import TheShoeBox.TheShoeBox.service.ShoeService;
import TheShoeBox.TheShoeBox.service.UserEntityService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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
        ShoeEntity shoe = shoeService.findShoeById(productId);
        UserEntity creator = shoe.getCreator();

        OrderEntity order = new OrderEntity()
                .setBrandAndModelProduct(shoe.getBrand() + " " + shoe.getModel())
                .setImageUrl(shoe.getImageUrl())
                .setPrice(shoe.getPrice())
                .setSellerId(creator.getId())
                .setSellerFullName(creator.getFirstName() + " " + creator.getLastName())
                .setBuyerId(buyer.getId())
                .setBuyerFullName(buyer.getFirstname() + " " + buyer.getLastname())
                .setProductId(productId)
                .setShipped(false);

        shoe.setOrdered(true);
        shoeService.save(shoe);
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

    @Override
    public List<OrderViewModel> findAllUserOrder(Long id) {
        return orderRepository
                .findAll()
                .stream()
                .filter(u -> Objects.equals(u.getBuyerId(), id) || Objects.equals(u.getSellerId(), id))
                .map(u -> modelMapper.map(u, OrderViewModel.class)).collect(Collectors.toList());
    }

    @Override
    public OrderEntity findById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Order with id " + id + " not found!"));
    }

    @Override
    public void shipping(Long id) {
        OrderEntity order = orderRepository
                .findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Order with id " + id + " not found!"))
                .setShipped(true);
        orderRepository.save(order);
        shoeService.deleteOffer(order.getProductId());
    }

    @Override
    public void cancelOrder(Long id) {

        Long productId = orderRepository.findById(id).get().getProductId();

        shoeService.save(shoeService.findShoeById(productId).setOrdered(false));

        orderRepository.deleteById(id);
    }
}
