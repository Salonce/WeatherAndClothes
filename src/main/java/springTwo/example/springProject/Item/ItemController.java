package springTwo.example.springProject.Item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/item")
public class ItemController {

    private ItemService itemService;

    @Autowired
    ItemController(ItemService itemService){
        this.itemService = itemService;
    }

    @ModelAttribute
    void addAttributes(Model model){
        model.addAttribute("itemToAdd", new Item());
        model.addAttribute("itemIdToRemove", "");
        model.addAttribute("activePage", "item");
    }

    @GetMapping
    public String get(@RequestParam(value="name", required = false) String name, Model model){
        model.addAttribute("itemList", itemService.getItemsList());
        return "item";
    }

    @PostMapping(params = "name")
    public String post(@ModelAttribute("itemToAdd") Item itemToAdd, Model model){
        itemService.saveItem(itemToAdd);
        model.addAttribute("itemList", itemService.getItemsList());
        return "item";
    }
    @PostMapping(params = "itemId")
    public String post(@RequestParam(value="itemId", required = true) String id, Model model){
        itemService.deleteItem(id);
        model.addAttribute("itemList", itemService.getItemsList());
        return "item";
    }
}
