package com.demo.controller;

import com.demo.service.CustomerService;
import com.demo.dto.CustomerRequest;
import com.demo.dto.CustomerResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@Validated
@Tag(name = "Customers", description = "Customer management APIs") // <--- TAG SWAGGER 3
public class CustomerController {

  private final CustomerService service;

  public CustomerController(CustomerService service) {
    this.service = service;
  }

  @Operation(
      summary = "Get all customers",
      description = "Retrieve a list of all customers",
      responses = {
          @ApiResponse(responseCode = "200", description = "List of customers retrieved successfully",
              content = @Content(mediaType = "application/json",
                  schema = @Schema(implementation = CustomerResponse.class)))
      }
  )
  @GetMapping
  public List<CustomerResponse> all() {
    return service.findAll();
  }

  @Operation(
      summary = "Get a customer by ID",
      description = "Retrieve a single customer by its unique identifier",
      responses = {
          @ApiResponse(responseCode = "200", description = "Customer retrieved successfully",
              content = @Content(mediaType = "application/json",
                  schema = @Schema(implementation = CustomerResponse.class))),
          @ApiResponse(responseCode = "404", description = "Customer not found", content = @Content)
      }
  )
  @GetMapping("/{id}")
  public CustomerResponse one(
      @Parameter(description = "Customer ID (must be positive)", example = "1")
      @PathVariable @Positive(message = "id must be positive") Long id) {
    return service.findById(id);
  }

  @Operation(
      summary = "Create a new customer",
      description = "Add a new customer to the system",
      responses = {
          @ApiResponse(responseCode = "201", description = "Customer created successfully",
              content = @Content(mediaType = "application/json",
                  schema = @Schema(implementation = CustomerResponse.class))),
          @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content)
      }
  )
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CustomerResponse create(
      @io.swagger.v3.oas.annotations.parameters.RequestBody(
          description = "Customer data for creation",
          required = true,
          content = @Content(schema = @Schema(implementation = CustomerRequest.class))
      )
      @RequestBody @Valid CustomerRequest request) {
    return service.create(request);
  }

  @Operation(
      summary = "Update a customer",
      description = "Update an existing customer by ID",
      responses = {
          @ApiResponse(responseCode = "200", description = "Customer updated successfully",
              content = @Content(mediaType = "application/json",
                  schema = @Schema(implementation = CustomerResponse.class))),
          @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content),
          @ApiResponse(responseCode = "404", description = "Customer not found", content = @Content)
      }
  )
  @PutMapping("/{id}")
  public CustomerResponse update(
      @Parameter(description = "Customer ID (must be positive)", example = "1")
      @PathVariable @Positive(message = "id must be positive") Long id,
      @io.swagger.v3.oas.annotations.parameters.RequestBody(
          description = "Updated customer data",
          required = true,
          content = @Content(schema = @Schema(implementation = CustomerRequest.class))
      )
      @RequestBody @Valid CustomerRequest request) {
    return service.update(id, request);
  }

  @Operation(
      summary = "Delete a customer",
      description = "Remove a customer by ID",
      responses = {
          @ApiResponse(responseCode = "204", description = "Customer deleted successfully"),
          @ApiResponse(responseCode = "404", description = "Customer not found", content = @Content)
      }
  )
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(
      @Parameter(description = "Customer ID (must be positive)", example = "1")
      @PathVariable @Positive(message = "id must be positive") Long id) {
    service.delete(id);
  }
}
