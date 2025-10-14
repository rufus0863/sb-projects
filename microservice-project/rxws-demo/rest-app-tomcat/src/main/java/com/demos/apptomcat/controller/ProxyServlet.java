package com.demos.apptomcat.controller;

import com.demos.apptomcat.service.AppService;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@Slf4j
public class ProxyServlet extends HttpServlet {

  private AppService appService;

  public ProxyServlet() {
    this.appService = new AppService();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {

    log.info("ProxyServlet: getOrderDetails");
    Map<String, Object> response = appService.getUserDetails(13L);
    log.info("ProxyServlet: {}", response);

    resp.setContentType("application/json;charset=UTF-8");
    try (PrintWriter out = resp.getWriter()) {
      out.print(response);
    }
  }


}