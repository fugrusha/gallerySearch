package com.golovko.gallerysearch.client.gallery.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PictureShortDTO {

    private String id;

    @JsonProperty("cropped_picture")
    private String croppedPictureUrl;
}
