package com.aadhya.cultivateart.dao;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = { "$$hashKey" })
@Entity
@Table(name = "EVENT_IMAGES")

public class ImageSetDO {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /*@Column(name = "STUDENT_EVENT_ID")
    private int studentEventId;*/

    @Column(name = "IMAGE_NAME")
    private String image;

    @Column(name = "IMAGE_DESCRIPTION")
    private String imageName;

   /* @OneToOne
    @JoinColumn(name="STUDENT_EVENT_ID", nullable = false, updatable = false, insertable = true)
    private StudentEventDO studentEventDO;*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   /* public int getStudentEventId() {
        return studentEventId;
    }

    public void setStudentEventId(int studentEventId) {
        this.studentEventId = studentEventId;
    }*/

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
