package com.aadhya.cultivateart.service;

import com.aadhya.cultivateart.dao.EventDO;
import com.aadhya.cultivateart.repository.EventRepository;
import com.aadhya.cultivateart.response.EventResponse;
import com.aadhya.cultivateart.response.SchoolResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    @Autowired
    EventRepository eventRepository;

    public EventDO saveEvent(EventDO eventDO) {
        return eventRepository.save(eventDO);
    }

    public EventResponse getAllEvents(){
        EventResponse response = new EventResponse();
        response.setEventInfoList(eventRepository.findAll());
        return response;
    }
}
