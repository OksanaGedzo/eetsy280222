package ee.bcs.eetsy.domain.item;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ItemPagetDto implements Serializable {
    private final String sellerName;
    private final String name;
    private final BigDecimal price;
    private final String description;
}
