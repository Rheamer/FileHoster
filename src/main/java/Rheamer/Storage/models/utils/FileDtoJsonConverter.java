package Rheamer.Storage.models.utils;

import Rheamer.Storage.models.FileDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class FileDtoJsonConverter {

    private final ObjectMapper objectMapper;

    @Autowired
    public FileDtoJsonConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

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
