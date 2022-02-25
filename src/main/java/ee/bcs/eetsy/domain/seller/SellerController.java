package ee.bcs.eetsy.domain.seller;

import ee.bcs.eetsy.domain.primary_group.PrimaryGroupDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class SellerController {

    @Resource
    private SellerService sellerService;

    @GetMapping("/get/all/sellers")
    @Operation(summary = "Get all validated sellers")
    public List<SellerBasicDto> getAllValidatedSellers() {
        List<SellerBasicDto> allValidSellers = sellerService.findAllValidatedSellers();
        return allValidSellers;
    }
}
