package TheShoeBox.TheShoeBox.web;

import TheShoeBox.TheShoeBox.model.bindng.OrderBindingModel;
import TheShoeBox.TheShoeBox.model.bindng.ShoeBindingModel;
import TheShoeBox.TheShoeBox.model.service.OrderServiceModel;
import TheShoeBox.TheShoeBox.model.service.ShoeServiceModel;
import TheShoeBox.TheShoeBox.model.validator.anotations.PageTitle;
import TheShoeBox.TheShoeBox.model.view.OrderViewModel;
import TheShoeBox.TheShoeBox.service.OrderService;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class OrderController {
    private final OrderService orderService;


    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders/all")
    @PageTitle("Order Page")
    public String getAllOrders(Model model){
        List<OrderViewModel> orders = orderService.findAllOrders();

        model.addAttribute("orders", orders);

        return "orders";
    }

    @PatchMapping("/orders/{id}/all")
    public String addOrders(@PathVariable Long id,
                            @CurrentSecurityContext(expression = "authentication?.name")
                                        String buyer) {

        orderService.addOrder(id, buyer);

        return "redirect:/orders/all";
    }


}
