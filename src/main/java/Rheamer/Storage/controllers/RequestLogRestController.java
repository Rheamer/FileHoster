package Rheamer.Storage.controllers;

import Rheamer.Storage.models.RequestInfo;
import Rheamer.Storage.services.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RequestLogRestController {

    private final LoggerService loggerService;

    @Autowired
    public RequestLogRestController(LoggerService loggerService) {
        this.loggerService = loggerService;
    }

    @PostMapping(value = "/logs",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getLogs(@RequestParam String requestUri){
        var requests = loggerService.findRequests(requestUri);
        if (requests == null || requests.size() == 0){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }

}
