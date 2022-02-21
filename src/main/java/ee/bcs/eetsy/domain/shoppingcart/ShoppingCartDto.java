package ee.bcs.eetsy.domain.shoppingcart;

import ee.bcs.eetsy.domain.deliverymethod.DeliveryMethodDto;
import ee.bcs.eetsy.domain.paymentmethod.PaymentMethod;
import ee.bcs.eetsy.domain.paymentmethod.PaymentMethodDto;
import ee.bcs.eetsy.domain.user.UserDto;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
@Data
public class ShoppingCartDto {
    private Integer orderId;
    private String orderNumber;

    private List<OrderItemDto> orderItemDtos;
    private List<PaymentMethodDto> paymentMethodDtos;
    private List<DeliveryMethodDto> deliveryMethodDtos;
}
