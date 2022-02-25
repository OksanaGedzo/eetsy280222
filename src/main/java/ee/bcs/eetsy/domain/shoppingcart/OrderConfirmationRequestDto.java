package ee.bcs.eetsy.domain.shoppingcart;

import ee.bcs.eetsy.domain.deliverymethod.DeliveryMethodDto;
import ee.bcs.eetsy.domain.paymentmethod.PaymentMethodDto;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
public class OrderConfirmationRequestDto implements Serializable {
    private final Integer id;
    private final Integer userId;
    private final PaymentMethodDto paymentMethodDto;
    private final String orderNumber;
    private final Instant orderDate;
    private final String orderStatus;
    private final BigDecimal totalPrice;
    private final List<OrderItemDto> orderItemDto;
    private final DeliveryMethodDto deliveryMethodDto;
}

