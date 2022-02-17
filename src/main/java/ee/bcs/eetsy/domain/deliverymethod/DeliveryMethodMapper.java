package ee.bcs.eetsy.domain.deliverymethod;

import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface DeliveryMethodMapper {
    DeliveryMethod deliveryMethodDtoToDeliveryMethod(DeliveryMethodDto deliveryMethodDto);

    DeliveryMethodDto deliveryMethodToDeliveryMethodDto(DeliveryMethod deliveryMethod);
    List<DeliveryMethodDto> deliveryMethodToDeliveryMethodDtos(List<DeliveryMethod> deliveryMethod);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateDeliveryMethodFromDeliveryMethodDto(DeliveryMethodDto deliveryMethodDto, @MappingTarget DeliveryMethod deliveryMethod);
}
