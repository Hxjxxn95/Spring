package hello.hellospring;


import hello.hellospring.repository.JdbcTemplateMemberRepository;
import hello.hellospring.repository.JpaMemberRepostory;
import hello.hellospring.repository.MemberReopository;
import hello.hellospring.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import javax.swing.text.html.parser.Entity;

@Configuration
public class SpringConfig {

    private final MemberReopository memberRepository;

    @Autowired
    public SpringConfig(MemberReopository memberReopository) {
        this.memberRepository = memberReopository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository) ;
    }

//
//    @Bean
//    public MemberReopository memberRepository() {
//        return new JpaMemberRepostory(em);
//    }

}