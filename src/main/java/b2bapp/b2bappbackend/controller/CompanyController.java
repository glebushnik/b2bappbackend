package b2bapp.b2bappbackend.controller;

import b2bapp.b2bappbackend.entity.CompanyEntity;
import b2bapp.b2bappbackend.entity.UserEntity;
import b2bapp.b2bappbackend.exception.CompanyAlreadyExistsException;
import b2bapp.b2bappbackend.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/new")
    public ResponseEntity createCompany(@RequestBody CompanyEntity company, @RequestParam Long userId){
        try {
            return ResponseEntity.ok().body(companyService.createCompany(company, userId));
        }catch (CompanyAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity getAllCompanies(){
        try {
            List<CompanyEntity> companies = companyService.getAllCompanies();
            return ResponseEntity.ok().body(companies);
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{companyId}/users")
    public ResponseEntity getCompanyUsers(@PathVariable Long companyId) {
        try {
            Set<UserEntity> users = companyService.getCompanyUsers(companyId);
            return ResponseEntity.ok().body(users);
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping()
    public ResponseEntity updateCompany(@RequestBody CompanyEntity company,@RequestParam Long companyId) {
        try {
            return ResponseEntity.ok().body(companyService.updateCompany(company, companyId));
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping()
    public ResponseEntity deleteCompany(@RequestParam Long companyId) {
        try {
            return ResponseEntity.ok().body("Вы успешно удалили компанию.");
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/add/{userId}")
    public ResponseEntity addUsers(@PathVariable Long userId, @RequestParam Long companyId) {
        try{
            return ResponseEntity.ok().body(companyService.addCompanyUser(userId, companyId));
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
