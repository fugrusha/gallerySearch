package com.golovko.gallerysearch.dto;

import lombok.Data;

@Data
public class PictureReadDTO {

    private Long id;
    private String externalId;
    private String author;
    private String camera;
    private String croppedPictureUrl;
    private String fullPictureUrl;
    private String tags;
}
