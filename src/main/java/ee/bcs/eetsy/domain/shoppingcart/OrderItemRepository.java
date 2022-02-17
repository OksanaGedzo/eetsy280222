package ee.bcs.eetsy.domain.shoppingcart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    @Query("select c from OrderItem c where c.item.id = ?1")
    OrderItem findByItemId(Integer id);


}