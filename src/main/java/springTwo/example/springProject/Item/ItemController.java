package springTwo.example.springProject.Item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/student")
public class ItemController {

    private ItemService studentService;

    @Autowired
    ItemController(ItemService studentService){
        this.studentService = studentService;
    }

    @ModelAttribute
    void addAttributes(Model model){
        model.addAttribute("studentToAdd", new Item());
        model.addAttribute("studentIdToRemove", "");
        model.addAttribute("activePage", "student");
    }

    @GetMapping
    public String get(@RequestParam(value="name", required = false) String name, Model model){
        model.addAttribute("studentList", studentService.getItemsList());
        return "student";
    }

    @PostMapping(params = "name")
    public String post(@ModelAttribute("studentToAdd") Item studentToAdd, Model model){
        studentService.saveItem(studentToAdd);
        model.addAttribute("studentList", studentService.getItemsList());
        return "student";
    }
    @PostMapping(params = "studentId")
    public String post(@RequestParam(value="studentId", required = true) String id, Model model){
        studentService.deleteItem(id);
        model.addAttribute("studentList", studentService.getItemsList());
        return "student";
    }
}
