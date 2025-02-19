package cafeboard.board;

import cafeboard.board.DTO.BoardResponse;
import cafeboard.board.DTO.UpdateBoard;
import cafeboard.board.DTO.CreateBoard;
import cafeboard.board.DTO.BoardDetailedResponse;
import cafeboard.comment.Comment;
import cafeboard.comment.CommentRepository;
import cafeboard.comment.CommentService;
import cafeboard.post.DTO.PostResponse;
import cafeboard.post.Post;
import cafeboard.post.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final PostRepository postRepository;


    public BoardService(BoardRepository boardRepository, PostRepository postRepository) {
        this.boardRepository = boardRepository;
        this.postRepository = postRepository;

    }

    public BoardDetailedResponse create(CreateBoard dto) {  // 게시판 생성
        Board board = new Board(dto.title());
        boardRepository.save(board);
        return new BoardDetailedResponse(board.getId(), board.getTitle(), board.getCreatedTime());
    }

    public List<BoardResponse> findAll() { // 모든 게시판 조회
        List<Board> boards = boardRepository.findAll();
        return boards.stream()
                .map(board -> new
                        BoardResponse(board.getId(), board.getTitle()))
                .toList();
    }

    @Transactional
    public void update(Long id, UpdateBoard dto) {
        Board board = boardRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("게시판을 찾을 수 없음"));
        board.setTitle(dto.title());
    }

    @Transactional
    public void delete(Long id) { // 특정 게시판 삭제
        boardRepository.deleteById(id);
    }

}
