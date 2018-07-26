package com.skaz.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author jungle
 */
@Data
public class UserCreateForm {

    @NotEmpty(message = "用户名不能为空")
    private String username;

    @NotEmpty(message = "密码不能为空")
    private String password;
}
