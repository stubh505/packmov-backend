package com.deloitte.packmov.services;

import com.deloitte.packmov.daos.QuoteRepository;
import com.deloitte.packmov.exceptions.NotFoundException;
import com.deloitte.packmov.models.documents.Quote;
import com.deloitte.packmov.models.dtos.QuoteDTO;
import com.deloitte.packmov.models.converters.QuoteConverter;
import com.deloitte.packmov.utilities.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class for retrieving and adding {@linkplain Quote}s
 */
@Service
public class QuoteService {
    @Autowired
    private QuoteRepository quoteRepository;

    /**
     * Retrieve {@linkplain Quote} request for provided ID
     * @param id the ID
     * @return the {@linkplain QuoteDTO}
     * @throws NotFoundException if no item found
     */
    public QuoteDTO getQuote (String id) {
        Quote quote = quoteRepository.findById(id).orElseThrow(() -> new NotFoundException("Quote not found"));
        return QuoteConverter.convertToDto(quote);
    }

    /**
     * Add new {@linkplain Quote} request and validate the request body
     * @param dto the {@linkplain QuoteDTO}
     * @return the persisted ID
     */
    public String addQuote (QuoteDTO dto) {
        Quote quote = QuoteConverter.convertToDoc(dto);
        Validator.validate(quote);
        return quoteRepository.insert(quote).getId();
    }

    /**
     * Retrieve all {@linkplain Quote} requests
     * @return the List of all {@linkplain Quote}s
     * @throws NotFoundException if no item found
     */
    public List<QuoteDTO> getAllQuotes () {
        List<Quote> result = quoteRepository.findAll();
        if (result == null || result.isEmpty()) {
            throw new NotFoundException("Quote not found");
        }

        List<QuoteDTO> quotes = new ArrayList<>(result.size());
        for (Quote quote : result) {
            quotes.add(QuoteConverter.convertToDto(quote));
        }
        return quotes;
    }
}
