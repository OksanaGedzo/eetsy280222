package ee.bcs.eetsy.domain.primary_group;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PrimaryGroupService {

    @Resource
    private PrimaryGroupRepository primaryGroupRepository;

    @Resource
    private PrimaryGroupMapper primaryGroupMapper;


    public List<PrimaryGroupDto> findAllPrimaryGroups() {

        List<PrimaryGroup> allPrimaryGroups = primaryGroupRepository.findAll();
        List<PrimaryGroupDto> primaryGroupDtos = primaryGroupMapper.primaryGroupToPrimaryGroupDto(allPrimaryGroups);

        return primaryGroupDtos;
    }





}
