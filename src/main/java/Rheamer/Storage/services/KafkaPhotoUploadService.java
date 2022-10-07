package Rheamer.Storage.services;

import Rheamer.Storage.models.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Resource(name = "Kafka")
public class KafkaPhotoUploadService implements PhotoMessagingService {
    private KafkaTemplate<String, Photo> kafkaTemplate;

    public KafkaPhotoUploadService(){}

    @Autowired
    public KafkaPhotoUploadService(KafkaTemplate<String, Photo> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Autowired
    public void setTemplate(KafkaTemplate template){
        this.kafkaTemplate = template;
    }

    public void sendPhoto(Photo file) {
        var res = kafkaTemplate.send("rheamer.storage.topic", file);
    }
}