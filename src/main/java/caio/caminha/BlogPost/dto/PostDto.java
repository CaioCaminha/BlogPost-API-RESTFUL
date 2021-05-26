package caio.caminha.BlogPost.dto;

import caio.caminha.BlogPost.models.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    @NotNull@NotEmpty
    private String titulo;
    @NotNull@NotEmpty
    private String texto;

    public PostDto(Post post) {
        this.titulo = post.getTitulo();
        this.texto = post.getTexto();
    }

    public static Page<PostDto> converteDto(Page<Post> task){
        return task.map(PostDto::new);
    }

    public Post converteParaPost(){
        return new Post(this.titulo, this.texto);
    }

}
