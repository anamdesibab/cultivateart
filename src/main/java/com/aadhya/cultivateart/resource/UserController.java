package com.aadhya.cultivateart.resource;

import com.aadhya.cultivateart.dao.SchoolDO;
import com.aadhya.cultivateart.response.SchoolResponse;
import com.aadhya.cultivateart.service.SchoolService;
import com.aadhya.cultivateart.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@RestController
@RequestMapping("/cultivatingart")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    SchoolService schoolService;

    @ApiOperation(value = "Create School")
    @RequestMapping(value = "/createSchool", method = RequestMethod.POST, produces = "application/json")
    public SchoolDO createSchool(@RequestBody SchoolDO schoolInfo) {
        System.out.println("data got "+schoolInfo);
        schoolService.createSchool(schoolInfo);
        schoolInfo.setLogo("");
        return schoolInfo;
    }

    @ApiOperation(value = "Activate Selected Offer For SKU")
    @RequestMapping(value = "/manageSchool", method = RequestMethod.GET, produces = "application/json")
    public SchoolResponse manageSchool() {
        return schoolService.getAllSchools();
    }

    @ApiOperation(value = "Activate Selected Offer For SKU")
    @RequestMapping(value = "/getSchoolInfo", method = RequestMethod.GET, produces = "application/json")
    public SchoolDO getSchoolInfo(@RequestParam(required = true) int schoolId) {
        return schoolService.getSchoolInfo(schoolId);
    }


    @ApiOperation(value = "Login")
    @RequestMapping(value = "/login", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?>  login(@RequestParam(required = true) String userId, @RequestParam(required = true) String password) {
        userService.validateLogin(userId, password);
        HttpCookie cookie = ResponseCookie.from("userId", userId)//.domain("cultivateart.com")
                .path("/")
                .build();
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .build();
    }

    @ApiOperation(value = "Logout")
    //http://localhost:8888/cultivatingart/login?userId=admin&password=admi
    @RequestMapping(value = "/logout", method = RequestMethod.GET, produces = "application/json")
    public boolean logout(HttpServletRequest request, HttpServletResponse response) {
        Arrays.asList(request.getCookies()).forEach(cookie->{
            cookie.setMaxAge(0);
            cookie.setValue(null);
            cookie.setPath("/");
            response.addCookie(cookie);
        });

        return true;
    }

    @ApiOperation(value = "create user")
    @RequestMapping(value = "/createAdminUser", method = RequestMethod.GET, produces = "application/json")
    public boolean createAdminUser(@RequestParam(required = true) String userId,
                                   @RequestParam(required = true) String password, HttpServletRequest request) {
        Cookie cookie = Arrays.asList(request.getCookies()).stream().filter(
                it-> it.getName().equals("userId")).findAny().orElse(null);
        if(cookie != null ) {
            return userService.createUser(userId, password, true);
        }else{
            return false;
        }
    }

    @ApiOperation(value = "create user")
    //http://localhost:8888/cultivatingart/createAdminUserLogin?userId=arvind1&password=arvind1
    @RequestMapping(value = "/createAdminUserLogin", method = RequestMethod.GET, produces = "application/json")
    public boolean createAdminUserLogin(@RequestParam(required = true) String userId,
                                   @RequestParam(required = true) String password, @CookieValue(required = false) String loginUser) {
        if(loginUser != null ) {
            return userService.createUser(userId, password, true);
        }else{
            return false;
        }
    }

}
