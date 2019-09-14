package com.chika.server.services;

import org.springframework.stereotype.Service;

@Service
public interface HttpService {

    void get();

    void post();

    void put(String houseIp, String id, int state);

    void delete();
}
