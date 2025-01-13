package cafeboard.comment.DTO;

import java.time.LocalDateTime;

public record CommentDetailedResponse(Long id,
                                      String writer,
                                      String content,
                                      LocalDateTime createdTime) {
}
