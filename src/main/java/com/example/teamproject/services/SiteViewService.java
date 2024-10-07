package com.example.teamproject.services;

import com.example.teamproject.entity.SiteView;
import com.example.teamproject.repositories.SiteViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SiteViewService {
    @Autowired
    private SiteViewRepository siteViewRepository;

    public void update(SiteView siteView){
        SiteView siteView1 = siteViewRepository.findById(1);
        siteView1.setMainText(siteView.getMainText());
        siteView1.setLocationsStat(siteView.getLocationsStat());
        siteView1.setMovingCompanyStat(siteView.getMovingCompanyStat());
        siteView1.setReviewsStat(siteView.getReviewsStat());
        siteView1.setSatisfiedCustomersStat(siteView.getSatisfiedCustomersStat());
        siteView1.setCreatedDate(new Date());
        siteViewRepository.save(siteView1);

    }
    public SiteView getById(long id){
        return siteViewRepository.findById(id);
    }
}
