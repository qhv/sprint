package qhv.alex.spring.validation.impl;

import org.springframework.util.StringUtils;
import qhv.alex.spring.dto.UserCreateEditDto;
import qhv.alex.spring.validation.UserInfo;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserInfoValidator implements ConstraintValidator<UserInfo, UserCreateEditDto> {

    @Override
    public boolean isValid(UserCreateEditDto value, ConstraintValidatorContext context) {
        return StringUtils.hasText(value.getFirstname()) && StringUtils.hasText(value.getLastname());
    }
}
