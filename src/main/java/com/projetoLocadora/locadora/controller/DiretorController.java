package com.projetoLocadora.locadora.controller;

import java.util.UUID;

import javax.management.relation.RelationNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetoLocadora.locadora.model.Diretor;
import com.projetoLocadora.locadora.service.DiretorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/diretor")
public class DiretorController {

    @Autowired
    private DiretorService service;

    @PostMapping("/criar")
    @Operation(description = "Dado o nome, cadastra um diretor.", responses = {
            @ApiResponse(responseCode = "200", description = "Caso o diretor seja cadastrado com sucesso."),
            @ApiResponse(responseCode = "400", description = "O servidor não pode processar a requisição devido a alguma coisa que foi entendida como um erro do cliente."),
            @ApiResponse(responseCode = "500", description = "Caso não tenha sido possível realizar a operação.")
    })
    public ResponseEntity<?> salvarDiretor(@RequestBody Diretor novoDiretor) {
        try {
            return ResponseEntity.ok(service.saveAll(novoDiretor));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao criar diretor: " + e.getMessage());
        }
    }

    @GetMapping("/listar")
    @Operation(description = "Listagem de todos os diretores.", responses = {
            @ApiResponse(responseCode = "200", description = "Caso os diretores sejam listados com sucesso."),
            @ApiResponse(responseCode = "400", description = "O servidor não pode processar a requisição devido a alguma coisa que foi entendida como um erro do cliente."),
            @ApiResponse(responseCode = "500", description = "Caso não tenha sido possível realizar a operação.")
    })
    public ResponseEntity<?> obterIdDiretor() throws RelationNotFoundException {
        try {
            return ResponseEntity.ok(service.listAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao listar diretores: " + e.getMessage());
        }
    }

    @GetMapping("/listar/{id}")
    @Operation(description = "Dado o id, lista um diretor.", responses = {
            @ApiResponse(responseCode = "200", description = "Caso o diretor seja listado com sucesso."),
            @ApiResponse(responseCode = "400", description = "O servidor não pode processar a requisição devido a alguma coisa que foi entendida como um erro do cliente."),
            @ApiResponse(responseCode = "500", description = "Caso não tenha sido possível realizar a operação.")
    })
    public ResponseEntity<?> obterIdDiretor(@PathVariable UUID id) throws RelationNotFoundException {
        try {
            return ResponseEntity.ok(service.listId(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao listar diretor: " + e.getMessage());
        }
    }

    @PutMapping("/editar/{id}")
    @Operation(description = "Dado o id, edita um diretor.", responses = {
            @ApiResponse(responseCode = "200", description = "Caso o diretor seja editado com sucesso."),
            @ApiResponse(responseCode = "400", description = "O servidor não pode processar a requisição devido a alguma coisa que foi entendida como um erro do cliente."),
            @ApiResponse(responseCode = "500", description = "Caso não tenha sido possível realizar a operação.")
    })
    public ResponseEntity<?> editarDiretor(@PathVariable UUID id, @RequestBody Diretor diretor)
            throws RelationNotFoundException {
        try {
            return ResponseEntity.ok(service.editId(diretor, id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao editar diretor: " + e.getMessage());
        }
    }

    @DeleteMapping("/deletar/{id}")
    @Operation(description = "Dado o id, deleta um diretor.", responses = {
            @ApiResponse(responseCode = "200", description = "Caso o diretor seja deletado com sucesso."),
            @ApiResponse(responseCode = "400", description = "O servidor não pode processar a requisição devido a alguma coisa que foi entendida como um erro do cliente."),
            @ApiResponse(responseCode = "500", description = "Caso não tenha sido possível realizar a operação.")
    })
    public ResponseEntity<?> deletarDiretor(@PathVariable UUID id) {
        try {
            service.deleteId(id);
            return ResponseEntity.ok("Diretor deletado");
        } catch (RelationNotFoundException erro) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + erro.getMessage());
        }
    }
}
