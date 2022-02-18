package ee.bcs.eetsy.domain.shoppingcart;


import ee.bcs.eetsy.domain.item.ItemDto;
import ee.bcs.eetsy.domain.seller.Seller;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class OrderItemResponse {
    private final Integer quantity;
    private final BigDecimal sum;
    private final Integer itemId;
    private final String itemName;
    private final BigDecimal itemPrice;
    private final String itemDescription;

}
