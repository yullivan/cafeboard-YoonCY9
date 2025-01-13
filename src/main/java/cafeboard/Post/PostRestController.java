package cafeboard.Post;

import cafeboard.Post.DTO.CreatePost;
import cafeboard.Post.DTO.PostDetailResponse;
import cafeboard.Post.DTO.PostResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostRestController {

    private final PostService postService;

    public PostRestController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/posts")
    public PostDetailResponse create(@RequestBody CreatePost post) {
        return postService.create(post);
    }

    @GetMapping("/posts")
    public List<PostResponse> findAll() {
        return postService.findAll();
    }

    @DeleteMapping("/posts/{postId}")
    public void delete(@PathVariable Long postId) {
        postService.delete(postId);
    }
}
