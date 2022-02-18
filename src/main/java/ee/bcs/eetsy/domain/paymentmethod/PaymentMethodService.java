package ee.bcs.eetsy.domain.paymentmethod;

import ee.bcs.eetsy.domain.deliverymethod.DeliveryMethod;
import ee.bcs.eetsy.domain.deliverymethod.DeliveryMethodDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PaymentMethodService {

    @Resource
    private PaymentMethodRepository paymentMethodRepository;

    @Resource
    private PaymentMethodMapper paymentMethodMapper;

    public List<PaymentMethodDto> findAllPaymentMethods() {
        List<PaymentMethod> paymentMethods = paymentMethodRepository.findAll();
        List<PaymentMethodDto> paymentMethodDtos = paymentMethodMapper.paymentMethodToPaymentMethodDtos(paymentMethods);
        return paymentMethodDtos;
    }



}
