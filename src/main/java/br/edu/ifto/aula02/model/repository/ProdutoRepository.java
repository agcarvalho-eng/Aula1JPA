package br.edu.ifto.aula02.model.repository;

import br.edu.ifto.aula02.model.entity.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
/*
    * @Repository - definir um papel específico para classes que acessam
    * e manipulam dados em uma aplicação.
    *
    * @PersistenceContext - é usada para especificar o EntityManager que
    * precisa ser injetado como dependência.
    *
    * EntityManager - é um serviço responsável por gerenciar as entidades.
    * Por meio dele é possível gerenciar o ciclo de vida das entidades, a
    * operação de sincronização com a base de dados (inserir, atualizar ou
    * remover), a consulta de entidades, entre outras operações.
 */
@Repository
public class ProdutoRepository {
    @PersistenceContext
    private EntityManager em;

    public void save(Produto produto){
        em.persist(produto);
    }

    public Produto produto(Long id){
        return em.find(Produto.class, id);
    }

    public List<Produto> produtos(){
        Query query = em.createQuery("from Produto");
        return query.getResultList();
    }

    public void remove(Long id){
        Produto p = em.find(Produto.class, id);
        em.remove(p);
    }

    public void update(Produto produto){
        em.merge(produto);
    }


}
