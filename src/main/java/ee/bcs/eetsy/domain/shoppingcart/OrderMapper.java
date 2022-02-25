package ee.bcs.eetsy.domain.shoppingcart;

import ee.bcs.eetsy.domain.RequestResponse;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface OrderMapper {

    Order orderConfirmationRequestDtoToOrderConfirmationResponse(OrderConfirmationRequestDto orderConfirmationRequestDto);

    RequestResponse orderToRequestResponse(Order order);

    Order orderDtoToOrder(OrderDto orderDto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "orderNumber", source = "orderNumber")
    @Mapping(target = "orderDate", source = "orderDate")
    OrderDto orderToOrderDto(Order order);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateOrderFromOrderDto(OrderDto orderDto, @MappingTarget Order order);
}
