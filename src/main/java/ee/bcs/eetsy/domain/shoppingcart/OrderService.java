package ee.bcs.eetsy.domain.shoppingcart;
import ee.bcs.eetsy.domain.item.Item;
import ee.bcs.eetsy.domain.item.ItemRepository;
import ee.bcs.eetsy.domain.paymentmethod.PaymentMethodRepository;
import ee.bcs.eetsy.domain.user.UserRepository;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class OrderService {
    public static final String ORDER_RECEIVED = "R";
    public static final String ORDER_IN_PROGRESS = "P";
    public static final String ORDER_IN_TRANSIT = "T";
    public static final String ORDER_DELIVERED = "D";
    public static final String ORDER_CANCELLED = "C";
    public static final String ORDER_OPEN = "O";

    @Resource
    private OrderItemRepository orderItemRepository;

    @Resource
    private OrderItemMapper orderItemMapper;

    @Resource
    private ItemRepository itemRepository;

    @Resource
    private OrderRepository orderRepository;

    @Resource
    private UserRepository userRepository;

    @Resource
    private PaymentMethodRepository paymentMethodRepository;


    public OrderItem createOrderItem(OrderItemRequest orderItemRequest) {
        OrderItem orderItem = new OrderItem();
        Integer userId = orderItemRequest.getUserId();
        Integer itemId= orderItemRequest.getItemId();
        Integer quantity = orderItemRequest.getQuantity();
        Order order = orderRepository.findByUserIdAndOrderStatus(userId, ORDER_OPEN).get();
        Item item = itemRepository.findById(itemId).get();
        orderItem.setOrder(order);
        orderItem.setQuantity(quantity);
        orderItem.setItem(item);
        BigDecimal itemSum = calculateOrderItemSum(quantity, item.getPrice());
        orderItem.setSum(itemSum);
        orderItemRepository.save(orderItem);
        return orderItem;
    }

    public BigDecimal calculateOrderItemSum(Integer amount, BigDecimal price) {
        BigDecimal quantity = BigDecimal.valueOf(amount);
        return price.multiply(quantity);
    }

    public List<OrderItemDto> findOrderItemsByOrderId(Integer id) {
        List<OrderItem> orderItems = orderItemRepository.findOrderItemsByOrderId(id);
        List<OrderItemDto> orderItemDtos = orderItemMapper.orderItemToOrderItemDtos(orderItems);
        return orderItemDtos;
    }

    public List<OrderItemResponse> getOrderItemsByOrderId(Integer id) {
        List<OrderItem> orderItems = orderItemRepository.findOrderItemsByOrderId(id);
        List<OrderItemResponse> orderItemResponses = orderItemMapper.orderItemsToOrderItemsResponses(orderItems);
        return orderItemResponses;
    }

    public Integer findOpenOrderByUserId(Integer userId) {
        Optional<Order> order = orderRepository.findByUserIdAndOrderStatus(userId, ORDER_OPEN);
        if (order.isEmpty()) {
            Order newOrder = createNewOrder(userId);
            return newOrder.getId();
        }else{
            Order newOrder = order.get();
            return newOrder.getId();

        }

    }

    public Order createNewOrder(Integer userId) {
        Order order = new Order();
        order.setOrderNumber(createOrderNumber());
        order.setOrderStatus(ORDER_OPEN);
        order.setUser(userRepository.findById(userId).get());
        order.setOrderDate(Instant.now());
        order.setPaymentMethod(paymentMethodRepository.findAll().get(0));
        order.setTotalPrice(BigDecimal.ZERO);
        orderRepository.save(order);
        return order;
    }

    public String createOrderNumber() {
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyy");
        Date date = new Date();
        Integer randomInt = new Random().nextInt(9999 - 1000);
        String orderNumber = formatter.format(date) + "" + randomInt;
        return orderNumber;
    }
}

//remove item from cart
//update quantity