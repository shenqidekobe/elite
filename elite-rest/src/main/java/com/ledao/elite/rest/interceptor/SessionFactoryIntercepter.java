package com.ledao.elite.rest.interceptor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * SessionFactoryIntercepter hibernate会话工厂拦截器
 */
@Slf4j
@Component
public class SessionFactoryIntercepter extends EmptyInterceptor implements BeanFactoryAware {

	private static final long serialVersionUID = -1472328552297644070L;

	@Resource
	private BeanFactory beanFactory;
	
	/**
	 * 保存实体的时候
	 */
	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		Timestamp saveTime = new Timestamp(new Date().getTime());
		for (int i = 0; i < propertyNames.length; i++) {
			if (StringUtils.equals(propertyNames[i], "createTime")) {
				if (state[i] == null) {
					state[i] = saveTime;
				}
			} 
		}
		return true;
	}

	/**
	 * session刷新的时候
	 */
	@Override
	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
		log.debug("invoke SessionFactoryIntercepter#FlushDirty@" + entity.getClass().getSimpleName());
		Timestamp updateTime = new Timestamp(new Date().getTime());
		for (int i = 0; i < propertyNames.length; i++) {
			if (StringUtils.equals(propertyNames[i], "updateTime")) {
				currentState[i] = updateTime;
			}
		}
		return true;
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}

}
