package hello.core.singletonTest;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertSame;

public class SingletinTest {

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")

    void singletonServiceTest(){
        AppConfig appConfig = new AppConfig();
        //1. 조회: 호출할 때마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();
        //2. 조회: 호출할 때마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        //참조값이 다른 것을 확인
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest2(){
        //new SingletonService();
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();
        //참조값이 다른 것을 확인
        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);
        Assertions.assertThat(singletonService1).isSameAs(singletonService2);
        //same == 참조 비교
        //equal == 자바의 equals와 같음
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤") //스프링 컨테이너는 싱글톤 패턴을 적용하지 않아도, 객체 인스턴스를 싱글톤으로 관리한다.
    void singletonServiceTest3(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService = ac.getBean("memberService",MemberService.class);
        MemberService memberService2 = ac.getBean("memberService",MemberService.class);

        System.out.println("memberService = " + memberService);
        System.out.println("memberService2 = " + memberService2);

        assertSame(memberService,memberService2);

    }
}
