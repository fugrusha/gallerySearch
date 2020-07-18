package com.golovko.gallerysearch.repository;

import com.golovko.gallerysearch.domain.ImageTag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageTagRepository extends CrudRepository<ImageTag, Long> {

    ImageTag findByTagName(String name);
}
