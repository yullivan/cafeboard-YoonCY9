//package cafeboard.comment;
//
//import cafeboard.ApiSetting;
//import cafeboard.board.DTO.CreateBoard;
//import cafeboard.board.DTO.BoardDetailedResponse;
//import cafeboard.comment.DTO.CommentDetailedResponse;
//import cafeboard.comment.DTO.UpdateComment;
//import cafeboard.comment.DTO.CreateComment;
//import cafeboard.post.DTO.CreatePost;
//import cafeboard.post.DTO.PostResponse;
//import io.restassured.RestAssured;
//import io.restassured.http.ContentType;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//public class CommentTest extends ApiSetting {
//
//    @Test
//    void 댓글생성Test() {
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
//                .body(new CreatePost(board.id(), "테스트제목", "테스트내용"))
//                .when()
//                .post("/posts")
//                .then()
//                .statusCode(200);
//
//
//        CommentDetailedResponse comment = RestAssured
//                .given()
//                .contentType(ContentType.JSON)
//                .body(new CreateComment(1L, "테스트댓글작성자"))
//                .when()
//                .post("/comments")
//                .then()
//                .statusCode(200)
//                .extract()
//                .as(CommentDetailedResponse.class);
//
//        assertThat(comment.id()).isEqualTo(1);
//    }
//
//    @Test
//    void 댓글삭제Test() {
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
//                .body(new CreatePost(board.id(), "테스트제목", "테스트내용"))
//                .when()
//                .post("/posts")
//                .then()
//                .statusCode(200);
//
//
//        CommentDetailedResponse comment = RestAssured
//                .given()
//                .contentType(ContentType.JSON)
//                .body(new CreateComment(1L, "테스트댓글작성자"))
//                .when()
//                .post("/comments")
//                .then()
//                .statusCode(200)
//                .extract()
//                .as(CommentDetailedResponse.class);
//
//        RestAssured.given()
//                .pathParam("commentId", comment.id())
//                .when()
//                .delete("/comments/{commentId}")
//                .then()
//                .statusCode(200);
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
//        assertThat(responses2.get(0).commentCount()).isEqualTo(0);
//    }
//
//    @Test
//    void 댓글수정Test() {
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
//                .body(new CreatePost(board.id(), "테스트제목", "테스트내용"))
//                .when()
//                .post("/posts")
//                .then()
//                .statusCode(200);
//
//
//        CommentDetailedResponse comment = RestAssured
//                .given()
//                .contentType(ContentType.JSON)
//                .body(new CreateComment(1L, "테스트댓글작성자"))
//                .when()
//                .post("/comments")
//                .then()
//                .statusCode(200)
//                .extract()
//                .as(CommentDetailedResponse.class);
//
//        RestAssured.given().contentType(ContentType.JSON)
//                .pathParam("commentId", comment.id())
//                .body(new UpdateComment("수정한작성자", "수정한내용"))
//                .when()
//                .put("/comments/{commentId}")
//                .then()
//                .statusCode(200);
//    }
//}
