package com.deloitte.packmov.daos;

import com.deloitte.packmov.models.documents.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository class for service {@linkplain Ticket}s
 */
@Repository
public class TicketDao {
    @Autowired
    private MongoOperations ops;

    /**
     * Retrieve service {@linkplain Ticket} request for provided ID
     * @param id the ID
     * @return the {@linkplain Ticket}
     */
    public Ticket getTicket (String id) {
        return ops.findOne(Query.query(Criteria.where("id").is(id)), Ticket.class);
    }

    /**
     * Add new service {@linkplain Ticket} request
     * @param ticket the {@linkplain Ticket}
     * @return the persisted ID
     */
    public String addTicket (Ticket ticket) {
        return ops.insert(ticket).getId();
    }

    /**
     * Retrieve all service {@linkplain Ticket} requests
     * @return the List of all service {@linkplain Ticket}s
     */
    public List<Ticket> getAllTickets () {
        return ops.findAll(Ticket.class);
    }
}
