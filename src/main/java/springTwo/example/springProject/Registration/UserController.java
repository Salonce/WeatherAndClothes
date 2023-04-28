package springTwo.example.springProject.Registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    UserService userService;

    @Autowired
    UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/user/register")
    String getRegister(Model model){
        model.addAttribute("user", new User());

        return "register";
    }

    @PostMapping("/user/register")
    String postRegister(@RequestParam("User") User user){
        userService.addUser(user);
        return "register";
    }

}
