package com.demo.controller;

import com.demo.model.User;
import com.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Users", description = "CRUD operations for user management")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @Operation(
      summary = "Retrieve all users",
      description = "Returns the complete list of users in the system"
  )
  @ApiResponse(
      responseCode = "200",
      description = "User list successfully retrieved",
      content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = User.class))
  )
  @GetMapping
  public List<User> getAll() {
    return userService.findAll();
  }

  @Operation(
      summary = "Retrieve a user by ID",
      description = "Returns a single user by specifying their ID"
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "User found",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = User.class))),
      @ApiResponse(responseCode = "404", description = "User not found")
  })
  @GetMapping("/{id}")
  public ResponseEntity<User> getById(
      @Parameter(description = "ID of the user to search for", required = true)
      @PathVariable Long id) {
    return userService.findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @Operation(
      summary = "Create a new user",
      description = "Adds a new user to the system"
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "User successfully created",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = User.class))),
      @ApiResponse(responseCode = "400", description = "Invalid request")
  })
  @PostMapping
  public User create(
      @Parameter(description = "Data of the user to create", required = true)
      @RequestBody User user) {
    return userService.create(user);
  }

  @Operation(
      summary = "Update an existing user",
      description = "Updates the data of a user identified by their ID"
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "User successfully updated",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = User.class))),
      @ApiResponse(responseCode = "404", description = "User not found")
  })
  @PutMapping("/{id}")
  public ResponseEntity<User> update(
      @Parameter(description = "ID of the user to update", required = true)
      @PathVariable Long id,
      @Parameter(description = "New user data", required = true)
      @RequestBody User user) {
    try {
      return ResponseEntity.ok(userService.update(id, user));
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @Operation(
      summary = "Delete a user",
      description = "Removes a user from the system by specifying their ID"
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "User successfully deleted"),
      @ApiResponse(responseCode = "404", description = "User not found")
  })
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(
      @Parameter(description = "ID of the user to delete", required = true)
      @PathVariable Long id) {
    userService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
