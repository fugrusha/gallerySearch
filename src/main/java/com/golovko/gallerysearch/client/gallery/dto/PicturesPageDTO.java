package com.golovko.gallerysearch.client.gallery.dto;

import lombok.Data;

import java.util.List;

@Data
public class PicturesPageDTO {

    private Integer page;
    private Integer pageCount;
    private Boolean hasMore;
    private List<PictureShortDTO> pictures;
}
