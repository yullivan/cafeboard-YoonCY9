package cafeboard.Post.DTO;

public record PostResponse(String title,
                           String content,
                           String writer,
                           Long id) {
}
