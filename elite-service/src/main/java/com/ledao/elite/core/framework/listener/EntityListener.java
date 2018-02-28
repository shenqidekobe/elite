package com.ledao.elite.core.framework.listener;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.ledao.elite.core.domain.IdentifiedEntity;

/**
 * Listener - 创建日期、修改日期处理
 * 
 * @author kobe.liu
 * @version 1.0
 */
public class EntityListener {

	private Timestamp currentTime=new Timestamp(new Date().getTime());
	
	/**
	 * 保存前处理
	 * 
	 * @param entity
	 *            基类
	 */
	@PrePersist
	public void prePersist(IdentifiedEntity entity) {
		entity.setCreateTime(currentTime);
		entity.setUpdateTime(currentTime);
	}

	/**
	 * 更新前处理
	 * 
	 * @param entity
	 *            基类
	 */
	@PreUpdate
	public void preUpdate(IdentifiedEntity entity) {
		entity.setUpdateTime(currentTime);
	}

}