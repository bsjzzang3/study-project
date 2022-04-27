package com.testjun.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import com.testjun.core.member.MemberRepository;
import com.testjun.core.member.MemoryMemberRepository;

@Configuration
@ComponentScan(
		// basePackages 디폴트는 @ComponentScan 을 붙인 클레스의 패키지부터(여기에선 com.testjun.core) 하위 패키지 전부를 검색함
		basePackages = "com.testjun.core.member",
		// basePackageClasses 를 사용하면 해당 클레스의 패키지부터 검색함
//		basePackageClasses = AutoAppConfig.class,
		excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
		)
public class AutoAppConfig {
	/**
	 * MemoryMemberRepository는 자동으로 빈으로 등록이 됨
	 * 지금의 경우 일부러 같은 이름을 가진 빈을 만들어서 테스트
	 * 수동으로 생성된 빈이 우선권을 가진다.
	 * 수동빈이 자동빈을 오버라이딩 해버린다.
	 */
	@Bean(name = "memoryMemberRepository")
	MemberRepository memberRepository() {
		return new MemoryMemberRepository();
	}
}
