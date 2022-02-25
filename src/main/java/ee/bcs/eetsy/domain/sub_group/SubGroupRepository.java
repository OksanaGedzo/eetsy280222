package ee.bcs.eetsy.domain.sub_group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SubGroupRepository extends JpaRepository<SubGroup, Integer> {
    @Query("select s from SubGroup s where s.primaryGroup.id = ?1")
    List<SubGroup> findSubGroupsByPrimaryGroupId(Integer id);


    @Query("select distinct s from SubGroup s where s.primaryGroup.id = :id")
    List<SubGroup> findDistinctByPrimaryGroup_Id(@Param("id") Integer id);

    @Query("select s from SubGroup s where upper(s.name) = upper(:name)")
    List<SubGroup> findListByNameIgnoreCase(@Param("name") String name);

    @Query("select s from SubGroup s where upper(s.name) = upper(:name) order by s.id DESC")
    List<SubGroup> findFirstByName(@Param("name") String name);












//
//    @Query("select s from SubGroup s where s.primaryGroup.id = :id")
//    SubGroup findByPrimaryGroupId(@Param("id") Integer id);











}