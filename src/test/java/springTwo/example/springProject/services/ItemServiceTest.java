package springTwo.example.springProject.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import springTwo.example.springProject.entities.Item;
import springTwo.example.springProject.repositories.ItemRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

    private ItemService itemService;
    @Mock private ItemRepository itemRepository;

    //@BeforeEach
    //void setUp() {
    //    itemService = new ItemService(itemRepository);
    //}

    @Test
    void getItemsList() {
        //when
        itemService.getItemsList();

        //then
        verify(itemRepository).findAll();
    }

    @Test
    void deleteItem() {
        //given
        Long id = 123L;

        //when
        itemService.deleteById(id);

        //then
        ArgumentCaptor<Long> longArgumentCaptor = ArgumentCaptor.forClass(Long.class);

        verify(itemRepository).deleteById(longArgumentCaptor.capture());

        assertThat(longArgumentCaptor.getValue()).isEqualTo(id);
    }

    @Test
    void saveItem() {
        //given
        Item item = new Item();

        //when
        itemService.saveItem(item);

        //then
        ArgumentCaptor<Item> itemArgumentCaptor = ArgumentCaptor.forClass(Item.class);

        verify(itemRepository).save(itemArgumentCaptor.capture());

        assertThat(itemArgumentCaptor.getValue()).isEqualTo(item);
    }
}