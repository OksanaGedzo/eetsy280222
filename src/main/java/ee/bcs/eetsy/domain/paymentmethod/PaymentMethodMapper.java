package ee.bcs.eetsy.domain.paymentmethod;

import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface PaymentMethodMapper {
    PaymentMethod paymentMethodDtoToPaymentMethod(PaymentMethodDto paymentMethodDto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "paymentType", source = "paymantType")
    PaymentMethodDto paymentMethodToPaymentMethodDto(PaymentMethod paymentMethod);
    List<PaymentMethodDto> paymentMethodToPaymentMethodDtos(List<PaymentMethod> paymentMethod);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePaymentMethodFromPaymentMethodDto(PaymentMethodDto paymentMethodDto, @MappingTarget PaymentMethod paymentMethod);
}
