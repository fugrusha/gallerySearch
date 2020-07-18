package com.golovko.gallerysearch.service;

import com.golovko.gallerysearch.dto.PictureReadDTO;
import com.golovko.gallerysearch.repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PictureService {

    @Autowired
    private PictureRepository pictureRepository;

    public List<PictureReadDTO> getPicturesByKeyword(String searchTerm) {
        return null;
    }
}
