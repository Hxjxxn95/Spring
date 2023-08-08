package hello.core.discount;

import hello.core.member.Member;

public interface DiscountPolicy {

    int dicount(Member member, int price);

}
