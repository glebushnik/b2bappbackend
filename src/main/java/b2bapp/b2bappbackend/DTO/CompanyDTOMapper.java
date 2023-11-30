package b2bapp.b2bappbackend.DTO;

import b2bapp.b2bappbackend.entity.CompanyEntity;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CompanyDTOMapper implements Function<CompanyEntity, CompanyDTO> {

    @Override
    public CompanyDTO apply(CompanyEntity company) {
        return new CompanyDTO (
                company.getId(),
                company.getCompanyName(),
                company.getInn(),
                company.getPhNumber(),
                company.getCompanyTag(),
                company.getAddress(),
                company.getEmail(),
                company.getExperience()
        );
    }
}
