package cafeboard;

import cafeboard.board.QBoard;
import cafeboard.post.QPost;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

@Repository
public class QueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    private final QPost post = QPost.post;

    public QueryRepository(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }
}
