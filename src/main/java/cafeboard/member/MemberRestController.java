package cafeboard.member;

import cafeboard.member.DTO.CreateMember;
import cafeboard.member.DTO.DeleteMember;
import cafeboard.member.DTO.LoginResponse;
import cafeboard.member.DTO.LoginRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberRestController {

    private final MemberService memberService;

    private final JwtProvider jwtProvider;

    public MemberRestController(MemberService memberService, JwtProvider jwtProvider) {
        this.memberService = memberService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/signup")
    public void create(@Valid @RequestBody CreateMember member) {
        memberService.create(member);
    }

    @PostMapping("/login")
    public LoginRequest login(@RequestBody LoginResponse response) {
        return memberService.login(response);
    }

    @GetMapping("/me")
    public String getProfile(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
        return memberService.getMember(authorization);
    }

    @DeleteMapping("/resign/{memberId}")
    public void delete(@RequestBody DeleteMember member, @PathVariable Long memberId) {
        memberService.delete(memberId, member);
    }


}
