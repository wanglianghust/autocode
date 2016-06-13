 /**
  * 版权所有：版权所有(C) 2016，学酷网络  
 * 文件名称: ITeachGroupDAO.java 
 * 设计作者: 
 * 完成日期: 
 * 内容摘要:
 *
 * 修改记录: 
 * 修改日期:
 * 修 改 人:
 * 修改内容:
 */

package com.tsb.ischool.challenge.dao;

import com.tsb.ischool.challenge.model.TeachGroup;
import com.tsb.ischool.challenge.webservice.c2sbean.C2STeachGroup;
import com.tsb.ischool.framework.bean.comm.PageBean;

/**
 * 类 名 称：ITeachGroupDao
 * 内容摘要：
 * 完成日期：
 * 编码作者：
 */
public interface ITeachGroupDao
{

 	/**
	 * 新增TeachGroup
	 * @param TeachGroup bean
	 * @return
	 */
	public int insert(TeachGroup bean);
	
	
	/**
	 * 编辑TeachGroup
	 * @param TeachGroup bean
	 * @return
	 */
	public int update(TeachGroup bean);
	
	/**
	 * 通过TeachGroup主键查询TeachGroup信息
	 * @param  cSid  TeachGroup主键
	 * @return TeachGroup
	 */
	public TeachGroup queryById(String cSid);
	
	/**
	 * 删除TeachGroup通过主键
	 * @param   cSid  TeachGroup主键
	 * @return
	 */
	public int deleteById(String cSid);
	
	/**
	 * 逻辑删除TeachGroup通过主键
	 * @param   cSid  TeachGroup主键
	 * @return
	 */
	public int deletelogicById(String cSid);
	
	/**
	 * 查询TeachGroup
	 * @param C2STeachGroup bean
	 * @return
	 */
	public PageBean<TeachGroup> query(C2STeachGroup bean);
}
