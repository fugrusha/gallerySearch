package com.golovko.gallerysearch.service.importer;

import com.golovko.gallerysearch.client.gallery.GalleryClient;
import com.golovko.gallerysearch.client.gallery.dto.PictureFullDTO;
import com.golovko.gallerysearch.domain.Picture;
import com.golovko.gallerysearch.repository.PictureRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Sql(statements = {
        "delete from picture",
        "delete from image_tag",},
        executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class PictureImporterServiceTest {

    @MockBean
    private GalleryClient galleryClient;

    @Autowired
    private PictureImporterService importerService;

    @Autowired
    private PictureRepository pictureRepository;

    @Test
    public void testImportPicture() {
        String externalId = "d329241ef93500ba67d1";

        PictureFullDTO pictureDTO = createPictureFullDTO(externalId);
        Mockito.when(galleryClient.getPictureById(externalId)).thenReturn(pictureDTO);

        Long pictureId = importerService.importPicture(externalId);

        Picture picture = pictureRepository.findById(pictureId).get();

        Assert.assertEquals(pictureDTO.getAuthor(), picture.getAuthor());
        Assert.assertEquals(pictureDTO.getId(), picture.getExternalId());
        Assert.assertEquals(3, picture.getImageTags().size());
    }

    private PictureFullDTO createPictureFullDTO(String id) {
        PictureFullDTO dto = new PictureFullDTO();
        dto.setId(id);
        dto.setAuthor("name surname");
        dto.setCamera("camera name");
        dto.setCroppedPictureUrl("some url");
        dto.setFullPictureUrl("some other url");
        dto.setTags("#nature #life #view");
        return dto;
    }
}
