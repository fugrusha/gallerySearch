package com.golovko.gallerysearch.controller;

import com.golovko.gallerysearch.dto.PictureReadDTO;
import com.golovko.gallerysearch.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchPictureController {

    @Autowired
    private PictureService pictureService;

    @GetMapping("/{searchTerm}")
    public List<PictureReadDTO> getPicturesByKeyword(@PathVariable String searchTerm) {
        return pictureService.getPicturesByKeyword(searchTerm);
    }
 }
