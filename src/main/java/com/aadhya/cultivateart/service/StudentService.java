package com.aadhya.cultivateart.service;

import com.aadhya.cultivateart.dao.StudentDO;
import com.aadhya.cultivateart.repository.StudentRepository;
import com.aadhya.cultivateart.response.StudentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public StudentDO saveStudent(StudentDO studentInfo) {
        return studentRepository.save(studentInfo);
    }

    public StudentResponse getAllStudents(){
        StudentResponse response = new StudentResponse();
        response.setStudentsInfo(studentRepository.findAll());
        return response;
    }

    public StudentDO getEvents(int studentId) {
        Optional<StudentDO> optionalStudentDO = studentRepository.findById(studentId);
        return optionalStudentDO.isPresent() ? optionalStudentDO.get() : new StudentDO();
    }
}
