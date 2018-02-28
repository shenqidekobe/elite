package com.ledao.elite.core.repository.member.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ledao.elite.core.domain.member.MemberBasic;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.member.MemberBasicRepository;

@Repository
public class MemberBasicRepositoryImpl extends GenericRepositoryImpl<MemberBasic, Long>
		implements MemberBasicRepository {

	@Override
	public int updateBasic(MemberBasic obj){
		String sql="update t_member_basic set email=?,sex=?,birthday=?,area_id="+obj.getAreaId()+",area_name=?,photo_Id="+obj.getPhotoId()+",member_Sign=? where id=?";
		Query query=getSession().createSQLQuery(sql);
		query.setString(0, obj.getEmail());
		query.setString(1, obj.getSex());
		query.setDate(2, obj.getBirthday());
		query.setString(3, obj.getAreaName());
		query.setString(4, obj.getMemberSign());
		query.setLong(5, obj.getId());
		return query.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MemberBasic> queryMemberBasicByEmail(String email) {
		String hql = "from MemberBasic where email=?";
		Query query = getSession().createQuery(hql);
		query.setString(0, email);
		return query.list();
	}

	@Override
	public MemberBasic merge(MemberBasic obj){
		getSession().merge(obj);
		return obj;
	}

}
