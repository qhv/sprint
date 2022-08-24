package qhv.alex.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import qhv.alex.spring.database.ConnectionPool.ConnectionPool;
import qhv.alex.spring.database.repository.CompanyRepository;

public class ApplicationRunner {

    public static void main(String[] args) {
        var context = new ClassPathXmlApplicationContext("application.xml");
        var pool = context.getBean("t", ConnectionPool.class);
        System.out.println(context.getBean("t"));
        var companyRepository = context.getBean("companyRepository", CompanyRepository.class);
        System.out.println(companyRepository);
    }
}
