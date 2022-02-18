package ee.bcs.eetsy.domain.user;


import ee.bcs.eetsy.domain.shoppingcart.OrderItem;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {

    @Resource
    UserService userService;

    @GetMapping("/login")
    @Operation(summary = " this is login info")
    public Integer getUserIdByUsernameAndPassword(@RequestParam String username, @RequestParam String password) {
        int id = userService.findUserIdByUsernameAndPassword(username,password);
        return id;
    }

}

//    @GetMapping("/add/orderitem/to/cart")
//    @Operation(summary = "add oder item to shopping cart ")
//    public OrderItem addItemToCart(@RequestParam Integer itemId, @RequestParam Integer itemQuantity) {
//        orderService.addItemToCart(itemId, itemQuantity);
//}
