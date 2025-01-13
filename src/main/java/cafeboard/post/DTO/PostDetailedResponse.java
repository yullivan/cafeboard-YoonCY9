package cafeboard.post.DTO;

import java.time.LocalDateTime;

public record PostDetailedResponse(String title,
                                   String content,
                                   String writer,
                                   LocalDateTime createdTime,
                                   Long id) {
}
