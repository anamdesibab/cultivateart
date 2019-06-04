package com.aadhya.cultivateart.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = { "$$hashKey" })
public class EventModel {

    private String eventId;
    private String category;
    private String prize;
    private List<ImageSetModel> imageSet;

}
