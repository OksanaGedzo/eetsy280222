package ee.bcs.eetsy.domain.deliverymethod;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class DeliveryMethodDto implements Serializable {
    private final Integer id;
    private final BigDecimal price;
    private final String name;
    private final String deliveryTime;
}
