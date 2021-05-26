package caio.caminha.BlogPost.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "post")
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String titulo;
    private String texto;
    private LocalDate data = LocalDate.now();

    public Post(String titulo, String texto){
        this.titulo = titulo;
        this.texto = texto;
    }
}
