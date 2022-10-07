package Rheamer.Storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(produces = "application/json")
public class RestfulController {
    private final PhotoMessagingService messageQueue;

    @Autowired
    public RestfulController(@Qualifier("Kafka")PhotoMessagingService messageQueue) {
        this.messageQueue = messageQueue;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/uploadPhoto")
    public ResponseEntity<?> uploadFile(@Valid @RequestBody PhotoDto photoDto){
        DtoMapperPhoto.validate(photoDto);
        var photo = DtoMapperPhoto.toPhoto(photoDto);
        messageQueue.sendPhoto(photo);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
