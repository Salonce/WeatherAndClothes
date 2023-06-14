package springTwo.example.springProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springTwo.example.springProject.repositories.ItemRepository;
import springTwo.example.springProject.entities.Item;

import java.util.List;

@Service
public class ItemService {

    private ItemRepository itemRepository;

    @Autowired
    ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getItemsList(){
        return itemRepository.findAll();
    }

    public void deleteById(Long id){
        itemRepository.deleteById(id);
    }

    public void saveItem(Item item){
        itemRepository.save(item);
    }
}
