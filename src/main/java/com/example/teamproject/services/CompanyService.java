package com.example.teamproject.services;

import com.example.teamproject.entity.Company;
import com.example.teamproject.entity.Review;
import com.example.teamproject.repositories.CompanyRepository;
import com.example.teamproject.repositories.ImageRepository;
import com.example.teamproject.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    public Review addReview(Long companyId, int rating, String comment) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Company not found"));

        Review review = new Review();
        review.setRating(rating);
        review.setComment(comment);
        review.setCompany(company);
        reviewRepository.save(review);
        updateCompanyRating(company);
        return review;
    }

    private void updateCompanyRating(Company company) {
        Double averageRating = reviewRepository.findAverageRatingByCompanyId(Long.valueOf(company.getId()));
        company.setRating(averageRating);
        companyRepository.save(company);
    }
}
