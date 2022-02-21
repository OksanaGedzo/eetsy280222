package ee.bcs.eetsy.domain.shoppingcart;

import ee.bcs.eetsy.domain.paymentmethod.PaymentMethodDto;
import ee.bcs.eetsy.domain.user.UserDto;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
public class OrderDto implements Serializable {
    private final Integer id;
    private final String orderNumber;
    private final Instant orderDate;
}
