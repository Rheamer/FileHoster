package Rheamer.Storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(produces = "application/json")
public class RestfulController {

    PhotoBrokerService messageQueue;

    @Autowired
    public RestfulController(){
        messageQueue = new KafkaPhotoUploadService();
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/uploadPhoto/{userId}")
    public void uploadFile(@PathVariable("userId") Long id, @RequestBody PhotoDto photoDto){
        DtoMapperPhoto.validate(photoDto);
        var photo = DtoMapperPhoto.toPhoto(photoDto);
        messageQueue.sendPhoto(photo);
    }
}
