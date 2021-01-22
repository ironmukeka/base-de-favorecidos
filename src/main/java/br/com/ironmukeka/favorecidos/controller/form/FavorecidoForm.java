package br.com.ironmukeka.favorecidos.controller.form;

import br.com.ironmukeka.favorecidos.model.Favorecido;
import br.com.ironmukeka.favorecidos.model.Pagador;
import br.com.ironmukeka.favorecidos.repository.PagadorRepository;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class FavorecidoForm {

    @ApiModelProperty(notes = "Apelido do Favorecido")
    @NotNull @NotEmpty
    private String apelido;

    @ApiModelProperty(notes = "Banco do Favorecidor")
    @NotNull @NotEmpty
    private String banco;
    @ApiModelProperty(notes = "Agência com digito")
    @NotNull @NotEmpty
    private String agencia;

    @ApiModelProperty(notes = "Conta com digito")
    @NotNull @NotEmpty
    private String conta;

    @ApiModelProperty(notes = "CPF do Favorecido")
//    @Pattern(regexp = "(^\\d{3}\\x2E\\d{3}\\x2E\\d{3}\\x2D\\d{2}$)")
    @NotNull @NotEmpty
    private String cpfcnpj;

    @ApiModelProperty(notes = "CPF do Pagador")
    @NotNull @NotEmpty
    private String pagadorCpf;

    public Favorecido converter(FavorecidoForm favorecidoForm, PagadorRepository pagadorRepository){
            Pagador pagador = pagadorRepository.findByCpf(pagadorCpf);
            if (pagador != null)
                return new Favorecido(apelido, banco, agencia, conta, cpfcnpj, pagador);
            else
                return new Favorecido();
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public void setPagadorCpf(String pagadorCpf) {
        this.pagadorCpf = pagadorCpf;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public void setCpfcnpj(String cpfcnpj) {
        this.cpfcnpj = cpfcnpj;
    }
}
