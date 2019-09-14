package com.chika.server.services.implement;

import com.chika.server.services.HttpService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Sy Nguyen
 * @version 1.0
 * @since 13-09-2019
 */
@Service
public class HttpServiceImpl implements HttpService {

    @Override
    public void get() {

    }

    @Override
    public void post() {

    }

    @Override
    public void put(String houseIp, String id, int state) {
        String url = "http://172,29.43.12:8080/device/" + id + "/" + state;

        HttpHeaders headers = new HttpHeaders();
//        headers.set("Authorization", token);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);
    }

    @Override
    public void delete() {

    }
}
