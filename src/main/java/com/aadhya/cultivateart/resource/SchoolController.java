package com.aadhya.cultivateart.resource;

import com.aadhya.cultivateart.dao.EventDO;
import com.aadhya.cultivateart.service.EventService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/school")
public class SchoolController {

   /* @Autowired
    EventService eventService;

    @ApiOperation(value = "Create Event")
    @RequestMapping(value = "/createEvent", method = RequestMethod.POST, produces = "application/json")
    public EventDO createEvent(@RequestBody EventDO eventInfo) {
        System.out.println("data got "+eventInfo);
        return eventService.saveEvent(eventInfo);
    }*/
}
