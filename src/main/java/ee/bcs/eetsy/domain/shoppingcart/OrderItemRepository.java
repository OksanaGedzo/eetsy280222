package ee.bcs.eetsy.domain.shoppingcart;

import ee.bcs.eetsy.domain.seller.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    @Query("select c from OrderItem c where c.item.id = ?1")
    OrderItem findByItemId(Integer id);

    @Query("select o from OrderItem o where o.order.id = :id")
    List<OrderItem> findOrderItemsByOrderId(@Param("id") Integer id);

    @Query("select o from OrderItem o where o.order.user.seller = :seller")
    List<OrderItem> findByOrderUserSeller(@Param("seller") Seller seller);








}