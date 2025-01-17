package cafeboard.member;

import cafeboard.SecurityUtils;
import cafeboard.member.DTO.CreateMember;
import cafeboard.member.DTO.DeleteMember;
import cafeboard.member.DTO.LoginResponse;
import cafeboard.member.DTO.MemberResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    private final JwtProvider jwtProvider;

    public MemberService(MemberRepository memberRepository, JwtProvider jwtProvider) {
        this.memberRepository = memberRepository;
        this.jwtProvider = jwtProvider;
    }

    public void create(CreateMember dto) {
        Member member = new Member(dto.name(), SecurityUtils.sha256EncryptHex2(dto.password()));
        memberRepository.save(member);
    }

    public String logIn(LoginResponse response) {
        Member member = memberRepository.findByName(response.name());
        if (member.matchPassword(response.password())) {
            return jwtProvider.createToken(response.name());
        } else throw new IllegalStateException("아이디 및 비밀번호가 일치하지 않습니다");
    }

    @Transactional
    public void delete(Long memberId, DeleteMember dto) {
        Member member = memberRepository.findById(memberId).orElseThrow(()
                -> new NoSuchElementException("존재하지 않는 멤버 id" + memberId));

        if (member.matchPassword(dto.password())) {
            memberRepository.deleteById(memberId);
        } else throw new IllegalStateException("비밀번호가 일치하지 않습니다");
    }
}
