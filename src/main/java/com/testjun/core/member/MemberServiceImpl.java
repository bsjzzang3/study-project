package com.testjun.core.member;

public class MemberServiceImpl implements MemberService {

	/**
	 * DIP, OCP ����
	 */
//	private final MemberRepository memberRepository = new MemoryMemberRepository();
	private final MemberRepository memberRepository;

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
	
	// �׽�Ʈ �뵵
	public MemberRepository getMemberRepository() {
		return memberRepository;
	}

}
