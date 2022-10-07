package Rheamer.Storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(produces = "application/json")
public class RestfulController {
    KafkaPhotoUploadService messageQueue;

    @Autowired
    public RestfulController(KafkaPhotoUploadService messageQueue) {
        this.messageQueue = messageQueue;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/uploadPhoto")
    public void uploadFile(@Valid @RequestBody PhotoDto photoDto){
        var photo = DtoMapperPhoto.toPhoto(photoDto);
        messageQueue.sendPhoto(photo);
    }
}
