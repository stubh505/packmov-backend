package com.deloitte.packmov.daos;

import com.deloitte.packmov.models.documents.Photo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PhotoRepository extends MongoRepository<Photo, String> {

    /**
     * Insert a new {@linkplain Photo} into the database.
     * @param entity the {@linkplain Photo}
     * @return the persisted {@linkplain Photo}
     */
    @Override
    <S extends Photo> S insert(S entity);

    /**
     * Find a {@linkplain Photo} by ID.
     * @param s the ID
     * @return the Optional of the {@linkplain Photo}
     */
    @Override
    Optional<Photo> findById(String s);

    /**
     * Delete a {@linkplain Photo} by ID.
     * @param s the ID
     */
    @Override
    void deleteById(String s);
}
