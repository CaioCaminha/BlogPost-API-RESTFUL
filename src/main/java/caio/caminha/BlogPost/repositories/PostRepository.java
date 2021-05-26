package caio.caminha.BlogPost.repositories;

import caio.caminha.BlogPost.models.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByTitulo(String titulo, Pageable pageable);
}
