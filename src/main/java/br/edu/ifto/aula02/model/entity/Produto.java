package br.edu.ifto.aula02.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;

/*
    * Programa para CRUD completo com entidade Produto utilizando JPA e ORM.
    * (@Entity) - Informando que esta classe representa uma entidade e que seus objetos devem ser persistidos no banco de dados.
*/
@Entity
public class Produto implements Serializable {

    //Informando que o campo mapeado (no caso o id) será gerado automaticamente pelo banco de dados.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String descricao;
    private double valor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
