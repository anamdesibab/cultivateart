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

    @Transient
    private String date;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "EVENT_DATE")
    private Date eventDate;

    @Column(name = "LINK")
    private String link;

    @Column(name = "DESCRIPTION")
    private String description;

    //@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "eventDO")
   /* @OneToOne(fetch=FetchType.EAGER, cascade={CascadeType.ALL})
    @JoinColumn(name="EVENT_ID", nullable=false)
    private StudentEventDO studentEventDO;
    public StudentEventDO getStudentEventDO() {
        return studentEventDO;
    }

    public void setStudentEventDO(StudentEventDO studentEventDO) {
        this.studentEventDO = studentEventDO;
    }
    */

    public String getDate() {
        if(null == date && null != eventDate){
            this.date = new SimpleDateFormat(DD_MM_YYYY).format(eventDate);
        }
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }


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
        if(null == date) {
            this.date = new SimpleDateFormat(DD_MM_YYYY).format(eventDate);
        }
        return this.eventDate;
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


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
