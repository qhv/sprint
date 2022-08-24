package qhv.alex.spring.database.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import qhv.alex.spring.bpp.Auditing;
import qhv.alex.spring.bpp.InjectBean;
import qhv.alex.spring.bpp.Transaction;
import qhv.alex.spring.database.ConnectionPool.ConnectionPool;
import qhv.alex.spring.database.entity.Company;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Transaction
@Auditing
public class CompanyRepository implements CrudRepository<Integer, Company> {

    @Autowired
    private ConnectionPool connectionPool;
    @Value("${db.pool.size}")
    private Integer poolSize;

    @PostConstruct
    private void init() {
        System.out.println("Init company repositor...");
    }

    @Override
    public Optional<Company> findById(Integer id) {
        System.out.println("findById method...");
        return Optional.of(new Company(id));
    }

    @Override
    public void delete(Company entity) {
        System.out.println("delete method...");
    }
}
