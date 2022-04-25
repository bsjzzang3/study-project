package com.testjun.core.discount;

import com.testjun.core.member.Grade;
import com.testjun.core.member.Member;

public class FixDiscountPolicy implements DiscountPolicy{

	private int discountFixAmount = 1000; // 1000¿ø ÇÒÀÎ
	@Override
	public int discount(Member member, int price) {
		if(member.getGrade() == Grade.VIP) {
			return discountFixAmount;
		}else {
			return 0;			
		}
	}

}
