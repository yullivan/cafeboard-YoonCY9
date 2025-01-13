package cafeboard.comment.DTO;

public record CreateComment(Long postId, String writer, String content) {
}
