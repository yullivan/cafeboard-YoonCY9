package cafeboard.post;

import cafeboard.ApiSetting;
import cafeboard.board.DTO.BoardDetailedResponse;
import cafeboard.board.DTO.CreateBoard;
import cafeboard.comment.DTO.CommentDetailedResponse;
import cafeboard.comment.DTO.CreateComment;
import cafeboard.post.DTO.CreatePost;
import cafeboard.post.DTO.PostDetailedResponse;
import cafeboard.post.DTO.PostResponse;
import cafeboard.post.DTO.UpdatePost;
import cafeboard.board.Board;
import cafeboard.board.BoardRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hibernate.query.results.Builders.fetch;

public class PostApiTest extends ApiSetting {

    private final BoardRepository boardRepository;
    private final JPAQueryFactory jpaQueryFactory;

    @Autowired
    public PostApiTest(BoardRepository boardRepository, JPAQueryFactory jpaQueryFactory) {
        this.boardRepository = boardRepository;
        this.jpaQueryFactory = jpaQueryFactory;
    }

//    @Test
//    void 게시글7일이내조회() {
//
//
//        LocalDateTime days7 = LocalDateTime.now().minusDays(7);
//        LocalDateTime now = LocalDateTime.now();
//
//
//
//        jpaQueryFactory.selectFrom(post)
//                .where(
//                        post.createdTime.goe(days7)
//                                .and(post.createdTime.lt(now))
//                )
//                .fetch();
//    }

    //    @Test
//    void 게시글생성Test() {
//
//        Board board = new Board("테스트게시판");
//        boardRepository.save(board);
//
//        CreatePost createPostRequest = new CreatePost(
//                board.getId(),
//                "테스트 게시글",
//                "테스트 내용",
//                "테스트 작성자",
//                1L
//        );
//
//        PostDetailedResponse response = RestAssured.given()
//                .contentType(ContentType.JSON)
//                .body(createPostRequest)
//                .when()
//                .post("/posts")
//                .then()
//                .statusCode(200)
//                .extract()
//                .as(PostDetailedResponse.class);
//
//        assertThat(response.content()).isEqualTo("테스트 내용");
//    }
//
//    @Test
//    void 게시글삭제Test() {
//
//        Board board = new Board("테스트게시판");
//        boardRepository.save(board);
//
//        CreatePost createPostRequest = new CreatePost(
//                board.getId(),
//                "테스트 게시글",
//                "테스트 내용",
//                "테스트 작성자",
//                1L
//        );
//
//        RestAssured.given()
//                .contentType(ContentType.JSON)
//                .body(createPostRequest)
//                .when()
//                .post("/posts")
//                .then()
//                .statusCode(200);
//
//
//        RestAssured.given()
//                .pathParam("postId",1)
//                .when()
//                .delete("/posts/{postId}")
//                .then()
//                .statusCode(200);
//    }
//
//    @Test
//    void 게시글조회Test() {
//
//        Board board = new Board("테스트게시판");
//        boardRepository.save(board);
//
//        CreatePost createPostRequest = new CreatePost(
//                board.getId(),
//                "테스트 게시글",
//                "테스트 내용",
//                "테스트 작성자",
//                1L
//        );
//
//        RestAssured.given()
//                .contentType(ContentType.JSON)
//                .body(createPostRequest)
//                .when()
//                .post("/posts")
//                .then()
//                .statusCode(200);
//
//
//        List<PostResponse> responses = RestAssured.given()
//                .when()
//                .get("/posts")
//                .then()
//                .statusCode(200)
//                .extract()
//                .jsonPath()
//                .getList(".", PostResponse.class);
//
//        assertThat(responses.get(0).title()).isEqualTo("테스트 게시글");
//        assertThat(responses.get(0).commentCount()).isEqualTo(0);
//
//        CommentDetailedResponse comment = RestAssured
//                .given()
//                .contentType(ContentType.JSON)
//                .body(new CreateComment(responses.get(0).id(), "테스트댓글작성자", "테스트댓글내용"))
//                .when()
//                .post("/comments")
//                .then()
//                .statusCode(200)
//                .extract()
//                .as(CommentDetailedResponse.class);
//
//        List<PostResponse> responses2 = RestAssured.given()
//                .when()
//                .get("/posts")
//                .then()
//                .statusCode(200)
//                .extract()
//                .jsonPath()
//                .getList(".", PostResponse.class);
//
//        assertThat(responses2.get(0).commentCount()).isEqualTo(1);
//    }
//
//    @Test
//    void 특정게시판의게시글목록조회Test() {
//
//        BoardDetailedResponse board = RestAssured.given()
//                .contentType(ContentType.JSON)
//                .body(new CreateBoard("테스트게시판"))
//                .log().all() // 요청 로그 출력
//                .when()
//                .post("/boards") // POST 요청 전송
//                .then()
//                .statusCode(200)
//                .extract()
//                .as(BoardDetailedResponse.class);
//
//        RestAssured.given()
//                .contentType(ContentType.JSON)
//                .body(new CreatePost(board.id(), "테스트제목", "테스트내용", "테스트이름"))
//                .when()
//                .post("/posts")
//                .then()
//                .statusCode(200);
//
//        RestAssured.given()
//                .contentType(ContentType.JSON)
//                .body(new CreatePost(board.id(), "테스트제목2", "테스트내용2", "테스트이름2"))
//                .when()
//                .post("/posts")
//                .then()
//                .statusCode(200);
//
//        List<PostResponse> posts = RestAssured.given()
//                .pathParam("boardId", board.id())
//                .when()
//                .get("/posts/boards/{boardId}")
//                .then()
//                .log().ifError()
//                .statusCode(200)
//                .extract()
//                .jsonPath()
//                .getList(".", PostResponse.class);
//
//        assertThat(posts.get(0).content()).isEqualTo("테스트내용");
//        assertThat(posts.get(1).content()).isEqualTo("테스트내용2");
//    }
//
//    @Test
//    void 게시글수정Test() {
//        Board board = new Board("테스트게시판");
//        boardRepository.save(board);
//
//        CreatePost createPostRequest = new CreatePost(
//                board.getId(),
//                "테스트 게시글",
//                "테스트 내용",
//                "테스트 작성자"
//        );
//
//        PostDetailedResponse response = RestAssured.given()
//                .contentType(ContentType.JSON)
//                .body(createPostRequest)
//                .when()
//                .post("/posts")
//                .then()
//                .statusCode(200)
//                .extract()
//                .as(PostDetailedResponse.class);
//
//        RestAssured.given()
//                .contentType(ContentType.JSON)
//                .pathParam("postId",response.id())
//                .body(new UpdatePost("수정한 제목","수정한 내용","테스트이름"))
//                .when()
//                .put("/posts/{postId}")
//                .then()
//                .statusCode(200);
//
//        List<PostResponse> responses = RestAssured.given()
//                .when()
//                .get("/posts")
//                .then()
//                .statusCode(200)
//                .extract()
//                .jsonPath()
//                .getList(".", PostResponse.class);
//
//        assertThat(responses.get(0).content()).isEqualTo("수정한 내용");
//
//    }
}
