package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor // final이 붙은 필드를 모아서 생성자를 자동으로 만들어줌
public class OrderServiceImpl implements OrderService {


    // 테스트 용도
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); // -> DIP, OCP 위반
    @Getter
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy; // -> DIP, OCP 지킴


//    @Autowired //"REQUIREDArgsConstructor" 가 있어서 필요 없음
//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId); // 회원 정보 조회
        int discountPrice = discountPolicy.dicount(member, itemPrice); // 할인 정책에 회원 정보를 넘겨서 할인 가격을 받아온다.
        return new Order(memberId, itemName, itemPrice, discountPrice); // 주문 생성
    }
    // 테스트 용도
//    public DiscountPolicy getDiscountPolicy() {
//        return discountPolicy;
//    }
}
