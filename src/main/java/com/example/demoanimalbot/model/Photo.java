package com.example.demoanimalbot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.util.Arrays;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Photo {
    @Id
    @GeneratedValue
    private long id;

    private String filePath;
    private long fileSize;
    private String mediaType;

    @Lob
    private byte[] preview;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Photo photo = (Photo) o;
        return id == photo.id && fileSize == photo.fileSize && Objects.equals(filePath, photo.filePath) && Objects.equals(mediaType, photo.mediaType) && Arrays.equals(preview, photo.preview);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, filePath, fileSize, mediaType);
        result = 31 * result + Arrays.hashCode(preview);
        return result;
    }
}
