package springTwo.example.springProject.repositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import springTwo.example.springProject.entities.Item;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class ItemRepositoryTest {

    private ItemRepository itemRepository;

    @Autowired
    ItemRepositoryTest(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @AfterEach
    void tearDown(){
        itemRepository.deleteAll();
    }

    @Test
    void checkItemCreation() {
        //given
        Item item = new Item();
        item.setName("lamp");
        itemRepository.save(item);

        //when
        boolean exists = itemRepository.existsByName("lamp");
        int number = itemRepository.countByName("lamp");

        //then
        assertThat(exists).isTrue();
        assertThat(number).isEqualTo(1L);
    }

    @Test
    void checkItemExistence(){
        boolean exists = itemRepository.existsByName("lamp");
        assertThat(exists).isFalse();
    }

}