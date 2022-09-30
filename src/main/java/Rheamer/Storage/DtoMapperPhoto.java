package Rheamer.Storage;

public class DtoMapperPhoto {
    public static void validate(PhotoDto dto) throws IllegalArgumentException{
        if (dto == null || dto.getData() == null || dto.getName() == null){
            throw new IllegalArgumentException("Parsing error: photo dto is invalid");
        }
    }

    public static Photo toPhoto(PhotoDto dto){
        return Photo.builder()
                .data(dto.getData())
                .name(dto.getName())
                .size(dto.getData().length)
                .hash(String.valueOf(dto.getData().hashCode()))
                .build();
    }
}
