package caio.caminha.BlogPost.services;

import caio.caminha.BlogPost.dto.PostDto;
import caio.caminha.BlogPost.models.Post;
import caio.caminha.BlogPost.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    public Post savePost(PostDto dto){
        Post post = dto.converteParaPost();
        this.postRepository.save(post);
        return post;
    }




}
