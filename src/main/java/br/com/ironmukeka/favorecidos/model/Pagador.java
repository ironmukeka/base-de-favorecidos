package br.com.ironmukeka.favorecidos.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Pagador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;

    @OneToMany(mappedBy = "pagador", cascade=CascadeType.ALL)
    private List<Favorecido> favorecidos = new ArrayList<>();

    public Pagador(){}

    public Pagador(String nome, String cpf){
        this.nome = nome;
        this.cpf = cpf;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Favorecido> getFavorecidos() {
        return favorecidos;
    }

    public void setFavorecidos(List<Favorecido> favorecidos) {
        this.favorecidos = favorecidos;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pagador other = (Pagador) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
