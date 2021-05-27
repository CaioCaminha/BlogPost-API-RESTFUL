package caio.caminha.BlogPost.controllers;

import caio.caminha.BlogPost.forms.PostForm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.net.URI;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PostControllerTest {



    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName(value = "Deve criar um post")
    public void createPostTest() throws Exception{
        URI uri = new URI("/posts");
        PostForm form = PostForm.builder()
                .titulo("correr na praia")
                .texto("correr na praia por 5km")
                .build();

        String json = new ObjectMapper().writeValueAsString(form);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc
                .perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").isNotEmpty())
                .andExpect(jsonPath("titulo").value(form.getTitulo()))
                .andExpect(jsonPath("texto").value(form.getTexto()))
                .andExpect(jsonPath("data").value(form.getData()));
    }

    @Test
    @DisplayName(value = "Deve retornar 400 como resposta")
    public void createIvalidPostTest() throws Exception{
        URI uri = new URI("/posts");
        PostForm form = PostForm.builder()
                .titulo(null)
                .texto(null)
                .build();

        String json = new ObjectMapper().writeValueAsString(form);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(request)
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updatePostTest() throws Exception{
        URI uri = new URI("/posts/2");
        PostForm form = PostForm.builder()
                .titulo("Estudar Spring")
                .texto("Estudar Spring Boot e Testes Unitários")
                .build();

        String json = new ObjectMapper().writeValueAsString(form);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").isNotEmpty())
                .andExpect(jsonPath("titulo").value(form.getTitulo()))
                .andExpect(jsonPath("texto").value(form.getTexto()))
                .andExpect(jsonPath("data").value(form.getData()));

    }
    @Test
    @DisplayName(value = "Deve retornar 400 por erro no input")
    public void updateIvalidFormPostTest() throws Exception{
        URI uri = new URI("/posts/2");
        PostForm form = PostForm.builder()
                .titulo(null)
                .texto(null)
                .build();

        String json = new ObjectMapper().writeValueAsString(form);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(request)
                .andExpect(status().isBadRequest());

    }
    @Test
    @DisplayName(value = "Deve retornar 400 por erro no id")
    public void updateIvalidIdPostTest() throws Exception{
        URI uri = new URI("/posts/9898989898989899990000");
        PostForm form = PostForm.builder()
                .titulo("Estudar Spring")
                .texto("Estudar Spring Boot e todo o spring framework")
                .build();

        String json = new ObjectMapper().writeValueAsString(form);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(request)
                .andExpect(status().isBadRequest());

    }

    @Test
    @DisplayName(value = "Deve retornar 200 ao deletar um registro")
    public void deletePostTest() throws Exception{
        URI uri = new URI("/posts/2");

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete(uri);
        mockMvc.perform(request)
                .andExpect(status().isOk());
    }


}
