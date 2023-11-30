package b2bapp.b2bappbackend.DTO;



public record CompanyDTO(
        Long id,
        String companyName,
        String inn,
        String phNumber,
        String companyTag,
        String address,
        String email,
        String description
) {

}
