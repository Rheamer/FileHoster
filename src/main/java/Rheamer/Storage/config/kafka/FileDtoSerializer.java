package Rheamer.Storage.config.kafka;

import Rheamer.Storage.models.FileDto;
import Rheamer.Storage.models.utils.FileDtoJsonConverter;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class FileDtoSerializer implements Serializer<FileDto> {

    private final String encoding = StandardCharsets.UTF_8.name();
    private final FileDtoJsonConverter fileDtoJsonConverter;

    @Autowired
    public FileDtoSerializer(FileDtoJsonConverter fileDtoJsonConverter) {
        this.fileDtoJsonConverter = fileDtoJsonConverter;
    }

    @Override
    public byte[] serialize(String topic, FileDto data) {
        if (data == null)
            return null;
        var json = fileDtoJsonConverter.fileToJson(data);
        if (json.isEmpty())
            return null;
        return getJsonPayload(json);
    }

    private byte[] getJsonPayload(Optional<String> json) {
        byte[] jsonPayload;
        try {
            jsonPayload = json.get().getBytes(encoding);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
        return jsonPayload;
    }

}
