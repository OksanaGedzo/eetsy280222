package ee.bcs.eetsy.domain.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    @Query("select i from Item i where i.seller.id = :id")
    Item findBySeller_Id(@Param("id") Integer id);



}
//    //Oksana:
//    @Query("select i from Item i where i.name = ?1")
//    Item findByName(String name);}

