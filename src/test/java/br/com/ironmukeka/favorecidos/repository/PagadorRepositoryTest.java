package br.com.ironmukeka.favorecidos.repository;

import br.com.ironmukeka.favorecidos.model.Favorecido;
import br.com.ironmukeka.favorecidos.model.Pagador;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class PagadorRepositoryTest {

    @Autowired
    private PagadorRepository pagadorRepository;

    @Autowired
    private TestEntityManager em;

    @Before
    public void init(){
        Pagador pagador = new Pagador("MURILO", "34925803867");
        em.persist(pagador);
    }

    @Test
    public void deveriaRetornarUmFavorecidorPeloApelido(){

        String cpf = "34925803867";
        Pagador pagador = pagadorRepository.findByCpf(cpf);

        Assert.assertNotNull(pagador);
        Assert.assertEquals(cpf, pagador.getCpf() );
    }

}
