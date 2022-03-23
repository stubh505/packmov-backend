package com.deloitte.packmov.services;

import com.deloitte.packmov.daos.PhotoRepository;
import com.deloitte.packmov.exceptions.NotFoundException;
import com.deloitte.packmov.models.documents.Photo;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Service
public class PhotoService {

    @Autowired
    private PhotoRepository photoRepo;

    /**
     * Recieves a {@linkplain Photo} from API and persists it.
     * @param title the name of the {@linkplain Photo}
     * @param image the {@linkplain Photo}
     * @return the persisted ID
     * @throws IOException if there was an issue in converting {@linkplain Photo}
     */
    public String addPhoto(String title, MultipartFile image) throws IOException {
        Photo photo = new Photo();
        photo.setTitle(title);
        photo.setType(image.getContentType());
        photo.setImage(new Binary(BsonBinarySubType.BINARY, image.getBytes()));
        return photoRepo.insert(photo).getId();
    }

    /**
     * Retrieves a {@linkplain Photo} from DB
     * @param id the ID of the {@linkplain Photo}
     * @return the {@linkplain Photo}
     */
    public String getPhoto(String id) {
        Photo photo = photoRepo.findById(id).orElseThrow(() -> new NotFoundException("Photo not found."));
        String image = Base64.getEncoder().encodeToString(photo.getImage().getData());
        image = "data:" + photo.getType() + ";base64, " + image;
        return image;
    }

    /**
     * Deletes a {@linkplain Photo} from the DB
     * @param id the {@linkplain Photo} ID
     */
    public void delete(String id) {
        photoRepo.deleteById(id);
    }
}