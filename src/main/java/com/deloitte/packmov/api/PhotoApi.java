package com.deloitte.packmov.api;

import com.deloitte.packmov.models.documents.Photo;
import com.deloitte.packmov.services.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("/photos")
public class PhotoApi {
    @Autowired
    private PhotoService photoService;

    /**
     * REST API controller for recieving a {@linkplain Photo} and persists it.
     * @param title the name of the {@linkplain Photo}
     * @param image the {@linkplain Photo}
     * @return the persisted ID
     * @throws IOException if there was an issue in converting {@linkplain Photo}
     */
    @PostMapping("/")
    public ResponseEntity<char[]> addPhoto(@RequestParam String title, @RequestParam MultipartFile image)
            throws IOException {
        return new ResponseEntity<>(photoService.addPhoto(title, image).toCharArray(), HttpStatus.OK);
    }

    /**
     * REST API controller for retrieving a {@linkplain Photo} from DB.
     * @param id the ID of the {@linkplain Photo}
     * @return the persisted {@linkplain Photo}
     */
    @GetMapping("/{id}")
    public ResponseEntity<char[]> getPhoto(@PathVariable String id) {
        return new ResponseEntity<>(photoService.getPhoto(id).toCharArray(), HttpStatus.OK);
    }

    /**
     * REST API controller for deleting a {@linkplain Photo} from DB.
     * @param id the ID of the {@linkplain Photo}
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        photoService.delete(id);
    }
}
