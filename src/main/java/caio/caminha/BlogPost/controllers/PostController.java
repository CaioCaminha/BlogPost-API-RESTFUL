package caio.caminha.BlogPost.controllers;

import caio.caminha.BlogPost.dto.PostDto;
import caio.caminha.BlogPost.forms.PostForm;
import caio.caminha.BlogPost.models.Post;
import caio.caminha.BlogPost.repositories.PostRepository;
import caio.caminha.BlogPost.services.PostService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @GetMapping
    public Page<PostDto> listPosts(String titulo,
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10)Pageable pageable){
        return this.postService.findPosts(pageable, titulo);
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody @Valid PostForm form, UriComponentsBuilder uriBuilder){
        Post post = this.postService.savePost(form);
        URI uri = uriBuilder.path("posts/{id}").buildAndExpand(post.getId()).toUri();
        return ResponseEntity.created(uri).body(new PostDto(post));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable Long id, @RequestBody @Valid PostForm form){
        Optional<Post> optionalPost = this.postRepository.findById(id);
        if(optionalPost.isPresent()){
            PostDto postAlterado = this.postService.updatePost(form, optionalPost.get());
            return ResponseEntity.ok(postAlterado);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePost(@PathVariable Long id){
        Optional<Post> optionalPost = this.postRepository.findById(id);
        if(optionalPost.isPresent()){
            this.postRepository.delete(optionalPost.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
