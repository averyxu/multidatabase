package com.averyxu.controller;

import com.averyxu.model.Order;
import com.averyxu.model.User;
import com.averyxu.repository.OrderRepository;
import com.averyxu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;

    @RequestMapping("/test")
    public String test(){
        User user = new User();
        userRepository.save(user);

        Order order = new Order();
        orderRepository.save(order);

        return "test OK";
    }
}
