package springTwo.example.springProject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springTwo.example.springProject.entities.Item;
import springTwo.example.springProject.services.AuthenticationService;
import springTwo.example.springProject.services.ItemService;

@RequiredArgsConstructor
@Controller
public class WardrobeController {

    private final ItemService itemService;
    private final AuthenticationService authenticationService;

    @ModelAttribute
    void addAttributes(Model model, Authentication authentication){
        model.addAttribute("userId", authenticationService.getUserId(authentication));
        model.addAttribute("activePage", "wardrobe");
    }

    @GetMapping(value = "/wardrobe")
    public String getWardrobe(@RequestParam(value = "wardrobeSort", required = false) String sortMethod, Model model, Authentication authentication){
        model.addAttribute("itemList", itemService.getItemsList(authentication, sortMethod));
        return "wardrobe";
    }

    @GetMapping(value = "/wardrobe/add")
    public String addItemForm(Model model, Authentication authentication){
        model.addAttribute("item", new Item());
        return "itemAdd";
    }

    @PostMapping(value = "/wardrobe")
    public String addItem(@ModelAttribute("item") Item item, Model model, Authentication authentication){
        itemService.newItem(authentication, item);
        model.addAttribute("itemList", itemService.getItemsListByName(authentication));
        return "wardrobe";
    }

    @GetMapping(value = "/wardrobe/update/{id}")
    public String updateItemForm(@PathVariable("id") String itemId, Model model, Authentication authentication){
        if (itemService.userHasItem(authentication, itemId)){
            model.addAttribute("item", itemService.getItemById(itemId));
            return "itemUpdate";
        }
        return "error";
    }

    @PatchMapping(value = "/wardrobe/{id}")
    public String updateItem(@PathVariable("id") String itemId, @ModelAttribute("item") Item item, Model model, Authentication authentication){
        if (itemService.userHasItem(authentication, itemId)){
            itemService.updateItem(itemId, item);
            model.addAttribute("itemList", itemService.getItemsListByName(authentication));
            return "wardrobe";
        }
        return "error";
    }

    @DeleteMapping(value = "/wardrobe/{id}")
    public String deleteItem(@PathVariable("id") String itemId, Model model, Authentication authentication){
        if (itemService.deleteItem(authentication, itemId)){
            model.addAttribute("itemList", itemService.getItemsListByName(authentication));
            return "wardrobe";
        }
        return "error";
    }
}