package ee.bcs.eetsy.domain.delivery;

import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface DeliveryMapper {
    Delivery deliveryDtoToDelivery(DeliveryDto deliveryDto);

    DeliveryDto deliveryToDeliveryDto(Delivery delivery);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateDeliveryFromDeliveryDto(DeliveryDto deliveryDto, @MappingTarget Delivery delivery);
}
