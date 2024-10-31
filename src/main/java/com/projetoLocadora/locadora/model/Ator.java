package com.projetoLocadora.locadora.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Data
@Entity
public class Ator {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @ManyToMany (fetch=FetchType.EAGER)
        @JoinTable(name = "Titulo_Ator",
        joinColumns={@JoinColumn(name = "idAtor")},
        inverseJoinColumns= {@JoinColumn(name="idTitulo")})
    private Set<Titulo> titulos = new HashSet<>();

}
