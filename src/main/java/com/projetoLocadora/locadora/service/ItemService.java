package com.projetoLocadora.locadora.service;

import java.util.UUID;

import javax.management.relation.RelationNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetoLocadora.locadora.model.Item;
import com.projetoLocadora.locadora.repository.ItemRepository;

@Service
public class ItemService {

    @Autowired
    private ItemRepository repository;

    public Item saveAll(Item item) {
        repository.save(item);
        return item;
    }

    public Iterable<Item> listAll(){
        return repository.findAll();
    }

    public Item listId (UUID id) throws  RelationNotFoundException{
        return repository.findById(id).orElseThrow(() -> new RelationNotFoundException("Não existe Item com este id " + id));
    }

    public void deletarId(UUID id) throws RelationNotFoundException{
        Item deletado  = repository.findById(id).orElseThrow(() -> new RelationNotFoundException("Não existe Item com este id " + id));
        repository.delete(deletado);
    }

    public Item editId (Item item, UUID id) throws RelationNotFoundException{
        Item alterado = repository.findById(id).orElseThrow(() -> new RelationNotFoundException("Não existe Item com este id " + id));
        
        alterado.setNumSerie(item.getNumSerie());
        alterado.setTipoItem(item.getTipoItem());
        alterado.setDtAquisicao(item.getDtAquisicao());
        alterado.setTitulo(item.getTitulo());

        return repository.save(alterado);
    }
}
