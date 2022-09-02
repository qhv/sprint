package qhv.alex.spring.database.repository;

import qhv.alex.spring.database.entity.User;
import qhv.alex.spring.dto.UserFilter;

import java.util.List;

public interface FilterUserRepository {

    List<User> findAllByFilter(UserFilter userFilter);
}
