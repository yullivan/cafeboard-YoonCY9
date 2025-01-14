package cafeboard.comment;

import cafeboard.comment.DTO.CommentDetailedResponse;
import cafeboard.comment.DTO.CommentResponse;
import cafeboard.comment.DTO.UpdateComment;
import cafeboard.comment.DTO.CreateComment;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentRestController {

    private final CommentService commentService;

    public CommentRestController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comments")
    public CommentDetailedResponse create(@RequestBody CreateComment comment) {
        return commentService.create(comment);
    }

    @GetMapping("/comments/posts/{postId}") // 특정 게시판의 댓글목록 조회
    public List<CommentResponse> findByCommetList(@PathVariable Long postId) {
        return commentService.findByCommentList(postId);
    }

    @PutMapping("/comments/{commentId}")
    public void update(@PathVariable Long commentId, @RequestBody UpdateComment comment) {
        commentService.update(commentId,comment);
    }

    @DeleteMapping("/comments/{commentId}")
    public void delete(@PathVariable Long commentId) {
        commentService.delete(commentId);
    }

}
