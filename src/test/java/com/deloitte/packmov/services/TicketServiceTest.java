package com.deloitte.packmov.services;

import com.deloitte.packmov.daos.TicketDao;
import com.deloitte.packmov.exceptions.InvalidTicketException;
import com.deloitte.packmov.exceptions.NotFoundException;
import com.deloitte.packmov.models.documents.Ticket;
import com.deloitte.packmov.models.dtos.TicketDTO;
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

import java.util.Collections;
import java.util.stream.Stream;

@SpringBootTest
class TicketServiceTest {
    
    @Mock
    private TicketDao ticketDao;
    
    @InjectMocks
    private TicketService ticketService = new TicketService();

    private static final String VALID_NAME = "Sample Name";
    private static final String VALID_CONTACT = "7894561230";
    private static final String VALID_EMAIL = "samp.le@ema.il";
    private static final String VALID_DESC = "SAmple Description";
    
    @Test
    void testGetTicketValid() {
        Mockito.when(ticketDao.getTicket(Mockito.anyString())).thenReturn(new Ticket());
        Assertions.assertNotNull(ticketService.getTicket("id"));
    }

    @Test
    void testGetTicketDoesNotExist() {
        Mockito.when(ticketDao.getTicket(Mockito.anyString())).thenReturn(null);
        Assertions.assertThrows(NotFoundException.class, () -> ticketService.getTicket("id"));
    }

    @Test
    void testGetAllTicketsValid() {
        Mockito.when(ticketDao.getAllTickets()).thenReturn(Collections.singletonList(new Ticket()));
        Assertions.assertNotNull(ticketService.getAllTickets());
    }

    @Test
    void testGetAllTicketsDoesNotExist() {
        Mockito.when(ticketDao.getAllTickets()).thenReturn(Collections.emptyList());
        Assertions.assertThrows(NotFoundException.class, () -> ticketService.getAllTickets());
    }

    @Test
    void testAddTicketValid() {
        TicketDTO dto = createTicket(VALID_NAME, VALID_EMAIL, VALID_DESC, VALID_CONTACT);
        Mockito.when(ticketDao.addTicket(Mockito.any(Ticket.class))).thenReturn("id");
        Assertions.assertNotNull(ticketService.addTicket(dto));
    }

    @ParameterizedTest
    @MethodSource("createInvalidTests")
    void testAddTicketInvalid(TicketDTO dto, String message) {
        Exception e = Assertions.assertThrows(InvalidTicketException.class, () -> ticketService.addTicket(dto));
        Assertions.assertEquals("Invalid Ticket : " + message, e.getMessage());
    }

    private static Stream<Arguments> createInvalidTests() {
        return Stream.of(
                Arguments.of(createTicket("", VALID_EMAIL, VALID_DESC, VALID_CONTACT), Validator.INVALID_NAME),
                Arguments.of(createTicket(null, VALID_EMAIL, VALID_DESC, VALID_CONTACT), Validator.INVALID_NAME),
                Arguments.of(createTicket(VALID_NAME, VALID_EMAIL, VALID_DESC, "1230456789"), Validator.INVALID_CONTACT),
                Arguments.of(createTicket(VALID_NAME, VALID_EMAIL, VALID_DESC, "12304567"), Validator.INVALID_CONTACT),
                Arguments.of(createTicket(VALID_NAME, null, VALID_DESC, VALID_CONTACT), Validator.INVALID_EMAIL),
                Arguments.of(createTicket(VALID_NAME, "", VALID_DESC, VALID_CONTACT), Validator.INVALID_EMAIL),
                Arguments.of(createTicket(VALID_NAME, VALID_EMAIL, null, VALID_CONTACT), Validator.INVALID_DESCRIPTION),
                Arguments.of(createTicket(VALID_NAME, VALID_EMAIL, "", VALID_CONTACT), Validator.INVALID_DESCRIPTION)
        );
    }

    private static TicketDTO createTicket(String name, String email, String desc, String contact) {
        TicketDTO dto = new TicketDTO();
        dto.setName(name);
        dto.setContact(contact);
        dto.setDescription(desc);
        dto.setEmail(email);
        return dto;
    }
}
