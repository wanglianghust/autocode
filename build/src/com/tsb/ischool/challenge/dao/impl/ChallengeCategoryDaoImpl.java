/**
 * 版权所有：版权所有(C) 2016，学酷网络 
 * 文件名称: ChallengeCategoryDaoImpl.java 
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
import com.tsb.ischool.challenge.dao.IChallengeCategoryDao;
import com.tsb.ischool.challenge.model.ChallengeCategory;
import com.tsb.ischool.challenge.webservice.c2sbean.C2SChallengeCategory;
import com.tsb.ischool.framework.bean.comm.PageBean;
import com.tsb.ischool.framework.common.ISchoolConstants;
import com.tsb.ischool.framework.dao.MybatisDao;
import org.apache.log4j.Logger;

/**
 * 类 名 称：ChallengeCategoryDaoImpl.java
 * 内容摘要：
 * 完成日期：
 * 编码作者：
 */
@Repository("challengeCategoryDao")
public class ChallengeCategoryDaoImpl extends MybatisDao implements IChallengeCategoryDao
{
    private static Logger logger = Logger.getLogger(ChallengeCategoryDaoImpl.class);

	@Override
	public int insert(ChallengeCategory bean) {
		String operation =ISchoolConstants.LOGGER_PREFIX_INFO+ "THREADID = "+Thread.currentThread().getId()+".|Mapper-method:";
		String method = "ChallengeCategory.insert";
		logger.info(operation + method + ".|");
		return insert(method, bean);
	}

	@Override
	public int update(ChallengeCategory bean) {
		String operation =ISchoolConstants.LOGGER_PREFIX_INFO+ "THREADID = "+Thread.currentThread().getId()+".|Mapper-method:";
		String method = "ChallengeCategory.update";
		logger.info(operation + method + ".|");
		return update(method, bean);
	}

	@Override
	public ChallengeCategory queryById(String pkid) {
		String operation =ISchoolConstants.LOGGER_PREFIX_INFO+ "THREADID = "+Thread.currentThread().getId()+".|Mapper-method:";
		String method = "ChallengeCategory.queryById";
		logger.info(operation + method + ".|");
		return (ChallengeCategory)selectOne(method, pkid);
	}

	@Override
	public int deleteById(String pkid) {
		String operation =ISchoolConstants.LOGGER_PREFIX_INFO+ "THREADID = "+Thread.currentThread().getId()+".|Mapper-method:";
		String method = "ChallengeCategory.deleteById";
		logger.info(operation + method + ".|");
		return delete(method, pkid);
	}

	@Override
	public int deletelogicById(String pkid) {
		String operation =ISchoolConstants.LOGGER_PREFIX_INFO+ "THREADID = "+Thread.currentThread().getId()+".|Mapper-method:";
		String method = "ChallengeCategory.deletelogicById";
		logger.info(operation + method + ".|");
		return update(method, pkid);
	}

	@Override
	public PageBean<ChallengeCategory> query(C2SChallengeCategory bean) {
		String operation =ISchoolConstants.LOGGER_PREFIX_INFO+ "THREADID = "+Thread.currentThread().getId()+".|Mapper-method:";
		String method = "ChallengeCategory.query";
		logger.info(operation + method + ".|");
		return selectPage(method, bean, bean.getCurpage(), bean.getPagesize());
	}

}