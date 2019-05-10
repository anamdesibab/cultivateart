package com.aadhya.cultivateart.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SCHOOL_INFO")
public class SchoolDO {
    @Id
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PROFILE")
    private String profile;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "DISTRICT")
    private String district;

    @Column(name = "PHONE1")
    private String phone1;

    @Column(name = "PHONE2")
    private String phone2;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "WEBSITE")
    private String website;

    @Column(name = "LOGO")
    private String logo;

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

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public static SchoolDO getSchool(int id, String name){
        SchoolDO schoolDO = new SchoolDO();
        schoolDO.setId(id);
        schoolDO.setName(name);
        schoolDO.setLogo("/Users/ababu/Documents/images/"+id+".jpg");
        schoolDO.setEmail(name+"@testmail.com");
        schoolDO.setAddress("Address "+ id  );
        schoolDO.setPhone1("889-898-"+id);
        schoolDO.setWebsite(id+"Website.com");

        return schoolDO;
    }
}
