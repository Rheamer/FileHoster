package Rheamer.Storage.models;

import lombok.Builder;

@Builder
public class Photo {
    private final byte[] data;
    private final String name;
    private final long size;
    private final String hash;
}
