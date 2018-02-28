package com.ledao.elite.core.service.sys;

import java.util.List;

import com.ledao.elite.core.domain.sys.Attas;
import com.ledao.elite.core.exception.EliteServiceException;

/**
 * 附件服务接口
 * 
 * @author kobe.liu
 * */
public interface AttasService {

	/**
	 * 新创建一个附件
	 * 
	 * @param Attas
	 * @return Attas
	 * */
	Attas createAttas(Attas obj)throws EliteServiceException;
	
	/**
	 * 按ID逻辑删除
	 * 
	 * @param attaId
	 * @return Attas
	 * */
	Attas removeLogicById(Long attaId)throws EliteServiceException;
	
	/**
	 * 按ID物理删除
	 * 
	 * @param attaId
	 * @return Attas
	 * */
	Attas removePhysicalById(Long attaId)throws EliteServiceException;
	
	/**
	 * 修改附件信息
	 * @param attaId
	 * @param Attas
	 * @return Attas
	 * */
	Attas updateAttas(Long attaId,Attas attas)throws EliteServiceException;
	
	/**
	 * 根据ID查询附件
	 * 
	 * @param attaId
	 * @return Attas
	 * */
	Attas findById(Long attaId)throws EliteServiceException;
	
	/**
	 * 根据一组ID查询附件列表
	 * 
	 * @param attaIds,逗号分割
	 * @return List<Attas>
	 * */
	List<Attas> findByIds(String attaIds)throws EliteServiceException;
}
