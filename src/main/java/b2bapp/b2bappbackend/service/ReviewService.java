package b2bapp.b2bappbackend.service;


import b2bapp.b2bappbackend.DTO.ReviewDTO;
import b2bapp.b2bappbackend.entity.ReviewEntity;
import b2bapp.b2bappbackend.exception.company.CompanyNotFoundByIdException;
import b2bapp.b2bappbackend.exception.user.UserNotFoundByIdException;

import java.util.List;

public interface ReviewService {

    ReviewEntity createReview(ReviewDTO review, Long userId, Long companyId) throws UserNotFoundByIdException, CompanyNotFoundByIdException;

    List<ReviewEntity> getAllReviews();
}
