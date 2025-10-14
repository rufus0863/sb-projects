package com.demos.apptomcat.mapper;

import com.demos.apptomcat.dto.LocalUser;
import com.demos.shareddto.UserDto;


public class MapService {

  public LocalUser fromRemoteToLocal(UserDto remoteUser) {
    return LocalUser
        .builder()
        .id(remoteUser.getId())
        .nicName(remoteUser.getNicName())
        .email(remoteUser.getEmail())
        .build();
  }
}
