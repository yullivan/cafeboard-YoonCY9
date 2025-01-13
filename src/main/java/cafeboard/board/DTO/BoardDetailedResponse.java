package cafeboard.board.DTO;

import java.time.LocalDateTime;

public record BoardDetailedResponse(
        Long id,
        String title,
        LocalDateTime createdTime
) {
}
