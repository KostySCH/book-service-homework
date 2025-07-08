package com.library.books.client;

import com.library.books.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "http://localhost:8080/v1")
public interface UserClient {
    @GetMapping("/user/{id}")
    UserDto getUserById(@PathVariable("id") Long id);
} 