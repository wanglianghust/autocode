/**
 * 版权所有：版权所有(C) 2016，学酷网络 
 * 文件名称: TeachGroupDaoImpl.java 
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
import com.tsb.ischool.challenge.dao.ITeachGroupDao;
import com.tsb.ischool.challenge.model.TeachGroup;
import com.tsb.ischool.challenge.webservice.c2sbean.C2STeachGroup;
import com.tsb.ischool.framework.bean.comm.PageBean;
import com.tsb.ischool.framework.common.ISchoolConstants;
import com.tsb.ischool.framework.dao.MybatisDao;
import org.apache.log4j.Logger;

/**
 * 类 名 称：TeachGroupDaoImpl.java
 * 内容摘要：
 * 完成日期：
 * 编码作者：
 */
@Repository("teachGroupDao")
public class TeachGroupDaoImpl extends MybatisDao implements ITeachGroupDao
{
    private static Logger logger = Logger.getLogger(TeachGroupDaoImpl.class);

	@Override
	public int insert(TeachGroup bean) {
		String operation =ISchoolConstants.LOGGER_PREFIX_INFO+ "THREADID = "+Thread.currentThread().getId()+".|Mapper-method:";
		String method = "TeachGroup.insert";
		logger.info(operation + method + ".|");
		return insert(method, bean);
	}

	@Override
	public int update(TeachGroup bean) {
		String operation =ISchoolConstants.LOGGER_PREFIX_INFO+ "THREADID = "+Thread.currentThread().getId()+".|Mapper-method:";
		String method = "TeachGroup.update";
		logger.info(operation + method + ".|");
		return update(method, bean);
	}

	@Override
	public TeachGroup queryById(String pkid) {
		String operation =ISchoolConstants.LOGGER_PREFIX_INFO+ "THREADID = "+Thread.currentThread().getId()+".|Mapper-method:";
		String method = "TeachGroup.queryById";
		logger.info(operation + method + ".|");
		return (TeachGroup)selectOne(method, pkid);
	}

	@Override
	public int deleteById(String pkid) {
		String operation =ISchoolConstants.LOGGER_PREFIX_INFO+ "THREADID = "+Thread.currentThread().getId()+".|Mapper-method:";
		String method = "TeachGroup.deleteById";
		logger.info(operation + method + ".|");
		return delete(method, pkid);
	}

	@Override
	public int deletelogicById(String pkid) {
		String operation =ISchoolConstants.LOGGER_PREFIX_INFO+ "THREADID = "+Thread.currentThread().getId()+".|Mapper-method:";
		String method = "TeachGroup.deletelogicById";
		logger.info(operation + method + ".|");
		return update(method, pkid);
	}

	@Override
	public PageBean<TeachGroup> query(C2STeachGroup bean) {
		String operation =ISchoolConstants.LOGGER_PREFIX_INFO+ "THREADID = "+Thread.currentThread().getId()+".|Mapper-method:";
		String method = "TeachGroup.query";
		logger.info(operation + method + ".|");
		return selectPage(method, bean, bean.getCurpage(), bean.getPagesize());
	}

}