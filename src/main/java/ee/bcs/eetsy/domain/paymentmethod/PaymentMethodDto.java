package ee.bcs.eetsy.domain.paymentmethod;

import lombok.Data;

import java.io.Serializable;

@Data
public class PaymentMethodDto implements Serializable {
    private final Integer id;
    private final String paymantType;
}
