package com.chika.server.services.implement;

import com.chika.server.payload.DeviceResponse;
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
    public void put(String houseIp, DeviceResponse deviceResponse) {
        String url = "http://" + houseIp + ":8080/device";

        HttpHeaders headers = new HttpHeaders();
//        headers.set("Authorization", token);

        HttpEntity<DeviceResponse> entity = new HttpEntity<>(deviceResponse, headers);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);
    }

    @Override
    public void delete() {

    }
}
