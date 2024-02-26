package b2bapp.b2bappbackend.controller;

import b2bapp.b2bappbackend.DTO.CompanyDTO;
import b2bapp.b2bappbackend.entity.CompanyEntity;
import b2bapp.b2bappbackend.entity.UserEntity;
import b2bapp.b2bappbackend.exception.company.CompanyAlreadyExistsException;
import b2bapp.b2bappbackend.exception.company.CompanyNotFoundByIdException;
import b2bapp.b2bappbackend.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {
    private final CompanyService companyService;
    private final Logger logger = LoggerFactory.getLogger(CompanyController.class);
    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/new")
    public ResponseEntity createCompany(@RequestBody CompanyEntity company, @RequestParam Long userId){
        try {
            return ResponseEntity.ok().body(companyService.createCompany(company, userId));
        }catch (CompanyAlreadyExistsException e) {
            logger.trace(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e){
            logger.trace(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity getAllCompanies(){
        try {
            List<CompanyDTO> companies = companyService.getAllCompanies();
            return ResponseEntity.ok().body(companies);
        } catch(Exception e) {
            logger.trace(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{companyId}/users")
    public ResponseEntity getCompanyUsers(@PathVariable Long companyId) {
        try {
            Set<UserEntity> users = companyService.getCompanyUsers(companyId);
            return ResponseEntity.ok().body(users);
        } catch (CompanyNotFoundByIdException e){
            logger.trace(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch(Exception e) {
            logger.trace(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping()
    public ResponseEntity updateCompany(@RequestBody CompanyEntity company,@RequestParam Long companyId) {
        try {
            return ResponseEntity.ok().body(companyService.updateCompany(company, companyId));
        } catch (CompanyNotFoundByIdException e){
            logger.trace(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (CompanyAlreadyExistsException e){
            logger.trace(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch(Exception e) {
            logger.trace(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{companyId}/{userId}")
    public ResponseEntity deleteCompany(@PathVariable Long companyId, @PathVariable Long userId) {
        try {
            companyService.deleteCompany(companyId, userId);
            return ResponseEntity.ok().body("Вы успешно удалили компанию.");
        } catch(Exception e) {
            logger.trace(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/add/{userId}")
    public ResponseEntity addUsers(@PathVariable Long userId, @RequestParam Long companyId) {
        try{
            return ResponseEntity.ok().body(companyService.addCompanyUser(userId, companyId));
        } catch(Exception e) {
            logger.trace(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity getCompany(@RequestParam Long companyId) {
        try {
            return ResponseEntity.ok().body(companyService.getOneCompany(companyId));
        } catch (CompanyNotFoundByIdException e) {
            logger.trace(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch(Exception e) {
            logger.trace(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
