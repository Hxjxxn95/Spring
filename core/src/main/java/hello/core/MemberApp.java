package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
//순수 자바 코드
public class MemberApp {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        memberService.join(new Member(1L, "memberA", Grade.VIP));

        Member findmember = memberService.findMember(1L);
        System.out.println("new member = " + findmember.getName());
        System.out.println("find member = " + findmember.getName());
    }
}
