package cafeboard.board;

import cafeboard.ApiSetting;
import cafeboard.board.DTO.BoardResponse;
import cafeboard.board.DTO.UpdateBoard;
import cafeboard.board.DTO.CreateBoard;
import cafeboard.board.DTO.BoardDetailedResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardTest extends ApiSetting {

    private final BoardRepository boardRepository;

    @Autowired
    public BoardTest(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Test
    void 게시판생성테스트() {


        BoardDetailedResponse response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(new CreateBoard("테스트게시판"))
                .log().all() // 요청 로그 출력
                .when()
                .post("/boards") // POST 요청 전송
                .then()
                .statusCode(200)
                .extract()
                .as(BoardDetailedResponse.class);

        assertThat(response.id()).isEqualTo(1);
    }

    @Test
    void 게시판응답데이터Test() {


        boardRepository.save(new Board("테스트1"));
        boardRepository.save(new Board("테스트2"));

        List<BoardResponse> responses = RestAssured.given()
                .log().all()
                .when()
                .get("/boards")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList(".", BoardResponse.class);

        assertThat(responses.get(0).title()).isEqualTo("테스트1");
        System.out.println(responses.get(0).id());
    }

    @Test
    void 게시판삭제Test() {


        BoardDetailedResponse 만들었던게시판 = RestAssured.given()
                .contentType(ContentType.JSON) // 요청의 Content-Type 설정
                .body(new CreateBoard("테스트게시판")) // 요청 본문 설정
                .log().all() // 요청 로그 출력
                .when()
                .post("/boards") // POST 요청 전송
                .then()
                .statusCode(200) // HTTP 상태 코드 검증 (201 Created)
                .extract()
                .as(BoardDetailedResponse.class);

        RestAssured.
                given()
                .log().all().pathParam("boardId", 만들었던게시판.id())
                .when()
                .delete("/boards/{boardId}")
                .then().log().all()
                .statusCode(200);

        List<BoardResponse> boardResponses = /*게시판 목록 조회 요청*/
                RestAssured.given()
                        .when()
                        .get("/boards")
                        .then()
                        .statusCode(200)
                        .extract()
                        .jsonPath()
                        .getList(".", BoardResponse.class);

        assertThat(boardResponses).allSatisfy(b -> {
            assertThat(b.id()).isNotEqualTo(만들었던게시판.id());
        });
    }

    @Test
    void 게시판수정Test() {

        BoardDetailedResponse 만들었던게시판 = RestAssured.given()
                .contentType(ContentType.JSON) // 요청의 Content-Type 설정
                .body(new CreateBoard("테스트게시판")) // 요청 본문 설정
                .log().all() // 요청 로그 출력
                .when()
                .post("/boards") // POST 요청 전송
                .then()
                .statusCode(200) // HTTP 상태 코드 검증 (201 Created)
                .extract()
                .as(BoardDetailedResponse.class);

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(new UpdateBoard("수정게시판"))
                .log().all()
                .pathParam("boardId", 만들었던게시판.id())
                .when()
                .put("/boards/{boardId}")
                .then()
                .statusCode(200);

        List<BoardResponse> boardResponses = /*게시판 목록 조회 요청*/
                RestAssured.given()
                        .when()
                        .get("/boards")
                        .then()
                        .statusCode(200)
                        .extract()
                        .jsonPath()
                        .getList(".", BoardResponse.class);


        assertThat(boardResponses.get(0).title()).isEqualTo("수정게시판");
    }
}
