package com.ledao.elite.core.repository.sys.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.sys.Dict;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.repository.GenericRepositoryImpl;
import com.ledao.elite.core.repository.sys.DictRepository;

@Repository
public class DictRepositoryImpl extends GenericRepositoryImpl<Dict, Long> implements DictRepository {

	@Override
	public SearchResult<Dict> fuzzySearchDicts(String dictType, String dictKey, String dictVal, Pager pager) {
		if (pager == null)
			pager = new Pager();
		Search search = new Search(Dict.class);
		search.addFilterEqual("dictType", dictType);
		if (null != dictKey) {
			search.addFilterLike("dictKey", "%" + dictKey + "%");
		}
		if (null != dictVal) {
			search.addFilterLike("dictVal", "%" + dictVal + "%");
		}
		search.setFirstResult(pager.getStartIndex());
		search.setMaxResults(pager.getPageSize());
		String sort = pager.getSort();
		if (StringUtils.isNotEmpty(sort)) {
			search.addSort(sort, StringUtils.equalsIgnoreCase(pager.getDir(), "desc") ? true : false);
		} else {
			search.addSort("orders", false);
		}
		return this.searchAndCount(search);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Dict> findRootDictsByDictType(String type) {
		String hql = "from Dict where dictType=? and (parentId is null or parentId=null) order by orders asc";
		Query query = getSession().createQuery(hql);
		query.setCacheable(true);
		query.setString(0,type);
		return query.list();
	}

	@Override
	public Dict findDictByTypeAndKey(String type, String key) {
		Search search = new Search(Dict.class);
		search.addFilterEqual("dictType", type);
		search.addFilterEqual("dictKey", key);

		// 需要添加缓存方法
		return this.searchUnique(search);
	}

	// 查询树形结构
	@SuppressWarnings("unchecked")
	@Override
	public List<Dict> queryDictByParentId(Long parentId, String dictType) {
		String hql = "from Dict where dictType =?";
		if (parentId == null) {
			hql += " and (parentId is null or parentId ='') ";
		} else {
			hql += " and parentId=? ";
		}
		hql += " order by orders asc";
		Query query = getSession().createQuery(hql);
		query.setString(0, dictType);
		if (parentId != null) {
			query.setLong(1, parentId);
		}
		return query.list();
	}

}
