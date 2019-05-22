package com.aadhya.cultivateart.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "STUDENT_INFO")
public class StudentDO {
    @Id
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PHOTO")
    private String photo;

    @Column(name = "SCHOOL_ID")
    private int school_id;

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

}
