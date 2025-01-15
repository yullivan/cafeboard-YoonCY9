package cafeboard.post;

import cafeboard.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByBoardId(Long boardId);
    Member findMemberByPostId(Long postId);
}
