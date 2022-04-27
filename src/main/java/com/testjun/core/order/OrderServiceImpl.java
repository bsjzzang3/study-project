package com.testjun.core.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.testjun.core.discount.DiscountPolicy;
import com.testjun.core.member.Member;
import com.testjun.core.member.MemberRepository;

@Component
public class OrderServiceImpl implements OrderService{

	
	/**
	 * DIP, OCP ����
	 */
//	private final MemberRepository memberRepository = new MemoryMemberRepository();
//	private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//	private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
	
	/**
	 * ������ ����(setter ����)�� ���
	 * @Autowired(required = false)�� �����ϸ� ������ ����� ��� ������ �߻����� �ʴ´�.
	 */
	private final MemberRepository memberRepository;
	private final DiscountPolicy discountPolicy;

	/**
	 * ������ ���� 
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
	
	// �׽�Ʈ �뵵
	public MemberRepository getMemberRepository() {
		return memberRepository;
	}
	
}
