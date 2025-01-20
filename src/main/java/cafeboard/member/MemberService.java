package cafeboard.member;

import cafeboard.SecurityUtils;
import cafeboard.member.DTO.*;
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

    public LoginRequest login(LoginResponse response) {
        Member member = memberRepository.findByName(response.name());
        if (member == null) { // 아이디가 다를 경우 member를 찾지 못해 비어있으면 오류메세지 출력
            throw new IllegalStateException("아이디 및 비밀번호가 일치하지 않습니다");
        }
        if (member.matchPassword(response.password())) {
            return new LoginRequest(jwtProvider.createToken(response.name()));
        } else throw new IllegalStateException("아이디 및 비밀번호가 일치하지 않습니다");
    }

    public String getMember(String authorization) {
        String[] tokenFormat = authorization.split(" ");

        String tokenType = tokenFormat[0];
        String token = tokenFormat[1];

        // Bearer 토큰인지 검증
        if (!tokenType.equals("Bearer")) {
            throw new IllegalArgumentException("로그인 정보가 유효하지 않습니다");
        }

        // 유효한 JWT 토큰인지 검증
        if (!jwtProvider.isValidToken(token)) {
            throw new IllegalArgumentException("로그인 정보가 유효하지 않습니다");
        }

        return jwtProvider.getSubject(token);
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
