package cafeboard.post;

import cafeboard.comment.Comment;
import cafeboard.comment.CommentRepository;
import cafeboard.comment.DTO.CommentResponse;
import cafeboard.post.DTO.*;
import cafeboard.board.Board;
import cafeboard.board.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PostService {

    private final PostRepository postRepository;

    private final BoardRepository boardRepository;

    private final CommentRepository commentRepository;

    public PostService(PostRepository postRepository, BoardRepository boardRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.boardRepository = boardRepository;
        this.commentRepository = commentRepository;
    }

    public PostDetailedResponse create(CreatePost dto) {
        Board board = boardRepository
                .findById(dto.boardId()).orElseThrow
                        (() -> new NoSuchElementException("존재하지 않는 보드 id" + dto.boardId()));

        Post post = new Post(dto.title(), dto.content(), dto.writer(), board);
        postRepository.save(post);
        return new PostDetailedResponse(
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
                        p.getId(),
                        p.commentCount()
                )).toList();
    }

    public PostInComment findByPostId(Long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);  // 게시판 id 로 comment 리스트를 담는 쿼리메서드
        List<CommentResponse> commentResponses =
                comments.stream().map(c -> new CommentResponse(
                        c.getId(),
                        c.getWriter(),
                        c.getContent())).toList();

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new NoSuchElementException("존재하지 않는 post id" + postId));

        return new PostInComment(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getWriter(),
                commentResponses);
    }

    @Transactional
    public void update(Long postId, PostUpdate dto) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new NoSuchElementException("게시글을 찾을수 없음"));

        post.setPost(dto.title(), dto.content(), dto.writer());
    }

    @Transactional
    public void delete(Long postId) {
        postRepository.deleteById(postId);
    }
}
