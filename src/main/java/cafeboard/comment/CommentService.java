package cafeboard.comment;

import cafeboard.comment.DTO.CommentDetailedResponse;
import cafeboard.comment.DTO.CommentResponse;
import cafeboard.comment.DTO.UpdateComment;
import cafeboard.comment.DTO.CreateComment;
import cafeboard.post.Post;
import cafeboard.post.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public CommentDetailedResponse create(CreateComment dto) {
        Post post = postRepository.findById(dto.postId()).orElseThrow(()
                -> new NoSuchElementException("존재하지 않는 게시글 id"));

        Comment comment = new Comment(dto.writer(), dto.content(), post);
        commentRepository.save(comment);
        return new CommentDetailedResponse(
                comment.getId(),
                comment.getWriter(),
                comment.getContent(),
                comment.getCreatedTime());
    }

    public List<CommentResponse> findByCommentList(Long postId) { // 특정 게시판의 댓글리스트 조회
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream().map(c -> new CommentResponse(
                c.getId(),
                c.getWriter(),
                c.getContent())).toList();
    }

    @Transactional
    public void update(Long commentId, UpdateComment dto) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new NoSuchElementException("존재하지 않는 댓글 id" + commentId));

        comment.setComment(dto.writer(), dto.content());
    }

    @Transactional
    public void delete(Long commentId) {
        commentRepository.deleteById(commentId);
    }

}
