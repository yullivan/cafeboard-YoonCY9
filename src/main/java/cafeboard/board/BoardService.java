package cafeboard.board;

import cafeboard.board.DTO.BoardResponse;
import cafeboard.board.DTO.CreateBoard;
import cafeboard.board.DTO.CreateBoardResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public CreateBoardResponse create(CreateBoard dto) {
        Board board = new Board(dto.title());
        boardRepository.save(board);
        return new CreateBoardResponse(board.getId(), board.getTitle(), board.getCreatedTime());
    }

    public List<BoardResponse> findAll() {
        List<Board> boards = boardRepository.findAll();
        return boards.stream().map(board -> new BoardResponse(board.getId(), board.getTitle())).toList();
    }

}
