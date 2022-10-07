package Rheamer.Storage.models.utils;

import Rheamer.Storage.models.Photo;
import Rheamer.Storage.models.PhotoDto;

public class DtoMapperPhoto {
    public static void validate(PhotoDto dto) throws IllegalArgumentException{
        if (dto == null || dto.data() == null || dto.name() == null){
            throw new IllegalArgumentException("Parsing error: photo dto is invalid");
        }
    }

    public static Photo toPhoto(PhotoDto dto){
        return Photo.builder()
                .data(dto.data())
                .name(dto.name())
                .size(dto.data().length)
                .hash(String.valueOf(dto.data().hashCode()))
                .build();
    }
}
