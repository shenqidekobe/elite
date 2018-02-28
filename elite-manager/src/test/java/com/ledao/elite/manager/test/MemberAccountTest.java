package com.ledao.elite.manager.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ledao.elite.core.service.member.MemberAccountService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:app.xml")
public class MemberAccountTest {

	@Resource
	private MemberAccountService memberAccountService;

	@Rollback(true)
	@Test
	public void test() {
	/*	Long partnerMemberId = 1335L;
		BigDecimal awardAmount = new BigDecimal(2);
		memberAccountService.updateMemberAccountTotalIncome(partnerMemberId, awardAmount, awardAmount, Data_Oper.sum);
		System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
		memberAccountService.updateMemberAccountTotalIncome(partnerMemberId, awardAmount, awardAmount, Data_Oper.sum);*/
	}

}
