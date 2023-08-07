package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import org.springframework.web.bind.annotation.PostMapping;
import hello.hellospring.service.MemberService;
public class MemberForm {
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
