package com.testjun.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {

	/**
	 * DIP, OCP 위반
	 */
//	private final MemberRepository memberRepository = new MemoryMemberRepository();
	private final MemberRepository memberRepository;

	@Autowired  // ac.getBean(MemberRepository.class)
	public MemberServiceImpl(MemberRepository memberRepository) {
		super();
		this.memberRepository = memberRepository;
	}

	@Override
	public void join(Member member) {
		memberRepository.save(member);
	}

	@Override
	public Member findMember(Long memberId) {
		return memberRepository.findById(memberId);
	}
	
	// 테스트 용도
	public MemberRepository getMemberRepository() {
		return memberRepository;
	}

}
