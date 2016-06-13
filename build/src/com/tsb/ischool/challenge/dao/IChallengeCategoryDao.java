 /**
  * 版权所有：版权所有(C) 2016，学酷网络  
 * 文件名称: IChallengeCategoryDAO.java 
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

import com.tsb.ischool.challenge.model.ChallengeCategory;
import com.tsb.ischool.challenge.webservice.c2sbean.C2SChallengeCategory;
import com.tsb.ischool.framework.bean.comm.PageBean;

/**
 * 类 名 称：IChallengeCategoryDao
 * 内容摘要：
 * 完成日期：
 * 编码作者：
 */
public interface IChallengeCategoryDao
{

 	/**
	 * 新增ChallengeCategory
	 * @param ChallengeCategory bean
	 * @return
	 */
	public int insert(ChallengeCategory bean);
	
	
	/**
	 * 编辑ChallengeCategory
	 * @param ChallengeCategory bean
	 * @return
	 */
	public int update(ChallengeCategory bean);
	
	/**
	 * 通过ChallengeCategory主键查询ChallengeCategory信息
	 * @param  categoryid  ChallengeCategory主键
	 * @return ChallengeCategory
	 */
	public ChallengeCategory queryById(String categoryid);
	
	/**
	 * 删除ChallengeCategory通过主键
	 * @param   categoryid  ChallengeCategory主键
	 * @return
	 */
	public int deleteById(String categoryid);
	
	/**
	 * 逻辑删除ChallengeCategory通过主键
	 * @param   categoryid  ChallengeCategory主键
	 * @return
	 */
	public int deletelogicById(String categoryid);
	
	/**
	 * 查询ChallengeCategory
	 * @param C2SChallengeCategory bean
	 * @return
	 */
	public PageBean<ChallengeCategory> query(C2SChallengeCategory bean);
}
