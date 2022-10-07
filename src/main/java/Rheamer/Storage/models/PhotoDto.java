package Rheamer.Storage.models;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Builder
public record PhotoDto(
        @NotBlank String name,
        @NotBlank byte[] data) {
}
