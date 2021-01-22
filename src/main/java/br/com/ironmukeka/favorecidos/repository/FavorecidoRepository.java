package br.com.ironmukeka.favorecidos.repository;

import br.com.ironmukeka.favorecidos.model.Favorecido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavorecidoRepository extends JpaRepository<Favorecido, Long> {

    Page<Favorecido> findByApelido(String apelido, Pageable paginacao);

    Favorecido findByApelido(String apelido);

}
