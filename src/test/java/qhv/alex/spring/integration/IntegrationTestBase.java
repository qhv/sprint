package qhv.alex.spring.integration;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;
import qhv.alex.spring.integration.annotation.IT;

@Sql({
        "classpath:sql/data.sql"
})
@IT
public abstract class IntegrationTestBase {

    private static final PostgreSQLContainer<?> CONTAINER = new PostgreSQLContainer<>("postgres:14.4");

    @BeforeAll
    static void runContainer() {
        CONTAINER.start();
    }

    @DynamicPropertySource
    static void postgresProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", CONTAINER::getJdbcUrl);
    }
}
