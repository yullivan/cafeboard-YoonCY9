package cafeboard.post.DTO;

import cafeboard.comment.DTO.CommentResponse;

import java.time.LocalDateTime;
import java.util.List;

public record PostInComment( // 댓글목록 포함 게시글 상세조회 dto
        Long postId,
        String title,
        String content,
        String writer,
        List<CommentResponse> comments) {
}
