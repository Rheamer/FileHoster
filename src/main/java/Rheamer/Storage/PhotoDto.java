package Rheamer.Storage;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class PhotoDto {
    @NotBlank
    private final String name;
    @NotBlank
    private final byte[] data;
}
