package qhv.alex.spring.integration.database.repository;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.support.TransactionTemplate;
import qhv.alex.spring.database.entity.Company;
import qhv.alex.spring.database.repository.CompanyRepository;
import qhv.alex.spring.integration.annotation.IT;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@IT
@RequiredArgsConstructor
class CompanyRepositoryTest {

    private static final Integer APPLE_ID = 5;
    private final EntityManager entityManager;
    private final TransactionTemplate transactionTemplate;
    private final CompanyRepository companyRepository;

    @Test
    void checkFindByQueries() {
        Optional<Company> google = companyRepository.findByName("google");
        List<Company> companies = companyRepository.findAllByNameContainingIgnoreCase("a");
    }

    @Test
    void delete() {
        var maybeCompany = companyRepository.findById(APPLE_ID);
        assertThat(maybeCompany).isPresent();
        maybeCompany.ifPresent(companyRepository::delete);
        entityManager.flush();
        assertThat(companyRepository.findById(APPLE_ID)).isEmpty();
    }

    @Test
    void findById() {
        transactionTemplate.executeWithoutResult(transactionStatus -> {
            var company = entityManager.find(Company.class, 1);
            assertNotNull(company);
            assertThat(company.getLocales()).hasSize(2);
        });
    }

    @Test
    void save() {
        var company = Company.builder()
                .name("Apple")
                .locales(Map.of(
                        "ru", "Apple описание",
                        "en", "Apple description"
                ))
                .build();
        entityManager.persist(company);
        assertNotNull(company.getId());
    }
}