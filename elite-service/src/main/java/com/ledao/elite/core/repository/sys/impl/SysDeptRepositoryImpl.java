package com.ledao.elite.core.repository.sys.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.sys.SysDept;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.sys.SysDeptRepository;

@Repository
public class SysDeptRepositoryImpl extends GenericRepositoryImpl<SysDept, Long> implements SysDeptRepository {

	@SuppressWarnings("unchecked")
	@Override
	public List<SysDept> querySysDeptByOrganIdAndParentId(Long organId,Long parentId) {
		String hql="from SysDept where status =? and organId=?";
		if(parentId==null){
			hql+=" and (parentId is null or parentId ='') ";
		}else{
			hql+=" and parentId=? ";
		}
		hql+=" order by orders asc";
		Query query=getSession().createQuery(hql);
		query.setString(0, GlobalDefinedConstant.System_Status.normal.name());
		query.setLong(1, organId);
		if(parentId!=null){
			query.setLong(2, parentId);
		}
		return query.list();
	}

}
