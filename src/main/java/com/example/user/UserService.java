package com.example.user;

import jakarta.validation.ConstraintDeclarationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Set;

@Service
@Validated
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Validator validator;

//    public Integer addUser(UserModel user){
//        UserModel newUser = userRepository.save(user);
//        return newUser.getId();
//    }

    public UserModel findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public UserModel findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // 0410
    public Integer addUser(UserModel user){
        // 主要透過 validator.validate 檢驗資料，當有欄位不通過檢驗，就會將其蒐集
        Set<ConstraintViolation<UserModel>> violations = validator.validate(user);
        if(!violations.isEmpty()){
            StringBuffer stringBuffer = new StringBuffer();
            for(ConstraintViolation<UserModel> constraintViolation : violations){
                stringBuffer.append(constraintViolation.getMessage());
            }
            // 透過 ConstraintViolationException 來將錯誤訊息以及錯誤本身丟出，而丟出的 ConstraintViolationException 將會在 controller 捕獲並處理
            throw new ConstraintViolationException(stringBuffer.toString() ,violations);
        }
        UserModel newUser = userRepository.save(user);
        return newUser.getId();
    }
}
