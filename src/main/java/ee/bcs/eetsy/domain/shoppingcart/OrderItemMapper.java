package ee.bcs.eetsy.domain.shoppingcart;

import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface OrderItemMapper {

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


    OrderItem orderItemDtoToOrderItemResponse (OrderItemDto orderItemDto);
    List<OrderItem> orderItemDtosToOrderItems(List<OrderItemDto> orderItemDto);








    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateOrderItemFromOrderItemDto(OrderItemDto cartItemDto, @MappingTarget OrderItem orderItem);
}
