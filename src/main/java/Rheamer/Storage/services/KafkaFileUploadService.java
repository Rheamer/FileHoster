package Rheamer.Storage.services;

import Rheamer.Storage.models.File;
import Rheamer.Storage.models.FileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("Kafka")
public class KafkaFileUploadService implements FileMessagingService {
    private KafkaTemplate<String, FileDto> kafkaTemplate;

    public KafkaFileUploadService(){}

    @Autowired
    public KafkaFileUploadService(KafkaTemplate<String, FileDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Autowired
    public void setTemplate(KafkaTemplate template){
        this.kafkaTemplate = template;
    }

    public void sendFile(FileDto file) {
        var res = kafkaTemplate.send("rheamer.storage.topic", file);
    }
}