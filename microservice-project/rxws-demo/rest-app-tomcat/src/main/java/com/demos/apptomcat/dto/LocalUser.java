package com.demos.apptomcat.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LocalUser {
  private Long id;
  private String nicName;
  private String email;
}
