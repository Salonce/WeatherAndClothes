package springTwo.example.springProject.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springTwo.example.springProject.repositories.ItemRepository;
import springTwo.example.springProject.entities.Item;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public List<Item> getItemsList(){
        return itemRepository.findAll();
    }

    public List<Item> getItemsList(String userId){ return itemRepository.findByUserId(userId); };

    public Boolean itemExistsByBothIds(Long id, String userId){
        return itemRepository.itemExistsByBothIds(id, userId);
    }

    public Item getItemById(Long id) { return itemRepository.getReferenceById(id);}

    @Transactional
    public void updateItem(Long id, String name, String weight){ itemRepository.updateItem(id, name, weight);}

    public void deleteById(Long id){
        itemRepository.deleteById(id);
    }

    public boolean existsById(Long id){
        return itemRepository.existsById(id);
    }

    //public void patchById(Long id, Item item){
    //    itemRepository.patchById(id);
    //}

    public void saveItem(Item item){
        itemRepository.save(item);
    }
}
