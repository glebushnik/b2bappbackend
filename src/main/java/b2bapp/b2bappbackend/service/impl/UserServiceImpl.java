package b2bapp.b2bappbackend.service.impl;

import b2bapp.b2bappbackend.entity.CompanyEntity;
import b2bapp.b2bappbackend.entity.ReviewEntity;
import b2bapp.b2bappbackend.entity.UserEntity;
import b2bapp.b2bappbackend.exception.company.CompanyNotFoundByIdException;
import b2bapp.b2bappbackend.exception.review.ReviewNotFoundByIdException;
import b2bapp.b2bappbackend.exception.user.UserIsNotAdminException;
import b2bapp.b2bappbackend.exception.user.UserNotFoundByIdException;
import b2bapp.b2bappbackend.repository.CompanyRepo;
import b2bapp.b2bappbackend.repository.ReviewRepo;
import b2bapp.b2bappbackend.repository.UserRepo;
import b2bapp.b2bappbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final CompanyRepo companyRepo;
    private final UserRepo userRepo;
    private final ReviewRepo reviewRepo;

    @Autowired
    public UserServiceImpl(CompanyRepo companyRepo, UserRepo userRepo, ReviewRepo reviewRepo) {
        this.companyRepo = companyRepo;
        this.userRepo = userRepo;
        this.reviewRepo = reviewRepo;
    }

    @Override
    public Set<CompanyEntity> getMyCompanies(Long userId) throws UserNotFoundByIdException {
        UserEntity user = userRepo.findById(userId).orElse(null);
        if(user == null) {
            throw new UserNotFoundByIdException("Пользователя с таким Id не существует.");
        }
        return user.getCompanies();
    }

    @Override
    public void deleteCompany(Long userId, Long companyId) throws UserNotFoundByIdException, CompanyNotFoundByIdException {
        CompanyEntity company = companyRepo.findById(companyId).orElse(null);
        if(company == null) {
            throw new CompanyNotFoundByIdException("Компании с таким Id не существует.");
        }

        UserEntity user = userRepo.findById(userId).orElse(null);
        if(user == null) {
            throw new CompanyNotFoundByIdException("Компании с таким Id не существует.");
        }

        user.removeCompany(company);
    }

    @Override
    public void moderateReview(Long userId, Long reviewId) throws UserNotFoundByIdException, ReviewNotFoundByIdException, UserIsNotAdminException {
        UserEntity user = userRepo.findById(userId).orElse(null);
        ReviewEntity review = reviewRepo.findById(reviewId).orElse(null);

        if(user == null) {
            throw new UserNotFoundByIdException("Пользователя с таким ID не существует.");
        }
        if(review == null) {
            throw new ReviewNotFoundByIdException("Отзыва с таким ID не существует.");
        }

        if(!user.getIs_superuser()){
            throw new UserIsNotAdminException("Недостаточно прав.");
        }

        review.setModerated(Boolean.TRUE);
        reviewRepo.save(review);
    }
}
