package com.chika.server.services;

import com.chika.server.payload.DeviceResponse;
import org.springframework.stereotype.Service;

@Service
public interface HttpService {

    void get();

    void post();

    void put(String houseIp, DeviceResponse deviceResponse);

    void delete();
}
