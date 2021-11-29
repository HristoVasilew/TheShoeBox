package TheShoeBox.TheShoeBox.web;

import TheShoeBox.TheShoeBox.model.bindng.ShoeBindingModel;
import TheShoeBox.TheShoeBox.model.service.ShoeServiceModel;
import TheShoeBox.TheShoeBox.service.ShoeService;
import TheShoeBox.TheShoeBox.service.impl.ShoeBoxUser;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("")
public class ShoeController {

    private final ModelMapper modelMapper;
    private final ShoeService shoeService;


    public ShoeController(ModelMapper modelMapper, ShoeService shoeService) {
        this.modelMapper = modelMapper;
        this.shoeService = shoeService;
    }

    @GetMapping("/collections/all")
    public String home() {
        return "home";
    }


    @GetMapping("/collections/create-shoe")
    public String createShoe(Model model) {
        if (!model.containsAttribute("shoeBindingModel")) {
            model.
                    addAttribute("shoeBindingModel", new ShoeBindingModel());
        }
        return "create-shoe";
    }

    @PostMapping("/collections/create-shoe")
    public String createShoeConfirm(@Valid ShoeBindingModel shoeBindingModel,
                                    BindingResult bindingResult, RedirectAttributes redirectAttributes,
                                    @CurrentSecurityContext(expression="authentication?.name")
                                                String username) {

         if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("shoeBindingModel", shoeBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.shoeBindingModel", bindingResult);
            return "redirect:/collections/create-shoe";
        }

        ShoeServiceModel saved = shoeService.addShoe(shoeBindingModel, username);

        return "redirect:/collections/" + saved + "/details";
    }

    @GetMapping("/collections/add-to-cart")
    public String addToCart() {
        return "add-to-cart";
    }

    @GetMapping("/collections/details")
    public String details() {
        return "details";
    }


}


