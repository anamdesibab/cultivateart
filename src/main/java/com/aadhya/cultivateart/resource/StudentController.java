package com.aadhya.cultivateart.resource;

import com.aadhya.cultivateart.dao.EventDO;
import com.aadhya.cultivateart.dao.StudentDO;
import com.aadhya.cultivateart.response.StudentModel;
import com.aadhya.cultivateart.response.StudentResponse;
import com.aadhya.cultivateart.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

   /* @ApiOperation(value = "Create Student")
    @RequestMapping(value = "/createStudent", method = RequestMethod.POST, produces = "application/json")
    public StudentDO createStudent(@RequestBody String studentInfo) {
        System.out.println("data got " + studentInfo);
        return studentService.saveStudent(studentInfo);
    }*/

    @ApiOperation(value = "Create Student")
    @RequestMapping(value = "/createStudent", method = RequestMethod.POST, produces = "application/json")
    public void createStudent(@RequestBody String studentInfo) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            StudentDO studentModel = mapper.readValue(studentInfo, StudentDO.class);
            studentService.saveStudent(studentModel);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("data got " + studentInfo);
    }

    @ApiOperation(value = "Manage Students")
    @RequestMapping(value = "/manageStudents", method = RequestMethod.GET, produces = "application/json")
    public StudentResponse manageStudents() {
        return studentService.getAllStudents();
    }

    @ApiOperation(value = "Get Event info")
    @RequestMapping(value = "/getStudentInfo", method = RequestMethod.GET, produces = "application/json")
    public StudentDO getStudentInfo(int studentId) {
        return studentService.getEvents(studentId);
    }

}
