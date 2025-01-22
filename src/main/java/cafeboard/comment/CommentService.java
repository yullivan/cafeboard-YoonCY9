package cafeboard.comment;

import cafeboard.comment.DTO.CommentDetailedResponse;
import cafeboard.comment.DTO.CommentResponse;
import cafeboard.comment.DTO.UpdateComment;
import cafeboard.comment.DTO.CreateComment;
import cafeboard.member.Member;
import cafeboard.member.MemberRepository;
import cafeboard.member.MemberService;
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
    private final MemberRepository memberRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository, MemberRepository memberRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.memberRepository = memberRepository;
    }

    public CommentDetailedResponse create(CreateComment dto, String memberName) {
        Post post = postRepository.findById(dto.postId()).orElseThrow(()
                -> new NoSuchElementException("존재하지 않는 게시글 id"));

        Member member = memberRepository.findByName(memberName);

        Comment comment = new Comment(member, dto.content(), post);
        commentRepository.save(comment);
        return new CommentDetailedResponse(
                comment.getId(),
                member.getName(),
                comment.getContent(),
                comment.getCreatedTime());
    }

    public List<CommentResponse> findByCommentList(Long postId) { // 특정 게시판의 댓글리스트 조회
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream().map(c -> new CommentResponse(
                c.getId(),
                c.getMember().getName(),
                c.getContent())).toList();
    }

    @Transactional
    public void update(Long commentId, UpdateComment dto) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new NoSuchElementException("존재하지 않는 댓글 id" + commentId));

        comment.setComment(dto.writer(), dto.content());
    }

    @Transactional
    public void delete(Long commentId, String memberName) {
        Member member = memberRepository.findByName(memberName);
        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new NoSuchElementException("존재하지 않는 commentId" + commentId));
        if (comment.getMember().getName().equals(member.getName())) {
            commentRepository.deleteById(commentId);
        }

    }

}
