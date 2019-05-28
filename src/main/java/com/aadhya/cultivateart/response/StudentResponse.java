package com.aadhya.cultivateart.response;

import com.aadhya.cultivateart.dao.SchoolDO;
import com.aadhya.cultivateart.dao.StudentDO;

import java.util.List;

public class StudentResponse {

    List<StudentDO> studentsInfo;
    private String message;

    public List<StudentDO> getStudentsInfo() {
        return studentsInfo;
    }

    public void setStudentsInfo(List<StudentDO> studentsInfo) {
        this.studentsInfo = studentsInfo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
