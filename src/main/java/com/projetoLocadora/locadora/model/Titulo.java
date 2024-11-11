package com.projetoLocadora.locadora.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Titulo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idTitulo;

    private String nome;

    private int ano;

    private String sinopse;

    private String categoria;

    @ManyToOne
    private Classe classe;

    @ManyToOne
    private Diretor diretor;

    @ManyToMany (mappedBy="titulos", fetch = FetchType.EAGER)
    private Set<Ator> atores = new HashSet<>();

}
