package ee.bcs.eetsy.domain.shoppingcart;

import ee.bcs.eetsy.domain.RequestResponse;
import ee.bcs.eetsy.domain.deliverymethod.DeliveryMethodDto;
import ee.bcs.eetsy.domain.deliverymethod.DeliveryMethodService;
import ee.bcs.eetsy.domain.item.Item;
import ee.bcs.eetsy.domain.item.ItemRepository;
import ee.bcs.eetsy.domain.paymentmethod.PaymentMethod;
import ee.bcs.eetsy.domain.paymentmethod.PaymentMethodDto;
import ee.bcs.eetsy.domain.paymentmethod.PaymentMethodRepository;
import ee.bcs.eetsy.domain.paymentmethod.PaymentMethodService;
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
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private PaymentMethodService paymentMethodService;
    @Resource
    private DeliveryMethodService deliveryMethodService;


    public RequestResponse createOrderItem(OrderItemRequest orderItemRequest) {
        OrderItem orderItem = new OrderItem();
        RequestResponse response = new RequestResponse();
        Integer userId = orderItemRequest.getUserId();
        Integer itemId = orderItemRequest.getItemId();
        Integer quantity = orderItemRequest.getQuantity();
        if (userRepository.existsById(userId) && itemRepository.existsById(itemId) && quantity > 0) {
            Order order = orderRepository.findByUserIdAndOrderStatus(userId, ORDER_OPEN).get();

            Item item = itemRepository.findById(itemId).get();
            orderItem.setOrder(order);
            orderItem.setQuantity(quantity);
            orderItem.setItem(item);
            BigDecimal itemSum = calculateOrderItemSum(quantity, item.getPrice());
            orderItem.setSum(itemSum);
            orderItemRepository.save(orderItem);
            response.setMessage("order item added to cart");
            return response;
        } else {
            response.setError(" order item not added itemId or userId or quantity error ");
            return response;
        }
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

    public Order findOpenOrderByUserId(Integer userId) {
        Optional<Order> order = orderRepository.findByUserIdAndOrderStatus(userId, ORDER_OPEN);
        if (order.isEmpty()) {
            Order newOrder = createNewOrder(userId);
            return newOrder;
        } else {
            Order newOrder = order.get();
            return newOrder;
        }
    }

    public RequestResponse checkForOpenOrderByUserId(Integer userId) {
        RequestResponse requestResponse = new RequestResponse();
        Optional<Order> order = orderRepository.findByUserIdAndOrderStatus(userId, ORDER_OPEN);
        if (order.isEmpty()) {
            createNewOrder(userId);
            requestResponse.setMessage("New empty order was created.");
        } else {
            requestResponse.setMessage("User already has an open order.");
        }
        return requestResponse;
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

    public ShoppingCartDto shoppingCartResource(Integer userId) {
        ShoppingCartDto shoppingCartDto = new ShoppingCartDto();

        Order order = findOpenOrderByUserId(userId);

        List<OrderItem> orderItems = orderItemRepository.findOrderItemsByOrderId(order.getId());
        List<OrderItemDto> orderItemDtos = orderItemMapper.orderItemToOrderItemDtos(orderItems);

        List<PaymentMethodDto> paymentMethodDtos = paymentMethodService.findAllPaymentMethods();

        List<DeliveryMethodDto> deliveryMethodDtos = deliveryMethodService.findAllDeliveryMethods();

        shoppingCartDto.setOrderId(order.getId());
        shoppingCartDto.setOrderNumber(order.getOrderNumber());

        shoppingCartDto.setOrderItemDtos(orderItemDtos);
        shoppingCartDto.setPaymentMethodDtos(paymentMethodDtos);
        shoppingCartDto.setDeliveryMethodDtos(deliveryMethodDtos);

        return shoppingCartDto;

    }

}

//remove item from cart
//update quantity