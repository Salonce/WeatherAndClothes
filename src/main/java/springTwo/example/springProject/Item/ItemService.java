package springTwo.example.springProject.Item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    ItemRepository studentRepository;

    @Autowired
    ItemService(ItemRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    List<Item> getItemsList(){
        return studentRepository.findAll();
    }

    void deleteItem(String id){
        studentRepository.deleteById(Long.parseLong(id));
    }

    void saveItem(Item student){
        studentRepository.save(student);
    }
}
