package ee.bcs.eetsy.domain.shoppingcart;
import ee.bcs.eetsy.domain.item.Item;
import ee.bcs.eetsy.domain.item.ItemRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {


    @Resource
    private OrderItemRepository orderItemRepository;

    @Resource
    private OrderItemMapper orderItemMapper;

    @Resource
    private ItemRepository itemRepository;

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

    public List<OrderItemDto> findOrderItemsByOrderId(Integer id){
        List<OrderItem> orderItems = orderItemRepository.findOrderItemsByOrderId(id);
        List<OrderItemDto> orderItemDtos = orderItemMapper.orderItemToOrderItemDtos(orderItems);
        return  orderItemDtos;
    }

    public List<OrderItemResponse> getOrderItemsByOrderId(Integer id){
        List<OrderItem> orderItems = orderItemRepository.findOrderItemsByOrderId(id);
        List<OrderItemResponse> orderItemResponses = orderItemMapper.orderItemsToOrderItemsResponses(orderItems);
        return  orderItemResponses;
    }


}

//add item to cart
//remove item from cart
//update quantity