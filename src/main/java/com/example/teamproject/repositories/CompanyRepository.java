package com.example.teamproject.repositories;

import com.example.teamproject.entity.Company;
import com.example.teamproject.enums.States;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {


    Page<Company> findCompaniesByActiveTrue(Pageable pageable);

    List<Company> findCompaniesByAddress(States address);

    List<Company> findCompanyByName(String name);

    Company findCompanyById(Long id);

}
