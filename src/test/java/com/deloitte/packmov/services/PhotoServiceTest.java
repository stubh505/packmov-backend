package com.deloitte.packmov.services;

import com.deloitte.packmov.daos.PhotoRepository;
import com.deloitte.packmov.exceptions.NotFoundException;
import com.deloitte.packmov.models.documents.Photo;
import org.bson.types.Binary;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class PhotoServiceTest {

    @Mock
    private PhotoRepository photoRepository;

    @InjectMocks
    private PhotoService photoService = new PhotoService();

    @Test
    void testGetPhotoValid() {
        Photo photo = new Photo();
        photo.setImage(new Binary("Sample Data".getBytes()));
        Mockito.when(photoRepository.findById(Mockito.anyString())).thenReturn(Optional.of(photo));
        String res = photoService.getPhoto("ID");
        Assertions.assertNotNull(res);
        System.out.println(res);
        Assertions.assertTrue(res.startsWith("data:"));
    }

    @Test
    void testGetPhotoInValid() {
        Mockito.when(photoRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());
        Assertions.assertNotNull(NotFoundException.class, () -> photoService.getPhoto("id"));
    }
}
