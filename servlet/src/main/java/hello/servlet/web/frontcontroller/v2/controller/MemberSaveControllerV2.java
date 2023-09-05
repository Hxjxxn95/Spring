package hello.servlet.web.frontcontroller.v2.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.ControllerV2;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MemberSaveControllerV2 implements ControllerV2 {

    MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String username = request.getParameter("username"); // 회원가입 폼에서 넘어온 데이터를 꺼내서
        int age = Integer.parseInt(request.getParameter("age"));
        Member member = new Member(username, age); // Member 객체를 만들고
        memberRepository.save(member); // 저장하고
        request.setAttribute("member", member);
        return new MyView("/WEB-INF/views/save-result.jsp");
    }
}
