package com.golovko.gallerysearch.service.importer;

import com.golovko.gallerysearch.client.gallery.GalleryClient;
import com.golovko.gallerysearch.client.gallery.dto.PictureFullDTO;
import com.golovko.gallerysearch.domain.Picture;
import com.golovko.gallerysearch.repository.PictureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class PictureImporterService {

    @Autowired
    private GalleryClient galleryClient;

    @Autowired
    private PictureRepository pictureRepository;

    public Long importPicture(String pictureExternalId) {
        log.info("Importing picture with external id={}", pictureExternalId);

        PictureFullDTO pictureDTO = galleryClient.getPictureById(pictureExternalId);

        Picture picture = createPicture(pictureDTO);

        pictureRepository.save(picture);

        log.info("Picture with external id={} imported", pictureExternalId);
        return picture.getId();
    }

    private Picture createPicture(PictureFullDTO dto) {
        Picture picture = new Picture();
        picture.setAuthor(dto.getAuthor());
        picture.setCamera(dto.getCamera());
        picture.setExternalId(dto.getId());
        picture.setCroppedPictureUrl(dto.getCroppedPictureUrl());
        picture.setFullPictureUrl(dto.getFullPictureUrl());
        return picture;
    }
}
