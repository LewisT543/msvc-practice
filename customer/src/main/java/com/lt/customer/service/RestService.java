package com.lt.customer.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

// Not currently part of the program but this gives us an idea how multiple msvc's will interact with each other.
public interface RestService {

    <T>ResponseEntity<T> get(String url, Class<T> clazz);

    <T>ResponseEntity<T> exchange(String url, HttpMethod method, HttpEntity<?> requestEntity, Class<T> responseType);
}
