package Rheamer.Storage.models;

import lombok.Builder;

import javax.validation.constraints.NotBlank;

@Builder
public record FileDto(
        @NotBlank String name,
        @NotBlank byte[] data) {
}
