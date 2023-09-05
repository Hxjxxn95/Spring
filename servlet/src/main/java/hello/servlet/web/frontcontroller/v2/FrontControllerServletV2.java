package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {
    private Map<String, ControllerV2> controllerMap = new HashMap<>();

    public FrontControllerServletV2() {
        controllerMap.put("/front-controller/v2/members/new-form", new MemberFormControllerV2()); // 회원 등록 폼
        controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV2()); // 회원 저장
        controllerMap.put("/front-controller/v2/members", new MemberListControllerV2()); // 회원 조회
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI(); // 요청 URI를 받아옴
        ControllerV2 controller = controllerMap.get(requestURI);

        if(controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404
            return;
        }
        MyView view = controller.process(request, response);
        view.render(request, response);

    }
}
