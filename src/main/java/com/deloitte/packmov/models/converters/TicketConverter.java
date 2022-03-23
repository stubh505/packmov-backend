package com.deloitte.packmov.models.converters;

import com.deloitte.packmov.models.documents.Ticket;
import com.deloitte.packmov.models.dtos.TicketDTO;

/**
 * Converts {@linkplain Ticket} between DTO and Document
 */
public class TicketConverter {

    private TicketConverter() {}

    public static Ticket covertToDoc(TicketDTO dto) {
        Ticket ticket = new Ticket();
        ticket.setId(dto.getId());
        ticket.setName(dto.getName());
        ticket.setContact(dto.getContact());
        ticket.setDescription(dto.getDescription());
        ticket.setDetails(dto.getDetails());
        ticket.setEmail(dto.getEmail());
        ticket.setImage(dto.getImage());
        return ticket;
    }

    public static TicketDTO covertToDto(Ticket ticket) {
        TicketDTO dto = new TicketDTO();
        dto.setId(ticket.getId());
        dto.setName(ticket.getName());
        dto.setContact(ticket.getContact());
        dto.setDescription(ticket.getDescription());
        dto.setDetails(ticket.getDetails());
        dto.setEmail(ticket.getEmail());
        dto.setImage(ticket.getImage());
        return dto;
    }
}
