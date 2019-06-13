package com.aadhya.cultivateart.service;

import com.aadhya.cultivateart.dao.*;
import com.aadhya.cultivateart.repository.ImageSetRepository;
import com.aadhya.cultivateart.repository.StudentEventRepository;
import com.aadhya.cultivateart.repository.StudentRepository;
import com.aadhya.cultivateart.response.SchoolProfileResponse;
import com.aadhya.cultivateart.response.StudentResponse;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
        studentDO.setSchoolDO(schoolService.getSchoolInfo(studentDO.getSchoolId()));
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
        Map<Integer, EventDO> eventMap = eventService.getAllEventsInMap();
        List<StudentDO> studentsList = studentRepository.findStudentsBySchoolId(schoolId);
        Map<Integer, StudentDO> studentDOMap = new HashMap<>();
        studentsList.forEach(studentDO -> {
            studentDOMap.put(studentDO.getId(), studentDO);
        });
        studentsList = studentDOMap.values().stream().collect(Collectors.toList());
        studentsList.stream().forEach(studentDO -> {
            studentDO.getEvents().forEach(studentEventDO -> {
                studentEventDO.setEventDO(eventMap.get(studentEventDO.getEventId()));
            });
            //studentsListReturn.add(getStudentInfoBy(studentDO.getId()));
        });
        studentsListReturn.addAll(studentsList);
        schoolProfileResponse.setStudents(studentsListReturn);
        return schoolProfileResponse;
    }

    public StudentResponse searchStudent(String searchText) {
        StudentResponse response = new StudentResponse();
        List<StudentDO> students = studentRepository.findBySearchString(searchText);
        Map<Integer, SchoolDO> schoolDOMap = schoolService.getAllSchoolsAsMap();
        students.forEach(studentDO -> {
            studentDO.setSchoolDO(schoolDOMap.get(studentDO.getSchoolId()));
        });
        response.setStudentsInfo(students);
        return response;
    }
}
