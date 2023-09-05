package hello.servlet.web.frontcontroller.v1.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.v1.ControllerV1;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class MemberSaveControllerV1 implements ControllerV1 {
    MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username"); // 회원가입 폼에서 넘어온 데이터를 꺼내서
        int age = Integer.parseInt(request.getParameter("age"));
        Member member = new Member(username, age); // Member 객체를 만들고
        memberRepository.save(member); // 저장하고

        // Model에 데이터를 보관한다.
        request.setAttribute("member", member);
        String viewPath = "/WEB-INF/views/save-result.jsp"; // 뷰의 경로를 지정하고
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath); // 컨트롤러에서 뷰로 이동할 때 사용
        dispatcher.forward(request, response); // 다른 서블릿이나 JSP로 이동할 수 있는 기능

    }
}
