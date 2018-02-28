package com.ledao.elite.core.repository.sys.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.sys.Area;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.sys.AreaRepository;

@Repository
public class AreaRepositoryImpl extends GenericRepositoryImpl<Area, Long> implements AreaRepository {
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Area> queryRootAreaList(){
		String hql="from Area where parentId=?";
		Query query=getSession().createQuery(hql);
		query.setCacheable(true);
		query.setLong(0, Area.CHNAL_PARENT_ID);
		return query.list();
	}

}
