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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventService {

    static List<EventDO> eventsCache = null;


    @Autowired
    EventRepository eventRepository;

    public EventDO saveEvent(EventDO eventDO) {
        SimpleDateFormat formatter = new SimpleDateFormat(Constants.DD_MM_YYYY);
        try {
            Date date = formatter.parse(eventDO.getDate());
            eventDO.setEventDate(date);
        }catch (ParseException e){
            e.printStackTrace();
        }
        EventDO returnEventDO = eventRepository.save(eventDO);
        eventsCache = eventRepository.findAll();
        return returnEventDO;
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
