package caio.caminha.BlogPost.controllers;

import caio.caminha.BlogPost.dto.PostDto;
import caio.caminha.BlogPost.forms.PostForm;
import caio.caminha.BlogPost.models.Post;
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

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

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
        return this.postService.updatePost(id, form);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePost(@PathVariable Long id){
        return this.postService.deletePost(id);
    }
}
