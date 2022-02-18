package ee.bcs.eetsy.domain.shoppingcart;


import lombok.Data;

@Data
public class OrderItemRequest {

    private final Integer userId;
    private final Integer itemId;
    private final Integer quantity;


}
