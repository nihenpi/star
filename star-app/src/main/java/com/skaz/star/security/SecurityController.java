package com.skaz.star.security;

import com.skaz.bean.Result;
import com.skaz.entity.User;
import com.skaz.security.core.SecurityDetails;
import com.skaz.security.core.SecurityManager;
import com.skaz.service.SecurityService;
import com.skaz.service.user.UserService;
import com.skaz.utils.Lists;
import com.skaz.utils.Results;
import com.skaz.utils.Strings;
import com.skaz.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * @author jungle
 */
@RestController
public class SecurityController extends BaseController {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private SecurityManager securityManager;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(String username, String password, HttpServletRequest request) {
        if (Strings.isBlank(username)) {
            return failure("请输入账号");
        }

        if (Strings.isBlank(password)) {
            return failure("请输入密码");
        }

        User user = userService.getUserByUserName(username);
        if (!securityService.isPasswordValidate(password, user.getPassword())) {
            return failure("帐号密码错误");
        }
        List<String> roles = Lists.newArrayList();
        roles.add("ADMIN");
        try {
            SecurityDetails principal = new SecurityDetails(user.getId(), user.getUsername(), roles);
            String token = securityManager.login(principal, request);
            return Results.successWithData(new HashMap(16) {
                {
                    put("token", token);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            return failure(e.getMessage());
        }

    }


}
