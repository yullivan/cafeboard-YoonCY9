package cafeboard.member;

import cafeboard.member.DTO.CreateMember;
import cafeboard.member.DTO.DeleteMember;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void signUp(CreateMember dto) {
        Member member = new Member(dto.name(), dto.password());
        memberRepository.save(member);
    }

    @Transactional
    public void resign(Long memberId, DeleteMember dto) {
        Member member = memberRepository.findById(memberId).orElseThrow(()
                -> new NoSuchElementException("존재하지 않는 멤버 id" + memberId));

        if (member.matchPassword(dto.password())) {
            memberRepository.deleteById(memberId);
        } else throw new IllegalStateException("비밀번호가 일치하지 않습니다");
    }
}
