package cafeboard.member;

import cafeboard.ApiSetting;
import cafeboard.member.DTO.CreateMember;
import cafeboard.member.DTO.LoginRequest;
import cafeboard.member.DTO.LoginResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;

import static org.assertj.core.api.Assertions.assertThat;

public class MemberTest extends ApiSetting {

    @Test
    void 프로필조회() {
        String name = "윤찬영";
        String password = "1234";

        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(new CreateMember(name, password))
                .when()
                .post("/signup")
                .then().log().all()
                .statusCode(200);

        LoginRequest accessToken = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(new LoginResponse(name, password))
                .when()
                .post("/login")
                .then().log().all()
                .statusCode(200)
                .extract()
                .as(LoginRequest.class);

        // when & then
        RestAssured.given().log().all()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken.hashPassword())
                .when()
                .get("/me")
                .then().log().all()
                .statusCode(200);
    }
}
