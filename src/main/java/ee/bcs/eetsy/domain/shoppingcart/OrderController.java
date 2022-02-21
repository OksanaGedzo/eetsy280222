package ee.bcs.eetsy.domain.shoppingcart;


import ee.bcs.eetsy.domain.RequestResponse;
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


    @PostMapping("/add/orderitem/to/cart")
    @Operation(summary = "add oder item to shopping cart ")
    public RequestResponse addItemToCart(@RequestBody OrderItemRequest orderItemRequest) {
        RequestResponse response = orderService.createOrderItem(orderItemRequest);
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

    @GetMapping("/check/for/open/order/by/user/id")
    @Operation(summary = "Checks if User has an open order")
    public RequestResponse checkForOpenOrderIdByUserId(@RequestParam Integer userId) {
        return orderService.checkForOpenOrderByUserId(userId);
    }

    @GetMapping("/get/shopping/cart")
    @Operation(summary = "Composes all necessary data to display the shopping cart page")
    public ShoppingCartDto getShoppingCartResource(@RequestParam Integer userId) {
        ShoppingCartDto response = orderService.shoppingCartResource(userId);
        return response;
    }


}


//    public List<SubGroupResponse> getSubGroupsByPrimaryGroupId(@RequestParam Integer id) {
//        List<SubGroupResponse> response = subGroupService.findSubGroupsNameAndPicture(id);
//        return response;
//}
