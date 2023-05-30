package springTwo.example.springProject.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Example;
import springTwo.example.springProject.entity.Item;

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
        boolean expected = itemRepository.exists(Example.of(item));

        //then
        assertThat(expected);
    }
}