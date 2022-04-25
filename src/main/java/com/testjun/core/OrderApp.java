package com.testjun.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.testjun.core.member.Grade;
import com.testjun.core.member.Member;
import com.testjun.core.member.MemberService;
import com.testjun.core.order.Order;
import com.testjun.core.order.OrderService;

public class OrderApp {

	public static void main(String[] args) {
		
//		AppConfig appConfig = new AppConfig();
//		MemberService memberService = appConfig.memberService();
//		OrderService orderService = appConfig.orderService();
		// 수정 테스트
		// 깃 수정 테스트2
		
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		
		MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
		OrderService orderService = applicationContext.getBean("orderService", OrderService.class);
		
		Long memberId = 1L;
		String memberName = "memberA";
		String itemName = "itemA";
		
		Member member = new Member(memberId, memberName, Grade.VIP);
		memberService.join(member);
		
		Order order = orderService.createOrder(memberId, itemName, 20000);
		
		System.out.println("order : " + order);
		System.out.println("order : " + order.calculatePrice());
	}

}
