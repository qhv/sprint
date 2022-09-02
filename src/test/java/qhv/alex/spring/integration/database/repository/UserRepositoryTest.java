package qhv.alex.spring.integration.database.repository;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import qhv.alex.spring.database.entity.Role;
import qhv.alex.spring.database.entity.User;
import qhv.alex.spring.database.repository.UserRepository;
import qhv.alex.spring.integration.annotation.IT;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@IT
@RequiredArgsConstructor
class UserRepositoryTest {

    private final UserRepository userRepository;

    @Test
    void checkUpdate() {
        User ivan = userRepository.getById(1L);
        assertThat(ivan.getRole()).isEqualTo(Role.ADMIN);

        int resultCount = userRepository.updateRole(Role.USER, 1L, 5L);
        assertThat(resultCount).isEqualTo(2);

        User theSameIvan = userRepository.getById(1L);
        assertThat(theSameIvan.getRole()).isEqualTo(Role.USER);
    }
    @Test
    void findAllBy() {
        List<User> users = userRepository.findAllBy("a", "ov");
        assertThat(users).hasSize(3);
    }
}