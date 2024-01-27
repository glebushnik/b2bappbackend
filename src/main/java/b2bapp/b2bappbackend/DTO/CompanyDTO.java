package b2bapp.b2bappbackend.DTO;


import b2bapp.b2bappbackend.entity.UserEntity;

import java.util.Set;

public record CompanyDTO(
        Long id,
        String companyName,
        String inn,
        String phNumber,
        String companyTag,
        String address,
        String email,
        String description,
        Set<UserEntity> users
) {

}
