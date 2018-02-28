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

import com.ledao.elite.core.domain.project.Project;
import com.ledao.elite.core.domain.project.ProjectAtta;

/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:app.xml")
@Transactional*/
public class ProjectServiceTest {
	
	/*
	@Resource
	private SessionFactory sessionFactory;*/
	
	
	//@Test
/*	public void test(){
		String hql="from Project where id =1";
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		Query query=session.createQuery(hql);
		Project pro=(Project) query.uniqueResult();
		session.getTransaction().commit();
	}*/

}
