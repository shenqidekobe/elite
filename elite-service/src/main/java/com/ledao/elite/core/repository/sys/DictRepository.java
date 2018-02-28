package com.ledao.elite.core.repository.sys;

import java.util.List;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.sys.Dict;
import com.ledao.elite.core.framework.base.Pager;

/**
 * 数据字典接口
 * 
 * @author kobe.liu
 */
public interface DictRepository extends GenericDAO<Dict, Long> {

	/**
	 * 
	 * 根据Key val 模糊分页查询
	 * 
	 * @param dictType
	 * @param dictKey
	 * @param dictVal
	 * @param pager
	 * @return SearchResult<DIct>
	 */
	SearchResult<Dict> fuzzySearchDicts(String dictType, String dictKey, String dictVal, Pager pager);

	/**
	 * 根据type查询
	 * 
	 * @param type
	 * @return List<Dict>
	 */
	List<Dict> findRootDictsByDictType(String dictType);

	/**
	 * 根据type和type查询
	 * 
	 * @param type，list<key>
	 * @return List<Dict>
	 */
	Dict findDictByTypeAndKey(String type, String key);

	/**
	 * 根据parentId查询子类
	 * 
	 * @param
	 * @return Dict
	 */
	List<Dict> queryDictByParentId(Long parentId, String dictType);

}
