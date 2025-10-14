package com.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.time.Clock;
import java.util.Locale;

@Configuration
public class BeansConfig {

  @Bean
  public Clock systemClock() {
    return Clock.systemUTC();
  }

  @Bean
  @Profile("dev")
  public String devOnlyBean() {
    return "DEV-BEAN";
  }

  @Bean
  @Profile("prod")
  public String prodOnlyBean() {
    return "PROD-BEAN";
  }

}
