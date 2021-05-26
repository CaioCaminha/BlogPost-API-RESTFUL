package caio.caminha.BlogPost.forms;

import caio.caminha.BlogPost.models.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostForm {
    @NotNull@NotEmpty
    private String titulo;
    @NotNull@NotEmpty
    private String texto;
    private LocalDate data = LocalDate.now();

    public Post converteParaPost(){
        return new Post(this.titulo, this.texto, this.data);
    }

    public Post updateParaPost(Post post){
        post.setTitulo(this.titulo);
        post.setTexto(this.texto);
        post.setData(this.data);
        return post;
    }
}
