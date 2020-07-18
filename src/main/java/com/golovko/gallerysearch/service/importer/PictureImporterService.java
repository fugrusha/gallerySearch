package com.golovko.gallerysearch.service.importer;

import com.golovko.gallerysearch.client.gallery.GalleryClient;
import com.golovko.gallerysearch.client.gallery.dto.PictureFullDTO;
import com.golovko.gallerysearch.domain.ImageTag;
import com.golovko.gallerysearch.domain.Picture;
import com.golovko.gallerysearch.repository.ImageTagRepository;
import com.golovko.gallerysearch.repository.PictureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class PictureImporterService {

    @Autowired
    private GalleryClient galleryClient;

    @Autowired
    private PictureRepository pictureRepository;

    @Autowired
    private ImageTagRepository imageTagRepository;

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
        picture.setImageTags(getListOfTags(dto.getTags()));
        return picture;
    }

    private List<ImageTag> getListOfTags(String tagString) {
        List<ImageTag> tagList = new ArrayList<>();

        String[] array = tagString.split(" ");

        for (String tag : array) {
            ImageTag existedTag = imageTagRepository.findByTagName(tag);

            if (existedTag == null) {
                ImageTag newTag = createTag(tag);
                tagList.add(newTag);
            } else {
                tagList.add(existedTag);
            }
        }
        return tagList;
    }

    private ImageTag createTag(String tagName) {
        ImageTag tag = new ImageTag();
        tag.setTagName(tagName);

        log.info("Tag with name {} was created", tagName);
        return imageTagRepository.save(tag);
    }
}
