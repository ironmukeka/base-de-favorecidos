package br.com.ironmukeka.favorecidos.controller.dto;

import br.com.ironmukeka.favorecidos.model.Favorecido;
import org.springframework.data.domain.Page;

public class FavorecidoDto {

    private Long id;
    private String apelido;
    private String banco;
    private String agencia;
    private String conta;
    private String cpfcnpj;

    public FavorecidoDto(Favorecido favorecido){
        this.id = favorecido.getId();
        this.apelido = favorecido.getApelido();
        this.banco = favorecido.getBanco();
        this.agencia = favorecido.getAgencia();
        this.conta = favorecido.getConta();
        this.cpfcnpj = favorecido.getCpfcnpj();
    }

    public Long getId() {
        return id;
    }

    public String getApelido() {
        return apelido;
    }

    public String getBanco() {
        return banco;
    }

    public String getAgencia() {
        return agencia;
    }

    public String getConta() {
        return conta;
    }

    public String getCpfcnpj() {
        return cpfcnpj;
    }

    public static Page<FavorecidoDto> converter(Page<Favorecido> favorecidos) {
        return favorecidos.map(FavorecidoDto::new);
    }

//    public static FavorecidoDto converter(Favorecido favorecido) {
//        return favorecidos.map(FavorecidoDto::new);
//    }

}
