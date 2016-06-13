/**
 * 版权所有：版权所有(C) 2016，学酷网络 
 * 文件名称: ICmsColumnService.java 
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

import com.tsb.ischool.challenge.model.CmsColumn;
import com.tsb.ischool.challenge.webservice.c2sbean.C2SCmsColumn;
import com.tsb.ischool.framework.bean.comm.PageBean;

/**
 * 类 编 号： 
 * 类 名 称：ICmsColumnService
 * 内容摘要：
 * 完成日期：
 * 编码作者：
 */
public interface ICmsColumnService
{
 
    /**
	 * 新增CmsColumn
	 * @param CmsColumn
	 * @return
	 */
	public int insert(CmsColumn bean);
	
	
	/**
	 * 编辑CmsColumn
	 * @param CmsColumn
	 * @return
	 */
	public int update(CmsColumn bean);
	
	/**
	 * 获取CmsColumn通过主键
	 * @param pkid
	 * @return
	 */
	public CmsColumn queryById(String pkid);
	
	/**
	 * 删除CmsColumn通过主键
	 * @param CmsColumn
	 * @return
	 */
	public int deleteById(String pkid);
	
	/**
	 * 逻辑删除CmsColumn通过主键
	 * @param CmsColumn
	 * @return
	 */
	public int deletelogicById(String pkid);
	
	/**
	 * 查询CmsColumn
	 * @param C2SCmsColumn
	 * @return
	 */
	public PageBean<CmsColumn> query(C2SCmsColumn bean);
 	
}
