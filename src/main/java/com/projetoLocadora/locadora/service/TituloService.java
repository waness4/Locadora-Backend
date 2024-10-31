package com.projetoLocadora.locadora.service;

import java.util.UUID;

import javax.management.relation.RelationNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetoLocadora.locadora.model.Titulo;
import com.projetoLocadora.locadora.repository.TituloRepository;

@Service
public class TituloService {

    @Autowired
    private TituloRepository repository;

    public Titulo saveAll(Titulo titulo) {
        repository.save(titulo);
        return titulo;
    }

    public Iterable<Titulo> listAll(){
        return repository.findAll();
    }

    public Titulo listId (UUID id) throws  RelationNotFoundException{
        return repository.findById(id).orElseThrow(() -> new RelationNotFoundException("Não existe titulo com este id " + id));
    }

    public void deletarId(UUID id) throws RelationNotFoundException{
        Titulo deletado  = repository.findById(id).orElseThrow(() -> new RelationNotFoundException("Não existe titulo com este id " + id));
        repository.delete(deletado);
    }

    public Titulo editId (Titulo titulo, UUID id) throws RelationNotFoundException{
        Titulo alterado = repository.findById(id).orElseThrow(() -> new RelationNotFoundException("Não existe titulo com este id " + id));
        
        alterado.setAno(titulo.getAno());
        alterado.setAtores(titulo.getAtores());
        alterado.setNome(titulo.getNome());
        alterado.setSinopse(titulo.getSinopse());
        alterado.setCategoria(titulo.getCategoria());
        alterado.setClasse(titulo.getClasse());
        alterado.setDiretor(titulo.getDiretor());
        alterado.setItens(titulo.getItens());

        return repository.save(alterado);
    }
}
