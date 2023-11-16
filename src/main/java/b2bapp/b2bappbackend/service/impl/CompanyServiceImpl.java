package b2bapp.b2bappbackend.service.impl;

import b2bapp.b2bappbackend.entity.CompanyEntity;
import b2bapp.b2bappbackend.entity.UserEntity;
import b2bapp.b2bappbackend.exception.CompanyAlreadyExistsException;
import b2bapp.b2bappbackend.repository.CompanyRepo;
import b2bapp.b2bappbackend.repository.UserRepo;
import b2bapp.b2bappbackend.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepo companyRepo;
    private final UserRepo userRepo;

    @Autowired
    public CompanyServiceImpl(CompanyRepo companyRepo, UserRepo userRepo) {
        this.companyRepo = companyRepo;
        this.userRepo = userRepo;
    }

    @Override
    public CompanyEntity createCompany(CompanyEntity company, Long userId) throws CompanyAlreadyExistsException {
        if(companyRepo.findByCompanyName(company.getCompanyName())!=null){
            throw new CompanyAlreadyExistsException("Компания с таким названием уже существует.");
        }
        companyRepo.save(company);
        UserEntity user = new UserEntity();
        user = userRepo.findById(userId).orElse(null);

        user.getCompanies().add(company);
        company.getUsers().add(user);

        return companyRepo.save(company);
    }

    @Override
    public List<CompanyEntity> getAllCompanies() {
        return companyRepo.findAll();
    }

    @Override
    public Set<UserEntity> getCompanyUsers(Long companyId){
        CompanyEntity company = companyRepo.findById(companyId).orElse(null);
        return company.getUsers();
    }

    @Override
    public CompanyEntity updateCompany(CompanyEntity company, Long companyId) {
        CompanyEntity updCompany = companyRepo.findById(companyId).orElse(null);
        return mapCompany(company, updCompany);
    }

    @Override
    public void deleteCompany(Long companyId) {
        companyRepo.deleteById(companyId);
    }

    @Override
    public Set<UserEntity> addCompanyUser(Long userId, Long companyId) {
        CompanyEntity company = companyRepo.findById(companyId).orElse(null);
        UserEntity user = userRepo.findById(userId).orElse(null);

        user.getCompanies().add(company);
        company.getUsers().add(user);
        companyRepo.save(company);
        userRepo.save(user);

        return company.getUsers();
    }
    private CompanyEntity mapCompany(CompanyEntity company, CompanyEntity updCompany) {
        updCompany.setCompanyTag(company.getCompanyTag()==null ? updCompany.getCompanyTag() : company.getCompanyTag());
        updCompany.setCompanyName(company.getCompanyName()==null ? updCompany.getCompanyName() : company.getCompanyName());
        updCompany.setInn(company.getInn()==null ? updCompany.getInn() : company.getInn());
        updCompany.setPhNumber(company.getPhNumber()==null ? updCompany.getPhNumber() : company.getPhNumber());
        updCompany.setEmail(company.getEmail()==null ? updCompany.getEmail() : company.getEmail());
        updCompany.setExperience(company.getExperience()==null ? updCompany.getExperience() : company.getExperience());

        return companyRepo.save(updCompany);
    }
}
