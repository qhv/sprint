package qhv.alex.spring.database.ConnectionPool;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
@Component("pool1")
@RequiredArgsConstructor
public class ConnectionPool {

    @Value("${db.username}")
    private final String username;
    @Value("${db.pool.size}")
    private final Integer poolsize;
//    private final List<Object> args;
//    private Map<String, Object> properties;

//    public ConnectionPool(@Value("${db.username}") String username,
//                          @Value("${db.pool.size}") Integer poolsize) {
//        this.username = username;
//        this.poolsize = poolsize;
//    }

    @PostConstruct
    private void init() {
        log.info("Initiating connection pool...");
    }

    @PreDestroy
    public void destroy() {
        log.info("Closing connection pool...");
    }
}
