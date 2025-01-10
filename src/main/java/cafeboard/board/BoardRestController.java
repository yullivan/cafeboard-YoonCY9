package cafeboard.board;

import cafeboard.board.DTO.BoardResponse;
import cafeboard.board.DTO.CreateBoard;
import cafeboard.board.DTO.CreateBoardResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BoardRestController {

    private final BoardService boardService;

    public BoardRestController(BoardService boardService) {
        this.boardService = boardService;
    }


    @PostMapping("/boards")
    public CreateBoardResponse create(@RequestBody CreateBoard board) {
        return boardService.create(board);
    }

    @GetMapping("/boards")
    public List<BoardResponse> findAll() {
        return boardService.findAll();
    }

    @DeleteMapping("/boards/{boardId}")
    public void delete(@PathVariable Long boardId) {
        boardService.delete(boardId);
    }
}
