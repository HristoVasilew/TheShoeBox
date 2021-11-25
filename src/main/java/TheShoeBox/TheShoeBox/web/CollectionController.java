package TheShoeBox.TheShoeBox.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CollectionController {
    @GetMapping("/collections/all")
    public String home() {
        return "home";
    }
}

