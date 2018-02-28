package com.ledao.elite.core.repository.sys.impl;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.sys.SysOrgan;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.sys.SysOrganRepository;

@Repository
public class SysOrganRepositoryImpl extends GenericRepositoryImpl<SysOrgan, Long> implements SysOrganRepository {

	@Override
	public SearchResult<SysOrgan> fuzzySearchSysOrgans(String name, String status, Long parentId, Long areaId,
			Pager pager) {

		if (pager == null)
			pager = new Pager();
		Search search = new Search(SysOrgan.class);
		search.addFilterEqual("status", status);
		search.addFilterEqual("areaId", areaId);
		if (null != name) {
			search.addFilterLike("name", "%" + name + "%");
		}
		search.setFirstResult(pager.getStartIndex());
		search.setMaxResults(pager.getPageSize());
		String sort = pager.getSort();
		if (StringUtils.isNotEmpty(sort)) {
			search.addSort(sort, StringUtils.equalsIgnoreCase(pager.getDir(), "desc") ? true : false);
		} else {
			search.addSort("createTime", true);
		}
		return this.searchAndCount(search);
	}

	@Override
	public Integer querySysOrganMaxOrders() {
		String hql = "select max(orders) from SysOrgan ";
		Query query = getSession().createQuery(hql);
		return   (Integer) query.uniqueResult();
	}

}
