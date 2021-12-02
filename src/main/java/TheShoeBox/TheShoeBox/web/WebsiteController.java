package TheShoeBox.TheShoeBox.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebsiteController {

    @GetMapping("/shop/about")
    public String about() {
        return "about";
    }

    @GetMapping("/shop/newsletter")
    public String newsletter() {
        return "newsletter";
    }
    @GetMapping("/shop/conditions")
    public String conditions() {
        return "conditions";
    }
}
