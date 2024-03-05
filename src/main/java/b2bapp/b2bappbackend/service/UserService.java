package b2bapp.b2bappbackend.service;

import b2bapp.b2bappbackend.entity.CompanyEntity;
import b2bapp.b2bappbackend.exception.company.CompanyNotFoundByIdException;
import b2bapp.b2bappbackend.exception.user.UserNotFoundByIdException;

import java.util.Set;

public interface UserService {
    Set<CompanyEntity> getMyCompanies(Long userId) throws UserNotFoundByIdException;

    void deleteCompany(Long companyId, Long userId) throws UserNotFoundByIdException, CompanyNotFoundByIdException;
}
