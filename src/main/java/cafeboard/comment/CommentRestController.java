package cafeboard.comment;

import cafeboard.comment.DTO.CommentDetailedResponse;
import cafeboard.comment.DTO.CreateComment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
}
