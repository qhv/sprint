package qhv.alex.spring.integration.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import qhv.alex.spring.config.DatabaseProperties;
import qhv.alex.spring.dto.CompanyReadDto;
import qhv.alex.spring.integration.annotation.IT;
import qhv.alex.spring.service.CompanyService;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
//@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL) // property in test spring.properties
@IT
//@ActiveProfiles("test")

//@SpringBootTest
// or two below:
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = ApplicationRunner.class, initializers = ConfigDataApplicationContextInitializer.class)
public class CompanyServiceIT {

    private static final Integer COMPANY_ID = 1;

    private final CompanyService companyService;
    private final DatabaseProperties databaseProperties;

    @Test
    void findById() {
        var actualResult = companyService.findById(COMPANY_ID);

        assertThat(actualResult).isPresent();

        var expectedResult = new CompanyReadDto(COMPANY_ID);
        actualResult.ifPresent(actual -> assertThat(actual).isEqualTo(expectedResult));
    }
}
