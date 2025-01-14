package cafeboard.board;

import cafeboard.board.DTO.BoardResponse;
import cafeboard.board.DTO.UpdateBoard;
import cafeboard.board.DTO.CreateBoard;
import cafeboard.board.DTO.BoardDetailedResponse;
import cafeboard.post.DTO.PostResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BoardRestController {

    private final BoardService boardService;

    public BoardRestController(BoardService boardService) {
        this.boardService = boardService;
    }


    @PostMapping("/boards")
    public BoardDetailedResponse create(@RequestBody CreateBoard board) {
        return boardService.create(board);
    }

    @GetMapping("/boards")
    public List<BoardResponse> findAll() {
        return boardService.findAll();
    }

    @PutMapping("/boards/{boardId}")
    public void update(@PathVariable Long boardId, @RequestBody UpdateBoard board) {
        boardService.update(boardId,board);
    }

    @DeleteMapping("/boards/{boardId}")
    public void delete(@PathVariable Long boardId) {
        boardService.delete(boardId);
    }


}
