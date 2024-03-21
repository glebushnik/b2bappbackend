package b2bapp.b2bappbackend.service.impl;

import b2bapp.b2bappbackend.DTO.ReviewDTO;
import b2bapp.b2bappbackend.DTO.mapper.ReviewMapper;
import b2bapp.b2bappbackend.entity.CompanyEntity;
import b2bapp.b2bappbackend.entity.ReviewEntity;
import b2bapp.b2bappbackend.entity.UserEntity;
import b2bapp.b2bappbackend.exception.company.CompanyNotFoundByIdException;
import b2bapp.b2bappbackend.exception.user.UserNotFoundByIdException;
import b2bapp.b2bappbackend.repository.CompanyRepo;
import b2bapp.b2bappbackend.repository.ReviewRepo;
import b2bapp.b2bappbackend.repository.UserRepo;
import b2bapp.b2bappbackend.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepo reviewRepo;

    private final UserRepo userRepo;

    private final CompanyRepo companyRepo;

    private final ReviewMapper reviewMapper;

    @Autowired
    public ReviewServiceImpl(ReviewRepo reviewRepo, UserRepo userRepo, CompanyRepo companyRepo, ReviewMapper reviewMapper) {
        this.reviewRepo = reviewRepo;
        this.userRepo = userRepo;
        this.companyRepo = companyRepo;
        this.reviewMapper = reviewMapper;
    }

    @Override
    public ReviewEntity createReview(ReviewDTO reviewDTO, Long userId, Long companyId) throws UserNotFoundByIdException, CompanyNotFoundByIdException {
        UserEntity user = userRepo.findById(userId).orElse(null);
        CompanyEntity company = companyRepo.findById(companyId).orElse(null);
        if(user == null) {
            throw new UserNotFoundByIdException("Пользователя с таким ID не существует.");
        }

        if(company == null) {
            throw new CompanyNotFoundByIdException("Компании с таким ID не существует.");
        }

        var review = reviewMapper.reviewMapper(reviewDTO, user);
        review.setCompany(company);
        company.getReviews().add(review);
        return reviewRepo.save(review);
    }

    @Override
    public List<ReviewEntity> getAllReviews() {
        return reviewRepo.findAll();
    }
}
