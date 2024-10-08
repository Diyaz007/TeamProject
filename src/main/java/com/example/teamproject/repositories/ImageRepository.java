package com.example.teamproject.repositories;

import com.example.teamproject.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
     List<Image> findImagesByCompanyId(Long companyId);
     void deleteImagesByCompanyId(Long companyId);
}
