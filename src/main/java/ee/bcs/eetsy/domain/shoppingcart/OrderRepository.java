package ee.bcs.eetsy.domain.shoppingcart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("select o from Order o where o.user.id = :id and upper(o.orderStatus) = upper(:orderStatus)")
    Optional<Order> findByUserIdAndOrderStatus(@Param("id") Integer id, @Param("orderStatus") String orderStatus);


}