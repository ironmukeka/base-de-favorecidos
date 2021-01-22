package br.com.ironmukeka.favorecidos.controller;

import br.com.ironmukeka.favorecidos.model.Pagador;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;
import java.net.URISyntaxException;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PagadorControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void deveriaDevolverJSONVazioCasoDadosDePagadorNaoEncontrado() throws Exception {
        String nome = "Murilo";
        URI uri = new URI("/pagador");

        final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .get(uri)
                .param("nome", nome)
        ).andDo(print())
                .andExpect(MockMvcResultMatchers
                        .content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    public void deveriaRetornarErroAoTentarCadastrarSemOCPF() throws Exception {
        String json = "{ \"nome:\" \"Murilo\"}";
        URI uri = new URI("/pagador");
        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                .status()
                .is(400));
    }
}
