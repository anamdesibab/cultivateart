package com.aadhya.cultivateart.service;

import com.aadhya.cultivateart.dao.ImageSetDO;
import com.aadhya.cultivateart.dao.StudentDO;
import com.aadhya.cultivateart.dao.StudentEventDO;
import com.aadhya.cultivateart.repository.ImageSetRepository;
import com.aadhya.cultivateart.repository.StudentEventRepository;
import com.aadhya.cultivateart.repository.StudentRepository;
import com.aadhya.cultivateart.response.StudentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StudentEventRepository studentEventRepository;

    @Autowired
    ImageSetRepository imageSetRepository;

    public StudentDO saveStudent(StudentDO studentInfo) {

        StudentDO updateStudentInfo =  studentRepository.saveAndFlush(studentInfo);
        studentInfo.getEvents().forEach(studentEventDO -> {
            studentEventDO.setStudentId(updateStudentInfo.getId());
            StudentEventDO updatedStudentEventDO =  studentEventRepository.save(studentEventDO);
            studentEventDO.getImageSet().stream().forEach(imageSetDO -> {
                imageSetDO.setStudentEventId(studentEventDO.getId());
                imageSetRepository.saveAndFlush(imageSetDO);
            });
        });

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

    public StudentDO getEvents(int studentId) {
        Optional<StudentDO> optionalStudentDO = studentRepository.findById(studentId);
        StudentDO studentDO = optionalStudentDO.isPresent() ? optionalStudentDO.get() : new StudentDO();
        List<StudentEventDO> studentEventDOList =  studentEventRepository.findEventsBy(studentId);
        studentDO.setEvents(studentEventDOList);
        studentEventDOList.stream().forEach(studentEventDO -> {
            List<ImageSetDO> imageSetDOList = imageSetRepository.findImageSetBy(studentEventDO.getId());
            studentEventDO.setImageSet(imageSetDOList);
        });
        return studentDO;
    }
}
