package com.deloitte.packmov.models.converters;

import com.deloitte.packmov.models.documents.Quote;
import com.deloitte.packmov.models.dtos.QuoteDTO;

public class QuoteConverter {

    private QuoteConverter() {}

    public static Quote convertToDoc(QuoteDTO dto) {
        Quote quote = new Quote();
        quote.setName(dto.getName());
        quote.setContact(dto.getContact());
        quote.setEmail(dto.getEmail());
        quote.setLocation(dto.getLocation());
        quote.setFrom(dto.getFrom());
        quote.setTo(dto.getTo());
        quote.setOn(dto.getOn());
        quote.setId(dto.getId());
        return quote;
    }

    public static QuoteDTO convertToDto(Quote quote) {
        QuoteDTO dto = new QuoteDTO();
        dto.setName(quote.getName());
        dto.setContact(quote.getContact());
        dto.setEmail(quote.getEmail());
        dto.setLocation(quote.getLocation());
        dto.setFrom(quote.getFrom());
        dto.setTo(quote.getTo());
        dto.setOn(quote.getOn());
        dto.setId(quote.getId());
        return dto;
    }
}
