package com.deloitte.packmov.services;

import com.deloitte.packmov.daos.QuoteRepository;
import com.deloitte.packmov.exceptions.InvalidQuoteException;
import com.deloitte.packmov.exceptions.NotFoundException;
import com.deloitte.packmov.models.documents.Quote;
import com.deloitte.packmov.models.dtos.QuoteDTO;
import com.deloitte.packmov.utilities.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Stream;

@SpringBootTest
class QuoteServiceTest {

    @Mock
    private QuoteRepository quoteRepository;

    @InjectMocks
    QuoteService quoteService = new QuoteService();

    private static final String VALID_NAME = "Sample Name";
    private static final String VALID_CONTACT = "7894561230";
    private static final String VALID_EMAIL = "samp.le@ema.il";
    private static final LocalDate VALID_DATE = LocalDate.now().plusDays(5);

    @Test
    void testGetQuoteValid() {
        Quote quote = new Quote();
        Mockito.when(quoteRepository.findById(Mockito.anyString())).thenReturn(Optional.of(quote));
        Assertions.assertNotNull(quoteService.getQuote("id"));
    }

    @Test
    void testGetQuoteThatDoesNotExist() {
        Mockito.when(quoteRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> quoteService.getQuote("id"));
    }

    @Test
    void testGetAllQuotesValid() {
        Quote quote = new Quote();
        Mockito.when(quoteRepository.findAll()).thenReturn(Collections.singletonList(quote));
        Assertions.assertNotNull(quoteService.getAllQuotes());
    }

    @Test
    void testGetAllQuotesThatDoesNotExist() {
        Mockito.when(quoteRepository.findAll()).thenReturn(Collections.emptyList());
        Assertions.assertThrows(NotFoundException.class, () -> quoteService.getAllQuotes());
    }

    @ParameterizedTest
    @MethodSource("createValidTests")
    void testAddQuoteValid(QuoteDTO dto) {
        Quote quote = new Quote();
        quote.setId("ID");
        Mockito.when(quoteRepository.insert(Mockito.any(Quote.class))).thenReturn(quote);
        Assertions.assertNotNull(quoteService.addQuote(dto));
    }

    @ParameterizedTest
    @MethodSource("createInvalidTests")
    void testAddQuoteInvalid(QuoteDTO dto, String message) {
        Exception e = Assertions.assertThrows(InvalidQuoteException.class, () -> quoteService.addQuote(dto));
        Assertions.assertEquals("Invalid Quote : " + message, e.getMessage());
    }

    private static Stream<Arguments> createValidTests() {
        return Stream.of(
                Arguments.of(createQuote(VALID_NAME, VALID_CONTACT, VALID_EMAIL, VALID_DATE)),
                Arguments.of(createQuote(VALID_NAME, "7894561230", VALID_EMAIL, VALID_DATE)),
                Arguments.of(createQuote(VALID_NAME, "9875416123", VALID_EMAIL, VALID_DATE)),
                Arguments.of(createQuote(VALID_NAME, VALID_CONTACT, "sample@email.com", VALID_DATE)),
                Arguments.of(createQuote(VALID_NAME, VALID_CONTACT, "sam.ple@ema.il.co", VALID_DATE)),
                Arguments.of(createQuote(VALID_NAME, VALID_CONTACT, null, VALID_DATE)),
                Arguments.of(createQuote(VALID_NAME, VALID_CONTACT, "", VALID_DATE)),
                Arguments.of(createQuote(VALID_NAME, VALID_CONTACT, VALID_EMAIL, LocalDate.now().plusMonths(1))),
                Arguments.of(createQuote(VALID_NAME, VALID_CONTACT, VALID_EMAIL, LocalDate.now().plusMonths(12).minusDays(1)))
        );
    }

    private static Stream<Arguments> createInvalidTests() {
        return Stream.of(
                Arguments.of(createQuote("", VALID_CONTACT, VALID_EMAIL, VALID_DATE), Validator.INVALID_NAME),
                Arguments.of(createQuote(null, VALID_CONTACT, VALID_EMAIL, VALID_DATE), Validator.INVALID_NAME),
                Arguments.of(createQuote(VALID_NAME, "1230456789", VALID_EMAIL, VALID_DATE), Validator.INVALID_CONTACT),
                Arguments.of(createQuote(VALID_NAME, "12304567", VALID_EMAIL, VALID_DATE), Validator.INVALID_CONTACT),
                Arguments.of(createQuote(VALID_NAME, "", VALID_EMAIL, VALID_DATE), Validator.INVALID_CONTACT),
                Arguments.of(createQuote(VALID_NAME, null, VALID_EMAIL, VALID_DATE), Validator.INVALID_CONTACT),
                Arguments.of(createQuote(VALID_NAME, VALID_CONTACT, "sample.email", VALID_DATE), Validator.INVALID_EMAIL),
                Arguments.of(createQuote(VALID_NAME, VALID_CONTACT, VALID_EMAIL, LocalDate.MAX), Validator.INVALID_DATE),
                Arguments.of(createQuote(VALID_NAME, VALID_CONTACT, VALID_EMAIL, LocalDate.now().plusYears(1).plusDays(10)), Validator.INVALID_DATE),
                Arguments.of(createQuote(VALID_NAME, VALID_CONTACT, VALID_EMAIL, LocalDate.MIN), Validator.INVALID_DATE),
                Arguments.of(createQuote(VALID_NAME, VALID_CONTACT, VALID_EMAIL, LocalDate.now()), Validator.INVALID_DATE),
                Arguments.of(createQuote(VALID_NAME, VALID_CONTACT, VALID_EMAIL, LocalDate.now().minusDays(1)), Validator.INVALID_DATE)
        );
    }

    private static QuoteDTO createQuote(String name, String contact, String email, LocalDate date) {
        QuoteDTO dto = new QuoteDTO();
        dto.setName(name);
        dto.setContact(contact);
        dto.setEmail(email);
        dto.setOn(date);
        return dto;
    }
}
