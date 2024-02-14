package gutenbergproject4cbu.demo.controller;

import gutenbergproject4cbu.demo.DTO.UserDTO;
import gutenbergproject4cbu.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;

    }

    @GetMapping("/login")
    public String loginGET(Model model, @RequestParam(name = "successMessage", required = false) String successMessage) {
        if (successMessage != null) {
            model.addAttribute("SuccessAttemption", successMessage);
        }
        model.addAttribute("UserDTO", new UserDTO());
        return "Login";
    }

    @PostMapping("/login")
    public String loginPOST(@RequestParam String email, @RequestParam String password) {
        Authentication authentication = userService.authenticateUser(email, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/dashboard";
        } else {
            return "redirect:/login?error=Invalid+email+or+password";
        }
    }

    @GetMapping("/")
    public String registerFormGET(Model model) {
        model.addAttribute("UserDTO", new UserDTO());
        return "Register";
    }

    @PostMapping("/")
    public String registerUser(@ModelAttribute("UserDTO") @Valid UserDTO userDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors() || userService.findUserwithEmail(userDTO.getEmail())) {
            model.addAttribute("FailAttemption", "Registration Not Successful! Password or Email Invalid");
            return "Register";
        } else {
            model.addAttribute("SuccessAttemption", "Registration Successful!");
            userService.saveUser(userDTO);
        }

        return "redirect:/login?successMessage=Registration+Successful!";
    }
}
