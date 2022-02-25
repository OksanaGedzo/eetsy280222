package ee.bcs.eetsy.domain.contact;

import ee.bcs.eetsy.domain.RequestResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ContactService {

    @Resource
    ContactRepository contactRepository;

    @Resource
    ContactMapper contactMapper;


    public RequestResponse updateContactInfo(ContactDto contactDto) {
        RequestResponse response = new RequestResponse();
        Contact contact = contactMapper.contactDtoToContact(contactDto);
        contactRepository.save(contact);
        response.setMessage("contact updated");
        return response;
    }
}
