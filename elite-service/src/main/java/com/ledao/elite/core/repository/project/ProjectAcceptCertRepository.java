package com.ledao.elite.core.repository.project;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.ledao.elite.core.domain.project.ProjectAcceptCert;

/**
 * 项目相关验收单(如项目阶段验收单、整个项目验收单)接口
 * 
 * @author kobe.liu
 * */
public interface ProjectAcceptCertRepository extends GenericDAO<ProjectAcceptCert, Long> {

}
