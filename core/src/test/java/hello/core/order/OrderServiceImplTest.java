package hello.core.order;

import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class OrderServiceImplTest {

    @Test
    void creatOrder() {
        MemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "name", Grade.VIP));

         OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new RateDiscountPolicy());
         orderService.createOrder(1L, "itemA", 10000);
        Assertions.assertThat(orderService.createOrder(1L, "itemA", 10000).getDiscountPrice()).isEqualTo(1000);

    }

}