package com.aadhya.cultivateart.resource;

import com.aadhya.cultivateart.response.SchoolProfileResponse;
import com.aadhya.cultivateart.response.SchoolResponse;
import com.aadhya.cultivateart.service.SchoolService;
import com.aadhya.cultivateart.service.StudentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/school")
public class SchoolController {

    @Autowired
    SchoolService schoolService;

    @Autowired
    StudentService studentService;

    @ApiOperation(value = "Get Event info")
    @RequestMapping(value = "/searchSchool", method = RequestMethod.GET, produces = "application/json")
    public SchoolResponse searchSchool( String searchText) {
        return schoolService.searchSchool(searchText);
    }

    @ApiOperation(value = "Activate Selected Offer For SKU")
    @RequestMapping(value = "/getSchoolProfile", method = RequestMethod.GET, produces = "application/json")
    public SchoolProfileResponse getSchoolProfile(@RequestParam(required = true) int schoolId) {
        return studentService.getSchoolInfoStudent(schoolId);
    }

    @ApiOperation(value = "Activate Selected Offer For SKU")
    @RequestMapping(value = "/manageSchool", method = RequestMethod.GET, produces = "application/json")
    public SchoolResponse manageSchool() {
        return schoolService.getAllSchools();
    }
}
