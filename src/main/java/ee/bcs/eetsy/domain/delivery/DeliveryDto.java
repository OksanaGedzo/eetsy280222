package ee.bcs.eetsy.domain.delivery;

import ee.bcs.eetsy.domain.deliverymethod.DeliveryMethodDto;
import ee.bcs.eetsy.domain.shoppingcart.OrderDto;
import lombok.Data;

import java.io.Serializable;

@Data
public class DeliveryDto implements Serializable {
    private final Integer id;
    private final DeliveryMethodDto deliveryMethod;
    private final OrderDto order;
}
