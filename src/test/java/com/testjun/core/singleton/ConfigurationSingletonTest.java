package com.testjun.core.singleton;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.testjun.core.AppConfig;
import com.testjun.core.member.MemberRepository;
import com.testjun.core.member.MemberServiceImpl;
import com.testjun.core.order.OrderServiceImpl;

public class ConfigurationSingletonTest {
	
	@Test
	void configurationTest() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		
		MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
		OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
		
		MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);
		
		MemberRepository memberRepository1 = memberService.getMemberRepository();
		MemberRepository memberRepository2 = orderService.getMemberRepository();
		
		System.out.println("memberService -> memberRepository = " + memberRepository1);
		System.out.println("memberService -> memberRepository = " + memberRepository1);
		System.out.println("memberRepository = " + memberRepository);
		
		assertThat(memberRepository1).isEqualTo(memberRepository);
		assertThat(memberRepository2).isEqualTo(memberRepository);
		
	}
	
	@Test
	void configurationDeep() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		AppConfig bean = ac.getBean(AppConfig.class);
		
		System.out.println("bean = " + bean.getClass());
	}
}
