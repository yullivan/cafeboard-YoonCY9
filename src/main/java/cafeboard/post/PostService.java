package cafeboard.post;

import cafeboard.comment.Comment;
import cafeboard.comment.CommentRepository;
import cafeboard.comment.DTO.CommentResponse;
import cafeboard.member.DTO.MemberResponse;
import cafeboard.member.Member;
import cafeboard.member.MemberRepository;
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

    private final MemberRepository memberRepository;

    public PostService(PostRepository postRepository, BoardRepository boardRepository, CommentRepository commentRepository, MemberRepository memberRepository) {
        this.postRepository = postRepository;
        this.boardRepository = boardRepository;
        this.commentRepository = commentRepository;
        this.memberRepository = memberRepository;
    }

    public void create(CreatePost dto) {
        Board board = boardRepository
                .findById(dto.boardId()).orElseThrow
                        (() -> new NoSuchElementException("존재하지 않는 보드 id" + dto.boardId()));

        Member member = memberRepository
                .findById(dto.memberId()).orElseThrow(() ->
                new NoSuchElementException("존재하지 않는 멤버 id" + dto.memberId()));

        Post post = new Post(dto.title(), dto.content(), board, member);
        postRepository.save(post);
    }

    public List<PostResponse> findAll() {  // 모든 게시글 조회
        List<Post> posts = postRepository.findAll();

        return posts.stream()
                .map(p -> new PostResponse(
                        p.getTitle(),
                        p.getContent(),
                        p.getId(),
                        p.commentCount()
                )).toList();
    }

    public List<PostResponse> findByBoardId(Long boardId) { // 해당 게시판의 게시글 조회
        List<Post> posts = postRepository.findByBoardId(boardId);
        return posts.stream().map(p -> new PostResponse(
                p.getTitle(),
                p.getContent(),
                p.getId(),
                p.commentCount())).toList();
    }

    public PostDetailedResponse findByPostId(Long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);  // 게시판 id 로 comment 리스트를 담는 쿼리메서드
        List<CommentResponse> commentResponses =
                comments.stream().map(c -> new CommentResponse(
                        c.getId(),
                        c.getWriter(),
                        c.getContent())).toList();

        Post post = postRepository.findById(postId).orElseThrow(()
                -> new NoSuchElementException("존재하지 않는 post id" + postId));

        Member member = postRepository.findMemberByPostId(postId);

        MemberResponse memberResponse = new MemberResponse(member.getName());

        return new PostDetailedResponse(
                post.getTitle(),
                post.getContent(),
                post.getCreatedTime(),
                post.getId(),
                commentResponses,
                memberResponse
                );
    }


    @Transactional
    public void update(Long postId, UpdatePost dto) {
        Post post = postRepository.findById(postId).orElseThrow(() ->
                new NoSuchElementException("게시글을 찾을수 없음"));

        post.setPost(dto.title(), dto.content(), dto.writer());
    }

    @Transactional
    public void delete(Long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        for (Comment c : comments) {
            commentRepository.delete(c);
        }
        postRepository.deleteById(postId);
    }
}
