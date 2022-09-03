package qhv.alex.spring.integration.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import qhv.alex.spring.database.ConnectionPool.ConnectionPool;
import qhv.alex.spring.integration.IntegrationTestBase;
import qhv.alex.spring.integration.annotation.IT;
import qhv.alex.spring.service.UserService;

public class UserServiceIT extends IntegrationTestBase {

    @Autowired
    private UserService userService;
    @Autowired
    private ConnectionPool pool1;

    @Test
    void test() {

    }
}
