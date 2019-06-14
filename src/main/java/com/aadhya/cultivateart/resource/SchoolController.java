package com.aadhya.cultivateart.resource;

import com.aadhya.cultivateart.dao.SchoolDO;
import com.aadhya.cultivateart.response.SchoolProfileResponse;
import com.aadhya.cultivateart.response.SchoolResponse;
import com.aadhya.cultivateart.service.SchoolService;
import com.aadhya.cultivateart.service.StudentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/school")
public class SchoolController {

    @Autowired
    SchoolService schoolService;

    @Autowired
    StudentService studentService;

    @ApiOperation(value = "Create School")
    @RequestMapping(value = "/createSchool", method = RequestMethod.POST, produces = "application/json")
    public SchoolDO createSchool(@RequestBody SchoolDO schoolInfo) {
        System.out.println("data got "+schoolInfo);
        schoolService.createSchool(schoolInfo);
        schoolInfo.setLogo("");
        return schoolInfo;
    }

    @ApiOperation(value = "Manage school")
    @RequestMapping(value = "/manageSchool", method = RequestMethod.GET, produces = "application/json")
    public SchoolResponse manageSchool() {
        return schoolService.getAllSchools();
    }

    @ApiOperation(value = "Get school info")
    @RequestMapping(value = "/getSchoolInfo", method = RequestMethod.GET, produces = "application/json")
    public SchoolDO getSchoolInfo(@RequestParam(required = true) int schoolId) {
        return schoolService.getSchoolInfo(schoolId);
    }

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

}
