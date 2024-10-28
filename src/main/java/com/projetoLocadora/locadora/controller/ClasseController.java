package com.projetoLocadora.locadora.controller;

import java.util.UUID;

import javax.management.relation.RelationNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetoLocadora.locadora.model.Classe;
import com.projetoLocadora.locadora.service.ClasseService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/classe")
public class ClasseController {

    @Autowired
    private ClasseService service;

    @PostMapping("/criar")
    @Operation(description = "Dado o nome, cadastra uma nova classe.", responses = {
            @ApiResponse(responseCode = "200", description = "Caso a classe seja incluída com sucesso."),
            @ApiResponse(responseCode = "400", description = "O servidor não pode processar a requisição devido a alguma coisa que foi entendida como um erro do cliente."),
            @ApiResponse(responseCode = "500", description = "Caso não tenha sido possível realizar a operação.")
    })
    public ResponseEntity<?> salvarClasse(@RequestBody Classe novaClasse) {
        try {
            return ResponseEntity.ok(service.saveAll(novaClasse));
        } catch (Exception erro) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + erro.getMessage());
        }
    }

    @GetMapping("/listar")
    @Operation(description = "Lista todas as classes.", responses = {
            @ApiResponse(responseCode = "200", description = "Caso as classes sejam listadas com sucesso."),
            @ApiResponse(responseCode = "400", description = "O servidor não pode processar a requisição devido a alguma coisa que foi entendida como um erro do cliente."),
            @ApiResponse(responseCode = "500", description = "Caso não tenha sido possível realizar a operação.")
    })
    public ResponseEntity<?> obterIdClasse() throws RelationNotFoundException {
        try {
            return ResponseEntity.ok(service.listAll());
        } catch (Exception erro) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + erro.getMessage());
        }
    }

    @GetMapping("/listar/{id}")
    @Operation(description = "Dado o id, lista uma classe.", responses = {
            @ApiResponse(responseCode = "200", description = "Caso a classe seja listada com sucesso."),
            @ApiResponse(responseCode = "400", description = "O servidor não pode processar a requisição devido a alguma coisa que foi entendida como um erro do cliente."),
            @ApiResponse(responseCode = "500", description = "Caso não tenha sido possível realizar a operação.")
    })
    public ResponseEntity<?> obterIdClasse(@PathVariable UUID id) throws RelationNotFoundException {
        try {
            return ResponseEntity.ok(service.listId(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao listar classe: " + e.getMessage());
        }
    }

    @PutMapping("/editar/{id}")
    @Operation(description = "Dado o id, edita uma classe.", responses = {
            @ApiResponse(responseCode = "200", description = "Caso a classe seja editada com sucesso."),
            @ApiResponse(responseCode = "400", description = "O servidor não pode processar a requisição devido a alguma coisa que foi entendida como um erro do cliente."),
            @ApiResponse(responseCode = "500", description = "Caso não tenha sido possível realizar a operação.")
    })
    public ResponseEntity<?> editarClasse(@PathVariable UUID id, @RequestBody Classe classe)
            throws RelationNotFoundException {
        try {
            return ResponseEntity.ok(service.editId(classe, id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao editar a classe: " + e.getMessage());
        }
    }

    @DeleteMapping("/deletar/{id}")
    @Operation(description = "Dado o nome, cadastra uma nova classe.", responses = {
            @ApiResponse(responseCode = "200", description = "Caso a classe seja deletada com sucesso."),
            @ApiResponse(responseCode = "400", description = "O servidor não pode processar a requisição devido a alguma coisa que foi entendida como um erro do cliente."),
            @ApiResponse(responseCode = "500", description = "Caso não tenha sido possível realizar a operação.")
    })
    public ResponseEntity<?> deletarClasse(@PathVariable UUID id) {
        try {
            service.deleteId(id);
            return ResponseEntity.ok("Classe deletada");
        } catch (RelationNotFoundException erro) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + erro.getMessage());
        }
    }
}
