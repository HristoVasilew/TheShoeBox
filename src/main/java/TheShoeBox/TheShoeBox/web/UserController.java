package TheShoeBox.TheShoeBox.web;

import TheShoeBox.TheShoeBox.model.bindng.UserRegisterBindingModel;
import TheShoeBox.TheShoeBox.model.service.UserRegisterServiceModel;
import TheShoeBox.TheShoeBox.service.UserEntityService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;

@Controller
public class UserController {

    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserEntityService userEntityService;

    public UserController(ModelMapper modelMapper, PasswordEncoder passwordEncoder, UserEntityService userEntityService) {
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userEntityService = userEntityService;
    }

    @GetMapping("/users/login")
    public String login() {


        return "login";
    }

    @PostMapping("/login-error")
    public String failedLogin(
            @ModelAttribute("email")
                    String email,
            RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("bad_credentials", true);
        redirectAttributes.addFlashAttribute("email", email);

        return "redirect:/login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @ModelAttribute("userRegisterBindingModel")
    public UserRegisterBindingModel userRegisterBindingModel(){
        return new UserRegisterBindingModel();
    }

    @PostMapping("/register")
    public String register(
            @Valid UserRegisterBindingModel userRegisterBindingModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors() || !userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())){
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);

            return "redirect:/register";
        }
        UserRegisterServiceModel user = modelMapper.map(userRegisterBindingModel, UserRegisterServiceModel.class);

        userEntityService.registerUser(user);

        return "redirect:/profile";
    }


    @GetMapping("/users/profile")
    public String profile() {
        return "profile";
    }

    @RequestMapping(value="/users/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/"; //You can redirect wherever you want, but generally it's a good practice to show login screen again.
    }

}
