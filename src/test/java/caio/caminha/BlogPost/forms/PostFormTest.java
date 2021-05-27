package caio.caminha.BlogPost.forms;

import caio.caminha.BlogPost.models.Post;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.time.LocalDate;

public class PostFormTest {

    @Test
    public void converteParaPostTest(){
        PostForm form = PostForm
                .builder()
                .titulo("Estudar Spring Boot")
                .texto("Estudar todo o framework Spring")
                .data(LocalDate.now())
                .build();

        Post post = new Post("Estudar", "Estudar Java", LocalDate.now());
        Post updatedPost = form.updateParaPost(post);
        Assertions.assertThat(updatedPost).isNotNull();
        Assertions.assertThat(updatedPost.getTitulo()).isEqualTo(form.getTitulo());
        Assertions.assertThat(updatedPost.getTexto()).isEqualTo(form.getTexto());
        Assertions.assertThat(updatedPost.getData()).isEqualTo(form.getData());
    }

    @Test
    public void updateParaPostTest(){
        PostForm form = PostForm
                .builder()
                .titulo("Estudar Spring Boot")
                .texto("Estudar todo o framework Spring")
                .data(LocalDate.now())
                .build();


        Post updatedPost = form.converteParaPost();
        Assertions.assertThat(updatedPost).isNotNull();
        Assertions.assertThat(updatedPost.getTitulo()).isEqualTo(form.getTitulo());
        Assertions.assertThat(updatedPost.getTexto()).isEqualTo(form.getTexto());
        Assertions.assertThat(updatedPost.getData()).isEqualTo(form.getData());
    }
}
