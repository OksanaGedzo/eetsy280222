package ee.bcs.eetsy.domain.paymentmethod;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class PaymentMethodController {

    @Resource
    private PaymentMethodService paymentMethodService;

    @GetMapping("/get/all/payment/methods")
    @Operation(summary = "get all payment methods")
    public List<PaymentMethodDto> findAllPaymentMethods(){
        List<PaymentMethodDto> paymentMethodDtos = paymentMethodService.findAllPaymentMethods();
       return paymentMethodDtos;
    }

}

