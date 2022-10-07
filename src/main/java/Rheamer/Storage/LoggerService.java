package Rheamer.Storage;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;

public class LoggerService {
    RequestRepository requestRepo;

    @Autowired
    public LoggerService(RequestRepository requestRepo) {
        this.requestRepo = requestRepo;
    }

    public void saveRequest(@Valid RequestInfo request){
        requestRepo.save(request);
    }

}
