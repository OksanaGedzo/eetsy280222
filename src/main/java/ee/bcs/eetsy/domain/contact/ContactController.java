package ee.bcs.eetsy.domain.contact;

import ee.bcs.eetsy.domain.RequestResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ContactController {

    @Resource
    ContactService contactService;

    @PutMapping("/change/user/contact/info")
    @Operation(summary="updating user  information")
    public RequestResponse updateContactInfo(@RequestBody ContactDto contactDto) {
        RequestResponse response = contactService.updateContactInfo(contactDto);
        return response;
    }
}
