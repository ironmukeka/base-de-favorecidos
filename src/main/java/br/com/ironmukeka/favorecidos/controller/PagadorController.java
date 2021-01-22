package br.com.ironmukeka.favorecidos.controller;

import br.com.ironmukeka.favorecidos.controller.dto.PagadorDto;
import br.com.ironmukeka.favorecidos.controller.form.PagadorForm;
import br.com.ironmukeka.favorecidos.model.Pagador;
import br.com.ironmukeka.favorecidos.repository.PagadorRepository;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
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
import java.util.Optional;

@RestController
@RequestMapping("/pagador")
public class PagadorController {

    @Autowired
    private PagadorRepository pagadorRepository;

    @Autowired
    private ModelMapper modelMapper;

    @ApiOperation(value = "Retorna todos os pagadores ou localiza o pagador através do nome", response = PagadorDto.class, responseContainer = "Page")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Números de páginas (0..N)", defaultValue = "0"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Número de registros por página.", defaultValue = "10"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")
    })
    @GetMapping(produces = "application/json")
    @Cacheable(value = "listaDePagadores")
    public Page<PagadorDto> lista(@RequestParam(required = false ) String nome,
                                  @PageableDefault(sort = "nome", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao){
        if(nome == null ){
            Page<Pagador> pagadores = pagadorRepository.findAll(paginacao);
            return  PagadorDto.converter(pagadores);
        }else{
            Page<Pagador> pagadores = pagadorRepository.findByNome(nome, paginacao);
            return  PagadorDto.converter(pagadores);
        }
    }

    @ApiOperation(value = "Cadastra um novo pagador", response = PagadorDto.class, responseContainer = "Page")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Números de páginas (0..N)", defaultValue = "0"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Número de registros por página.", defaultValue = "10"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")
    })
    @PostMapping(consumes = "application/json")
    @Transactional
    @CacheEvict(value = "listaDePagadores", allEntries = true)
    public ResponseEntity<PagadorDto> cadastrar(@RequestBody @Valid PagadorForm form, UriComponentsBuilder uriBuilder) {
        Pagador pagador = form.converter(form);
        pagadorRepository.save(pagador);

        URI uri = uriBuilder.path("/favorecidos/{id}").buildAndExpand(pagador.getId()).toUri();
        return ResponseEntity.created(uri).body(new PagadorDto(pagador));
    }

    @ApiOperation(value = "Exclui um pagador", response = ResponseEntity.class, responseContainer = "Page")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Números de páginas (0..N)", defaultValue = "0"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Número de registros por página.", defaultValue = "10"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")
    })
    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listaDePagadores", allEntries = true)
    public ResponseEntity<?> remover(@PathVariable Long id) {
        Optional<Pagador> optional = pagadorRepository.findById(id);
        if (optional.isPresent()) {
            pagadorRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

}
