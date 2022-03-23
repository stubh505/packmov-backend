package com.deloitte.packmov.daos;

import com.deloitte.packmov.models.documents.Quote;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository class for {@linkplain Quote} requests
 */

public interface QuoteRepository extends MongoRepository<Quote, String> {

    /**
     * Retrieve all {@linkplain Quote} requests
     * @return the List of all {@linkplain Quote}s
     */
    @Override
    List<Quote> findAll();

    /**
     * Add new {@linkplain Quote} request and validate the request body
     * @param entity the {@linkplain Quote}
     * @return the persisted ID
     */
    @Override
    <S extends Quote> S insert(S entity);

    /**
     * Retrieve {@linkplain Quote} request for provided ID
     * @param s the ID
     * @return the {@linkplain Quote}
     */
    @Override
    Optional<Quote> findById(String s);
}
