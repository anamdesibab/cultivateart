package com.aadhya.cultivateart.service;

import com.aadhya.cultivateart.common.UserType;
import com.aadhya.cultivateart.dao.UserDO;
import com.aadhya.cultivateart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

   /* @Autowired
    public UserRepository userRepository;*/

    public boolean validateLogin(String userId, String pwd) {
        /*Optional<UserDO> userDo = userRepository.findById(userId);
        if (userDo.isPresent() && pwd.equals(userDo.get().getPassword())) {
            return true;
        }*/
        return true;
    }

    public boolean createUser(String userId, String pwd, boolean isAdmin) {
      /*  UserDO user = new UserDO(userId, pwd, isAdmin? UserType.ADMIN.label : UserType.REGULAR.label);
        userRepository.save(user);*/
        return true;
    }
}
