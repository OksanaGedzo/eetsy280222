package ee.bcs.eetsy.domain.shoppingcart;

import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface OrderItemMapper {
    OrderItem orderItemDtoToOrderItem(OrdertItemDto cartItemDto);

    OrdertItemDto orderItemToOrderItemDto(OrderItem orderItem);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateOrderItemFromOrderItemDto(OrdertItemDto cartItemDto, @MappingTarget OrderItem orderItem);
}
