package cafeboard.post.DTO;

public record PostResponse(String title,
                           String content,
                           Long id,
                           int commentCount) {
}
