package qhv.alex.spring.validation;

import qhv.alex.spring.validation.impl.UserInfoValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UserInfoValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface UserInfo {

    String message() default "First name or last name should be filled in";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
