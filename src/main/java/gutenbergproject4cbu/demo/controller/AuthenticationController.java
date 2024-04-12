package gutenbergproject4cbu.demo.controller;

import gutenbergproject4cbu.demo.DTO.UserDTO;
import gutenbergproject4cbu.demo.security.CustomServiceDetails;
import gutenbergproject4cbu.demo.service.UserService;
import gutenbergproject4cbu.demo.service.UserServiceImp;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/public")

public class AuthenticationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);
    @Autowired
    private final UserService userService;
    @Autowired
    private final CustomServiceDetails customServiceDetails;

    public AuthenticationController(UserServiceImp userService, CustomServiceDetails customServiceDetails) {
        this.userService = userService;
        this.customServiceDetails = customServiceDetails;

    }

    @GetMapping("/login")
    public String loginGET(Model model, @RequestParam(name = "successMessage", required = false) String successMessage) {
        if (successMessage != null) {
            model.addAttribute("SuccessAttemption", successMessage);
        }

        return "Login";
    }

    @PostMapping("/login")
    public String loginPOST(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password) {
        LOGGER.info(email + " " + password);
        UserDetails user = customServiceDetails.loadUserByUsername(email);
        if (user != null) {
            LOGGER.info(user.getUsername() + " " + user.getPassword());
            Authentication authentication = customServiceDetails.authenticateUser(email, password);

            if (authentication != null && authentication.isAuthenticated()) {
                LOGGER.info(user.getUsername() + " has " + authentication.getAuthorities());
                return "redirect:/users/dashboard";
            }
            return "redirect:/login?error";
        }
        return "redirect:/login";
    }

    @GetMapping("/register")
    public String registerFormGET(Model model
    ) {
        model.addAttribute("UserDTO", new UserDTO());
        LOGGER.info("Register Page Accessed");
        return "Register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("UserDTO")
            @Valid UserDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors() || userService.existUserwithEmail(userDTO.getEmail())) {
            LOGGER.info("Failured Login Page");
            return "Register";
        } else {
            userService.saveUser(userDTO);
            LOGGER.info("Succesful For Login Page");
            return "redirect:/public/login?successMessage=Registration+Successful!";
        }
    }

}
