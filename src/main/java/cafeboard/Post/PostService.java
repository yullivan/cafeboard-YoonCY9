package cafeboard.Post;

import cafeboard.Post.DTO.CreatePost;
import cafeboard.Post.DTO.PostDetailResponse;
import cafeboard.Post.DTO.PostResponse;
import cafeboard.board.Board;
import cafeboard.board.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PostService {

    private final PostRepository postRepository;

    private final BoardRepository boardRepository;

    public PostService(PostRepository postRepository, BoardRepository boardRepository) {
        this.postRepository = postRepository;
        this.boardRepository = boardRepository;
    }

    public PostDetailResponse create(CreatePost dto) {
        Board board = boardRepository
                .findById(dto.boardId()).orElseThrow
                        (() -> new NoSuchElementException("존재하지 않는 보드 id" + dto.boardId()));

        Post post = new Post(dto.title(), dto.content(), dto.writer(), board);
        postRepository.save(post);
        return new PostDetailResponse(
                post.getTitle(),
                post.getContent(),
                post.getWriter(),
                post.getCreatedTime(),
                post.getId());
    }

    public List<PostResponse> findAll() {  // 모든 게시글 조회
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(p -> new PostResponse(
                        p.getTitle(),
                        p.getContent(),
                        p.getWriter(),
                        p.getId()
                )).toList();
    }

    public void delete(Long postId) {
        postRepository.deleteById(postId);
    }
}
