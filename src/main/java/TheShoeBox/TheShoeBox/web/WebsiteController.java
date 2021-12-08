package TheShoeBox.TheShoeBox.web;

import TheShoeBox.TheShoeBox.model.validator.anotations.PageTitle;
import TheShoeBox.TheShoeBox.model.view.UserViewModel;
import TheShoeBox.TheShoeBox.service.UserEntityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class WebsiteController {
    private final UserEntityService userEntityService;

    public WebsiteController(UserEntityService userEntityService) {
        this.userEntityService = userEntityService;
    }

    @GetMapping("/shop/about")
    @PageTitle("About Us Page")
    public String about() {
        return "about";
    }

    @GetMapping("/shop/newsletter")
    @PageTitle("Newsletter Page")
    public String newsletter() {
        return "newsletter";
    }

    @GetMapping("/shop/conditions")
    @PageTitle("Conditions Page")
    public String conditions() {
        return "conditions";
    }


}
