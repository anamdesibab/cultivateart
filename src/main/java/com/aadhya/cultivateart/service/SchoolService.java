package com.aadhya.cultivateart.service;

import com.aadhya.cultivateart.repository.SchoolRepository;
import com.aadhya.cultivateart.dao.SchoolDO;
import com.aadhya.cultivateart.response.SchoolResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SchoolService {

    @Autowired
    SchoolRepository schoolRepository;

    public static SchoolResponse dummySchoolRepo = schoolDummyData();

    public SchoolDO getSchoolInfo(int schoolId){
        Optional<SchoolDO> schoolDOOption = schoolRepository.findById(Integer.valueOf(schoolId));
        return schoolDOOption.isPresent()? schoolDOOption.get(): new SchoolDO();
    }

    public  SchoolResponse getAllSchools(){
        SchoolResponse response = new SchoolResponse();
        response.setSchoolsInfo(schoolRepository.findAll());
        return response;
    }

    public SchoolDO createSchool(SchoolDO schoolInfo){
        schoolInfo = schoolRepository.save(schoolInfo);
        return schoolInfo;
    }

    public static SchoolResponse schoolDummyData(){
        List<SchoolDO> schoolsInfo = new ArrayList<>();
        for(int i=1; i<10; i++){
            SchoolDO schoolDO = SchoolDO.getSchool(i * 11111, "Temp school name "+i*11111);
            schoolsInfo.add(schoolDO);
        }
        SchoolResponse response = new SchoolResponse();
        response.setSchoolsInfo(schoolsInfo);
        return response;
    }

    public SchoolResponse searchSchool(String searchText){
        SchoolResponse response = new SchoolResponse();
        response.setSchoolsInfo(schoolRepository.findBySearchString(searchText));
        return response;
    }
}
