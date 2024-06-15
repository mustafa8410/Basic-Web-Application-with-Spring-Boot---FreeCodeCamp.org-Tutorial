package com.example.demo.user;

import com.example.demo.run.Run;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

public interface UserHttpClient { //instead of coding UserRestClient, this can be used
    
    // you can use different types of clients, not only http
    // @HttpExchange
    @GetExchange("/users")
    List<User> findAll();
    @GetExchange("/users/{id}")
    User findById(@PathVariable Integer id);
}
