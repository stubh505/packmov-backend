package com.deloitte.packmov.api;

import com.deloitte.packmov.models.documents.Quote;
import com.deloitte.packmov.models.dtos.QuoteDTO;
import com.deloitte.packmov.services.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * Controller class for {@linkplain QuoteDTO} requests
 */
@CrossOrigin
@RestController
@RequestMapping("/quote")
public class QuoteApi {
    @Autowired
    private QuoteService quoteService;

    /**
     * Retrieve {@linkplain QuoteDTO} request for provided ID
     * @param id the ID
     * @return the {@linkplain QuoteDTO}
     */
    @GetMapping("/{id}")
    public ResponseEntity<QuoteDTO> getQuote(@PathVariable String id) {
        return new ResponseEntity<>(quoteService.getQuote(id), HttpStatus.OK);
    }

    /**
     * Add new {@linkplain QuoteDTO} request
     * @param quote the {@linkplain QuoteDTO}
     * @return the persisted ID
     */
    @PostMapping("/")
    public ResponseEntity<char[]> addQuote(@RequestBody QuoteDTO quote) {
        return new ResponseEntity<>(quoteService.addQuote(quote).toCharArray(), HttpStatus.OK);
    }

    /**
     * Retrieve all {@linkplain QuoteDTO} requests
     * @return the List of all {@linkplain QuoteDTO}s
     */
    @GetMapping("/")
    public ResponseEntity<List<QuoteDTO>> getAllQuotes() {
        return new ResponseEntity<>(quoteService.getAllQuotes(), HttpStatus.OK);
    }

    /**
     * Retrieve all available {@linkplain Quote.Location locations}
     * @return the List of all {@linkplain QuoteDTO}s
     */
    @GetMapping("/locations")
    public ResponseEntity<List<Quote.Location>> getAllLocations() {
        return new ResponseEntity<>(Arrays.asList(Quote.Location.values()), HttpStatus.OK);
    }
}
