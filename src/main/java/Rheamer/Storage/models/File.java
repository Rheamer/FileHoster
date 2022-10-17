package Rheamer.Storage.models;

import lombok.Builder;

@Builder
public class File {
    private final String data;
    private final String name;
    private final long size;
    private final String hash;
}
