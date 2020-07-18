package com.golovko.gallerysearch.client.gallery;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class GalleryConfig {

    @Bean
    public RequestInterceptor authRequestInterceptor(@Value("${gallery.api.key}") String apiKey) {
        return requestTemplate -> requestTemplate.header("Authorization", "Bearer " + apiKey);
    }
}
