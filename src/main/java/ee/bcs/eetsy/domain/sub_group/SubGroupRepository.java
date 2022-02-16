package ee.bcs.eetsy.domain.sub_group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubGroupRepository extends JpaRepository<SubGroup, Integer> {
    @Query("select s from SubGroup s where s.primaryGroup.id = ?1")
    List<SubGroup> findSubGroupsByPrimaryGroupId(Integer id);




    @Query("select distinct s from SubGroup s where s.primaryGroup.id = :id")
    List<SubGroup> findDistinctByPrimaryGroup_Id(@Param("id") Integer id);



// //  Oksana : ???? Küsime sub-group läbi item id ????????
//    @Query("select s from SubGroup s where s.item.id = ?1")
//    List<SubGroup> findSubGroupsByItemId(Integer id);
//    @Query("select s from SubGroup s where s.item.id = ?1")
//    SubGroup findSubGroupByItemId(Integer id);





}