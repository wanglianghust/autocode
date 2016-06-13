/**
 * 版权所有：版权所有(C) 2016，学酷网络 
 * 文件名称: TeachGroupServiceImpl.java 
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
import com.tsb.ischool.challenge.dao.ITeachGroupDao;
import com.tsb.ischool.challenge.model.TeachGroup;
import com.tsb.ischool.challenge.service.ITeachGroupService;
import com.tsb.ischool.challenge.webservice.c2sbean.C2STeachGroup;
import com.tsb.ischool.framework.bean.comm.PageBean;
import com.tsb.ischool.framework.common.ISchoolConstants;

/**
 * 类 编 号： 
 * 类 名 称：TeachGroupServiceImpl
 * 内容摘要：
 * 完成日期：
 * 编码作者：
 */
@Service("teachGroupService")
public class TeachGroupServiceImpl implements ITeachGroupService
{
   private Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Resource
	private ITeachGroupDao teachGroupDao;
	
	
	@Override
	public int insert(TeachGroup bean) {
		String operation = ISchoolConstants.LOGGER_PREFIX_DEBUG + "THREADID = "
				+ Thread.currentThread().getId() + ".|新增 爱挑战TeachGroup.|bean="
				+ bean.toString() + ".|";
		logger.debug(operation + "开始.|");
		int result =teachGroupDao.insert(bean);
		logger.debug(operation + "结束.|");
		return result;
	}
	
	@Override
	public int update(TeachGroup bean) {
		String operation = ISchoolConstants.LOGGER_PREFIX_DEBUG + "THREADID = "
				+ Thread.currentThread().getId() + ".|修改爱挑战TeachGroup.|bean="
				+ bean.toString() + ".|";
		logger.debug(operation + "开始.|");
		int result = teachGroupDao.update(bean);
		logger.debug(operation + "结束.|");
		return result;
	}
	@Override
	public TeachGroup queryById(String pkid) {
		String operation = ISchoolConstants.LOGGER_PREFIX_DEBUG + "THREADID = "
				+ Thread.currentThread().getId() + ".|按id查询爱挑战TeachGroup.|bean="
				+ pkid + ".|";
		logger.debug(operation + "开始.|");
		TeachGroup teachGroup = teachGroupDao.queryById(pkid);
		logger.debug(operation + "结束.|");
		return teachGroup;
	}
	
	@Override
	public int deleteById(String pkid) {
		String operation = ISchoolConstants.LOGGER_PREFIX_DEBUG + "THREADID = "
				+ Thread.currentThread().getId() + ".|删除爱挑战TeachGroup.|bean="
				+ pkid + ".|";
		logger.debug(operation + "开始.|");
		int result = teachGroupDao.deleteById(pkid);
		logger.debug(operation + "结束.|");
		return result;
	}
	
	@Override
	public int deletelogicById(String pkid) {
		String operation = ISchoolConstants.LOGGER_PREFIX_DEBUG + "THREADID = "
				+ Thread.currentThread().getId() + ".|逻辑删除爱挑战TeachGroup.|bean="
				+ pkid + ".|";
		logger.debug(operation + "开始.|");
		int result = teachGroupDao.deletelogicById(pkid);
		logger.debug(operation + "结束.|");
		return result;
	}
	
	@Override
	public PageBean<TeachGroup> query(C2STeachGroup bean) {
		String operation = ISchoolConstants.LOGGER_PREFIX_DEBUG + "THREADID = "
				+ Thread.currentThread().getId() + ".|查询爱挑战TeachGroup.|bean="
				+ bean.toString() + ".|";
		logger.debug(operation + "开始.|");
		PageBean<TeachGroup> pageBean = teachGroupDao.query(bean);
		logger.debug(operation + "结束.|");
		return pageBean;
	}
	
}
