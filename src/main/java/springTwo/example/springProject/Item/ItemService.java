package springTwo.example.springProject.Item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    ItemRepository itemRepository;

    @Autowired
    ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    List<Item> getItemsList(){
        return itemRepository.findAll();
    }

    void deleteItem(String id){
        itemRepository.deleteById(Long.parseLong(id));
    }

    void saveItem(Item item){
        itemRepository.save(item);
    }
}
