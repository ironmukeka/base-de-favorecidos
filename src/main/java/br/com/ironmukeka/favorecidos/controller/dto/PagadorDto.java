package br.com.ironmukeka.favorecidos.controller.dto;

import br.com.ironmukeka.favorecidos.model.Favorecido;
import br.com.ironmukeka.favorecidos.model.Pagador;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class PagadorDto {

    private Long id;
    private String nome;
    private String cpf;
    private List<FavorecidoDto> favorecidos = new ArrayList<>();

    public PagadorDto(Pagador pagador){
        this.id = pagador.getId();
        this.nome = pagador.getNome();
        this.cpf = pagador.getCpf();
        this.favorecidos.addAll(pagador.getFavorecidos().stream().map(FavorecidoDto::new).collect(Collectors.toList()));


    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public List<FavorecidoDto> getFavorecidos() {
        return favorecidos;
    }

    public static Page<PagadorDto> converter(Page<Pagador> pagadores) {
        return pagadores.map(PagadorDto::new);
    }
}
