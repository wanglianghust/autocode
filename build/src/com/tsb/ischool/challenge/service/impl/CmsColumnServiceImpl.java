/**
 * 版权所有：版权所有(C) 2016，学酷网络 
 * 文件名称: CmsColumnServiceImpl.java 
 * 设计作者: 
 * 完成日期: 
 * 内容摘要:
 *
 * 修改记录: 
 * 修改日期:
 * 修 改 人:
 * 修改内容:
 */
package com.tsb.ischool.challenge.service.impl;

import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import com.tsb.ischool.challenge.dao.ICmsColumnDao;
import com.tsb.ischool.challenge.model.CmsColumn;
import com.tsb.ischool.challenge.service.ICmsColumnService;
import com.tsb.ischool.challenge.webservice.c2sbean.C2SCmsColumn;
import com.tsb.ischool.framework.bean.comm.PageBean;
import com.tsb.ischool.framework.common.ISchoolConstants;

/**
 * 类 编 号： 
 * 类 名 称：CmsColumnServiceImpl
 * 内容摘要：
 * 完成日期：
 * 编码作者：
 */
@Service("cmsColumnService")
public class CmsColumnServiceImpl implements ICmsColumnService
{
   private Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Resource
	private ICmsColumnDao cmsColumnDao;
	
	
	@Override
	public int insert(CmsColumn bean) {
		String operation = ISchoolConstants.LOGGER_PREFIX_DEBUG + "THREADID = "
				+ Thread.currentThread().getId() + ".|新增 爱挑战CmsColumn.|bean="
				+ bean.toString() + ".|";
		logger.debug(operation + "开始.|");
		int result =cmsColumnDao.insert(bean);
		logger.debug(operation + "结束.|");
		return result;
	}
	
	@Override
	public int update(CmsColumn bean) {
		String operation = ISchoolConstants.LOGGER_PREFIX_DEBUG + "THREADID = "
				+ Thread.currentThread().getId() + ".|修改爱挑战CmsColumn.|bean="
				+ bean.toString() + ".|";
		logger.debug(operation + "开始.|");
		int result = cmsColumnDao.update(bean);
		logger.debug(operation + "结束.|");
		return result;
	}
	@Override
	public CmsColumn queryById(String pkid) {
		String operation = ISchoolConstants.LOGGER_PREFIX_DEBUG + "THREADID = "
				+ Thread.currentThread().getId() + ".|按id查询爱挑战CmsColumn.|bean="
				+ pkid + ".|";
		logger.debug(operation + "开始.|");
		CmsColumn cmsColumn = cmsColumnDao.queryById(pkid);
		logger.debug(operation + "结束.|");
		return cmsColumn;
	}
	
	@Override
	public int deleteById(String pkid) {
		String operation = ISchoolConstants.LOGGER_PREFIX_DEBUG + "THREADID = "
				+ Thread.currentThread().getId() + ".|删除爱挑战CmsColumn.|bean="
				+ pkid + ".|";
		logger.debug(operation + "开始.|");
		int result = cmsColumnDao.deleteById(pkid);
		logger.debug(operation + "结束.|");
		return result;
	}
	
	@Override
	public int deletelogicById(String pkid) {
		String operation = ISchoolConstants.LOGGER_PREFIX_DEBUG + "THREADID = "
				+ Thread.currentThread().getId() + ".|逻辑删除爱挑战CmsColumn.|bean="
				+ pkid + ".|";
		logger.debug(operation + "开始.|");
		int result = cmsColumnDao.deletelogicById(pkid);
		logger.debug(operation + "结束.|");
		return result;
	}
	
	@Override
	public PageBean<CmsColumn> query(C2SCmsColumn bean) {
		String operation = ISchoolConstants.LOGGER_PREFIX_DEBUG + "THREADID = "
				+ Thread.currentThread().getId() + ".|查询爱挑战CmsColumn.|bean="
				+ bean.toString() + ".|";
		logger.debug(operation + "开始.|");
		PageBean<CmsColumn> pageBean = cmsColumnDao.query(bean);
		logger.debug(operation + "结束.|");
		return pageBean;
	}
	
}
