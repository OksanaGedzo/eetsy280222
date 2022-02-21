package ee.bcs.eetsy.domain.shoppingcart;


import ee.bcs.eetsy.domain.item.ItemRepository;
import ee.bcs.eetsy.domain.sub_group.SubGroupResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class OrderController {

    @Resource
    private OrderItemRepository orderItemRepository;

    @Resource
    private OrderService orderService;

    @Resource
    private ItemRepository itemRepository;

    @Resource
    private OrderItemMapper orderItemMapper;


    @PutMapping("/add/orderitem/to/cart")
    @Operation(summary = "add oder item to shopping cart ")
    public OrderItemResponse createOrderItemResponse(@RequestBody OrderItemRequest orderItemRequest) {
        OrderItem orderItem = orderService.createOrderItem(orderItemRequest);
        OrderItemResponse response = orderItemMapper.orderItemToOrderItemResponse(orderItem);
        return response;
    }

    @GetMapping("/get/order/items/by/order/id")
    @Operation(summary = "get order Items by order id ")
    public List<OrderItemResponse> getOrderItemsByOrderId(@RequestParam Integer id) {
        List<OrderItemResponse> orderItemResponses = orderService.getOrderItemsByOrderId(id);
        return orderItemResponses;
    }

    @GetMapping("/get/open/order/by/user/id")
    @Operation(summary = "get open order by user id ")
    public Integer findOpenOrderIdByUserId(@RequestParam Integer userId) {
        Order order = orderService.findOpenOrderByUserId(userId);
        return order.getId();
    }

    @GetMapping("/get/shopping/cart")
    @Operation(summary = "Composes all necessary data to display the shopping cart page")
    public ShoppingCartDto getShoppingCartResource (@RequestParam Integer userId) {
        ShoppingCartDto response = orderService.shoppingCartResource(userId);
        return response;
    }




}


//    public List<SubGroupResponse> getSubGroupsByPrimaryGroupId(@RequestParam Integer id) {
//        List<SubGroupResponse> response = subGroupService.findSubGroupsNameAndPicture(id);
//        return response;
//}
