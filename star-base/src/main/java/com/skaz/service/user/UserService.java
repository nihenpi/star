package com.skaz.service.user;

import com.skaz.bean.Result;
import com.skaz.dao.user.UserDao;
import com.skaz.entity.User;
import com.skaz.form.UserCreateForm;
import com.skaz.service.BaseService;
import com.skaz.utils.Beans;
import com.skaz.utils.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jungle
 */
@Service
public class UserService extends BaseService<User> {

    @Autowired
    private UserDao userDao;

    public User getUserByUserName(String username){
            return userDao.getUserByUserName(username);
    }

    public Result create(UserCreateForm userCreateForm) {
        User user = new User();
        Beans.copyProperties(user,userCreateForm);
        userDao.create(user);
        return Results.success();
    }
}
