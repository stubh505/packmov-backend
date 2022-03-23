package com.deloitte.packmov.services;

import com.deloitte.packmov.daos.TicketDao;
import com.deloitte.packmov.exceptions.NotFoundException;
import com.deloitte.packmov.models.documents.Ticket;
import com.deloitte.packmov.models.dtos.TicketDTO;
import com.deloitte.packmov.models.converters.TicketConverter;
import com.deloitte.packmov.utilities.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class for service {@linkplain Ticket}s
 */
@Service
public class TicketService {
    @Autowired
    private TicketDao ticketDao;

    /**
     * Retrieve service {@linkplain Ticket} request for provided ID
     * @param id the ID
     * @return the {@linkplain Ticket}
     * @throws NotFoundException if no item found
     */
    public TicketDTO getTicket (String id) {
        Ticket result = ticketDao.getTicket(id);

        if (result == null) {
            throw new NotFoundException("Ticket not found");
        }
        return TicketConverter.covertToDto(result);
    }

    /**
     * Add new service {@linkplain Ticket} request and validates the request body
     * @param ticketDTO the {@linkplain TicketDTO}
     * @return the persisted ID
     */
    public String addTicket (TicketDTO ticketDTO) {
        Ticket ticket = TicketConverter.covertToDoc(ticketDTO);
        Validator.validate(ticket);
        return ticketDao.addTicket(ticket);
    }

    /**
     * Retrieve all service {@linkplain Ticket} requests
     * @return the List of all service {@linkplain TicketDTO}s
     * @throws NotFoundException if no item found
     */
    public List<TicketDTO> getAllTickets () {
        List<Ticket> result = ticketDao.getAllTickets();
        if (result == null || result.isEmpty()) {
            throw new NotFoundException("Ticket not found");
        }
        List<TicketDTO> tickets = new ArrayList<>(result.size());
        for (Ticket ticket : result) {
            tickets.add(TicketConverter.covertToDto(ticket));
        }
        return tickets;
    }
}
