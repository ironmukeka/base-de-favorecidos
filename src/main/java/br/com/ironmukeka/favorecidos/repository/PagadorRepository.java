package br.com.ironmukeka.favorecidos.repository;

import br.com.ironmukeka.favorecidos.model.Pagador;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagadorRepository extends JpaRepository<Pagador, Long> {

    Page<Pagador> findByNome(String nome, Pageable paginacao);

    Pagador findByCpf(String cpf);

}
