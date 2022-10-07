package Rheamer.Storage.controllers;

import Rheamer.Storage.models.utils.DtoMapperFile;
import Rheamer.Storage.models.FileDto;
import Rheamer.Storage.services.FileMessagingService;
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
    private final FileMessagingService messageQueue;

    @Autowired
    public FileUploadRestController(@Qualifier("Kafka") FileMessagingService messageQueue) {
        this.messageQueue = messageQueue;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping(value = "/uploadPhoto",
            consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<?> uploadFile(@Valid @RequestBody FileDto fileDto){
        DtoMapperFile.validate(fileDto);
        messageQueue.sendFile(fileDto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
