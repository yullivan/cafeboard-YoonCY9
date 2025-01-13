package cafeboard;

import cafeboard.Post.DTO.CreatePost;
import cafeboard.Post.DTO.PostDetailResponse;
import cafeboard.Post.DTO.PostResponse;
import cafeboard.Post.DTO.PostUpdate;
import cafeboard.board.Board;
import cafeboard.board.BoardRepository;
import cafeboard.board.DTO.BoardResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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

    @Test
    void 게시글조회Test() {

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

        List<PostResponse> responses = RestAssured.given()
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList(".", PostResponse.class);

        assertThat(responses.get(0).title()).isEqualTo("테스트 게시글");
    }

    @Test
    void 게시글수정Test() {
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
                .contentType(ContentType.JSON)
                .pathParam("postId",response.id())
                .body(new PostUpdate("수정한 제목","수정한 내용","테스트이름"))
                .when()
                .put("/posts/{postId}")
                .then()
                .statusCode(200);

        List<PostResponse> responses = RestAssured.given()
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList(".", PostResponse.class);

        assertThat(responses.get(0).title()).isEqualTo("수정한 제목");
        assertThat(responses.get(0).content()).isEqualTo("수정한 내용");
        assertThat(responses.get(0).writer()).isEqualTo("테스트이름");
        System.out.println(responses.get(0).id());
    }
}
