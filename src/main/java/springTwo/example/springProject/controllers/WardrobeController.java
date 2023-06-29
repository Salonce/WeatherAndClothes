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
    public String getWardrobe(Model model, Authentication authentication){
        String userId = authenticationService.getUserId(authentication);
        model.addAttribute("itemList", itemService.getItemsList(userId));
        return "wardrobe";
    }

    @GetMapping(value = "/wardrobe/add")
    public String addItem(Model model, Authentication authentication){
        model.addAttribute("item", new Item());
        return "itemAdd";
    }

    @PostMapping(value = "/wardrobe")
    public String addItem(@ModelAttribute("item") Item item, Model model, Authentication authentication){
        String userId = authenticationService.getUserId(authentication);
        item.setUserId(userId);
        itemService.saveItem(item);
        model.addAttribute("itemList", itemService.getItemsList(userId));
        return "wardrobe";
    }

    @GetMapping(value = "/wardrobe/update/{id}")
    public String updateItemForm(@PathVariable String id, Model model, Authentication authentication){
        String userId = authenticationService.getUserId(authentication);
        if (itemService.itemExistsByBothIds(Long.parseLong(id), userId)){
            model.addAttribute("item", itemService.getItemById(Long.parseLong(id)));
            return "itemUpdate";
        }
        return "error";
    }

/*
    @PutMapping(value = "/wardrobe/{id}")
    public String updateItem(@PathVariable String id, Model model, Authentication authentication){
        String userId = authenticationService.getUserId(authentication);
        if (itemService.itemExistsByBothIds(Long.parseLong(id), userId)){
            Item item = itemService.getItemById(Long.parseLong(id));
            model.addAttribute("item", itemService.getItemById(Long.parseLong(id)));
            return "wardrobe";
        }
        return "error";
    }
*/

    @DeleteMapping(value = "/wardrobe/{id}")
    public String deleteItem(@PathVariable String id, Model model, Authentication authentication){
        String userId = authenticationService.getUserId(authentication);
        if (itemService.itemExistsByBothIds(Long.parseLong(id), userId)){
            itemService.deleteById(Long.parseLong(id));
            model.addAttribute("itemList", itemService.getItemsList(userId));
            return "wardrobe";
        }
        return "error";
    }


}