package com.example.demo.user;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class UserRestClient {
    private final RestClient restClient;

    public UserRestClient(RestClient.Builder builder) { //we get a restClientBuilder and build this.restClient with it
        this.restClient = builder.baseUrl("https://jsonplaceholder.typicode.com/").build(); //provide a baseurl for the restclient and build
    }

    public List<User> findAll(){
        return restClient
                .get() //basically post a get request
                .uri("/users")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {}); //to get a list
    }

    public User findById(Integer id){
        return restClient.get()
                .uri("/users/{id}",id)
                .retrieve()
                .body(User.class);
    }
}
