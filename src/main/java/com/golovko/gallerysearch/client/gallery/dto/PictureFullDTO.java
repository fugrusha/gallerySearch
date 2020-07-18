package com.golovko.gallerysearch.client.gallery.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PictureFullDTO {

    private String id;

    private String author;

    private String camera;

    private String tags;

    @JsonProperty("cropped_picture")
    private String croppedPictureUrl;

    @JsonProperty("full_picture")
    private String fullPictureUrl;

}
