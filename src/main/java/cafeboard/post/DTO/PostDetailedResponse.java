package cafeboard.post.DTO;

import cafeboard.comment.DTO.CommentResponse;
import cafeboard.member.DTO.MemberResponse;

import java.time.LocalDateTime;
import java.util.List;

public record PostDetailedResponse(String title,
                                   String content,
                                   LocalDateTime createdTime,
                                   Long id,
                                   List<CommentResponse> comments,
                                   MemberResponse response) {
}
