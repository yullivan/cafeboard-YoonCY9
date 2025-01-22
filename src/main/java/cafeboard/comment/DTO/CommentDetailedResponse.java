package cafeboard.comment.DTO;

import java.time.LocalDateTime;

public record CommentDetailedResponse(Long id,
                                      String userName,
                                      String content,
                                      LocalDateTime createdTime) {
}
