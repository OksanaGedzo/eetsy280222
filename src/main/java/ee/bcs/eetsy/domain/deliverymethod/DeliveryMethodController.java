package ee.bcs.eetsy.domain.deliverymethod;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class DeliveryMethodController {

    @Resource
    private DeliveryMethodService deliveryMethodService;

    @GetMapping("/get/all/delivery/methods")
    @Operation(summary = "Get'i kõik delivery methods")
    public List<DeliveryMethodDto> getAllDeliveryMethods() {
     List<DeliveryMethodDto> deliveryMethodDtos = deliveryMethodService.findAllDeliveryMethods();
        return  deliveryMethodDtos;
    }
}
