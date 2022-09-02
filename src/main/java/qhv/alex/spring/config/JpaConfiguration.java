package qhv.alex.spring.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import qhv.alex.spring.config.condition.JpaCondition;

import javax.annotation.PostConstruct;

@Slf4j
@Configuration
@Conditional(JpaCondition.class)
public class JpaConfiguration {

//    @Bean
//    @ConfigurationProperties(prefix = "db")
//    public DatabaseProperties databaseProperties() {
//        return new DatabaseProperties();
//    }

    @PostConstruct
    void init() {
        log.warn("Jpa configuration initiating...");
    }
}
