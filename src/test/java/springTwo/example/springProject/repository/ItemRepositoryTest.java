package springTwo.example.springProject.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import springTwo.example.springProject.entity.Item;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class ItemRepositoryTest {

    private ItemRepository itemRepository;

    @Autowired
    ItemRepositoryTest(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Test
    void checkIfItemRepoWorks() {
        //given
        Item item = new Item();
        item.setName("lamp");
        itemRepository.save(item);
        //when
        Optional<Item> optionalItem = itemRepository.findItemByName("lamp");

        //then
        assertThat(optionalItem.isPresent()).isTrue();
    }

}