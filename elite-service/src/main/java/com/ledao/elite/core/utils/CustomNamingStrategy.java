package com.ledao.elite.core.utils;

import org.hibernate.cfg.ImprovedNamingStrategy;

/**
 * 自定义基于hbm2ddl工具生成的数据库Schema中相关名字生成策略,例如表名，字段名
 * 
 */
public class CustomNamingStrategy extends ImprovedNamingStrategy {

	@Override
	public String columnName(String columnName) {
		return addUnderscores(columnName).toUpperCase();
	}

	@Override
	public String tableName(String tableName) {
		return addUnderscores(tableName).toUpperCase();
	}

	@Override
	public String classToTableName(String className) {
		return "T_" + tableName(className);
	}

	@Override
	public String propertyToColumnName(String propertyName) {
		return super.propertyToColumnName(propertyName);
	}
	
	@Override
	public String foreignKeyColumnName(String propertyName,
			String propertyEntityName, String propertyTableName,
			String referencedColumnName) {
		return propertyName + "_ID";
	}
	
	private static final long serialVersionUID = 3557066638767211366L;
}
