package Rheamer.Storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaPhotoUploadService implements PhotoBrokerService {
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
        kafkaTemplate.send("rheamer.storage.topic", file);
    }
}