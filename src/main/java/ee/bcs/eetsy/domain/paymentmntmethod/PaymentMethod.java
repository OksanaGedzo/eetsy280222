package ee.bcs.eetsy.domain.paymentmntmethod;

import javax.persistence.*;

@Entity
@Table(name = "payment_method")
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "paymant_type", nullable = false, length = 20)
    private String paymantType;

    public String getPaymantType() {
        return paymantType;
    }

    public void setPaymantType(String paymantType) {
        this.paymantType = paymantType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}