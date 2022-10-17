package Rheamer.Storage.models.utils;

import Rheamer.Storage.models.FileDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FileDtoJsonConverter {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Optional<String> fileToJson(FileDto file) {
        try {
            String fileJson = objectMapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(file);
            return Optional.of(fileJson);
        } catch (JsonProcessingException ignored) {}
        return Optional.empty();
    };

}
