package Rheamer.Storage.models.utils;

import Rheamer.Storage.models.File;
import Rheamer.Storage.models.FileDto;

import java.util.Arrays;

public class DtoMapperFile {
    public static void validate(FileDto dto) throws IllegalArgumentException{
        if (dto == null || dto.data() == null || dto.name() == null){
            throw new IllegalArgumentException("Parsing error: file dto is invalid");
        }
    }

    public static File toFile(FileDto dto){
        return File.builder()
                .data(dto.data())
                .name(dto.name())
                .size(dto.data().length())
                .hash(String.valueOf(dto.data().hashCode()))
                .build();
    }
}
