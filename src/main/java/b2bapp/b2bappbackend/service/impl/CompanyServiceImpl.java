package b2bapp.b2bappbackend.service.impl;

import b2bapp.b2bappbackend.DTO.CompanyDTO;
import b2bapp.b2bappbackend.DTO.mapper.CompanyDTOMapper;
import b2bapp.b2bappbackend.entity.CompanyEntity;
import b2bapp.b2bappbackend.entity.ReviewEntity;
import b2bapp.b2bappbackend.entity.UserEntity;
import b2bapp.b2bappbackend.exception.category.CategoryNotFoundByNameException;
import b2bapp.b2bappbackend.exception.company.CompanyAlreadyExistsException;
import b2bapp.b2bappbackend.exception.company.CompanyNotFoundByIdException;
import b2bapp.b2bappbackend.exception.subcategory.SubcategoryNotFoundByNameException;
import b2bapp.b2bappbackend.repository.CategoryRepo;
import b2bapp.b2bappbackend.repository.CompanyRepo;
import b2bapp.b2bappbackend.repository.SubcategoryRepo;
import b2bapp.b2bappbackend.repository.UserRepo;
import b2bapp.b2bappbackend.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepo companyRepo;
    private final UserRepo userRepo;
    private final CompanyDTOMapper companyDTOMapper;
    private final CategoryRepo categoryRepo;

    private final SubcategoryRepo subcategoryRepo;

    @Autowired
    public CompanyServiceImpl(CompanyRepo companyRepo,
                              UserRepo userRepo,
                              CompanyDTOMapper companyDTOMapper, CategoryRepo categoryRepo, SubcategoryRepo subcategoryRepo) {
        this.companyRepo = companyRepo;
        this.userRepo = userRepo;
        this.companyDTOMapper = companyDTOMapper;
        this.categoryRepo = categoryRepo;
        this.subcategoryRepo = subcategoryRepo;
    }

    @Override
    public CompanyEntity createCompany(CompanyEntity company, Long userId) throws CompanyAlreadyExistsException, CategoryNotFoundByNameException, SubcategoryNotFoundByNameException {
        if(companyRepo.findByCompanyName(company.getCompanyName())!=null){
            throw new CompanyAlreadyExistsException("Компания с таким названием уже существует.");
        }
        if(categoryRepo.findCategoryEntitiesByName(company.getCategory())==null) {
            throw new CategoryNotFoundByNameException("Такой категории не существует");
        }
        if(subcategoryRepo.findSubcategoryEntitiesByName(company.getSubcategory()) == null) {
            throw new SubcategoryNotFoundByNameException("Такой подкатегории не существует");
        }
        companyRepo.save(company);
        UserEntity user = new UserEntity();
        user = userRepo.findById(userId).orElse(null);

        user.getCompanies().add(company);
        company.getUsers().add(user);

        return companyRepo.save(company);
    }

    @Override
    public List<CompanyDTO> getAllCompanies() {
        return companyRepo.findAll().stream().map(companyDTOMapper).collect(Collectors.toList());
    }

    @Override
    public Set<UserEntity> getCompanyUsers(Long companyId) throws CompanyNotFoundByIdException {
        CompanyEntity company = companyRepo.findById(companyId).orElse(null);
        if(company == null) {
            throw new CompanyNotFoundByIdException("Компании с таким Id не существует.");
        }
        return company.getUsers();
    }

    @Override
    public CompanyEntity updateCompany(CompanyEntity company, Long companyId) throws CompanyNotFoundByIdException, CompanyAlreadyExistsException {
        CompanyEntity updCompany = companyRepo.findById(companyId).orElse(null);
        if(updCompany == null) {
            throw new CompanyNotFoundByIdException("Компании с таким Id не существует.");
        }
        if(companyRepo.findByCompanyName(company.getCompanyName())!=null) {
            throw new CompanyAlreadyExistsException("Компания с таким названием уже существует.");
        }
        return mapCompany(company, updCompany);
    }

    @Override
    public void deleteCompany(Long companyId, Long userId) {
        CompanyEntity company = companyRepo.findById(companyId).orElse(null);
        UserEntity user = userRepo.findById(userId).orElse(null);

        user.removeCompany(company);
        company.removeUserAssociations();
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

    @Override
    public CompanyDTO getOneCompany(Long companyId) throws CompanyNotFoundByIdException {
        CompanyEntity company = companyRepo.findById(companyId).orElse(null);
        if(company == null) {
            throw new CompanyNotFoundByIdException("Компании с таким Id не существует.");
        }

        return new CompanyDTO(
                company.getId(),
                company.getCompanyName(),
                company.getInn(),
                company.getPhNumber(),
                company.getCategory(),
                company.getSubcategory(),
                company.getAddress(),
                company.getEmail(),
                company.getExperience(),
                company.getUsers()
        );
    }

    private CompanyEntity mapCompany(CompanyEntity company, CompanyEntity updCompany) {
        updCompany.setCategory(company.getCategory()==null ? updCompany.getCategory() : company.getCategory());
        updCompany.setInn(company.getInn()==null ? updCompany.getInn() : company.getInn());
        updCompany.setPhNumber(company.getPhNumber()==null ? updCompany.getPhNumber() : company.getPhNumber());
        updCompany.setEmail(company.getEmail()==null ? updCompany.getEmail() : company.getEmail());
        updCompany.setExperience(company.getExperience()==null ? updCompany.getExperience() : company.getExperience());

        return companyRepo.save(updCompany);
    }

    @Override
    public List<ReviewEntity> getCompanyReviews(Long companyId) throws CompanyNotFoundByIdException {
        CompanyEntity company = companyRepo.findById(companyId).orElse(null);

        if(company == null) {
            throw new CompanyNotFoundByIdException("Компании с таким ID не существует.");
        }


        return company.getReviews();
    }
}
