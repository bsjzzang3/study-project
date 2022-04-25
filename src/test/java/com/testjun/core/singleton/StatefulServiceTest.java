package com.testjun.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {

	@Test
	void statefulServiceSingleton() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
		
		StatefulService statefulService1 = ac.getBean(StatefulService.class);
		StatefulService statefulService2 = ac.getBean(StatefulService.class);
		
		//ThreadA: A����� 10000�� �ֹ� 
		statefulService1.order("userA", 10000);

		//ThreadB: B����� 20000�� �ֹ�
		statefulService2.order("userB", 20000);
		
		//ThreadA: A����� �ֹ� �ݾ� ��ȸ
		int price = statefulService1.getPrice();
		System.out.println("price = " + price);
		
		Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
	}
	
	static class TestConfig{
		@Bean
		public StatefulService statefulService() {
			return new StatefulService();
		}
	}
}
