 /**
  * 版权所有：版权所有(C) 2016，学酷网络  
 * 文件名称: ICmsColumnDAO.java 
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

import com.tsb.ischool.challenge.model.CmsColumn;
import com.tsb.ischool.challenge.webservice.c2sbean.C2SCmsColumn;
import com.tsb.ischool.framework.bean.comm.PageBean;

/**
 * 类 名 称：ICmsColumnDao
 * 内容摘要：
 * 完成日期：
 * 编码作者：
 */
public interface ICmsColumnDao
{

 	/**
	 * 新增CmsColumn
	 * @param CmsColumn bean
	 * @return
	 */
	public int insert(CmsColumn bean);
	
	
	/**
	 * 编辑CmsColumn
	 * @param CmsColumn bean
	 * @return
	 */
	public int update(CmsColumn bean);
	
	/**
	 * 通过CmsColumn主键查询CmsColumn信息
	 * @param  cSid  CmsColumn主键
	 * @return CmsColumn
	 */
	public CmsColumn queryById(String cSid);
	
	/**
	 * 删除CmsColumn通过主键
	 * @param   cSid  CmsColumn主键
	 * @return
	 */
	public int deleteById(String cSid);
	
	/**
	 * 逻辑删除CmsColumn通过主键
	 * @param   cSid  CmsColumn主键
	 * @return
	 */
	public int deletelogicById(String cSid);
	
	/**
	 * 查询CmsColumn
	 * @param C2SCmsColumn bean
	 * @return
	 */
	public PageBean<CmsColumn> query(C2SCmsColumn bean);
}
