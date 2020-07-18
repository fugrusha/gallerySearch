package com.golovko.gallerysearch.client.gallery;

import com.golovko.gallerysearch.client.gallery.dto.PictureShortDTO;
import com.golovko.gallerysearch.client.gallery.dto.PicturesPageDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GalleryClientTest {

    @Autowired
    private GalleryClient galleryClient;

    @Test
    public void testGetPicturesPage() {
        PicturesPageDTO pageDTO = galleryClient.getPicturesPage("1");

        Assert.assertEquals(1, pageDTO.getPage().intValue());
        Assert.assertTrue(pageDTO.getPageCount() > 0);
        Assert.assertTrue(pageDTO.getHasMore());

        for (PictureShortDTO dto : pageDTO.getPictures()) {
            Assert.assertNotNull(dto.getId());
            Assert.assertNotNull(dto.getCroppedPictureUrl());
        }
    }

}