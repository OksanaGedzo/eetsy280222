package ee.bcs.eetsy.domain.paymentmntmethod;

import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface PaymentMethodMapper {
    PaymentMethod paymentMethodDtoToPaymentMethod(PaymentMethodDto paymentMethodDto);

    PaymentMethodDto paymentMethodToPaymentMethodDto(PaymentMethod paymentMethod);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePaymentMethodFromPaymentMethodDto(PaymentMethodDto paymentMethodDto, @MappingTarget PaymentMethod paymentMethod);
}
