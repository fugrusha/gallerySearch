package com.golovko.gallerysearch.job.oneoff;

import com.golovko.gallerysearch.client.gallery.GalleryClient;
import com.golovko.gallerysearch.client.gallery.dto.PictureShortDTO;
import com.golovko.gallerysearch.client.gallery.dto.PicturesPageDTO;
import com.golovko.gallerysearch.service.importer.PictureImporterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(statements = {
        "delete from picture",
        "delete from image_tag",},
        executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class PictureImportOneOffJobTest {

    @Autowired
    private PicturesImportOneOffJob job;

    @MockBean
    private GalleryClient galleryClient;

    @MockBean
    private PictureImporterService importerService;

    @Test
    public void testDoImport() {
        PicturesPageDTO pageDTO = createPicturePageDTO();

        Mockito.when(galleryClient.getPicturesPage(1)).thenReturn(pageDTO);

        job.doImport();

        for (PictureShortDTO dto : pageDTO.getPictures()) {
            Mockito.verify(importerService).importPicture(dto.getId());
        }
    }

    private PicturesPageDTO createPicturePageDTO() {
        PicturesPageDTO dto = new PicturesPageDTO();
        dto.setHasMore(false);
        dto.setPage(1);
        dto.setPageCount(1);
        dto.setPictures(List.of(createShortDTO(), createShortDTO()));
        return dto;
    }

    private PictureShortDTO createShortDTO() {
        PictureShortDTO dto = new PictureShortDTO();
        dto.setId("123");
        dto.setCroppedPictureUrl("some url");
        return dto;
    }
}
