package springTwo.example.springProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springTwo.example.springProject.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    Boolean existsByName(String name);
    Integer countByName(String name);

}
