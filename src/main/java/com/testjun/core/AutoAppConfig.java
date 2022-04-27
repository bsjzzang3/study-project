package com.testjun.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import com.testjun.core.member.MemberRepository;
import com.testjun.core.member.MemoryMemberRepository;

@Configuration
@ComponentScan(
		// basePackages ����Ʈ�� @ComponentScan �� ���� Ŭ������ ��Ű������(���⿡�� com.testjun.core) ���� ��Ű�� ���θ� �˻���
		basePackages = "com.testjun.core.member",
		// basePackageClasses �� ����ϸ� �ش� Ŭ������ ��Ű������ �˻���
//		basePackageClasses = AutoAppConfig.class,
		excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
		)
public class AutoAppConfig {
	/**
	 * MemoryMemberRepository�� �ڵ����� ������ ����� ��
	 * ������ ��� �Ϻη� ���� �̸��� ���� ���� ���� �׽�Ʈ
	 * �������� ������ ���� �켱���� ������.
	 * �������� �ڵ����� �������̵� �ع�����.
	 */
	@Bean(name = "memoryMemberRepository")
	MemberRepository memberRepository() {
		return new MemoryMemberRepository();
	}
}
