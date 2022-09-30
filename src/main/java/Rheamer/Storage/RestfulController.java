package Rheamer.Storage;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(produces = "application/json")
public class RestfulController {

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/uploadPhoto/{userId}")
    public void uploadFile(@PathVariable("userId") Long id, @RequestBody PhotoDto photoDto){
        DtoMapperPhoto.validate(photoDto);
        var photo = DtoMapperPhoto.toPhoto(photoDto);
    }
}
