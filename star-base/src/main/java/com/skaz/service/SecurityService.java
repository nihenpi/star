package com.skaz.service;

import com.skaz.Securitys;
import org.springframework.stereotype.Service;

/**
 * @author jungle
 */
@Service
public class SecurityService {

    public boolean isPasswordValidate(String rawPassword, String encodedPassword) {
        return Securitys.isPasswordValidate(rawPassword, encodedPassword);
    }
}
