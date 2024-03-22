package b2bapp.b2bappbackend.service;

import b2bapp.b2bappbackend.DTO.CompanyDTO;
import b2bapp.b2bappbackend.entity.CompanyEntity;
import b2bapp.b2bappbackend.entity.ReviewEntity;
import b2bapp.b2bappbackend.entity.UserEntity;
import b2bapp.b2bappbackend.exception.company.CompanyAlreadyExistsException;
import b2bapp.b2bappbackend.exception.company.CompanyNotFoundByIdException;

import java.util.List;
import java.util.Set;

public interface CompanyService {
    CompanyEntity createCompany(CompanyEntity company, Long userId) throws CompanyAlreadyExistsException;
    List<CompanyDTO> getAllCompanies();
    Set<UserEntity> getCompanyUsers(Long companyId) throws CompanyNotFoundByIdException;
    Set<UserEntity> addCompanyUser(Long userId, Long companyId);
    CompanyEntity updateCompany(CompanyEntity company, Long companyId) throws CompanyNotFoundByIdException, CompanyAlreadyExistsException;
    void deleteCompany(Long companyId, Long userId);
    CompanyDTO getOneCompany(Long companyId) throws CompanyNotFoundByIdException;

    List<ReviewEntity> getCompanyReviews(Long companyId) throws CompanyNotFoundByIdException;
}
