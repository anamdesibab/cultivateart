package com.aadhya.cultivateart.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StudentModel {
    private int id;
    private String name;
    private String photo;
    private int school_id;
    private String parentName;
    private String address1;
    private String address2;
    private String address3;
    private String phone;
    private String phone2;
    private String emailId;
    private String category;

}
