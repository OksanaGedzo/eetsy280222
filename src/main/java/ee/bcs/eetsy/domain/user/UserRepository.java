package ee.bcs.eetsy.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select u from User u where upper(u.userName) = upper(?1) and u.password = ?2")
    Optional<User> findByUserNameAndPassword(String userName, String password);



}