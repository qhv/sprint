package qhv.alex.spring.integration.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import qhv.alex.spring.database.entity.Role;
import qhv.alex.spring.dto.UserCreateEditDto;
import qhv.alex.spring.integration.IntegrationTestBase;
import qhv.alex.spring.service.UserService;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
public class UserServiceIT extends IntegrationTestBase {

    private static final Long USER_1 = 1L;
    private static final Integer COMPANY_ID = 1;
    private static final Long NO_USER = -1L;
    private final UserService userService;

    @Test
    void findAll() {
        var result = userService.findAll();
        assertThat(result).hasSize(5);
    }

    @Test
    void findById() {
        var maybeUser = userService.findById(USER_1);
        assertThat(maybeUser).isPresent();
        assertThat(maybeUser.get().getUsername()).isEqualTo("ivan@gmail.com");
    }

    @Test
    void create() {
        var userDto = new UserCreateEditDto(
                "test@mail.ru",
                LocalDate.now(),
                "TestName",
                "Test",
                Role.ADMIN,
                COMPANY_ID
        );
        var userReadDto = userService.create(userDto);

        assertThat(userReadDto.getUsername()).isEqualTo(userDto.getUsername());
        assertThat(userReadDto.getBirthDate()).isEqualTo(userDto.getBirthDate());
        assertThat(userReadDto.getFirstname()).isEqualTo(userDto.getFirstname());
        assertThat(userReadDto.getLastname()).isEqualTo(userDto.getLastname());
        assertThat(userReadDto.getRole()).isEqualTo(userDto.getRole());
        assertThat(userReadDto.getCompany().id()).isEqualTo(userDto.getCompanyId());
    }

    @Test
    void update() {
        var expected = new UserCreateEditDto(
                "test@mail.ru",
                LocalDate.now(),
                "TestName",
                "Test",
                Role.ADMIN,
                COMPANY_ID
        );
        var actual = userService.update(USER_1, expected);
        assertThat(actual).isPresent();
        actual.ifPresent(userReadDto -> {
            assertThat(userReadDto.getUsername()).isEqualTo(expected.getUsername());
            assertThat(userReadDto.getBirthDate()).isEqualTo(expected.getBirthDate());
            assertThat(userReadDto.getFirstname()).isEqualTo(expected.getFirstname());
            assertThat(userReadDto.getLastname()).isEqualTo(expected.getLastname());
            assertThat(userReadDto.getRole()).isEqualTo(expected.getRole());
            assertThat(userReadDto.getCompany().id()).isEqualTo(expected.getCompanyId());
        });
    }

    @Test
    void delete() {
        assertThat(userService.delete(NO_USER)).isFalse();
        assertThat(userService.delete(USER_1)).isTrue();
    }
}
