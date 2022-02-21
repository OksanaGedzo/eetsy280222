package ee.bcs.eetsy.domain.shoppingcart;

import ee.bcs.eetsy.domain.item.ItemDto;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class OrderItemDto implements Serializable {
    private final Integer itemId;
    private final String itemName;
    private final BigDecimal itemPrice;
    private final Integer quantity;
    private final BigDecimal sum;
}
