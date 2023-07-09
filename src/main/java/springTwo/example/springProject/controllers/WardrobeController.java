package springTwo.example.springProject.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springTwo.example.springProject.entities.Item;
import springTwo.example.springProject.services.AuthenticationService;
import springTwo.example.springProject.services.ItemService;

import java.math.BigDecimal;

@Controller
@RequiredArgsConstructor
public class WardrobeController {

    private final ItemService itemService;
    private final AuthenticationService authenticationService;

    @ModelAttribute
    void addAttributes(Model model, Authentication authentication){
        model.addAttribute("userName", authenticationService.getUserName(authentication));
        model.addAttribute("userId", authenticationService.getUserId(authentication));
        model.addAttribute("activePage", "wardrobe");
    }

    private void addWardrobeTemplateModels(Model model, Authentication authentication){
        BigDecimal bigDecimal = itemService.getItemsListByName(authentication).stream().map(i -> i.getWeight()).reduce(BigDecimal.ZERO, BigDecimal::add);
        model.addAttribute("totalWeight", bigDecimal);
        model.addAttribute("itemList", itemService.getItemsListByName(authentication));
    }

    //add caching for sort type?

    @GetMapping(value = "/wardrobe")
    public String getWardrobe(@RequestParam(value = "wardrobeSort", required = false) String sortMethod, Model model, Authentication authentication){
        BigDecimal bigDecimal = itemService.getItemsListByName(authentication).stream().map(i -> i.getWeight()).reduce(BigDecimal.ZERO, BigDecimal::add);
        model.addAttribute("totalWeight", bigDecimal);
        model.addAttribute("itemList", itemService.getItemsList(authentication, sortMethod));

        return "wardrobe";
    }

    @GetMapping(value = "/wardrobe/add")
    public String addItemForm(Model model){
        model.addAttribute("item", new Item());
        return "itemAdd";
    }


    @PostMapping(value = "/wardrobe")
    public String addItem(@Valid @ModelAttribute("item") Item item, BindingResult bindingResult, Model model, Authentication authentication){
        if (bindingResult.hasErrors())
            return "itemAdd";
        itemService.newItem(authentication, item);
        addWardrobeTemplateModels(model, authentication);
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
    public String updateItem(@PathVariable("id") String itemId, @Valid @ModelAttribute("item") Item item, BindingResult bindingResult, Model model, Authentication authentication){
        if (bindingResult.hasErrors())
            return "itemUpdate";
        if (itemService.updateItem(authentication, itemId, item)){
            addWardrobeTemplateModels(model, authentication);
            return "wardrobe";
        }
        return "error";
    }

    @DeleteMapping(value = "/wardrobe/{id}")
    public String deleteItem(@PathVariable("id") String itemId, Model model, Authentication authentication){
        if (itemService.deleteItem(authentication, itemId)){
            addWardrobeTemplateModels(model, authentication);
            return "wardrobe";
        }
        return "error";
    }

}