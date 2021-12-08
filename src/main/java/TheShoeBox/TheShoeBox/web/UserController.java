package TheShoeBox.TheShoeBox.web;

import TheShoeBox.TheShoeBox.model.bindng.UserRegisterBindingModel;
import TheShoeBox.TheShoeBox.model.service.UserServiceModel;
import TheShoeBox.TheShoeBox.model.validator.anotations.PageTitle;
import TheShoeBox.TheShoeBox.model.view.UserViewModel;
import TheShoeBox.TheShoeBox.repository.UserRepository;
import TheShoeBox.TheShoeBox.service.UserEntityService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class UserController {

    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserEntityService userEntityService;
    private final UserEntityService userService;

    public UserController(ModelMapper modelMapper, PasswordEncoder passwordEncoder, UserEntityService userEntityService, UserEntityService userService) {
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userEntityService = userEntityService;
        this.userService = userService;

    }

    @GetMapping("/users/login")
    @PageTitle("Login Page")
    public String login() {

        return "login";
    }

    @PostMapping("/users/login-error")
    @PageTitle("Login Page")
    public String failedLogin(@ModelAttribute String email,
            RedirectAttributes attributes) {

        attributes.addFlashAttribute("bad_credentials", true);
        attributes.addFlashAttribute("email", email);

        return "redirect:/users/login";
    }

    @GetMapping("users/register")
    @PageTitle("Register Page")
    public String register() {
        return "register";
    }

    @PostMapping("/users/register")
    @PageTitle("Register Page")
    public String register(
            @Valid UserRegisterBindingModel userRegisterBindingModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);

            return "redirect:/users/register";
        }

        if (!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())){
            redirectAttributes
                    .addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel)
                    .addFlashAttribute("passwordMismatch", true);

            return "redirect:/users/register";
        }

        UserServiceModel user = modelMapper.map(userRegisterBindingModel, UserServiceModel.class);

        userEntityService.registerUser(user);

        return "redirect:/users/login";
    }

    @RequestMapping(value = "/users/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/users/login";
    }

    @ModelAttribute("userRegisterBindingModel")
    public UserRegisterBindingModel userRegisterBindingModel() {
        return new UserRegisterBindingModel();
    }

    @ModelAttribute("passwordMismatch")
    public Boolean passwordMismatch() {
        return false;
    }

//    @RequestMapping(value="/updateUserInfo", method=RequestMethod.POST)
//    public ResponseEntity profileInfo(
//            @RequestBody HashMap<String, Object> mapper
//    ) throws Exception{
//
//        int id = (Integer) mapper.get("id");
//        String email = (String) mapper.get("email");
//        String username = (String) mapper.get("username");
//        String firstName = (String) mapper.get("firstname");
//        String lastName = (String) mapper.get("lastname");
//        String newPassword = (String) mapper.get("newPassword");
//        String currentPassword = (String) mapper.get("currentPassword");
//
//        UserServiceModel currentUser = userService.findById((long) id);
//
//        if(currentUser == null) {
//            throw new Exception ("User not found");
//        }
//
//        if(userService.findUserByEmail(email) != null) {
//            if(userService.findByEmail(email).getId() != currentUser.getId()) {
//                return new ResponseEntity("Email not found!", HttpStatus.BAD_REQUEST);
//            }
//        }
//
////        if(userService.findByUsername(username) != null) {
////            if(userService.findByUsername(username).getId() != currentUser.getId()) {
////                return new ResponseEntity("Username not found!", HttpStatus.BAD_REQUEST);
////            }
////        }
//
//        SecurityConfig securityConfig = new SecurityConfig();
//
//
//        BCryptPasswordEncoder passwordEncoder = SecurityUtility.passwordEncoder();
//        String dbPassword = currentUser.getPassword();
//
//        if(null != currentPassword)
//            if(passwordEncoder.matches(currentPassword, dbPassword)) {
//                if(newPassword != null && !newPassword.isEmpty() && !newPassword.equals("")) {
//                    currentUser.setPassword(passwordEncoder.encode(newPassword));
//                }
//                currentUser.setEmail(email);
//            } else {
//                return new ResponseEntity("Incorrect current password!", HttpStatus.BAD_REQUEST);
//            }
//
//
//        currentUser.setFirstname(firstName);
//        currentUser.setLastname(lastName);
//        currentUser.setUsername(username);
//
//
//        userService.save(currentUser);
//
//        return new ResponseEntity("Update Success", HttpStatus.OK);
//    }
}
