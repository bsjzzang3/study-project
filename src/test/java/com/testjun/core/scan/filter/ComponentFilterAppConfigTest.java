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
	@DisplayName("������̼��� ���� ������Ʈ ���˿��� �����ϱ�")
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
		 * FilterType �ɼ��� 5������ �ִ�.
		 * type = FilterType.ANNOTATION: �⺻������ ���� ����, ������̼��� �ν��ؼ� �����Ѵ�.
		 * type = FilterType.ASSIGNABLE_TYPE: ������ Ÿ�԰� �ڽ� Ÿ���� �ν��ؼ� �����Ѵ�.
		 * type = FilterType.ASPECTJ: AspectJ ���� ���
		 * type = FilterType.REGEX: ���� ǥ����
		 * type = FilterType.CUSTOM: TypeFilter ��� �������̽��� �����ؼ� ó��
		 */
		
		/**
		 * BeanA Ŭ������ ������Ʈ ���˿��� ���� �ϰ� ������� �Ʒ��� ���� �ۼ��Ѵ�.
		 * excludeFilters = {
		 * 	@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class),
		 * 	@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = BeanA.class)
		 * }
		 */
	}
}
