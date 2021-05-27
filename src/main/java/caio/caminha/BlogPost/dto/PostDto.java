package caio.caminha.BlogPost.dto;

import caio.caminha.BlogPost.models.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private Long id;
    private String titulo;
    private String texto;
    private LocalDate data;

    public PostDto(Post post) {
        this.id = post.getId();
        this.titulo = post.getTitulo();
        this.texto = post.getTexto();
        this.data = post.getData();
    }

    public static Page<PostDto> converteDto(Page<Post> posts){
        return posts.map(PostDto::new);
    }


}
