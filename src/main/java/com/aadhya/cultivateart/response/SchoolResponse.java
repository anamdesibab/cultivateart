package com.aadhya.cultivateart.response;

import com.aadhya.cultivateart.dao.SchoolDO;

import java.util.List;

public class SchoolResponse {

    List<SchoolDO> schoolsInfo;
    private String message;

    public List<SchoolDO> getSchoolsInfo() {
        return schoolsInfo;
    }

    public void setSchoolsInfo(List<SchoolDO> schoolsInfo) {
        this.schoolsInfo = schoolsInfo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
