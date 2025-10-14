package com.demos.userservice.service;

import com.demos.shareddto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    public UserDto getUser(Long id) {
      log.info("Getting user with id: {}", id);

      return UserDto
          .builder()
          .id(id)
          .name("Mario")
          .lastName("Rossi")
          .email("mario.rossi@demos.com")
          .phoneNumber("33912345678")
          .nicName("Batman")
          .build();
    }
}