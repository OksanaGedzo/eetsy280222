package ee.bcs.eetsy.domain.sub_group;

import ee.bcs.eetsy.domain.primary_group.PrimaryGroupDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController

public class SubGroupController {
    @Resource
    private SubGroupMapper subGroupMapper;

    @Resource
    private  SubGroupRepository subGroupRepository;

    @Resource
    private SubGroupService subGroupService;

    @GetMapping("/getallsubgroups/")
    @Operation(summary = "Get'i kõik sub_group andmed")

    public List<SubGroupDto> getAllPrimaryGroups() {

        List<SubGroupDto> allSubGroups = subGroupService.findAllSubGroups();
        return allSubGroups;
    }

}
