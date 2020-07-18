package com.golovko.gallerysearch.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String externalId;

    private String author;

    private String camera;

    private String croppedPictureUrl;

    private String fullPictureUrl;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "picture_tag",
            joinColumns = @JoinColumn(name = "picture_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<ImageTag> imageTags = new ArrayList<>();
}
