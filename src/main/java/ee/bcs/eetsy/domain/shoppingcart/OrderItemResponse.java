package ee.bcs.eetsy.domain.shoppingcart;


import ee.bcs.eetsy.domain.item.ItemDto;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class OrderItemResponse {
    private final ItemDto item;
    private final Integer quantity;
    private final BigDecimal sum;
}
