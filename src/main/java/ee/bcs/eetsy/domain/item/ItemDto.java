package ee.bcs.eetsy.domain.item;

import ee.bcs.eetsy.domain.seller.Seller;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ItemDto implements Serializable {
    private final Integer id;
    private final Seller seller;
    private final String name;
    private final BigDecimal price;
    private final String description;
}
