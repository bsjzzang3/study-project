package com.testjun.core.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.testjun.core.AppConfig;
import com.testjun.core.member.Grade;
import com.testjun.core.member.Member;
import com.testjun.core.member.MemberService;

public class OrderServiceTest {
	
	MemberService memberService;
	OrderService orderService;
	
	@BeforeEach
	public void beforeEach() {
		AppConfig appConfig = new AppConfig();
		memberService = appConfig.memberService();
		orderService = appConfig.orderService();
	}
	
	@Test
	@DisplayName("VIP는 10% 할인이 적용되어야 한다.")
	void createOrder() {
		//given
		Long memberId = 1L;
		String memberName = "memberA";
		String itemName = "itemA";
				
		//when
		Member member = new Member(memberId, memberName, Grade.VIP);
		memberService.join(member);
		
		Order order = orderService.createOrder(memberId, itemName, 10000);

		//then
		Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
	}
}
