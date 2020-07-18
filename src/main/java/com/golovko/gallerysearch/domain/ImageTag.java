package com.golovko.gallerysearch.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class ImageTag {

    @Id
    private UUID id;

    private String tag;

    @ManyToMany(mappedBy = "imageTags")
    private List<Picture> pictures = new ArrayList<>();
}
