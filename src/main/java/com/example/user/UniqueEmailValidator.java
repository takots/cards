package com.example.user;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail,String> {

    @Autowired
    private UserService userService;
    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return  (userService.findUserByEmail(email) == null);
    }
}
