package cafeboard.board.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CreateBoardResponse(
        Long id,
        String title,
        LocalDateTime createdTime

) {
}
