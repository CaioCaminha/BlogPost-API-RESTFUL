package caio.caminha.BlogPost.services;

import caio.caminha.BlogPost.repositories.PostRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class PostServiceTest {
    private PostService service;

    @Mock
    private PostRepository postRepository;

    @BeforeEach
    public void beforeEach(){
        MockitoAnnotations.openMocks(postRepository);
        this.service = new PostService(postRepository);
    }

    @Test
    @DisplayName(value = "Deve retornar todos os posts cadastrados")
    public void findAllPostsTest(){
        service = new PostService(postRepository);
    }

}
