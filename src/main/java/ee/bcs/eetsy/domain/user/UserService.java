package ee.bcs.eetsy.domain.user;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Resource
    UserRepository userRepository;

    @Resource
    UserMapper userMapper;

    public Integer findUserIdByUsernameAndPassword(String username, String password) {
        Optional<User> user = userRepository.findByUserNameAndPassword(username, password);
        int id = user.get().getId();
        return id;
    }

    public Boolean checkIfUserExists(String username) {
        return userRepository.existsByUserName(username);
    }
}