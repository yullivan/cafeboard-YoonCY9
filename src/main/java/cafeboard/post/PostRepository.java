package cafeboard.post;

import cafeboard.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByBoardId(Long boardId);
    @Query("SELECT p.member FROM Post p WHERE p.id = :postId")
    Member findMemberByPostId(@Param("postId") Long postId);
}
