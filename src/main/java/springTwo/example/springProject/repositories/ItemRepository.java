package springTwo.example.springProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import springTwo.example.springProject.entities.Item;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query(value = "SELECT CASE WHEN COUNT(i) > 0 THEN true ELSE false END FROM Item i WHERE i.id = :id AND i.userId = :userId")
    Boolean itemExistsByBothIds(@Param("id") Long id, @Param("userId") String userId);

    //@Query(value = "SELECT i FROM item WHERE i.id = ?1 and i.user_id = ?2")
   // Item findItemByBothIds(Long id, String userId);

    List<Item> findByUserId(String userId);
    Boolean existsByName(String name);
    Integer countByName(String name);

    //@Query()
    //Boolean patchById(String userId, Item item);

}
