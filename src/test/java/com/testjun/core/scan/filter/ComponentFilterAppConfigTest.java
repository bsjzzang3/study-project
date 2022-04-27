package com.testjun.core.scan.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

public class ComponentFilterAppConfigTest {

	@Test
	@DisplayName("어노테이션을 만들어서 컴포넌트 스켄에서 제외하기")
	void filterScan() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
		
		BeanA beanA = ac.getBean("beanA", BeanA.class);
		Assertions.assertThat(beanA).isNotNull();

		org.junit.jupiter.api.Assertions.assertThrows(
				NoSuchBeanDefinitionException.class,
				() -> ac.getBean("beanB", BeanA.class));

	}

	@Configuration
	@ComponentScan(
			includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
			excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
			)
	static class ComponentFilterAppConfig {
		/**
		 * FilterType 옵션은 5가지가 있다.
		 * type = FilterType.ANNOTATION: 기본값으로 생략 가능, 어노테이션을 인식해서 동작한다.
		 * type = FilterType.ASSIGNABLE_TYPE: 지정한 타입과 자식 타입을 인식해서 동작한다.
		 * type = FilterType.ASPECTJ: AspectJ 패턴 사용
		 * type = FilterType.REGEX: 정규 표현식
		 * type = FilterType.CUSTOM: TypeFilter 라는 인터페이스를 구현해서 처리
		 */
		
		/**
		 * BeanA 클레스도 컴포넌트 스켄에서 제외 하고 싶을경우 아래와 같이 작성한다.
		 * excludeFilters = {
		 * 	@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class),
		 * 	@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = BeanA.class)
		 * }
		 */
	}
}
