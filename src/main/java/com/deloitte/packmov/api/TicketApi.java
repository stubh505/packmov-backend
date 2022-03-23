package com.deloitte.packmov.api;

import com.deloitte.packmov.models.dtos.TicketDTO;
import com.deloitte.packmov.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for service {@linkplain TicketDTO}s
 */
@CrossOrigin
@RestController
@RequestMapping("/ticket")
public class TicketApi {

    @Autowired
    private TicketService ticketService;

    /**
     * Retrieve service {@linkplain TicketDTO} request for provided ID
     * @param id the ID
     * @return the {@linkplain TicketDTO}
     */
    @GetMapping("/{id}")
    public ResponseEntity<TicketDTO> getTicket(@PathVariable String id) {
        return new ResponseEntity<>(ticketService.getTicket(id), HttpStatus.OK);
    }

    /**
     * Add new service {@linkplain TicketDTO} request
     * @param ticket the {@linkplain TicketDTO}
     * @return the persisted ID
     */
    @PostMapping("/")
    public ResponseEntity<char[]> addTicket(@RequestBody TicketDTO ticket) {
        return new ResponseEntity<>(ticketService.addTicket(ticket).toCharArray(), HttpStatus.CREATED);
    }

    /**
     * Retrieve all service {@linkplain TicketDTO} requests
     * @return the List of all service {@linkplain TicketDTO}s
     */
    @GetMapping("/")
    public ResponseEntity<List<TicketDTO>> getAllTickets() {
        return new ResponseEntity<>(ticketService.getAllTickets(), HttpStatus.OK);
    }
}
