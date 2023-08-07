package hello.hellospring.service;


import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberReopository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional // test 후 data를 DB에 넣은 것을 rollback 해줌
public class MemberServiceIntegrationTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberReopository memberRepository;

//    @BeforeEach // test 실행 전에 실행되는 method
//    public void beforeEach() {
//        memberRepository = new MemoryMemberRepository();
//        memberService = new MemberService(memberRepository);
//    }


    @Test
    void 회원가입() {
        //given
        //회원가입을 하려면 뭔가가 주어져야 한다.
        Member member = new Member();
        member.setName("hello");
        //when
        //이걸 실행했을 때
        Long saveId = memberService.join(member);
        //then
        //이게 나와야 한다.
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }
    @Test
    public void 중복_회원_예외() {
        //given
        //회원가입을 하려면 뭔가가 주어져야 한다.
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");
        //when
        //이걸 실행했을 때
        memberService.join(member1);
//        try {
//            memberService.join(member2);
//            fail();
//        }
//        //then
//        //이게 나와야 한다.
//        catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }

    @Test
    void findMembers() {

        Member member1 = new Member();
        member1.setName("spring1");
        memberService.join(member1);
        Member member2 = new Member();
        member2.setName("spring2");
        memberService.join(member2);

        List<Member> result = memberService.findMembers();
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    void findOne() {

        Member member1 = new Member();
        member1.setName("spring3");
        memberService.join(member1);
        Member member2 = new Member();
        member2.setName("spring4");
        memberService.join(member2);

        Member result = memberService.findOne(member1.getId()).get();
        assertThat(result).isEqualTo(member1);

    }
}
