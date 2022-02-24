package ee.bcs.eetsy.domain.shoppingcart;

import ee.bcs.eetsy.domain.RequestResponse;
import ee.bcs.eetsy.domain.delivery.Delivery;
import ee.bcs.eetsy.domain.delivery.DeliveryRepository;
import ee.bcs.eetsy.domain.deliverymethod.DeliveryMethod;
import ee.bcs.eetsy.domain.deliverymethod.DeliveryMethodDto;
import ee.bcs.eetsy.domain.deliverymethod.DeliveryMethodMapper;
import ee.bcs.eetsy.domain.deliverymethod.DeliveryMethodService;
import ee.bcs.eetsy.domain.item.Item;
import ee.bcs.eetsy.domain.item.ItemRepository;
import ee.bcs.eetsy.domain.paymentmethod.*;
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

    @Resource
    private DeliveryMethodMapper deliveryMethodMapper;

    @Resource
    private DeliveryRepository deliveryRepository;

    @Resource
    private PaymentMethodMapper paymentMethodMapper;


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


    //////////////////////////////////////// SEE JAMA IKKA ANNAB VASTUSE SWAGERIS //////////////////////////////////


//    public RequestResponse confirmOrder(OrderConfirmationRequestDto orderConfirmationRequestDto) {
//        RequestResponse requestResponse = new RequestResponse();
//        Integer userId = orderConfirmationRequestDto.getUserId();
//        Integer id = orderConfirmationRequestDto.getId();
//        Optional<Order> order = orderRepository.findById(id);
//        if (order.isEmpty()) {
//            createOrderInProgress(userId);
//            requestResponse.setMessage("ORDER IN PROGRESS.");
//        } else {
//            requestResponse.setMessage("UPS!SOMTHING IS WRONG");
//        }
//        return requestResponse;
//    }
//
//    public Order createOrderInProgress(Integer userId) {
//        Order order = new Order();
//        order.setOrderNumber(createOrderNumber());
//        order.setOrderStatus(ORDER_IN_PROGRESS);
//        order.setUser(userRepository.findById(userId).get());
//        order.setOrderDate(Instant.now());
//        order.setPaymentMethod(paymentMethodRepository.findAll().get(0));
//        order.setTotalPrice(BigDecimal.ZERO);
//        orderRepository.save(order);
//        return order;
//    }

//////////////////////////////////////////SIIN ON VIIMANE VERSION KOLMAPÄEVALT ///////////////////////////////////////////

//    public RequestResponse confirmClientOrder(OrderConfirmationDto orderConfirmationDto) {
//        RequestResponse requestResponse = new RequestResponse();
//
//        OrderDtoRequest orderDtoRequest = orderConfirmationDto.getOrderDtoRequest();
//
//        Order order = new Order();
//        order.setUser(userRepository.findById(orderConfirmationDto.getOrderDtoRequest().getId()).get());
//        order.setOrderDate(orderConfirmationDto.getOrderDtoRequest().getOrderDate());
//        order.setOrderStatus(orderConfirmationDto.getOrderDtoRequest().getOrderStatus());
//        order.setOrderNumber(orderConfirmationDto.getOrderDtoRequest().getOrderNumber());
//        order.setTotalPrice(orderConfirmationDto.getOrderDtoRequest().getTotalPrice());
////        order.setPaymentMethod(orderConfirmationDto.getOrderDtoRequest().getPaymentMethod().getId();
//        PaymentMethodDto paymentMethodDto = orderConfirmationDto.getOrderDtoRequest().getPaymentMethod();
//        paymentMethodRepository.find;
//
//        List<OrderItemDto> orderItemDtos = orderConfirmationDto.getOrderItemDtos();
//        List<OrderItem> orderItems = orderItemMapper.orderItemDtoToOrderItems(orderItemDtos);
//
//        DeliveryMethod deliveryMethod = orderConfirmationDto.getDeliveryMethod();
//
//        Delivery delivery = new Delivery();
//        delivery.setDeliveryMethod(deliveryMethod);
//        delivery.setOrder(order);
//
//        orderRepository.save(order);
//        orderItemRepository.saveAll(orderItems);
//        return requestResponse;
//    }


    //////////////////////////////////////// kala kala kala //////////////////////////////////////////////////////


//    public RequestResponse confirmOrder(OrderConfirmationRequestDto orderConfirmationRequestDto) {
//        RequestResponse requestResponse = new RequestResponse();
//        Integer id = orderConfirmationRequestDto.getId();
//        Optional<Order> order = orderRepository.findById(id);
////        Optional<Order> order = orderRepository.findByUserIdAndOrderStatus(userId, ORDER_IN_PROGRESS);
//        if (order.isEmpty()) {
//            Order order = new Order();
////          order.setOrderNumber(orderConfirmationRequestDto.getOrderNumber());
//            order.setOrderDate(orderConfirmationRequestDto.getOrderDate());
//            order.setOrderStatus(ORDER_IN_PROGRESS);
//            order.setId(orderConfirmationRequestDto.getId());
//            order.setTotalPrice(orderConfirmationRequestDto.getTotalPrice());
//
//            orderRepository.save(order);
//            requestResponse.setMessage("ORDER IN PROGRESS.");
//        } else {
//            requestResponse.setMessage("UPS!SOMTHING IS WRONG");
//        }
//        return requestResponse;
//    }
//


    //    public RequestResponse confirmClientOrder(OrderConfirmationRequestDto orderConfirmationRequestDto) {
//        RequestResponse requestResponse = new RequestResponse();
//
//        Order order = new Order();
//        order.setUser(userRepository.findById(orderConfirmationRequestDto.getId()).get());
//        order.setOrderDate(orderConfirmationRequestDto.getOrderDate());
//        order.setOrderStatus(orderConfirmationRequestDto.getOrderStatus());
//        order.setOrderNumber(orderConfirmationRequestDto.getOrderNumber());
//        order.setTotalPrice(orderConfirmationRequestDto.getTotalPrice());
//
//        PaymentMethodDto paymentMethodDto = orderConfirmationRequestDto.getPaymentMethodDto();
//        PaymentMethod paymentMethod = paymentMethodMapper.paymentMethodDtoToPaymentMethod(paymentMethodDto);
//        order.setPaymentMethod(paymentMethod);
//
//        List<OrderItemDto> orderItemDtos = orderConfirmationRequestDto.getOrderItemsDto();
//        List<OrderItem> orderItems = orderItemMapper.orderItemDtosToOrderItems(orderItemDtos);
//
//        DeliveryMethodDto deliveryMethodDto = orderConfirmationRequestDto.getDeliveryMethodDto();
//        DeliveryMethod deliveryMethod = deliveryMethodMapper.deliveryMethodDtoToDeliveryMethod(deliveryMethodDto);
//
//        Delivery delivery = new Delivery();
//        delivery.setDeliveryMethod(deliveryMethod);
//        delivery.setOrder(order);
//
//        orderRepository.save(order);
//        orderItemRepository.saveAll(orderItems);
//        return requestResponse;
//    }


    public RequestResponse confirmOrder(OrderConfirmationRequestDto orderConfirmationRequestDto) {
        RequestResponse requestResponse = new RequestResponse();
        Integer userId = orderConfirmationRequestDto.getUserId();
        Optional<Order> orderOpen = orderRepository.findByUserIdAndOrderStatus(userId, ORDER_OPEN);

        if (!orderOpen.isEmpty()) {
//          Order order = new Order();      kuna me uuendame eksisteerivat orderit siis me ei tohi uut orderit teha
            Order order = orderOpen.get();
//         order.setId(orderConfirmationRequestDto.getId());     id'd ei ei ole vaja settida kuna updatime olemasolevat orderit
            order.setOrderNumber(orderConfirmationRequestDto.getOrderNumber());
//          order.setOrderDate(orderConfirmationRequestDto.getOrderDate()); // getOrderDate()=null kuna frondis me ei pane talle väärtust
            order.setOrderDate(Instant.now()); // setime orderDate kuna front ei pane kaasa hetkest kellaaega
            order.setTotalPrice(orderConfirmationRequestDto.getTotalPrice());
            order.setOrderStatus(ORDER_IN_PROGRESS);
//          order.setTotalPrice(orderConfirmationRequestDto.getTotalPrice());     real 301 juba kutsusime

            PaymentMethodDto paymentMethodDto = orderConfirmationRequestDto.getPaymentMethodDto();
            PaymentMethod paymentMethod = paymentMethodMapper.paymentMethodDtoToPaymentMethod(paymentMethodDto);
            order.setPaymentMethod(paymentMethod);

            List<OrderItemDto> orderItemDtos = orderConfirmationRequestDto.getOrderItemsDto();
            List<OrderItem> orderItems = orderItemMapper.orderItemDtosToOrderItems(orderItemDtos); //orderItemitel puudub Seller info
            // todo set seller field with a for loop
            DeliveryMethodDto deliveryMethodDto = orderConfirmationRequestDto.getDeliveryMethodDto();
            DeliveryMethod deliveryMethod = deliveryMethodMapper.deliveryMethodDtoToDeliveryMethod(deliveryMethodDto);
            Delivery delivery = new Delivery();
            delivery.setDeliveryMethod(deliveryMethod);
            delivery.setOrder(order);

            orderRepository.save(order);
            deliveryRepository.save(delivery);
            orderItemRepository.saveAll(orderItems);

            requestResponse.setMessage("ORDER IN PROGRESS.");
        } else {
            requestResponse.setMessage("UPS!SOMTHING IS WRONG");
        }
        return requestResponse;
    }


//    public RequestResponse confirmOrder(OrderConfirmationRequestDto orderConfirmationRequestDto) {
//
//        RequestResponse response = new RequestResponse();
//
//        Integer id = orderConfirmationRequestDto.getId();
//        Integer userId = orderConfirmationRequestDto.getUserId();
//
//
//        if( orderRepository.existsById(id) ){
//            Order order = orderRepository.findByUserIdAndOrderStatus(userId, ORDER_IN_PROGRESS).get();
//
//            List<OrderItemDto> orderItemDtos = orderConfirmationRequestDto.getOrderItemsDtos();
//            List<OrderItem> orderItems = orderItemMapper.orderItemDtosToOrderItems(orderItemDtos);
//
//            PaymentMethodDto paymentMethodDto = orderConfirmationRequestDto.getPaymentMethodDto();
//            PaymentMethod paymentMethod = paymentMethodMapper.paymentMethodDtoToPaymentMethod(paymentMethodDto);
//
//            order.setId(orderConfirmationRequestDto.getId());
//            order.setOrderNumber(orderConfirmationRequestDto.getOrderNumber());
//            order.setOrderDate(orderConfirmationRequestDto.getOrderDate());
//            order.setTotalPrice(orderConfirmationRequestDto.getTotalPrice());
//            order.setOrderStatus(ORDER_IN_PROGRESS);
//            order.setTotalPrice(orderConfirmationRequestDto.getTotalPrice());
//            order.setPaymentMethod(paymentMethod);
//
//            DeliveryMethodDto deliveryMethodDto = orderConfirmationRequestDto.getDeliveryMethodDto();
//            DeliveryMethod deliveryMethod = deliveryMethodMapper.deliveryMethodDtoToDeliveryMethod(deliveryMethodDto);
//            Delivery delivery = new Delivery();
//            delivery.setDeliveryMethod(deliveryMethod);
//            delivery.setOrder(order);
//
//            RequestResponse requestResponse = orderMapper.orderToRequestResponse(order);
//            deliveryRepository.save(delivery);
//            orderItemRepository.saveAll(orderItems);
//
//            response.setMessage("Order in progress");
//            return response;
//        } else {
//            response.setError(" Order not added, check out errors ");
//            return response;
//        }
//    }


}

//remove item from cart
//update quantity