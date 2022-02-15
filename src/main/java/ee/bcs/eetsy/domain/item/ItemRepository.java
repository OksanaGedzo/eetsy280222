package ee.bcs.eetsy.domain.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Integer> {

    //Oksana:
    @Query("select i from Item i where i.name = ?1")
    Item findByName(String name);


}