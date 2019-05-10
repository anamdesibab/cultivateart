package com.aadhya.cultivateart.dao;


import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "EVENT_INFO")
public class EventDO {
    public static final String DD_MM_YYYY = "DD-MM-YYYY";
    @Id
    @Column(name = "ID")
    private int id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "EVENT_DATE")
    private Date eventDate;

    @Column(name = "LINK")
    private String link;

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
        return DateTime.parse(date, DateTimeFormat.forPattern(DD_MM_YYYY)).toDate();
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getLink() {
        return link;
    }


    public void setLink(String link) {
        this.link = link;
    }

    public String getDate() {
        return new SimpleDateFormat(DD_MM_YYYY).format(eventDate);
    }

    public void setDate(String date) {
        this.date = date;
    }
}
