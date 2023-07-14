package Salonce.WeatherWardrobe.repositories;

import Salonce.WeatherWardrobe.repositories.ItemRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import Salonce.WeatherWardrobe.entities.Item;

import java.math.BigDecimal;

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
        //GIVEN
        Item item = new Item();
        item.setName("lamp");
        item.setUserId("userIdTest");
        item.setWeight(new BigDecimal(10));
        itemRepository.save(item);

        //WHEN
        boolean exists = itemRepository.existsByName("lamp");
        int number = itemRepository.countByName("lamp");

        //THEN
        assertThat(exists).isTrue();
        assertThat(number).isEqualTo(1L);
    }

    @Test
    void checkItemNonExistence(){
        boolean exists = itemRepository.existsByName("lamp");
        assertThat(exists).isFalse();
    }

}