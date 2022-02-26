package ee.bcs.eetsy.domain.picture.item_picture;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ItemPictureRepository extends JpaRepository<ItemPicture, Integer> {
    @Query("select i from ItemPicture i where i.item.id = :id")
    List<ItemPicture> findByItemId(@Param("id") Integer id);

// No need to delete this separately because upon Picture deletion it will cascade into ItemPicture
//    @Transactional
//    @Modifying
//    @Query("delete from ItemPicture i where i.picture.id = :id")
//    int deleteByPictureId(@Param("id") Integer id);






}