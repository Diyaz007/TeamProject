package com.example.teamproject.repositories;

import com.example.teamproject.entity.SiteView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiteViewRepository extends JpaRepository<SiteView, Long> {
    SiteView findById(long id);
}
