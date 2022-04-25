package com.testjun.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.testjun.core.discount.DiscountPolicy;
import com.testjun.core.discount.RateDiscountPolicy;
import com.testjun.core.member.MemberRepository;
import com.testjun.core.member.MemberService;
import com.testjun.core.member.MemberServiceImpl;
import com.testjun.core.member.MemoryMemberRepository;
import com.testjun.core.order.OrderService;
import com.testjun.core.order.OrderServiceImpl;

@Configuration
public class AppConfig {

	// 깃 이클립스 연결 후 테스트 용도
	// @Bean memberServce -> new MemoryMemberRepository()
	// @Bean orderService -> new MemoryMemberRepository()

	@Bean
	public MemberService memberService() {
		System.out.println("call AppConfig.memberService");
		return new MemberServiceImpl(memberRepository());
	}

	@Bean
	public MemberRepository memberRepository() {
		System.out.println("call AppConfig.memberRepository");
		return new MemoryMemberRepository();
	}

	@Bean
	public OrderService orderService() {
		System.out.println("call AppConfig.orderService");
		return new OrderServiceImpl(memberRepository(), discountPolicy());
	}

	@Bean
	public DiscountPolicy discountPolicy() {
		return new RateDiscountPolicy();
	}

}
