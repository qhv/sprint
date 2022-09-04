package qhv.alex.spring.dto;

import lombok.Value;
import lombok.experimental.FieldNameConstants;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;
import qhv.alex.spring.database.entity.Role;
import qhv.alex.spring.validation.UserInfo;
import qhv.alex.spring.validation.group.CreateAction;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Value
@UserInfo(groups = CreateAction.class)
@FieldNameConstants
public class UserCreateEditDto {

    @NotBlank
    @Email
    String username;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    LocalDate birthDate;

    @Size(min = 2, max = 128)
    String firstname;

    String lastname;

    @NotNull
    Role role;

    @NotNull
    Integer companyId;

    MultipartFile image;
}
