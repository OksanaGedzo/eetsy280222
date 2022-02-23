package ee.bcs.eetsy.domain.user;


import ee.bcs.eetsy.domain.RequestResponse;
import ee.bcs.eetsy.domain.shoppingcart.OrderItem;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
public class UserController {

    @Resource
    UserService userService;

    @GetMapping("/login")
    @Operation(summary = " this is login info")
    public Integer getUserIdByUsernameAndPassword(@RequestParam String username, @RequestParam String password) {
        int id = userService.findUserIdByUsernameAndPassword(username, password);
        return id;
    }

    @GetMapping("/check/if/user/exist")
    @Operation(summary = " checking if user exists")
    public Boolean checkIfUserExists(@RequestParam String username) {
        boolean userCheck = userService.checkIfUserExists(username);
        return userCheck;

    }

    @PostMapping("/add/new/user")
    @Operation(summary = "adds new user")
    public RequestResponse addNewUser(@RequestBody UserDto userDto) {
        RequestResponse response = userService.addNewUser(userDto);
        return response;
    }


}


