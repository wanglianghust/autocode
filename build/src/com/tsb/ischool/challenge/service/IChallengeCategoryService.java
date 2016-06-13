/**
 * 版权所有：版权所有(C) 2016，学酷网络 
 * 文件名称: IChallengeCategoryService.java 
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

import com.tsb.ischool.challenge.model.ChallengeCategory;
import com.tsb.ischool.challenge.webservice.c2sbean.C2SChallengeCategory;
import com.tsb.ischool.framework.bean.comm.PageBean;

/**
 * 类 编 号： 
 * 类 名 称：IChallengeCategoryService
 * 内容摘要：
 * 完成日期：
 * 编码作者：
 */
public interface IChallengeCategoryService
{
 
    /**
	 * 新增ChallengeCategory
	 * @param ChallengeCategory
	 * @return
	 */
	public int insert(ChallengeCategory bean);
	
	
	/**
	 * 编辑ChallengeCategory
	 * @param ChallengeCategory
	 * @return
	 */
	public int update(ChallengeCategory bean);
	
	/**
	 * 获取ChallengeCategory通过主键
	 * @param pkid
	 * @return
	 */
	public ChallengeCategory queryById(String pkid);
	
	/**
	 * 删除ChallengeCategory通过主键
	 * @param ChallengeCategory
	 * @return
	 */
	public int deleteById(String pkid);
	
	/**
	 * 逻辑删除ChallengeCategory通过主键
	 * @param ChallengeCategory
	 * @return
	 */
	public int deletelogicById(String pkid);
	
	/**
	 * 查询ChallengeCategory
	 * @param C2SChallengeCategory
	 * @return
	 */
	public PageBean<ChallengeCategory> query(C2SChallengeCategory bean);
 	
}
