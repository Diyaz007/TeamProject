
package com.example.teamproject.services;

import com.example.teamproject.config.CustomizeAuthenticationSuccessHandler;
import com.example.teamproject.entity.Company;
import com.example.teamproject.entity.Image;
import com.example.teamproject.entity.Review;
import com.example.teamproject.entity.Users;
import com.example.teamproject.exceptions.FileException;
import com.example.teamproject.exceptions.PhoneNumberException;
import com.example.teamproject.repositories.CompanyRepository;
import com.example.teamproject.repositories.ImageRepository;
import com.example.teamproject.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private CustomizeAuthenticationSuccessHandler customizeAuthenticationSuccessHandler;
    @Autowired
    private DaoAuthenticationProvider authenticationProvider;
    @Autowired
    private UsersService usersService;

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
    public boolean isCompanyNameTaken(String name) {
        return companyRepository.findCompanyByName(name) != null;
    }

    private static final Pattern phone = Pattern.compile(
            "^\\+(\\d{1,3})( )?\\(\\d{1,4}\\)( )?\\d{3,4}( )?\\d{3,4}$"
    );

    public void saveCompany(Company company, MultipartFile file) throws IOException, FileException, PhoneNumberException {
        Image image;
        if (file.getSize() != 0) {
            image = toImageEntity(file);
            image.setPreviewImage(true);
            company.addImageToCompany(image);
        }
        if(!phone.matcher(company.getPhoneNumber()).matches() || company.getPhoneNumber() == null){
            throw new PhoneNumberException();
        }
        if(file.getSize() == 0){
            throw new FileException();
        }
        Double averageRating = 0.0;
        company.setRating(averageRating);

        company.setActive(true);

        company.setCreatedDate(new Date());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users users = usersService.findByUserName(authentication.getName());
        company.setUsersID(users);

        Company companyFromDB = companyRepository.save(company);
        companyFromDB.setPreviewImageId(companyFromDB.getImages().get(0).getId());
        companyRepository.save(company);
    }

    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }

    public Page<Company> getAllActiveCompanies(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return companyRepository.findCompaniesByActiveTrue(pageable);
    }

    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }

    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }
    public void updateCompany(Long id,Company company, MultipartFile file) throws IOException, PhoneNumberException, FileException {
        Company company1 = companyRepository.findCompanyById(id);
        if (file.getSize() != 0) {
            Image image = toImageEntity(file);
            image.setPreviewImage(true);
            company1.getImages().set(0, image);
            company1.addImageToCompany(image);
            imageRepository.save(image);
        }
        if(!phone.matcher(company.getPhoneNumber()).matches() || company.getPhoneNumber() == null){
            throw new PhoneNumberException();
        }
        if(file.getSize() == 0){
            throw new FileException();
        }
        company1.setName(company.getName());
        company1.setPhoneNumber(company.getPhoneNumber());
        company1.setAddress(company.getAddress());
        company1.setInfo(company.getInfo());

        company1.setPreviewImageId(company1.getImages().get(0).getId());

        companyRepository.save(company1);
    }
}
