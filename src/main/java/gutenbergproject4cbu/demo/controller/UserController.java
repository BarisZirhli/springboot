package gutenbergproject4cbu.demo.controller;

<<<<<<< HEAD
import org.springframework.stereotype.Controller;

@Controller

public class UserController {


=======
import gutenbergproject4cbu.demo.DTO.UserDTO;
import gutenbergproject4cbu.demo.configuration.Security;
import gutenbergproject4cbu.demo.service.UserServiceImp;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping

public class UserController {

    private final Security userSec;
    private final UserServiceImp userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    public UserController(Security userSec, UserServiceImp userService) {
        this.userSec = userSec;
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

        if (email.isEmpty() || password.isEmpty()) {
            LOGGER.info("Login post was here");
            return "redirect:/login";
        }
        Authentication authentication = userSec.authenticateUser(email, password);
        LOGGER.info("Login post was here2");

        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/dashboard";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/")
    public String registerFormGET(Model model) {
        model.addAttribute("UserDTO", new UserDTO());
        return "Register";
    }

    @PostMapping("/")
    public String registerUser(@ModelAttribute("UserDTO") @Valid UserDTO userDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors() || userService.existUserwithEmail(userDTO.getEmail())) {
            model.addAttribute("FailAttemption", "Registration Not Successful! Password or Email Invalid");
            return "redirect:/";
        } else {
            model.addAttribute("SuccessAttemption", "Registration Successful!");
            userService.saveUser(userDTO);
        }

        return "redirect:/login?successMessage=Registration+Successful!";
    }
>>>>>>> af5da07eb8f7d5e6b2c6769452f406ef468d3489
}
