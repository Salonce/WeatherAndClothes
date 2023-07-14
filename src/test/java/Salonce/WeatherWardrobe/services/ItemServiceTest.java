package Salonce.WeatherWardrobe.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import Salonce.WeatherWardrobe.entities.Item;
import Salonce.WeatherWardrobe.repositories.ItemRepository;
import org.springframework.security.core.Authentication;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

    private ItemService itemService;

    @Mock private AuthenticationService authenticationService;
    @Mock private ItemRepository itemRepository;

    @BeforeEach
    void setUp() {
        itemService = new ItemService(authenticationService, itemRepository);
    }

    @Test
    void getItemsList() {
        //WHEN
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

    @Disabled
    @Test
    void testUserItemsList() {
    }
    @Disabled
    @Test
    void getItemsListByName() {
    }
    @Disabled
    @Test
    void getItemsListByWeight() {
    }

    @Test
    void getItemById() {
        //given
        String itemId = "12345";

        //when
        itemService.getItemById(itemId);

        //then
        verify(itemRepository).getReferenceById(Long.parseLong(itemId));
    }

    @Test
    void userHasItem() {
        //given
        Authentication authentication = mock(Authentication.class);
        String itemId = "10150103";
        when(authenticationService.getUserId(authentication)).thenReturn("050301");

        //when
        itemService.userHasItem(authentication, itemId);

        //then
        verify(itemRepository).existsByIdAndUserId(Long.parseLong(itemId), "050301");
    }

    @Disabled
    @Test
    void updateItem() {

    }

    @Test
    void saveItem() {
        //given
        Item item = new Item();
        Authentication authentication = Mockito.mock(Authentication.class);
        ArgumentCaptor<Item> itemCaptor = ArgumentCaptor.forClass(Item.class);

        //when
        itemService.saveItem(authentication, item);
        verify(itemRepository).save(itemCaptor.capture());

        //then
        assertThat(item.equals(itemCaptor.getValue()));
    }

    @Test
    void deleteById() {
        //given
        Long testId = 12345L;
        Item item = new Item();
        item.setId(testId);

        //when
        itemService.deleteById(testId);

        //then
        ArgumentCaptor<Long> longArgumentCaptor = ArgumentCaptor.forClass(Long.class);

        Mockito.verify(itemRepository).deleteById(longArgumentCaptor.capture());

        assertThat(longArgumentCaptor.getValue().equals(testId));
    }
}