package com.golovko.gallerysearch.client.gallery;

import com.golovko.gallerysearch.client.gallery.dto.PictureFullDTO;
import com.golovko.gallerysearch.client.gallery.dto.PicturesPageDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "gallery.api", url = "${gallery.api.url}", configuration = GalleryConfig.class)
public interface GalleryClient {

    @RequestMapping(method = RequestMethod.GET, value = "/images")
    PicturesPageDTO getPicturesPage(@RequestParam(defaultValue = "1") String page);

    @RequestMapping(method = RequestMethod.GET, value = "/images/{id}")
    PictureFullDTO getPictureById(@PathVariable("id") String pictureId);
}
