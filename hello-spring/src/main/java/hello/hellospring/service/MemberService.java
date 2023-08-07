package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.repository.MemberReopository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public class MemberService {

    private final MemberReopository memberReopository;
    @Autowired
    public MemberService(MemberReopository memberReopository) {
        this.memberReopository = memberReopository;
    }

    //회원가입
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberReopository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberReopository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    //전체 회원 조회
    public List<Member> findMembers() {
        return memberReopository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberReopository.findById(memberId);
    }
}
