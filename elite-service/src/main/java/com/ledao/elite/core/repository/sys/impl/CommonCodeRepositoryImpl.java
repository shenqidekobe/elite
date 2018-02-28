package com.ledao.elite.core.repository.sys.impl;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.sys.CommonCode;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.sys.CommonCodeRepository;

@Repository
public class CommonCodeRepositoryImpl extends GenericRepositoryImpl<CommonCode, Long> implements CommonCodeRepository {

	
	/**
	 * UPGRADE:不管缓存中是否存在对象,总是通过select语句到数据库中加载该对象,如果映射文件中设置了版本元素,就执行版本检查,比较缓存中的对象是否和数据库中对象的版本一致,
	 *                  如果数据库系统支持悲观锁(如Oracle/MySQL),就执行select...for update语句,如果不支持(如Sybase),执行普通select语句
	 * */
	@SuppressWarnings("unchecked")
	@Override
	public List<CommonCode> queryCommonCodeList(CommonCode obj) {
		String hql="from CommonCode where preVal=? and funcCode=? and flag=?";
		Query query=getSession().createQuery(hql);
		//query.setLockMode(obj.toString(),LockMode.PESSIMISTIC_WRITE);
		query.setLockOptions(LockOptions.UPGRADE);
		query.setString(0, obj.getPreVal());
		query.setString(1, obj.getFuncCode());
		query.setInteger(2, obj.getFlag());
		return query.list();
	}
	
	@Override
	public void updateCommonCode(CommonCode obj){
		String sql="update t_common_code set glide=? where id=?";
		Query query=getSession().createSQLQuery(sql);
		query.setString(0,obj.getGlide());
		query.setLong(1,obj.getId());
		query.executeUpdate();
	}

}
