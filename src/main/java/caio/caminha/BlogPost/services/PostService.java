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

    private PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }

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

    public PostDto updatePost(PostForm form, Post post){
                form.updateParaPost(post);
                this.postRepository.save(post);
                return new PostDto(post);
    }





}
