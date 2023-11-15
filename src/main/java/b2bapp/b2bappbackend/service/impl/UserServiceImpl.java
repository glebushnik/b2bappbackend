package b2bapp.b2bappbackend.service.impl;

import b2bapp.b2bappbackend.entity.CompanyEntity;
import b2bapp.b2bappbackend.entity.UserEntity;
import b2bapp.b2bappbackend.repository.CompanyRepo;
import b2bapp.b2bappbackend.repository.UserRepo;
import b2bapp.b2bappbackend.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final CompanyRepo companyRepo;
    private final UserRepo userRepo;

    @Autowired
    public UserServiceImpl(CompanyRepo companyRepo, UserRepo userRepo) {
        this.companyRepo = companyRepo;
        this.userRepo = userRepo;
    }

    @Override
    public Set<CompanyEntity> getMyCompanies(Long userId) {
        UserEntity user = userRepo.findById(userId).orElse(null);
        return user.getCompanies();
    }
}
