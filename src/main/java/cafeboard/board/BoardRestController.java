package cafeboard.board;

import cafeboard.board.DTO.CreateBoard;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardRestController {

    private final BoardService boardService;

    public BoardRestController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("/boards")
    public void create(CreateBoard board) {
        boardService.create(board);
    }
}
