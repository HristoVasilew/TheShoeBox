package TheShoeBox.TheShoeBox.web;

import TheShoeBox.TheShoeBox.model.bindng.ShoeBindingModel;
import TheShoeBox.TheShoeBox.model.service.ShoeServiceModel;
import TheShoeBox.TheShoeBox.model.view.ShoeViewModel;
import TheShoeBox.TheShoeBox.service.ShoeService;
import TheShoeBox.TheShoeBox.service.impl.ShoeBoxUser;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class ShoeController {

    private final ModelMapper modelMapper;
    private final ShoeService shoeService;


    public ShoeController(ModelMapper modelMapper, ShoeService shoeService) {
        this.modelMapper = modelMapper;
        this.shoeService = shoeService;
    }

    @GetMapping("/collections/all")
    public String home(Model model) {
        List<ShoeViewModel> shoes = shoeService.findAllShoes();
        model.addAttribute("allShoes", shoes);

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

        return "redirect:/collections/all";
    }

    @GetMapping("/collections/add-to-cart")
    public String addToCart() {
        return "add-to-cart";
    }
    //DETAILS
    @GetMapping("/collections/{id}/details")
    public String showOffer(
            @PathVariable Long id, Model model,
            Principal principal) {
        model.addAttribute("shoe", this.shoeService.findById(id, principal.getName()));
        return "details";
    }

    // DELETE
    @PreAuthorize("isOwner(#id)")
    //@PreAuthorize("@offerServiceImpl.isOwner(#principal.name, #id)")
    @DeleteMapping("/collections/{id}")
    public String deleteOffer(@PathVariable Long id,
                              Principal principal) {

        // Most naive approach - check explicitly if the current user is an
        //owner and throw exception if this is not the case.
//        if (!offerService.isOwner(principal.getName(), id)) {
//            throw new RuntimeException();
//        }
        shoeService.deleteOffer(id);

        return "redirect:/collections/all";
    }

}


