package cafeboard.comment;

import cafeboard.comment.DTO.CommentDetailedResponse;
import cafeboard.comment.DTO.CreateComment;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/comments/{commentId}")
    public void delete(@PathVariable Long commentId) {
        commentService.delete(commentId);
    }

}
