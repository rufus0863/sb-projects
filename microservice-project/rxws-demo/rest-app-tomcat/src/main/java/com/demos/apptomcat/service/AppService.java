package com.demos.apptomcat.service;

import com.demos.shareddto.UserDto;
import com.demos.apptomcat.dto.LocalUser;
import com.demos.apptomcat.mapper.MapService;
import com.demos.restclient.service.ClientService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class AppService {

  MapService mapService;

  ClientService clientService;

  public Map<String, Object> getUserDetails(Long id) {
    return Map.of("userId", id, "status", "ACTIVE");
  }

  public Map<String, LocalUser> getOrderDetails(Long id) throws IOException {
    clientService = new ClientService();
    ObjectMapper mapper = new ObjectMapper()
        .configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true)
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    log.info("getOrderDetails: getUserById");
    String json = clientService.getUserById(id);
    UserDto user = mapper.readValue(json, UserDto.class);

    log.info("getOrderDetails: {}", user);
    Map<String, LocalUser> response = new HashMap<>();
    mapService = new MapService();
    response.put("user", mapService.fromRemoteToLocal(user));

    return response;
  }

}
