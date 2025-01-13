package cafeboard.Post;

import cafeboard.Post.DTO.CreatePost;
import cafeboard.Post.DTO.PostDetailResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
