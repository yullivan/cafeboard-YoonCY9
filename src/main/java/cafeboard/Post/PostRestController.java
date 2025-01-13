package cafeboard.Post;

import cafeboard.Post.DTO.CreatePost;
import cafeboard.Post.DTO.PostDetailResponse;
import cafeboard.Post.DTO.PostResponse;
import cafeboard.Post.DTO.PostUpdate;
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
    public List<PostResponse> findAll() { // 모든 게시글 조회 (나중에 댓글 개수 추가해야함)
        return postService.findAll();
    }

    @PutMapping("/posts/{postId}")
    public void update(@PathVariable Long postId, @RequestBody PostUpdate post) {
        postService.update(postId,post);
    }

    @DeleteMapping("/posts/{postId}")
    public void delete(@PathVariable Long postId) {
        postService.delete(postId);
    }
}
