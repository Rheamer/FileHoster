package Rheamer.Storage.controllers;

import Rheamer.Storage.models.utils.DtoMapperPhoto;
import Rheamer.Storage.models.PhotoDto;
import Rheamer.Storage.services.PhotoMessagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(produces = "application/json")
public class FileUploadRestController {
    private final PhotoMessagingService messageQueue;

    @Autowired
    public FileUploadRestController(@Qualifier("Kafka")PhotoMessagingService messageQueue) {
        this.messageQueue = messageQueue;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping(value = "/uploadPhoto",
            consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<?> uploadFile(@Valid @RequestBody PhotoDto photoDto){
        DtoMapperPhoto.validate(photoDto);
        var photo = DtoMapperPhoto.toPhoto(photoDto);
        messageQueue.sendPhoto(photo);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
