package ee.bcs.eetsy.domain.shoppingcart;

import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface OrderItemMapper {
    OrderItem orderItemDtoToOrderItem(OrderItemDto orderItemDto);

    OrderItemDto orderItemToOrderItemDto(OrderItem orderItem);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateOrderItemFromOrderItemDto(OrderItemDto orderItemDto, @MappingTarget OrderItem orderItem);
}
