package com.example.springboot.service.review;

import com.example.springboot.model.Review;
import com.example.springboot.model.User;
import com.example.springboot.service.IGeneralService;

import java.util.List;

public interface IReviewService extends IGeneralService<Review> {
    List<Review> getReviewByHouseId(Long id);

    void hideReview(Long id);
}
