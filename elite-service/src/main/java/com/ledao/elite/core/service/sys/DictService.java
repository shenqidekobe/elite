package com.ledao.elite.core.service.sys;

import java.util.List;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.sys.Dict;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.dto.manager.DictMangerTree;

/**
 * 数据字典接口
 *
 * @author zhiyu cao
 **/

public interface DictService {

	/**
	 * 创建
	 *
	 **/
	Dict createDict(Dict obj) throws EliteServiceException;

	/**
	 * 分页查询
	 *
	 **/
	SearchResult<Dict> findDictList(String dictType, String key, String val, Pager pager) throws EliteServiceException;

	/**
	 * 按ID物理删除
	 * 
	 */
	Dict removePhysicalById(Long Id) throws EliteServiceException;

	/**
	 * 根据Id查询
	 * 
	 */
	Dict findById(Long Id) throws EliteServiceException;

	/**
	 * 根据key查询
	 * 
	 */
	Dict findByKey(String key) throws EliteServiceException;

	/**
	 * 根据key查询
	 * 
	 */
	Dict findDictByKeyAndType(String dictType, String dictKey) throws EliteServiceException;

	/**
	 * 更新字典
	 * 
	 */
	Dict updateDict(Long id, Dict dict) throws EliteServiceException;

	/**
	 * 通过dictType查询顶级数据
	 * 
	 * @param dictType
	 */
	List<Dict> findRootDictListByDictType(String dictType) throws EliteServiceException;
	
	/**
	 * 通过dictType查询所有数据
	 * 
	 * @param dictType
	 */
	List<Dict> findDictListByDictType(String dictType) throws EliteServiceException;
	
	/**
	 * 通过dictType和父ID查询
	 * 
	 */
	List<Dict> findDictsByDictType(String dictType,Long parentId) throws EliteServiceException;

	/**
	 * 根据Id key查询
	 * 
	 */

	Dict findSysDictByIdAndKey(Long id, String dictKey) throws EliteServiceException;

	/**
	 * 上下移动
	 * 
	 */
	Dict updateMoveDict(Long id, String moveType) throws EliteServiceException;

	/**
	 * 查询二叉树
	 * 
	 * @param organId
	 * @param parentId
	 * 
	 * @return List<OrganDeptTree>
	 */
	List<DictMangerTree> findDictTreeChildren(Long parentId, String dictType)throws EliteServiceException;
	
	/**
	 * 根据一组key查询对应的val值
	 * 
	 * @param dictType
	 * @param dictKeys
	 * @param joinSymbol
	 * @return string
	 * */
	String findDictValsByKeys(String dictType,String dictKeys,String joinSymbol)throws EliteServiceException;
  
	/**
	 * 创建项目收益规则
	 */
	Dict createDictPartnerCompanyRule(String key,String dictType,Object rule) throws EliteServiceException;
	
	/**
	 * 查询项目渠道方收益规则
	 */
	Object findDictPartnerRule(String key)throws EliteServiceException;
	
	/**
	 * 查找jobrole顶端列表
	 */
	List<Dict>findDicJobRoleTop(String dictType)throws EliteServiceException;
}
