package com.example.teamproject.repositories;

import com.example.teamproject.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    // Можно добавить метод для подсчёта среднего рейтинга по компании
    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.company.id = :companyId")
    Double findAverageRatingByCompanyId(@Param("companyId") Long companyId);
}