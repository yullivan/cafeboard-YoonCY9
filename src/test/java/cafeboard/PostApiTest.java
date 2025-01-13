package cafeboard;

import cafeboard.Post.DTO.CreatePost;
import cafeboard.Post.DTO.PostDetailResponse;
import cafeboard.board.Board;
import cafeboard.board.BoardRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class PostApiTest extends ApiSetting{

    private final BoardRepository boardRepository;

    @Autowired
    public PostApiTest(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }


    @Test
    void 게시글생성Test() {

        Board board = new Board("테스트게시판");
        boardRepository.save(board);

        CreatePost createPostRequest = new CreatePost(
                board.getId(),
                "테스트 게시글",
                "테스트 내용",
                "테스트 작성자"
        );

        PostDetailResponse response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(createPostRequest)
                .when()
                .post("/posts")
                .then()
                .statusCode(200)
                .extract()
                .as(PostDetailResponse.class);

        assertThat(response.content()).isEqualTo("테스트 내용");
    }

    @Test
    void 게시글삭제Test() {

        Board board = new Board("테스트게시판");
        boardRepository.save(board);

        CreatePost createPostRequest = new CreatePost(
                board.getId(),
                "테스트 게시글",
                "테스트 내용",
                "테스트 작성자"
        );

        PostDetailResponse response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(createPostRequest)
                .when()
                .post("/posts")
                .then()
                .statusCode(200)
                .extract()
                .as(PostDetailResponse.class);

        RestAssured.given()
                .pathParam("postId",response.id())
                .when()
                .delete("/posts/{postId}")
                .then()
                .statusCode(200);



    }
}
