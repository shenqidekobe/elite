package com.ledao.elite.manager.test;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ledao.elite.core.domain.member.MemberProjects;

import lombok.extern.slf4j.Slf4j;

public class ObjectSerializeTest {
	
	private SessionFactory sessionFactory;
	
	private ObjectMapper objectMapper = new  ObjectMapper();  
	
	
	public void test(){
		String hql="from MemberProjects where id =30";
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		Query query=session.createQuery(hql);
		MemberProjects mp1=(MemberProjects) query.uniqueResult();
		try {
			MemberProjects mp=mp1;
			//mp.setProject("admin");
			//String s=objectMapper.writeValueAsString(mp);
			//log.info(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)throws Exception {
		ObjectMapper objectMapper = new  ObjectMapper(); 
		MemberProjects mp=new MemberProjects();
		mp.setProject("admin");
		System.out.println(mp.toString());
		String s=objectMapper.writeValueAsString(mp);
		System.out.println(s);
	}

}
