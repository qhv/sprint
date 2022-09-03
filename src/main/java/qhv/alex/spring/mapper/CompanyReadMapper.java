package qhv.alex.spring.mapper;

import org.springframework.stereotype.Component;
import qhv.alex.spring.database.entity.Company;
import qhv.alex.spring.dto.CompanyReadDto;

@Component
public class CompanyReadMapper implements Mapper<Company, CompanyReadDto> {

    @Override
    public CompanyReadDto map(Company object) {
        return new CompanyReadDto(object.getId(), object.getName());
    }
}
