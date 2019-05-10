package com.aadhya.cultivateart.response;

import com.aadhya.cultivateart.dao.EventDO;
import com.aadhya.cultivateart.dao.SchoolDO;

import java.util.List;

public class EventResponse {

    List<EventDO> eventInfoList;
    private String message;

    public List<EventDO> getEventInfoList() {
        return eventInfoList;
    }

    public void setEventInfoList(List<EventDO> eventInfoList) {
        this.eventInfoList = eventInfoList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
