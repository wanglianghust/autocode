/**
 * 版权所有：版权所有(C) 2016，学酷网络 
 * 文件名称: CmsColumnDaoImpl.java 
 * 设计作者: 
 * 完成日期: 
 * 内容摘要:
 *
 * 修改记录: 
 * 修改日期:
 * 修 改 人:
 * 修改内容:
 */
package com.tsb.ischool.challenge.dao.impl;

import org.springframework.stereotype.Repository;
import com.tsb.ischool.challenge.dao.ICmsColumnDao;
import com.tsb.ischool.challenge.model.CmsColumn;
import com.tsb.ischool.challenge.webservice.c2sbean.C2SCmsColumn;
import com.tsb.ischool.framework.bean.comm.PageBean;
import com.tsb.ischool.framework.common.ISchoolConstants;
import com.tsb.ischool.framework.dao.MybatisDao;
import org.apache.log4j.Logger;

/**
 * 类 名 称：CmsColumnDaoImpl.java
 * 内容摘要：
 * 完成日期：
 * 编码作者：
 */
@Repository("cmsColumnDao")
public class CmsColumnDaoImpl extends MybatisDao implements ICmsColumnDao
{
    private static Logger logger = Logger.getLogger(CmsColumnDaoImpl.class);

	@Override
	public int insert(CmsColumn bean) {
		String operation =ISchoolConstants.LOGGER_PREFIX_INFO+ "THREADID = "+Thread.currentThread().getId()+".|Mapper-method:";
		String method = "CmsColumn.insert";
		logger.info(operation + method + ".|");
		return insert(method, bean);
	}

	@Override
	public int update(CmsColumn bean) {
		String operation =ISchoolConstants.LOGGER_PREFIX_INFO+ "THREADID = "+Thread.currentThread().getId()+".|Mapper-method:";
		String method = "CmsColumn.update";
		logger.info(operation + method + ".|");
		return update(method, bean);
	}

	@Override
	public CmsColumn queryById(String pkid) {
		String operation =ISchoolConstants.LOGGER_PREFIX_INFO+ "THREADID = "+Thread.currentThread().getId()+".|Mapper-method:";
		String method = "CmsColumn.queryById";
		logger.info(operation + method + ".|");
		return (CmsColumn)selectOne(method, pkid);
	}

	@Override
	public int deleteById(String pkid) {
		String operation =ISchoolConstants.LOGGER_PREFIX_INFO+ "THREADID = "+Thread.currentThread().getId()+".|Mapper-method:";
		String method = "CmsColumn.deleteById";
		logger.info(operation + method + ".|");
		return delete(method, pkid);
	}

	@Override
	public int deletelogicById(String pkid) {
		String operation =ISchoolConstants.LOGGER_PREFIX_INFO+ "THREADID = "+Thread.currentThread().getId()+".|Mapper-method:";
		String method = "CmsColumn.deletelogicById";
		logger.info(operation + method + ".|");
		return update(method, pkid);
	}

	@Override
	public PageBean<CmsColumn> query(C2SCmsColumn bean) {
		String operation =ISchoolConstants.LOGGER_PREFIX_INFO+ "THREADID = "+Thread.currentThread().getId()+".|Mapper-method:";
		String method = "CmsColumn.query";
		logger.info(operation + method + ".|");
		return selectPage(method, bean, bean.getCurpage(), bean.getPagesize());
	}

}