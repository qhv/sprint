package qhv.alex.spring.dto;

import lombok.Value;
import qhv.alex.spring.database.entity.Role;

import java.time.LocalDate;

@Value
public class UserReadDto {

    Long id;

    String username;

    LocalDate birthDate;

    String firstname;

    String lastname;

    String image;

    Role role;

    CompanyReadDto company;
}
