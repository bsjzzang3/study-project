package com.testjun.core.beanfind;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.testjun.core.discount.DiscountPolicy;
import com.testjun.core.discount.FixDiscountPolicy;
import com.testjun.core.discount.RateDiscountPolicy;

class ApplicationContextExtendsFindTest {
	AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
	
	@Test
	@DisplayName("�θ� Ÿ������ ��ȸ��, �ڽ��� �� �̻� ������, �ߺ� ������ �߻��Ѵ�.")
	void findBeanByParentTypeDuplicate() {
		assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(DiscountPolicy.class));
	}
	
	@Test
	@DisplayName("�θ� Ÿ������ ��ȸ��, �ڽ��� �� �̻� ������, �� �̸��� �����ϸ� �ȴ�.")
	void findBeanByParentTypeBeanName() {
		DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
		
		assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
	}
	
	@Test
	@DisplayName("Ư�� ���� Ÿ������ ��ȸ")
	void findBeanBySubType() {
		RateDiscountPolicy rateDiscountPolicy = ac.getBean(RateDiscountPolicy.class);
		
		assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
	}
	
	@Test
	@DisplayName("�θ� Ÿ������ ��� ��ȸ�ϱ�")
	void findAllBeanByParantType() {
		Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
		
		assertThat(beansOfType.size()).isEqualTo(2);
		for (String key : beansOfType.keySet()) {
			System.out.println("key = " + key + ", value = " + beansOfType.get(key));
		}
	}
	
	@Test
	@DisplayName("�θ� Ÿ������ ��� ��ȸ�ϱ� - Object")
	void findAllBeanByObjectType() {
		Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
		
		for (String key : beansOfType.keySet()) {
			System.out.println("key = " + key + ", value = " + beansOfType.get(key));
		}
	}

	@Configuration
	static class TestConfig{
		@Bean
		public DiscountPolicy rateDiscountPolicy() {
			return new RateDiscountPolicy();
		}

		@Bean
		public DiscountPolicy fixDiscountPolicy() {
			return new FixDiscountPolicy();
		}
		
	}
}
