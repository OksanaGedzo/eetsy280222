package ee.bcs.eetsy.domain.item;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ItemRequest implements Serializable {
    private final Integer itemId;
    private final String name;
    private final Integer sellerId;
    private final BigDecimal price;
    private final String description;
}
