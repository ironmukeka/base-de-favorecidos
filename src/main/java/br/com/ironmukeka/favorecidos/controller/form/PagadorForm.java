package br.com.ironmukeka.favorecidos.controller.form;

import br.com.ironmukeka.favorecidos.model.Pagador;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class PagadorForm {

    @ApiModelProperty(notes = "Nome do Pagador/Cliente")
    @NotNull @NotEmpty
    private String nome;

    @ApiModelProperty(notes = "CPF do Pagador/Cliente")
//    @Pattern(regexp = "(^\\d{3}\\x2E\\d{3}\\x2E\\d{3}\\x2D\\d{2}$)")
    @NotNull @NotEmpty
    private String cpf;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Pagador converter(PagadorForm pagador){
        return new Pagador(nome, cpf);
    }
}
