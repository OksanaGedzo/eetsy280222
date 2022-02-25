package ee.bcs.eetsy.domain.seller;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SellerService {

    @Resource
    private SellerRepository sellerRepository;
    @Resource
    private SellerMapper sellerMapper;

    public List<SellerBasicDto> findAllValidatedSellers() {
        List<Seller> sellers = sellerRepository.findByValidatedTrue();
        return sellerMapper.sellersToSellerBasicDtos(sellers);
    }
}
