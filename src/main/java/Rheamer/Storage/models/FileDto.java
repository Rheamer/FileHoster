package Rheamer.Storage.models;

import lombok.Builder;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Builder
public final class FileDto {
    private final @NotBlank String name;
    private final @NotBlank String data;

    public FileDto(
            @NotBlank String name,
            @NotBlank String data) {
        this.name = name;
        this.data = data;
    }

    public @NotBlank String name() {
        return name;
    }

    public @NotBlank String data() {
        return data;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (FileDto) obj;
        return Objects.equals(this.name, that.name) &&
                Objects.equals(this.data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, data);
    }

    @Override
    public String toString() {
        return "FileDto[" +
                "name=" + name + ", " +
                "data=" + data + ']';
    }

}
