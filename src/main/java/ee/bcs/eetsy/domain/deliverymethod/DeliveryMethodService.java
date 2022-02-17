package ee.bcs.eetsy.domain.deliverymethod;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DeliveryMethodService {

    @Resource
    private DeliveryMethodRepository deliveryMethodRepository;

    @Resource
    private DeliveryMethodMapper deliveryMethodMapper;

    public List<DeliveryMethodDto> findAllDeliveryMethods() {
        List<DeliveryMethod> deliveryMethods = deliveryMethodRepository.findAll();
        List<DeliveryMethodDto> deliveryMethodDtos = deliveryMethodMapper.deliveryMethodToDeliveryMethodDtos(deliveryMethods);
        return deliveryMethodDtos;
    }
}
