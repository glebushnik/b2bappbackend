package b2bapp.b2bappbackend.DTO.mapper;

import b2bapp.b2bappbackend.DTO.ReviewDTO;
import b2bapp.b2bappbackend.entity.ReviewEntity;
import b2bapp.b2bappbackend.entity.UserEntity;

import java.time.LocalDateTime;

public class ReviewDTOMapper {
    public ReviewDTOMapper() {
    }


    public ReviewEntity reviewMapper(ReviewDTO reviewDTO, UserEntity user) {
        ReviewEntity review = new ReviewEntity();
        review.setText(reviewDTO.text());
        review.setModerated(Boolean.FALSE);
        review.setRating(reviewDTO.rating());
        review.setCreated(LocalDateTime.now());
        review.setUserName(user.getFirst_name() + " " + user.getLast_name());
        return review;
    }
}
