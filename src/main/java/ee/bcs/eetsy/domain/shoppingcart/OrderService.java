package ee.bcs.eetsy.domain.shoppingcart;
import ee.bcs.eetsy.domain.item.Item;
import ee.bcs.eetsy.domain.item.ItemRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
public class OrderService {

    @Resource
    OrdertItemDto cartItemDto;

    @Resource
    OrderItemRepository orderItemRepository;

    @Resource
    ItemRepository itemRepository;

    public OrderItem addItemToCart(Integer id, Integer quantity) {
        OrderItem orderItem = new OrderItem();
        Order order= new Order();
        orderItem.setOrder(order);
        orderItem.setQuantity(quantity);
        Item item = itemRepository.findById(id).get();
        orderItem.setItem(item);
        BigDecimal itemSum = calculateOrderItemSum(quantity, item.getPrice());
        orderItem.setSum(itemSum);
        orderItemRepository.save(orderItem);
        return orderItem;
    }
    public BigDecimal calculateOrderItemSum (Integer amount, BigDecimal price) {
        BigDecimal quantity = BigDecimal.valueOf(amount);
        return price.multiply(quantity);
    }
}

//add item to cart
//remove item from cart
//update quantity