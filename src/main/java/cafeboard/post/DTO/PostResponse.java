package cafeboard.post.DTO;

public record PostResponse(String title,
                           String content,
                           String writer,
                           Long id,
                           int commentCount) {
}
