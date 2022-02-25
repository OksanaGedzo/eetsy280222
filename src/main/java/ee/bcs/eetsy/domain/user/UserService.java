package ee.bcs.eetsy.domain.user;

import ee.bcs.eetsy.domain.RequestResponse;
import ee.bcs.eetsy.domain.contact.Contact;
import ee.bcs.eetsy.domain.contact.ContactDto;
import ee.bcs.eetsy.domain.contact.ContactRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Resource
    UserRepository userRepository;

    @Resource
    ContactRepository contactRepository;

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

    public RequestResponse addNewUser(UserDto userDto) {
        RequestResponse response = new RequestResponse();
        Contact contact = userDto.getContact();
        contactRepository.save(contact);
        User user = userMapper.userDtoToUser(userDto);
        userRepository.save(user);
        response.setMessage("user created");
        return response;
    }

    public UserDto findUserInfoById(Integer id) {
        User user = userRepository.findById(id).get();
        UserDto userDto = userMapper.userToUserDto(user);
        return userDto;

    }


}