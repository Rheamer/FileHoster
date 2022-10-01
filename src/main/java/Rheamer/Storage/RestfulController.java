package Rheamer.Storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(produces = "application/json")
public class RestfulController {
    @Autowired
    KafkaPhotoUploadService messageQueue;

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/uploadPhoto")
    public void uploadFile(@RequestBody PhotoDto photoDto){
        DtoMapperPhoto.validate(photoDto);
        var photo = DtoMapperPhoto.toPhoto(photoDto);
        messageQueue.sendPhoto(photo);
    }
}
