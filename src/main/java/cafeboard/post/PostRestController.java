package cafeboard.post;


import cafeboard.post.DTO.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostRestController {

    private final PostService postService;

    public PostRestController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/posts")
    public PostDetailedResponse create(@RequestBody CreatePost post) {
        return postService.create(post);
    }

    @GetMapping("/posts") // 댓글 개수포함 게시판 전체조회
    public List<PostResponse> findAll() { // 모든 게시글 조회 (나중에 댓글 개수 추가해야함)
        return postService.findAll();
    }

    @GetMapping("/posts/{postId}") // 댓글목록 포함 게시판 상세조회
    public PostInComment findByPostId (@PathVariable Long postId) {
        return postService.findByPostId(postId);
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
