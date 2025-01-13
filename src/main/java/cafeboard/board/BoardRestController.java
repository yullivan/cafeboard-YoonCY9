package cafeboard.board;

import cafeboard.board.DTO.BoardResponse;
import cafeboard.board.DTO.BoardUpdate;
import cafeboard.board.DTO.CreateBoard;
import cafeboard.board.DTO.CreateBoardResponse;
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
    public CreateBoardResponse create(@RequestBody CreateBoard board) {
        return boardService.create(board);
    }

    @GetMapping("/boards")
    public List<BoardResponse> findAll() {
        return boardService.findAll();
    }

    @PutMapping("/boards/{boardId}")
    public void update(@PathVariable Long boardId, @RequestBody BoardUpdate board) {
        boardService.update(boardId,board);
    }

    @DeleteMapping("/boards/{boardId}")
    public void delete(@PathVariable Long boardId) {
        boardService.delete(boardId);
    }

    @GetMapping("/boards/{boardId}") // 특정 게시판의 게시글 목록 조회
    public List<PostResponse> findByBoardId(@PathVariable Long boardId) {
        return boardService.findByBoardId(boardId);
    }
}
