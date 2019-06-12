package com.aadhya.cultivateart.service;

import com.aadhya.cultivateart.dao.EventDO;
import com.aadhya.cultivateart.dao.ImageSetDO;
import com.aadhya.cultivateart.dao.StudentDO;
import com.aadhya.cultivateart.dao.StudentEventDO;
import com.aadhya.cultivateart.repository.ImageSetRepository;
import com.aadhya.cultivateart.repository.StudentEventRepository;
import com.aadhya.cultivateart.repository.StudentRepository;
import com.aadhya.cultivateart.response.SchoolProfileResponse;
import com.aadhya.cultivateart.response.StudentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StudentEventRepository studentEventRepository;

    @Autowired
    ImageSetRepository imageSetRepository;

    @Autowired
    SchoolService schoolService;

    @Autowired
    EventService eventService;

    public StudentDO saveStudent(StudentDO studentInfo) {
        StudentDO updateStudentInfo =  studentRepository.saveAndFlush(studentInfo);
       /* studentInfo.getEvents().forEach(studentEventDO -> {
            studentEventDO.setStudentId(updateStudentInfo.getId());
            StudentEventDO updatedStudentEventDO =  studentEventRepository.save(studentEventDO);
            studentEventDO.getImageSet().stream().forEach(imageSetDO -> {
                imageSetDO.setStudentEventId(studentEventDO.getId());
                imageSetRepository.saveAndFlush(imageSetDO);
            });
        });*/

        return updateStudentInfo;
    }

    public StudentDO saveStudentInfo(StudentDO studentInfo) {
        return studentRepository.save(studentInfo);
    }

    public StudentResponse getAllStudents(){
        StudentResponse response = new StudentResponse();
        List<StudentDO> studentDOList = studentRepository.findAll();

        response.setStudentsInfo(studentDOList);
        return response;
    }

    public StudentDO getStudentInfoBy(int studentId) {
        Optional<StudentDO> optionalStudentDO = studentRepository.findById(studentId);
        StudentDO studentDO = optionalStudentDO.isPresent() ? optionalStudentDO.get() : new StudentDO();
        Map<Integer, EventDO> eventMap = eventService.getAllEventsInMap();
        studentDO.getEvents().forEach(studentEventDO -> {
            studentEventDO.setEventDO(eventMap.get(studentEventDO.getEventId()));
        });

        /*List<StudentEventDO> studentEventDOList =  studentEventRepository.findEventsBy(studentId);
        studentDO.setEvents(studentEventDOList);
        studentEventDOList.stream().forEach(studentEventDO -> {
            List<ImageSetDO> imageSetDOList = imageSetRepository.findImageSetBy(studentEventDO.getId());
            studentEventDO.setImageSet(imageSetDOList);
        });*/
        return studentDO;
    }

    public SchoolProfileResponse getSchoolInfoStudent(int schoolId) {
        SchoolProfileResponse schoolProfileResponse = new SchoolProfileResponse();
        schoolProfileResponse.setSchoolsInfo(schoolService.getSchoolInfo(schoolId));
        List<StudentDO> studentsListReturn = new ArrayList<>();
        List<StudentDO> studentsList = studentRepository.findStudentBySchoolId(schoolId);
        studentsList.stream().forEach(studentDO -> {
            studentsListReturn.add(getStudentInfoBy(studentDO.getId()));
        });
        schoolProfileResponse.setStudents(studentsListReturn);
        return schoolProfileResponse;
    }

    public StudentResponse searchStudent(String searchText) {
        StudentResponse response = new StudentResponse();
        List<StudentDO> students = studentRepository.findBySearchString(searchText);
        response.setStudentsInfo(students);
        return response;
    }
}
