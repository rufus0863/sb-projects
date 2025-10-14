package com.demos.restclient.service;

import com.demos.restclient.util.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class ClientService {

  public String getUserById(Long userId) throws IOException {
//    String externalUrl = "https://jsonplaceholder.typicode.com/todos/1";

    log.info("http://localhost:8081/users/{}", userId);
    String externalUrl = "http://localhost:8081/users/" + userId;
    log.info(externalUrl);
    return HttpClientUtil.get(externalUrl);
  }

}
