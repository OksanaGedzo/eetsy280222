package ee.bcs.eetsy.domain.sub_group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SubGroupRepository extends JpaRepository<SubGroup, Integer> {


}