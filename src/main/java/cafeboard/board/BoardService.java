package cafeboard.board;

import cafeboard.board.DTO.CreateBoard;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public void create(CreateBoard dto) {
        Board board = new Board(dto.title());
        boardRepository.save(board);
    }
}
