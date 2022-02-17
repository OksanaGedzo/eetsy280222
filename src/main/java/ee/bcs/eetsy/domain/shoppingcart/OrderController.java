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
    OrderItemRepository orderItemRepository;

    @Resource
    OrderService orderService;
    @Resource
    ItemRepository itemRepository;


    @GetMapping ("/add/orderitem/to/cart")
    @Operation(summary = "add oder item to shopping cart ")
    public OrderItem addItemToCart(@RequestParam Integer itemId, @RequestParam Integer itemQuantity) {
       orderService.addItemToCart(itemId, itemQuantity);

// TODO: 17 Feb 2022 finish!

        return null;

    }
}







//    public List<SubGroupResponse> getSubGroupsByPrimaryGroupId(@RequestParam Integer id) {
//        List<SubGroupResponse> response = subGroupService.findSubGroupsNameAndPicture(id);
//        return response;
//}
