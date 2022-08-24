package qhv.alex.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import qhv.alex.spring.database.ConnectionPool.ConnectionPool;
import qhv.alex.spring.database.repository.CompanyRepository;
import qhv.alex.spring.database.repository.CrudRepository;

public class ApplicationRunner {

    public static void main(String[] args) {
        try (var context = new ClassPathXmlApplicationContext("application.xml")) {
//            var pool = context.getBean("t", ConnectionPool.class);
//            System.out.println(context.getBean("t"));
            var companyRepository = context.getBean("companyRepository", CrudRepository.class);
            System.out.println(companyRepository.findById(1));
        }
    }
}
