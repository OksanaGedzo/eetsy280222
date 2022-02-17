package ee.bcs.eetsy.domain.shoppingcart;

import ee.bcs.eetsy.domain.item.ItemDto;
import org.mapstruct.*;

import java.math.BigDecimal;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface OrderItemMapper {
    OrderItem orderItemDtoToOrderItem(OrderItemDto cartItemDto);


    OrderItemDto orderItemToOrderItemDto(OrderItem orderItem);
    List<OrderItemDto> orderItemToOrderItemDtos(List<OrderItem> orderItem);


    @Mapping(target = "item ", source = "item")
    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "sum", source = "sum")
    OrderItemResponse orderItemToOrderItemResponse (OrderItem orderItem);
    List<OrderItemResponse> orderItemsToOrderItemsResponses (List<OrderItem> orderItems);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateOrderItemFromOrderItemDto(OrderItemDto cartItemDto, @MappingTarget OrderItem orderItem);
}
