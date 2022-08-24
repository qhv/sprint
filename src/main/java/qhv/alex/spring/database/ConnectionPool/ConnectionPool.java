package qhv.alex.spring.database.ConnectionPool;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Map;

public class ConnectionPool {

    private final String username;
    private final Integer poolsize;
    private final List<Object> args;
    private Map<String, Object> properties;

    public ConnectionPool(String username,
                          Integer poolsize,
                          List<Object> args,
                          Map<String, Object> properties) {
        this.username = username;
        this.poolsize = poolsize;
        this.args = args;
        this.properties = properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    @PostConstruct
    private void init() {
        System.out.println("Initiating connection pool...");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Closing connection pool...");
    }
}
