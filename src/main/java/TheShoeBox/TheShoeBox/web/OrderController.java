package TheShoeBox.TheShoeBox.web;

import TheShoeBox.TheShoeBox.model.validator.anotations.PageTitle;
import TheShoeBox.TheShoeBox.model.view.OrderViewModel;
import TheShoeBox.TheShoeBox.model.view.UserProfileView;
import TheShoeBox.TheShoeBox.service.OrderService;
import TheShoeBox.TheShoeBox.service.UserEntityService;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class OrderController {
    private final OrderService orderService;
    private final UserEntityService userEntityService;


    public OrderController(OrderService orderService, UserEntityService userEntityService) {
        this.orderService = orderService;
        this.userEntityService = userEntityService;
    }

    @Transactional
    @GetMapping("/orders/all")
    @PageTitle("Order Page")
    public String getAllOrders(Model model, Principal principal) {
        List<OrderViewModel> allOrders = orderService.findAllOrders().stream().filter(o -> Boolean.FALSE.equals(o.getShipped())).collect(Collectors.toList());
        UserProfileView user = userEntityService.findUserByEmail(principal.getName());
        List<OrderViewModel> orders = orderService.findAllUserOrder(user.getId()).stream().filter(o -> Boolean.FALSE.equals(o.getShipped())).collect(Collectors.toList());

        model
                .addAttribute("orders", orders)
                .addAttribute("allOrders", allOrders);

        return "orders";
    }

    @Transactional
    @PatchMapping("/orders/{id}/all")
    public String addOrders(@PathVariable Long id,
                            @CurrentSecurityContext(expression = "authentication?.name")
                                    String buyer) {

        orderService.addOrder(id, buyer);

        return "redirect:/orders/all";
    }

    @PostMapping("/orders/{id}/shipped")
    public String deleteOrder(@PathVariable Long id) {
        orderService.shipping(id);
        return "redirect:/orders/all";
    }

    @PostMapping("/orders/{id}/cancel")
    public String cancelOrder(@PathVariable Long id) {
        orderService.cancelOrder(id);
        return "redirect:/orders/all";
    }




}
