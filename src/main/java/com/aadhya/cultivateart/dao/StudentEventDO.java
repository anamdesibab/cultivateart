package com.aadhya.cultivateart.dao;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = { "$$hashKey" })
@Entity
@Table(name="STUDENT_EVENTS")
public class StudentEventDO {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "EVENT_ID")
    //@Transient
    private int eventId;

    @Column(name = "CATEGORY")
    private String category;

    @Column(name = "PRIZE_WONE")
    private String prize;

   /* @Column(name = "STUDENT_ID")*/
    @Transient
    private int studentId;

   /* @ManyToOne
    @JoinColumn(name="STUDENT_ID", referencedColumnName = "id", nullable = false, updatable = false, insertable = true)
    private StudentDO studentDO;*/

    /*public StudentDO getStudentDO() {
        return studentDO;
    }

    public void setStudentDO(StudentDO studentDO) {
        this.studentDO = studentDO;
    }*/

    //@OneToOne
    //@JoinColumn(name="EVENT_ID", referencedColumnName = "id", nullable = false, updatable = false, insertable = false)
   /* @OneToOne(mappedBy = "studentEventDO", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)*/
    @Transient
    private EventDO eventDO;
    public EventDO getEventDO() {
        return eventDO;
    }

    public void setEventDO(EventDO eventDO) {
        this.eventDO = eventDO;
    }

    //@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "studentEventDO")
    @OneToMany(fetch=FetchType.LAZY, cascade={CascadeType.ALL})
    @JoinColumn(name="STUDENT_EVENT_ID", nullable=false)
    //@Transient
    private List<ImageSetDO> imageSet;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public List<ImageSetDO> getImageSet() {
        return imageSet;
    }

    public void setImageSet(List<ImageSetDO> imageSet) {
        this.imageSet = imageSet;
    }
}
