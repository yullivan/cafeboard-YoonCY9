package cafeboard;

import cafeboard.board.Board;
import cafeboard.board.BoardRepository;
import cafeboard.board.DTO.BoardResponse;
import cafeboard.board.DTO.CreateBoard;
import cafeboard.board.DTO.CreateBoardResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.HashMap;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiTest {

    private final BoardRepository boardRepository;

    @LocalServerPort
    int port;

    @Autowired
    public ApiTest(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void 게시판생성테스트() {
        RestAssured.defaultParser = Parser.JSON;

        CreateBoardResponse response = RestAssured.given()
                .contentType(ContentType.JSON) // 요청의 Content-Type 설정
                .body(new CreateBoard("테스트게시판")) // 요청 본문 설정
                .log().all() // 요청 로그 출력
                .when()
                .post("/boards") // POST 요청 전송
                .then()
                .statusCode(200) // HTTP 상태 코드 검증 (201 Created)
                .extract()
                .as(CreateBoardResponse.class);

        System.out.println(response.createdTime());
        assertThat(response.id()).isEqualTo(1);
    }

    @Test
    void 게시판응답데이터Test() {
        RestAssured.defaultParser = Parser.JSON;

        boardRepository.save(new Board("테스트1"));
        boardRepository.save(new Board("테스트2"));

        List<BoardResponse> responses = RestAssured.given()
                .contentType(ContentType.JSON)
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
//
//    @Test
//    void 특정게시판의게시글목록Test() {
//        BoardByPosts responses = RestAssured
//                .given().log().all()
//                .pathParam("productId",1)
//                .when()
//                .get("/boards/{boardId}")
//                .then().log().all()
//                .statusCode(200)
//                .extract()
//                .as(BoardByPosts.class);
//    }
}
