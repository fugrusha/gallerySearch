package com.golovko.gallerysearch.repository;

import com.golovko.gallerysearch.domain.Picture;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PictureRepository extends CrudRepository<Picture, Long> {
}
