package cafeboard.board;

import cafeboard.board.DTO.BoardResponse;
import cafeboard.board.DTO.BoardUpdate;
import cafeboard.board.DTO.CreateBoard;
import cafeboard.board.DTO.CreateBoardResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public CreateBoardResponse create(CreateBoard dto) {  // 게시판 생성
        Board board = new Board(dto.title());
        boardRepository.save(board);
        return new CreateBoardResponse(board.getId(), board.getTitle(), board.getCreatedTime());
    }

    public List<BoardResponse> findAll() { // 모든 게시판 조회
        List<Board> boards = boardRepository.findAll();
        return boards.stream().map(board -> new BoardResponse(board.getId(), board.getTitle())).toList();
    }

    @Transactional
    public void update(Long id, BoardUpdate dto) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new NoSuchElementException("게시판을 찾을 수 없음"));
        board.setTitle(dto.title());
    }

    // 나중에 post를 다 삭제 후 보드를 삭제하도록 변경해야함
    @Transactional
    public void delete(Long id) { // 특정 게시판 삭제
        boardRepository.deleteById(id);
    }

}
