package com.projetoLocadora.locadora.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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

    private Classe classe;

    private Diretor diretor;

    @ManyToMany (mappedBy="titulos", fetch = FetchType.EAGER)
    private Set<Ator> atores = new HashSet<>();

    @OneToMany(mappedBy= "titulo", cascade=CascadeType.ALL)
    private Set<Item> itens = new HashSet<>();

}
