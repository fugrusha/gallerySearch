package com.golovko.gallerysearch.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.golovko.gallerysearch.dto.PictureReadDTO;
import com.golovko.gallerysearch.service.PictureService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SearchPictureControllerTest.class)
public class SearchPictureControllerTest {

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    private PictureService pictureService;

    @Test
    public void testGetPicturesByKeyword() throws Exception {
        String keyword = "summer";

        PictureReadDTO readDTO = createPictureReadDTO();
        List<PictureReadDTO> expectedResult = List.of(readDTO);

        Mockito.when(pictureService.getPicturesByKeyword(keyword)).thenReturn(expectedResult);

        String resultJson = mockMvc
                .perform(get("/search/{searchTerm}", keyword))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        List<PictureReadDTO> actualResult = objectMapper.readValue(resultJson, new TypeReference<>() {});
        Assertions.assertThat(actualResult).extracting(PictureReadDTO::getId)
                .containsExactlyInAnyOrder(readDTO.getId());

        Mockito.verify(pictureService).getPicturesByKeyword(keyword);
    }

    private PictureReadDTO createPictureReadDTO() {
        PictureReadDTO dto = new PictureReadDTO();
        dto.setAuthor("author name");
        dto.setCamera("camera name");
        dto.setExternalId("12345");
        dto.setCroppedPictureUrl("url");
        dto.setFullPictureUrl("url");
        dto.setTags("#summer #flower");
        dto.setId(123L);
        return dto;
    }
}
