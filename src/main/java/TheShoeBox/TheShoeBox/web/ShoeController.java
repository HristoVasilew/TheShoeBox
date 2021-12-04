package TheShoeBox.TheShoeBox.web;

import TheShoeBox.TheShoeBox.model.bindng.ShoeBindingModel;
import TheShoeBox.TheShoeBox.model.bindng.ShoeUpdateBindingModel;
import TheShoeBox.TheShoeBox.model.entity.enums.ConditionEnum;
import TheShoeBox.TheShoeBox.model.entity.enums.ShoeCategoryEnum;
import TheShoeBox.TheShoeBox.model.service.ShoeServiceModel;
import TheShoeBox.TheShoeBox.model.service.ShoeUpdateServiceModel;
import TheShoeBox.TheShoeBox.model.view.ShoeViewModel;
import TheShoeBox.TheShoeBox.service.ShoeService;
import TheShoeBox.TheShoeBox.service.UserEntityService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private final UserEntityService userEntityService;


    public ShoeController(ModelMapper modelMapper, ShoeService shoeService, UserEntityService userEntityService) {
        this.modelMapper = modelMapper;
        this.shoeService = shoeService;
        this.userEntityService = userEntityService;
    }

    @GetMapping("/collections/all")
    public String home(Model model, @CurrentSecurityContext(expression = "authentication?.name")
            String username) {
        List<ShoeViewModel> shoes = shoeService.findAllShoes();
        model.addAttribute("allShoes", shoes);

        String owner = userEntityService.findUserByEmail(username).get().getFirstName() + userEntityService.findUserByEmail(username).get().getLastName();
        model.addAttribute("owner", owner);

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
                                    @CurrentSecurityContext(expression = "authentication?.name")
                                            String username) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("shoeBindingModel", shoeBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.shoeBindingModel", bindingResult);
            return "redirect:/collections/create-shoe";
        }

        ShoeServiceModel saved = shoeService.addShoe(shoeBindingModel, username);

        return "redirect:/collections/" + saved.getId() + "/details";
    }

    //ADD TO CART AND BUY
    @GetMapping("/collections/{id}/add-to-cart")
    public String addToCart(@PathVariable Long id, Model model,
                            Principal principal) {
        model.addAttribute("shoe", this.shoeService.findByIdAndName(id, principal.getName()));
        return "add-to-cart";
    }

    //DETAILS
    @GetMapping("/collections/{id}/details")
    public String showOffer(
            @PathVariable Long id, Model model,
            Principal principal) {
        model.addAttribute("shoe", this.shoeService.findByIdAndName(id, principal.getName()));
        return "details";
    }

    // DELETE
    @PreAuthorize("@shoeServiceImpl.isOwner(#principal.name,#id)")
    //@PreAuthorize("@shoeServiceImpl.isOwner(#principal.name, #id)")
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


    // UPDATE

    @GetMapping("/collections/{id}/edit")
    public String editOffer(@PathVariable Long id, Model model,
                            @CurrentSecurityContext(expression = "authentication?.name")
                                    String username) {

        ShoeViewModel shoeViewModel = shoeService.findByIdAndName(id, username);
        ShoeUpdateBindingModel offerModel = modelMapper.map(
                shoeViewModel,
                ShoeUpdateBindingModel.class
        );

        model.addAttribute("categories", ShoeCategoryEnum.values());
        model.addAttribute("condition", ConditionEnum.values());
        model.addAttribute("offerModel", offerModel);
        return "edit";
    }

    @GetMapping("/collections/{id}/edit/errors")
    public String editOfferErrors(@PathVariable Long id, Model model) {
        model.addAttribute("categories", ShoeCategoryEnum.values());
        model.addAttribute("condition", ConditionEnum.values());
        return "edit";
    }

    @PatchMapping("/collections/{id}/edit")
    public String editOffer(
            @PathVariable Long id,
            @Valid ShoeUpdateBindingModel offerModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("offerModel", offerModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offerModel", bindingResult);

            return "redirect:/collections/" + id + "/edit/errors";
        }

        ShoeUpdateServiceModel serviceModel = modelMapper.map(offerModel,
                ShoeUpdateServiceModel.class);
        serviceModel.setId(id);

        shoeService.updateOffer(serviceModel);

        return "redirect:/collections/" + id + "/details";
    }

}


