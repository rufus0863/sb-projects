package com.demo.config;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;

@Data
@Validated
@ConfigurationProperties(prefix = "app")
public class AppProperties {

  @NotBlank
  private String name;
  @NotBlank
  private String greeting;
  private Feature feature = new Feature();
  private Map<String, String> metadata;
  private List<String> supportedLocales;

  @Min(1)
  private int threadPoolSize = 4;

  @Data
  public static class Feature {
    private boolean awesome = true;
    private boolean beta = false;
  }

}
