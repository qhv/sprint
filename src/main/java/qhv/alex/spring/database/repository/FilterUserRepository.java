package qhv.alex.spring.database.repository;

import qhv.alex.spring.database.entity.Role;
import qhv.alex.spring.database.entity.User;
import qhv.alex.spring.dto.PersonalInfo;
import qhv.alex.spring.dto.UserFilter;

import java.util.List;

public interface FilterUserRepository {

    List<User> findAllByFilter(UserFilter userFilter);

    List<PersonalInfo> findAllByCompanyIdAndRole(Integer companyId, Role role);

    void updateCompanyAndRole(List<User> users);

    void updateCompanyAndRoleNamed(List<User> users);
}
