/**
 * 版权所有：版权所有(C) 2016，学酷网络 
 * 文件名称: ITeachGroupService.java 
 * 设计作者: 
 * 完成日期: 
 * 内容摘要:
 *
 * 修改记录: 
 * 修改日期:
 * 修 改 人:
 * 修改内容:
 */
package com.tsb.ischool.challenge.service;

import com.tsb.ischool.challenge.model.TeachGroup;
import com.tsb.ischool.challenge.webservice.c2sbean.C2STeachGroup;
import com.tsb.ischool.framework.bean.comm.PageBean;

/**
 * 类 编 号： 
 * 类 名 称：ITeachGroupService
 * 内容摘要：
 * 完成日期：
 * 编码作者：
 */
public interface ITeachGroupService
{
 
    /**
	 * 新增TeachGroup
	 * @param TeachGroup
	 * @return
	 */
	public int insert(TeachGroup bean);
	
	
	/**
	 * 编辑TeachGroup
	 * @param TeachGroup
	 * @return
	 */
	public int update(TeachGroup bean);
	
	/**
	 * 获取TeachGroup通过主键
	 * @param pkid
	 * @return
	 */
	public TeachGroup queryById(String pkid);
	
	/**
	 * 删除TeachGroup通过主键
	 * @param TeachGroup
	 * @return
	 */
	public int deleteById(String pkid);
	
	/**
	 * 逻辑删除TeachGroup通过主键
	 * @param TeachGroup
	 * @return
	 */
	public int deletelogicById(String pkid);
	
	/**
	 * 查询TeachGroup
	 * @param C2STeachGroup
	 * @return
	 */
	public PageBean<TeachGroup> query(C2STeachGroup bean);
 	
}
