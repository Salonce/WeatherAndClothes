package springTwo.example.springProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import springTwo.example.springProject.entities.Item;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    Item getItemById(Long id);

    Boolean existsByIdAndUserId(Long id, String userId);

    List<Item> findByUserIdOrderByWeight(String userId);
    List<Item> findByUserIdOrderByName(String userId);

    Boolean existsByName(String name);
    Integer countByName(String name);

    @Transactional
    Integer deleteByIdAndUserId(Long id, String userId);
    /*
    DEPRECATED

    @Modifying
    @Query(value= "UPDATE Item i SET i.name = :name, i.weight = :weight WHERE i.id = :id")
    void updateItem(@Param("id") Long id, @Param("name") String name, @Param("weight") String weight);

    @Query(value = "SELECT CASE WHEN COUNT(i) > 0 THEN true ELSE false END FROM Item i WHERE i.id = :id AND i.userId = :userId")
    Boolean itemExistsByBothIds(@Param("id") Long id, @Param("userId") String userId);

    @Query(value = "SELECT i FROM item WHERE i.id = ?1 and i.user_id = ?2")
     Item findItemByBothIds(Long id, String userId);

    @Query()
    Boolean patchById(String userId, Item item);
    */
}
