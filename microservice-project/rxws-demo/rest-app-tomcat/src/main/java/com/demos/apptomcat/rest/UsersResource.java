package com.demos.apptomcat.rest;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.IOException;
import java.util.Map;

import com.demos.apptomcat.dto.LocalUser;
import com.demos.apptomcat.service.AppService;

@Path("/users")
public class UsersResource {

  @Inject
  private AppService appService;

  @GET
  @Path("/ping/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getUser(@PathParam("id") Long id) {
    Map<String, Object> user = appService.getUserDetails(id);
    return Response.ok(user)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }

  @GET
  @Path("/remote/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getOrder(@PathParam("id") Long id) throws IOException {
    Map<String, LocalUser> user = appService.getOrderDetails(id);
    return Response.ok(user)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }

}
