package com.golovko.gallerysearch.repository;

import com.golovko.gallerysearch.domain.ImageTag;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Sql(statements = {"delete from image_tag",}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ImageTagNameRepositoryTest {

    @Autowired
    private ImageTagRepository imageTagRepository;

    @Test
    public void testFindByTagName() {
        ImageTag tag1 = createTag("snow");
        createTag("summer");
        createTag("bird");

        ImageTag tagFromDB = imageTagRepository.findByTagName("snow");

        Assert.assertEquals(tag1.getId(), tagFromDB.getId());
    }

    private ImageTag createTag(String name) {
        ImageTag tag = new ImageTag();
        tag.setTagName(name);
        return imageTagRepository.save(tag);
    }
}
