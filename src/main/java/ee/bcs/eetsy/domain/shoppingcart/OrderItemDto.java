package ee.bcs.eetsy.domain.shoppingcart;

import ee.bcs.eetsy.domain.item.ItemDto;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class OrderItemDto implements Serializable {
    private final Integer id;
    private final Order order;
    private final ItemDto item;
    private final Integer quantity;
    private final BigDecimal sum;
}
