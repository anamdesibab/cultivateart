package com.aadhya.cultivateart.dao;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "STUDENT_INFO")
public class StudentDO {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PHOTO")
    private String photo;

    @Column(name = "SCHOOL_ID")
    private int schoolId;

    @Column(name = "PARENT_NAME")
    private String parentName;

    @Column(name = "ADDRESS1")
    private String address1;

    @Column(name = "ADDRESS2")
    private String address2;

    @Column(name = "ADDRESS3")
    private String address3;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "PHONE2")
    private String phone2;

    @Column(name = "EMAIL_ID")
    private String emailId;

    @Column(name = "CATEGORY")
    private String category;

    @OneToMany(fetch=FetchType.EAGER, cascade={CascadeType.ALL})
    @JoinColumn(name="STUDENT_ID", nullable=false)
    //@Transient
    private Set<StudentEventDO> events ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Set<StudentEventDO> getEvents() {
        return events;
    }

    public void setEvents(Set<StudentEventDO> events) {
        this.events = events;
    }
}
