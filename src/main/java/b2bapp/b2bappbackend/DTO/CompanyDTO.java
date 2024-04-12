package b2bapp.b2bappbackend.DTO;


import b2bapp.b2bappbackend.entity.UserEntity;

import java.util.Set;

public record CompanyDTO(
        Long id,
        String companyName,
        String inn,
        String phNumber,
        String category,
        String address,
        String email,
        String subcategory,
        String description,
        Set<UserEntity> users
) {

}
