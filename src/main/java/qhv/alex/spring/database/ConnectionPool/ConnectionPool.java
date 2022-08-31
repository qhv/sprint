package qhv.alex.spring.database.ConnectionPool;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

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
        System.out.println("Initiating connection pool...");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Closing connection pool...");
    }
}
