package TheShoeBox.TheShoeBox.web;

import TheShoeBox.TheShoeBox.model.validator.anotations.PageTitle;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    @PageTitle("Home Page")
    public String index() {
        return "index";
    }

}
