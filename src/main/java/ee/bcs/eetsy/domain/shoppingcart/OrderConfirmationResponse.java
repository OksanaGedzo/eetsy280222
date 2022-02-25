package ee.bcs.eetsy.domain.shoppingcart;

import ee.bcs.eetsy.domain.deliverymethod.DeliveryMethod;
import ee.bcs.eetsy.domain.paymentmethod.PaymentMethod;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
public class OrderConfirmationResponse implements Serializable {
    private final Integer id;
    private final Integer userId;
    private final PaymentMethod paymentMethod;
    private final String orderNumber;
    private final Instant orderDate;
    private final String orderStatus;
    private final BigDecimal totalPrice;
    private final List<OrderItem> orderItem;
    private final DeliveryMethod deliveryMethod;

}
