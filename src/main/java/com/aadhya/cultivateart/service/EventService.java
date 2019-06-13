package com.aadhya.cultivateart.service;

import com.aadhya.cultivateart.common.Constants;
import com.aadhya.cultivateart.dao.EventDO;
import com.aadhya.cultivateart.repository.EventRepository;
import com.aadhya.cultivateart.response.EventResponse;
import com.aadhya.cultivateart.response.SchoolResponse;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventService {

    List<EventDO> eventsCache = null;


    @Autowired
    EventRepository eventRepository;

    public EventDO saveEvent(EventDO eventDO) {
        eventDO.setEventDate(DateTime.parse(eventDO.getDate(),
                DateTimeFormat.forPattern(Constants.DD_MM_YYYY)).toDate());
        eventsCache = eventRepository.findAll();
        return eventRepository.save(eventDO);
    }

    public EventResponse getAllEvents(){
        EventResponse response = new EventResponse();
        if (null == eventsCache){
            eventsCache = eventRepository.findAll();
        }
        response.setEventInfoList(eventsCache);
        return response;
    }

    public Map<Integer, EventDO> getAllEventsInMap(){
        return getAllEvents().getEventInfoList().stream().collect(Collectors.toMap(event -> event.getId(), event -> event ));
    }

    public EventDO getEvents(int eventId){
        Optional<EventDO> optionalEventDO = eventRepository.findById(eventId);
        return optionalEventDO.isPresent() ? optionalEventDO.get() : new EventDO();
    }
}
