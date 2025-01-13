package cafeboard.Post.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record PostDetailResponse(String title,
                                 String content,
                                 String writer,
                                 LocalDateTime createdTime,
                                 Long id) {
}
