package Salonce.WeatherWardrobe.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import Salonce.WeatherWardrobe.repositories.ItemRepository;
import Salonce.WeatherWardrobe.entities.Item;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final AuthenticationService authenticationService;
    private final ItemRepository itemRepository;

    public List<Item> getItemsList(){
        return itemRepository.findAll();
    }

    public List<Item> getUserItemsList(Authentication authentication, String sortMethod){
        if(sortMethod != null && sortMethod.equals("byWeight"))
            return getItemsListByWeight(authentication);
        return getItemsListByName(authentication);
    }

    public List<Item> getItemsListByName(Authentication authentication){
        return itemRepository.findByUserIdOrderByName(getUserId(authentication));
    }

    public List<Item> getItemsListByWeight(Authentication authentication){
        return itemRepository.findByUserIdOrderByWeight(getUserId(authentication));
    }

    public Item getItemById(String id) {
        return itemRepository.getReferenceById(Long.parseLong(id));
    }


    public Boolean userHasItem(Authentication authentication, String itemId){
        String userId = getUserId(authentication); // test authenticationService.getUserId(authentication);
        return itemRepository.existsByIdAndUserId(Long.parseLong(itemId), userId);
    }

    @Transactional
    public Boolean updateItem(Authentication authentication, String itemId, Item item) {
        if (userHasItem(authentication, itemId)) {
            Item updatedItem = itemRepository.getItemById(Long.parseLong(itemId));
            updatedItem.setName(item.getName());
            updatedItem.setWeight(item.getWeight());
            return true;
        }
        return false;
    }

    public void saveItem(Authentication authentication, Item item){
        String userId = getUserId(authentication);
        item.setUserId(userId);
        itemRepository.save(item);
    }

    public Boolean deleteItem(Authentication authentication, String itemId){
        Long id = Long.parseLong(itemId);
        String userId = getUserId(authentication);
        Integer numDeleted = itemRepository.deleteByIdAndUserId(id, userId);
        return (numDeleted > 0) ? true : false;
    }

    public void deleteById(Long id){
        itemRepository.deleteById(id);
    }

    //PRIVATE
    private String getUserId(Authentication authentication) {
        return authenticationService.getUserId(authentication);
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

    public void deleteById(String id){
        Long idLong = parseIdToLong(id);
        itemRepository.deleteById(idLong);
    }
     */
}
