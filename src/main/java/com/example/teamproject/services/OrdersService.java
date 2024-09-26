package com.example.teamproject.services;

import com.example.teamproject.repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdersService {
    @Autowired
    private OrdersRepository ordersRepository;

}
