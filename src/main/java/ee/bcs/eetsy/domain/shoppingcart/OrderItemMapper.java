package ee.bcs.eetsy.domain.shoppingcart;

import org.mapstruct.*;

import java.math.BigDecimal;
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

//    @Mapping(target = "id", source = "id")
//    @Mapping(target = "item", source = "itemId")
//    @Mapping(target = "id", source = "itemName")
//    @Mapping(target = "id", source = "itemPrice")
//    @Mapping(target = "id", source = "quantity")
//    @Mapping(target = "id", source = "sum")
    OrderItem orderItemDtoToOrderItem(OrderItemDto orderItemDto);
    List<OrderItem> orderItemDtosToOrderItems(List<OrderItemDto> orderItemDto);









    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateOrderItemFromOrderItemDto(OrderItemDto cartItemDto, @MappingTarget OrderItem orderItem);
}
