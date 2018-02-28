package com.ledao.elite.core.repository.sys;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.ledao.elite.core.domain.sys.QuartzTask;

/**
 * 定时任务接口
 * 
 * @author kobe.liu
 * */
public interface QuartzTaskRepository extends GenericDAO<QuartzTask, Long> {
	

}
