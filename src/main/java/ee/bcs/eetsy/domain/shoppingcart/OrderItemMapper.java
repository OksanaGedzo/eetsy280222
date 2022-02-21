package ee.bcs.eetsy.domain.shoppingcart;

import ee.bcs.eetsy.domain.item.ItemDto;
import org.mapstruct.*;

import java.math.BigDecimal;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface OrderItemMapper {
    OrderItem orderItemDtoToOrderItem(OrderItemDto cartItemDto);


    @Mapping(target = "itemId", source = "item.id")
    @Mapping(target = "itemName", source = "item.name")
    @Mapping(target = "itemPrice", source = "item.price")
    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "sum", source = "sum")
    OrderItemDto orderItemToOrderItemDto(OrderItem orderItem);
    List<OrderItemDto> orderItemToOrderItemDtos(List<OrderItem> orderItem);


    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "sum", source = "sum")
    @Mapping(target = "itemId", source = "item.id")
    @Mapping(target = "itemName", source = "item.name")
    @Mapping(target = "itemPrice", source = "item.price")
    @Mapping(target = "itemDescription", source = "item.description")
    OrderItemResponse orderItemToOrderItemResponse (OrderItem orderItem);
    List<OrderItemResponse> orderItemsToOrderItemsResponses (List<OrderItem> orderItems);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateOrderItemFromOrderItemDto(OrderItemDto cartItemDto, @MappingTarget OrderItem orderItem);
}
