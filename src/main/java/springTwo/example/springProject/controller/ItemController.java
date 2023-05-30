package springTwo.example.springProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springTwo.example.springProject.entity.Item;
import springTwo.example.springProject.service.ItemService;

@Controller
public class ItemController {

    private ItemService itemService;

    @Autowired
    ItemController(ItemService itemService){
        this.itemService = itemService;
    }

    @ModelAttribute
    void addAttributes(Model model){
        model.addAttribute("itemToAdd", new Item());
        model.addAttribute("activePage", "wardrobe");
    }

    @GetMapping(value = "/wardrobe")
    public String getWardrobe(Model model){
        model.addAttribute("itemList", itemService.getItemsList());
        return "wardrobe";
    }


    @PostMapping(value = "/wardrobe")
    public String addItem(@ModelAttribute("itemToAdd") Item itemToAdd, Model model){
        itemService.saveItem(itemToAdd);
        model.addAttribute("itemList", itemService.getItemsList());
        return "wardrobe";
    }

    @DeleteMapping(value = "/wardrobe/{id}")
    public String deleteItem(@PathVariable String id, Model model){
        itemService.deleteItem(id);
        model.addAttribute("itemList", itemService.getItemsList());
        return "wardrobe";
    }
}
