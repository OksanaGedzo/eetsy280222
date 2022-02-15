package ee.bcs.eetsy.domain.primary_group;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
//@RequestMapping("/")
public class PrimaryGroupController {

    @Resource
    PrimaryGroupRepository primaryGroupRepository;

    @Resource
    PrimaryGroupService primaryGroupService;


    @GetMapping("/get/all/primarygroups/")
    @Operation(summary = "Get'i kõik primary_group andmed")
    public List<PrimaryGroupDto> getAllPrimaryGroups() {

        List<PrimaryGroupDto> allPrimaryGroups = primaryGroupService.findAllPrimaryGroups();
        return allPrimaryGroups;
    }

}
