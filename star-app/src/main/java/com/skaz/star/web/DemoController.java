package com.skaz.star.web;

import com.skaz.bean.Page;
import com.skaz.bean.Result;
import com.skaz.entity.User;
import com.skaz.form.UserCreateForm;
import com.skaz.service.user.UserService;
import com.skaz.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author jungle
 */
@RestController
@RequestMapping("/demo")
public class DemoController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping("/hello")
    public User hello() {
        return userService.getUserByUserName("admin");
    }

    @PostMapping("/create")
    public Result create(@Valid UserCreateForm userCreateForm, BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            return failure(errors.get(0).getDefaultMessage());
        }
        return userService.create(userCreateForm);
    }

    @GetMapping("/page")
    public Page<User> page(int no, int size){
        return userService.page(no, size);
    }

    @GetMapping("/list")
    public List<User> list(){
        return userService.list();
    }

    @GetMapping("/{id}")
    public User get(@PathVariable Long id){
        return userService.get(id);
    }
}
