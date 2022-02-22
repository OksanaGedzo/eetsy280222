package ee.bcs.eetsy.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer> {


    @Query("select u from User u where upper(u.userName) = upper(?1) and u.password = ?2")
    Optional<User> findByUserNameAndPassword(String userName, String password);


    @Query("select u from User u where u.id = ?1")
    Optional<User> findAllUsersById(Integer integer);

    @Query("select (count(u) > 0) from User u where upper(u.userName) = upper(?1)")
    boolean existsByUserName(String userName);


}