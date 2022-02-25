package ee.bcs.eetsy.domain.deliverymethod;

import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface DeliveryMethodMapper {


    DeliveryMethodDto deliveryMethodToDeliveryMethodDto(DeliveryMethod deliveryMethod);
    List<DeliveryMethodDto> deliveryMethodToDeliveryMethodDtos(List<DeliveryMethod> deliveryMethod);

    DeliveryMethod deliveryMethodDtoToDeliveryMethod(DeliveryMethodDto deliveryMethodDto);
    List<DeliveryMethod> deliveryMethodDtoToDeliveryMethods(List<DeliveryMethodDto> deliveryMethodDto);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateDeliveryMethodFromDeliveryMethodDto(DeliveryMethodDto deliveryMethodDto, @MappingTarget DeliveryMethod deliveryMethod);
}
