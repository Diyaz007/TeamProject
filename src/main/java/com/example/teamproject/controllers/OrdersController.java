package com.example.teamproject.controllers;

import com.example.teamproject.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

}
