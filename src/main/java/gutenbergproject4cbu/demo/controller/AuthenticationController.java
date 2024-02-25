package gutenbergproject4cbu.demo.controller;

import gutenbergproject4cbu.demo.DTO.UserDTO;
import gutenbergproject4cbu.demo.service.UserServiceImp;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AuthenticationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    private final UserServiceImp userService;

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
        UserDetails user = userService.loadUserByUsername(email.toString());
        if (user != null) {
            LOGGER.info(user.getUsername() + " " + user.getPassword());
            Authentication authentication = userService.authenticateUser(email, password);

            if (authentication != null && authentication.isAuthenticated()) {
                return "redirect:/dashboard";
            }
        }
        return "redirect:/login?successMessage=Login+Fail";
    }

    @GetMapping("/")
    public String registerFormGET(Model model) {
        model.addAttribute("UserDTO", new UserDTO());
        LOGGER.info("Register Page Accessed");
        return "Register";
    }

    @PostMapping("/")
    public String registerUser(@ModelAttribute("UserDTO") @Valid UserDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors() || userService.existUserwithEmail(userDTO.getEmail())) {
            LOGGER.info("Failured Login Page");
            return "Register";
        } else {
            userService.saveUser(userDTO);
            LOGGER.info("Succesful For Login Page");
            return "redirect:/login?successMessage=Registration+Successful!";
        }
    }
}
