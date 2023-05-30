package springTwo.example.springProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class PrivateController {

    @GetMapping(value = "/private")
    public String getWardrobe(Model model) {
        return "private";
    }
}
