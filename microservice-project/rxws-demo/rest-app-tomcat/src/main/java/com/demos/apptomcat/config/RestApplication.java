package com.demos.apptomcat.config;

import javax.ws.rs.ApplicationPath;

import com.demos.apptomcat.service.AppService;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

@ApplicationPath("/api")
public class RestApplication extends ResourceConfig {
  public RestApplication() {
    packages("com.demos.apptomcat");
    register(new AbstractBinder() {
      @Override
      protected void configure() {
        bind(AppService.class).to(AppService.class);
      }
    });
  }
}
