package cafeboard.post.DTO;

public record CreatePost(Long boardId, String title, String content, String writer) {
}
