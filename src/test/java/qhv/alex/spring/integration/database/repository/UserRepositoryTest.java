package qhv.alex.spring.integration.database.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import qhv.alex.spring.database.entity.Role;
import qhv.alex.spring.database.entity.User;
import qhv.alex.spring.database.querydsl.QPredicates;
import qhv.alex.spring.database.repository.UserRepository;
import qhv.alex.spring.dto.PersonalInfo;
import qhv.alex.spring.dto.UserFilter;
import qhv.alex.spring.integration.annotation.IT;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static qhv.alex.spring.database.entity.QUser.user;

@Slf4j
@IT
@RequiredArgsConstructor
class UserRepositoryTest {

    private final UserRepository userRepository;

    @Test
    void checkBatch() {
        var users = userRepository.findAll();
//        userRepository.updateCompanyAndRole(users);
        userRepository.updateCompanyAndRoleNamed(users);
    }

    @Test
    void checkJdbcTemplate() {
        var users = userRepository.findAllByCompanyIdAndRole(1, Role.USER);
        assertThat(users).hasSize(1);
    }

    @Test
    @Commit
    void checkAuditing() {
        var user = userRepository.findById(1L).get();
        user.setBirthDate(user.getBirthDate().plusYears(1L));
        userRepository.flush();
    }

    @Test
    void checkCustomImplementation() {
        var filter = new UserFilter(null, "ov", LocalDate.now());
        var predicate = QPredicates.builder()
                .add(filter.firstname(), user.firstname::containsIgnoreCase)
                .add(filter.lastname(), user.lastname::containsIgnoreCase)
                .add(filter.birthDate(), user.birthDate::before)
                .buildAnd();
//        var users = userRepository.findAllByFilter(filter);
        var users = userRepository.findAll(predicate);
        assertThat(users).hasSize(4);
    }

    @Test
    void checkProjections() {
        var users = userRepository.findAllByCompanyId(1);
        assertThat(users).hasSize(2);
    }

    @Test
    void checkPageable() {
        var pageable = PageRequest.of(0, 2, Sort.by("id"));
        var page = userRepository.findAllBy(pageable);

        page.forEach(user -> System.out.println(user.getCompany().getName()));

        System.out.println("=================");
        while (page.hasNext()) {
            page = userRepository.findAllBy(page.nextPageable());
            page.forEach(user -> System.out.println(user.getCompany().getName()));
        }

    }

    @Test
    void checkSort() {
//        var sortPattern = Sort.by("birthDate").descending()
//                .and(Sort.by("firstname"));
        var sortBy = Sort.sort(User.class);
        var sort = sortBy.by(User::getBirthDate).descending()
                .and(sortBy.by(User::getFirstname));

        var allUsers = userRepository.findTop3ByBirthDateBefore(LocalDate.now(), sort);
        assertThat(allUsers).hasSize(3);
    }

    @Test
    void checkFirstTop() {
        var topUser = userRepository.findFirstByOrderByIdDesc();
        assertThat(topUser).isPresent();
        assertThat(topUser.get().getId()).isEqualTo(5L);
    }

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