package Rheamer.Storage.services;

import Rheamer.Storage.models.RequestInfo;
import Rheamer.Storage.repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import java.util.List;

public class LoggerService {
    RequestRepository requestRepo;

    @Autowired
    public LoggerService(RequestRepository requestRepo) {
        this.requestRepo = requestRepo;
    }

    public void saveRequest(@Valid RequestInfo request){
        requestRepo.save(request);
    }

    public List<RequestInfo> findRequests(String requestUri) {
        return requestRepo.findAllByRequestUri(requestUri);
    }
}
