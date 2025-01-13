package cafeboard.comment;

import cafeboard.ApiSetting;
import cafeboard.board.DTO.CreateBoard;
import cafeboard.board.DTO.BoardDetailedResponse;
import cafeboard.comment.DTO.CommentDetailedResponse;
import cafeboard.comment.DTO.CreateComment;
import cafeboard.post.DTO.CreatePost;
import cafeboard.post.DTO.PostDetailedResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CommentTest extends ApiSetting {

    @Test
    void 댓글생성Test() {
        BoardDetailedResponse board = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(new CreateBoard("테스트게시판"))
                .log().all() // 요청 로그 출력
                .when()
                .post("/boards") // POST 요청 전송
                .then()
                .statusCode(200)
                .extract()
                .as(BoardDetailedResponse.class);

        PostDetailedResponse post = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(new CreatePost(board.id(), "테스트제목","테스트내용","테스트이름"))
                .when()
                .post("/posts")
                .then()
                .statusCode(200)
                .extract()
                .as(PostDetailedResponse.class);

        CommentDetailedResponse comment = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(new CreateComment(post.id(), "테스트댓글작성자", "테스트댓글내용"))
                .when()
                .post("/comments")
                .then()
                .statusCode(200)
                .extract()
                .as(CommentDetailedResponse.class);

        assertThat(comment.id()).isEqualTo(1);
    }
}
