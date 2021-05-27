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





}
