package TheShoeBox.TheShoeBox.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CollectionController {



    @GetMapping("/collections/all")
    public String home() {
        return "home";
    }

    @GetMapping("/collections/add-to-cart")
    public String addToCart() {
        return "add-to-cart";
    }

    @GetMapping("/collections/details")
    public String details() {
        return "details";
    }

    @GetMapping("/collections/create-shoe")
    public String createShoe() {
        return "create-shoe";
    }

}


