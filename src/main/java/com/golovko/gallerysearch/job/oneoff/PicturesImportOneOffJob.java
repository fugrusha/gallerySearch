package com.golovko.gallerysearch.job.oneoff;

import com.golovko.gallerysearch.client.gallery.GalleryClient;
import com.golovko.gallerysearch.client.gallery.dto.PictureShortDTO;
import com.golovko.gallerysearch.client.gallery.dto.PicturesPageDTO;
import com.golovko.gallerysearch.service.AsyncService;
import com.golovko.gallerysearch.service.importer.PictureImporterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class PicturesImportOneOffJob {

    @Autowired
    private PictureImporterService importerService;

    @Autowired
    private GalleryClient galleryClient;

    @Autowired
    private AsyncService asyncService;

    @Value("${gallery.import.job.enabled}")
    private boolean enabled;

    @PostConstruct
    void executeJob() {
        if (!enabled) {
            log.info("Import is disabled");
            return;
        }

        asyncService.executeAsync(this::doImport);
    }

    public void doImport() {
        log.info("Starting import ...");

        try {
            PicturesPageDTO firstPage = galleryClient.getPicturesPage(1);
            int pageCount = firstPage.getPageCount();

            int successfullyImported = 0;
            int failed = 0;
            int totalPictures = 0;

            for (int i = 2; i <= pageCount; i++) {
                log.info("Import from page {} started", i);

                PicturesPageDTO page = galleryClient.getPicturesPage(i);
                totalPictures += page.getPictures().size();

                for (PictureShortDTO dto : firstPage.getPictures()) {
                    try {
                        importerService.importPicture(dto.getId());
                        successfullyImported++;
                    } catch (Exception ex) {
                        log.info("Failed to import picture id={}: {}", dto.getId(), ex.getMessage());
                        failed++;
                    }
                }
                log.info("Pictures from page {} to import: {}",
                        i, page.getPictures().size());
            }

            log.info("Total pictures to import: {}, successfully imported: {}, failed: {}",
                    totalPictures, successfullyImported, failed);

        } catch (Exception ex) {
            log.warn("Failed to perform import", ex);
        }

        log.info("Import finished");
    }
}
