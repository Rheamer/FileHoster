package Rheamer.Storage;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PhotoDto {
    private final String name;
    private final byte[] data;
}
