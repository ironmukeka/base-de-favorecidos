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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class FavorecidoRepositoryTest {

    @Autowired
    private FavorecidoRepository favorecidoRepository;

    @Autowired
    private TestEntityManager em;

    @Before
    public void init(){
        Pagador pagador = new Pagador("349258","MURILO");
        em.persist(pagador);
        Favorecido favorecido = new Favorecido("Murilo", "dASDas", "321321" , "312321" , "32132", pagador);
        em.persist(favorecido);
    }

    @Test
    public void deveriaRetornarUmFavorecidorPeloApelido(){

        String apelido = "Murilo";
        Favorecido favorecido = favorecidoRepository.findByApelido(apelido);

        Assert.assertNotNull(favorecido);
        Assert.assertEquals(apelido, favorecido.getApelido() );
    }

}
