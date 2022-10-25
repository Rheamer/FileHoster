package Rheamer.Storage.services;

import Rheamer.Storage.models.FileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service("Kafka")
public class KafkaFileUploadService implements FileMessagingService {
    private KafkaTemplate<String, FileDto> kafkaTemplate;

    public KafkaFileUploadService(){}

    @Autowired
    public KafkaFileUploadService(KafkaTemplate<String, FileDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Autowired
    public void setTemplate(KafkaTemplate<String, FileDto> template){
        this.kafkaTemplate = template;
    }

    public void sendFile(FileDto file) {
        ListenableFuture<SendResult<String, FileDto>> res =
                kafkaTemplate.send("rheamer.storage.topic", file);
        res.addCallback(new ListenableFutureCallback<SendResult<String, FileDto>>() {
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send file due to : " + ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, FileDto> result) {
                System.out.println("Sent message file with offset=[" + result.getRecordMetadata().offset() + "]");
            }
        });
    }
}