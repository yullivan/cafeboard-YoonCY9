package cafeboard.member;

import cafeboard.member.DTO.CreateMember;
import cafeboard.member.DTO.DeleteMember;
import jakarta.persistence.Entity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberRestController {

    private final MemberService memberService;

    public MemberRestController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/signup")
    public void signUp(@RequestBody CreateMember member) {
        memberService.signUp(member);
    }

    @DeleteMapping("/resign/{memberId}")
    public void resign(@RequestBody DeleteMember member, @PathVariable Long memberId) {
        memberService.resign(memberId, member);
    }
}
