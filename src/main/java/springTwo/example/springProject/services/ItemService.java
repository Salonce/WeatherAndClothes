package springTwo.example.springProject.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import springTwo.example.springProject.repositories.ItemRepository;
import springTwo.example.springProject.entities.Item;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final AuthenticationService authenticationService;

    private final ItemRepository itemRepository;

    public List<Item> getItemsList(){
        return itemRepository.findAll();
    }

    public List<Item> getItemsList(Authentication authentication, String sortMethod){
        if(sortMethod != null && sortMethod.equals("byWeight"))
            return getItemsListByWeight(authentication);
        return getItemsListByName(authentication);
    }

    public List<Item> getItemsListByName(Authentication authentication){
        return itemRepository.findByUserIdOrderByName(getUserId(authentication));
    };

    public List<Item> getItemsListByWeight(Authentication authentication){ return itemRepository.findByUserIdOrderByWeight(getUserId(authentication)); };

    public Item getItemById(String id) {
        return itemRepository.getReferenceById(parseIdToLong(id));
    }

    public Boolean userHasItem(Authentication authentication, String id){
        String userId = getUserId(authentication);
        return itemRepository.existsByIdAndUserId(parseIdToLong(id), userId);
    }


    @Transactional
    public void updateItem(String id, Item updatedItem){
        Item item = itemRepository.getReferenceById(parseIdToLong(id));
        item.setName(updatedItem.getName());
        item.setWeight(updatedItem.getWeight());
    }

    public void newItem(Authentication authentication, Item item){
        String userId = getUserId(authentication);
        item.setUserId(userId);
        saveItem(item);
    }

    @Transactional
    public Boolean deleteItem(Authentication authentication, String itemId){
        Long id = parseIdToLong(itemId);
        String userId = getUserId(authentication);
        Integer numDeleted = itemRepository.deleteByIdAndUserId(id, userId);
        return (numDeleted > 0) ? true : false;
    }

    public void deleteById(Long id){
        itemRepository.deleteById(id);
    }

    public void deleteById(String id){
        Long idLong = parseIdToLong(id);
        itemRepository.deleteById(idLong);
    }

    public void saveItem(Item item){
        itemRepository.save(item);
    }




    //PRIVATE
    private String getUserId(Authentication authentication) {
        return authenticationService.getUserId(authentication);
    }

    private Long parseIdToLong(String id){
        return Long.parseLong(id);
    }




    /* DEPRECATED
    public List<Item> getItemsListByWeight(String userId){ return itemRepository.findByUserIdOrderByWeight(userId); };

    public List<Item> getItemsListByName(String userId){
        return itemRepository.findByUserIdOrderByName(userId);
    };

    public Item getItemById(Long id) { return itemRepository.getReferenceById(id);}


    @Transactional
    public void updateItem(Long id, String name, String weight){
        Item item = itemRepository.getReferenceById(id);
        item.setName(name);
        item.setWeight(weight);
    }

    public Boolean itemExistsByBothIds(Long id, String userId){
        return itemRepository.itemExistsByBothIds(id, userId);
    }
     */
}
