package caio.caminha.BlogPost.services;

import caio.caminha.BlogPost.dto.PostDto;
import caio.caminha.BlogPost.forms.PostForm;
import caio.caminha.BlogPost.models.Post;
import org.apache.catalina.LifecycleState;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostServiceTest {

    @Autowired(required = true)
    private PostService service;

    private PostForm form;

    @BeforeEach
    public void beforeEach(){
        this.form = new PostForm("correr amanhã", "correr 5km na praia", LocalDate.now());
    }

    @Test
    @DisplayName(value = "DeveSalvarUmPostNoBancoDeDados")
    public void savePostTest(){
        Post savedPost = this.service.savePost(this.form);
        Assertions.assertThat(savedPost.getId()).isNotNull();
        Assertions.assertThat(savedPost.getTitulo()).isEqualTo("correr amanhã");
        Assertions.assertThat(savedPost.getTexto()).isEqualTo("correr 5km na praia");
        Assertions.assertThat(savedPost.getData()).isEqualTo(LocalDate.now());
    }


}
