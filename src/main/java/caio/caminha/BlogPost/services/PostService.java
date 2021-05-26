package caio.caminha.BlogPost.services;

import caio.caminha.BlogPost.dto.PostDto;
import caio.caminha.BlogPost.forms.PostForm;
import caio.caminha.BlogPost.models.Post;
import caio.caminha.BlogPost.repositories.PostRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Page<PostDto> findPosts(Pageable pageable, String titulo){
        if(titulo == null){
            Page<Post> posts = this.postRepository.findAll(pageable);
            return PostDto.converteDto(posts);
        }
            Page<Post> posts = this.postRepository.findByTitulo(titulo, pageable);
            return PostDto.converteDto(posts);
    }

    public Post savePost(PostForm form){
        Post post = form.converteParaPost();
        this.postRepository.save(post);
        return post;
    }

    public ResponseEntity<PostDto> updatePost(Long id, PostForm form){
        Optional<Post> optionalPost = this.postRepository.findById(id);
            if(optionalPost.isPresent()) {
                Post post = optionalPost.get();
                form.updateParaPost(post);
                this.postRepository.save(post);
                return ResponseEntity.ok(new PostDto(post));
            }
        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity deletePost(Long id){
        Optional<Post> optionalPost = this.postRepository.findById(id);
        if(optionalPost.isPresent()){
            Post post = optionalPost.get();
            this.postRepository.delete(post);
            return ResponseEntity.ok("Deletado com sucesso!");
        }
        return ResponseEntity.badRequest().build();
    }




}
