package com.aadhya.cultivateart.dao;


import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.aadhya.cultivateart.common.Constants.DD_MM_YYYY;

@Entity
@Table(name = "EVENT_INFO")
public class EventDO {
    @Id
    @Column(name = "ID")
    private int id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "EVENT_DATE")
    private Date eventDate;

    @Column(name = "LINK")
    private String link;

    @Column(name = "DESCRIPTION")
    private String description;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "eventDO")
    private StudentEventDO studentEventDO;

    @Transient
    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getEventDate() {
        return this.eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
        this.date =  new SimpleDateFormat(DD_MM_YYYY).format(eventDate);
    }

    public String getLink() {
        return link;
    }


    public void setLink(String link) {
        this.link = link;
    }

    public String getDate() {
        if(null != eventDate) {
            this.date = new SimpleDateFormat(DD_MM_YYYY).format(eventDate);
        }
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
