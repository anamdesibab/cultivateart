package com.aadhya.cultivateart.response;

import com.aadhya.cultivateart.dao.SchoolDO;
import com.aadhya.cultivateart.dao.StudentDO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

public class SchoolProfileResponse {

    private SchoolDO schoolsInfo;
    private List<StudentDO> students;

    public SchoolDO getSchoolsInfo() {
        return schoolsInfo;
    }

    public void setSchoolsInfo(SchoolDO schoolsInfo) {
        this.schoolsInfo = schoolsInfo;
    }

    public List<StudentDO> getStudents() {
        return students;
    }

    public void setStudents(List<StudentDO> students) {
        this.students = students;
    }
}
