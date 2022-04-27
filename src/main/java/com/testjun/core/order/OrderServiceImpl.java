package com.testjun.core.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.testjun.core.discount.DiscountPolicy;
import com.testjun.core.member.Member;
import com.testjun.core.member.MemberRepository;

@Component
public class OrderServiceImpl implements OrderService{

	
	/**
	 * DIP, OCP 위반
	 */
//	private final MemberRepository memberRepository = new MemoryMemberRepository();
//	private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//	private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
	
	/**
	 * 수정자 주입(setter 주입)의 경우
	 * @Autowired(required = false)로 지정하면 주입할 대상이 없어도 오류가 발생하지 않는다.
	 */
	private final MemberRepository memberRepository;
	private final DiscountPolicy discountPolicy;

	/**
	 * 생성자 주입 
	 */
	@Autowired
	public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
		super();
		this.memberRepository = memberRepository;
		this.discountPolicy = discountPolicy;
	}

	@Override
	public Order createOrder(Long memberId, String itemName, int itemPrice) {
		Member member = memberRepository.findById(memberId);
		int discountPrice = discountPolicy.discount(member, itemPrice);
		
		return new Order(memberId, itemName, itemPrice, discountPrice);
	}
	
	// 테스트 용도
	public MemberRepository getMemberRepository() {
		return memberRepository;
	}
	
}
