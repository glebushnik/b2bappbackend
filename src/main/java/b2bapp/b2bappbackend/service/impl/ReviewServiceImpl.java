package b2bapp.b2bappbackend.service.impl;

import b2bapp.b2bappbackend.DTO.ReviewDTO;
import b2bapp.b2bappbackend.DTO.mapper.ReviewDTOMapper;
import b2bapp.b2bappbackend.entity.CompanyEntity;
import b2bapp.b2bappbackend.entity.ReviewEntity;
import b2bapp.b2bappbackend.entity.UserEntity;
import b2bapp.b2bappbackend.exception.company.CompanyNotFoundByIdException;
import b2bapp.b2bappbackend.exception.review.ReviewNotFoundByIdException;
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

    private final ReviewDTOMapper reviewDTOMapper;

    @Autowired
    public ReviewServiceImpl(ReviewRepo reviewRepo, UserRepo userRepo, CompanyRepo companyRepo, ReviewDTOMapper reviewDTOMapper) {
        this.reviewRepo = reviewRepo;
        this.userRepo = userRepo;
        this.companyRepo = companyRepo;
        this.reviewDTOMapper = reviewDTOMapper;
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

        var review = reviewDTOMapper.reviewMapper(reviewDTO, user);
        review.setCompany(company);
        company.getReviews().add(review);
        return reviewRepo.save(review);
    }

    @Override
    public List<ReviewEntity> getAllReviews() {
        return reviewRepo.findAll();
    }

    @Override
    public void deleteReview(Long companyId, Long reviewId) throws CompanyNotFoundByIdException, ReviewNotFoundByIdException {
        CompanyEntity company = companyRepo.findById(companyId).orElse(null);
        ReviewEntity review = reviewRepo.findById(reviewId).orElse(null);

        if(company == null) {
            throw new CompanyNotFoundByIdException("Компании с таким ID не существует.");
        }

        if(review == null) {
            throw new ReviewNotFoundByIdException("Отзыва с таким ID не существует.");
        }

        reviewRepo.deleteById(reviewId);
        company.getReviews().remove(review);
    }

    @Override
    public ReviewEntity getReviewById(Long reviewId) throws ReviewNotFoundByIdException {
        ReviewEntity review = reviewRepo.findById(reviewId).orElse(null);

        if(review == null) {
            throw new ReviewNotFoundByIdException("Отзыва с таким ID не существует.");
        }

        return review;
    }
}
