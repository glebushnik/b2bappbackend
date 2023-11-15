package b2bapp.b2bappbackend.service;

import b2bapp.b2bappbackend.entity.CompanyEntity;

import java.util.Set;

public interface UserService {
    Set<CompanyEntity> getMyCompanies(Long userId);
}
