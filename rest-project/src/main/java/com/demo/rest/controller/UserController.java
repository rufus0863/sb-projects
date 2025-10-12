package com.demo.rest.controller;

import com.demo.rest.model.User;
import com.demo.rest.service.UserService;
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
@Tag(name = "User Management", description = "Operazioni CRUD per la gestione degli utenti")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @Operation(
      summary = "Recupera tutti gli utenti",
      description = "Restituisce la lista completa degli utenti presenti nel sistema"
  )
  @ApiResponse(
      responseCode = "200",
      description = "Lista degli utenti recuperata con successo",
      content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = User.class))
  )
  @GetMapping
  public List<User> getAll() {
    return userService.findAll();
  }

  @Operation(
      summary = "Recupera un utente per ID",
      description = "Restituisce un singolo utente specificando il suo ID"
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Utente trovato",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = User.class))),
      @ApiResponse(responseCode = "404", description = "Utente non trovato")
  })
  @GetMapping("/{id}")
  public ResponseEntity<User> getById(
      @Parameter(description = "ID dell'utente da cercare", required = true)
      @PathVariable Long id) {
    return userService.findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @Operation(
      summary = "Crea un nuovo utente",
      description = "Aggiunge un nuovo utente al sistema"
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Utente creato con successo",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = User.class))),
      @ApiResponse(responseCode = "400", description = "Richiesta non valida")
  })
  @PostMapping
  public User create(
      @Parameter(description = "Dati dell'utente da creare", required = true)
      @RequestBody User user) {
    return userService.create(user);
  }

  @Operation(
      summary = "Aggiorna un utente esistente",
      description = "Aggiorna i dati di un utente identificato dal suo ID"
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Utente aggiornato con successo",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = User.class))),
      @ApiResponse(responseCode = "404", description = "Utente non trovato")
  })
  @PutMapping("/{id}")
  public ResponseEntity<User> update(
      @Parameter(description = "ID dell'utente da aggiornare", required = true)
      @PathVariable Long id,
      @Parameter(description = "Nuovi dati dell'utente", required = true)
      @RequestBody User user) {
    try {
      return ResponseEntity.ok(userService.update(id, user));
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @Operation(
      summary = "Elimina un utente",
      description = "Rimuove un utente dal sistema specificando il suo ID"
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "Utente eliminato con successo"),
      @ApiResponse(responseCode = "404", description = "Utente non trovato")
  })
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(
      @Parameter(description = "ID dell'utente da eliminare", required = true)
      @PathVariable Long id) {
    userService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
