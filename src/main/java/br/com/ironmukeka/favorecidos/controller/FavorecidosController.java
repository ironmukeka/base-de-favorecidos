package br.com.ironmukeka.favorecidos.controller;

import br.com.ironmukeka.favorecidos.controller.dto.FavorecidoDto;
import br.com.ironmukeka.favorecidos.controller.dto.PagadorDto;
import br.com.ironmukeka.favorecidos.controller.form.FavorecidoForm;
import br.com.ironmukeka.favorecidos.controller.form.PagadorForm;
import br.com.ironmukeka.favorecidos.model.Favorecido;
import br.com.ironmukeka.favorecidos.model.Pagador;
import br.com.ironmukeka.favorecidos.repository.FavorecidoRepository;
import br.com.ironmukeka.favorecidos.repository.PagadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/favorecidos")
public class FavorecidosController {

    @Autowired
    private FavorecidoRepository favorecidoRepository;

    @Autowired
    private PagadorRepository pagadorRepository;

    @GetMapping
    @Cacheable(value = "listaDeFavorecidos")
    public Page<FavorecidoDto> lista(@RequestParam(required = false ) String apelido,
                                     @PageableDefault(sort = "banco", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao){
        if(apelido == null ){
            Page<Favorecido> favorecidos = favorecidoRepository.findAll(paginacao);
            return  FavorecidoDto.converter(favorecidos);
        }else{
            Page<Favorecido> favorecidos = favorecidoRepository.findByApelido(apelido, paginacao);
            return  FavorecidoDto.converter(favorecidos);
        }
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "listaDeFavorecidos", allEntries = true)
    public ResponseEntity<FavorecidoDto> cadastrar(@RequestBody @Valid FavorecidoForm form, UriComponentsBuilder uriBuilder) {
        Favorecido favorecido = form.converter(form, pagadorRepository);
        if(favorecido != null )
            favorecidoRepository.save(favorecido);
        else
            return ResponseEntity.notFound().build();

        URI uri = uriBuilder.path("/favorecidos/{id}").buildAndExpand(favorecido.getId()).toUri();
        return ResponseEntity.created(uri).body(new FavorecidoDto(favorecido));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listaDeFavorecidos", allEntries = true)
    public ResponseEntity<?> remover(@PathVariable Long id) {
        Optional<Favorecido> optional = favorecidoRepository.findById(id);
        if (optional.isPresent()) {
            favorecidoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

}
