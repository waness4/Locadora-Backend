package com.projetoLocadora.locadora.model;

import java.util.UUID;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

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

    @ManyToMany (fetch = FetchType.EAGER)
        @JoinTable(name = "Titulo_Ator",
        joinColumns = {@JoinColumn(name = "idTitulo")},
        inverseJoinColumns = { @JoinColumn(name = "idAtor")})
    private List<Ator> ator;


}
