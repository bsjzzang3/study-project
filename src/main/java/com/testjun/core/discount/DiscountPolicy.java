package com.testjun.core.discount;

import com.testjun.core.member.Member;

public interface DiscountPolicy {

	/**
	 * @param member
	 * @param price
	 * @return ���� ��� �ݾ�
	 */
	int discount(Member member, int price);
	
}
