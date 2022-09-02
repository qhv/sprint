package qhv.alex.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import qhv.alex.spring.database.repository.CompanyRepository;
import qhv.alex.spring.database.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
}
