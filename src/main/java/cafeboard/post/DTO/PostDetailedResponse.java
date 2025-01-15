package cafeboard.post.DTO;

import cafeboard.comment.DTO.CommentResponse;

import java.time.LocalDateTime;
import java.util.List;

public record PostDetailedResponse(String title,
                                   String content,
                                   String writer,
                                   LocalDateTime createdTime,
                                   Long id,
                                   List<CommentResponse> comments,
                                   String name,
                                   Long memberId) {
}
