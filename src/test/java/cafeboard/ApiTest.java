package cafeboard;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.HashMap;
import java.util.*;

import static org.hamcrest.core.IsEqual.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiTest {

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void 게시판생성테스트() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("title", "테스트 게시판");


        RestAssured.given()
                .contentType(ContentType.JSON) // 요청의 Content-Type 설정
                .body(requestBody) // 요청 본문 설정
                .log().all() // 요청 로그 출력
                .when()
                .post("/boards") // POST 요청 전송
                .then()
                .statusCode(200) // HTTP 상태 코드 검증 (201 Created)
                .log().all();
    }

    //    @Test
//    void 게시판응답데이터Test() {
//        List<boardResponse> responses = RestAssured
//                .given().log().all()
//                .when()
//                .get("/boards")
//                .then().log().all()
//                .statusCode(200)
//                .extract()
//                .as(".",boardResponse.class);
//    }
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
