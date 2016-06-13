/**
 * 版权所有：版权所有(C) 2016，学酷网络 
 * 文件名称: TeachGroup.java 
 * 设计作者: 
 * 完成日期: 
 * 内容摘要:对应表[T_TEACH_GROUP],映射配置文件为TeachGroup.xml 
 *
 * 修改记录: 
 * 修改日期:
 * 修 改 人:
 * 修改内容:
 */
package com.tsb.ischool.challenge.model;

import java.io.Serializable;
import com.tsb.ischool.challenge.model.TeachGroup;
import com.tsb.ischool.framework.common.ErrorCode;
import com.tsb.ischool.framework.exception.ISchoolException;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
* 类 名 称：TeachGroup
* 内容摘要：
* 完成日期：
* 编码作者：
*/
public class TeachGroup implements Serializable 
{

    /** 
     *PRIMARY KEY,对应表[T_TEACH_GROUP]的主键C_SID
     */
    private String cSid;//主键

    /** 
     *对应表[T_TEACH_GROUP]的字段C_NAME
     */
	private String cName;//名称

    /** 
     *对应表[T_TEACH_GROUP]的字段C_LABLE
     */
	private String cLable;//标签

    /** 
     *对应表[T_TEACH_GROUP]的字段C_EXT1
     */
	private String cExt1;//

    /** 
     *对应表[T_TEACH_GROUP]的字段C_EXT2
     */
	private String cExt2;//

    /** 
     *对应表[T_TEACH_GROUP]的字段C_EXT3
     */
	private String cExt3;//

    /** 
     *对应表[T_TEACH_GROUP]的字段C_ISUSED
     */
	private Integer cIsused;//0：审核中；1：通过；2：拒绝；3关闭；

    /** 
     *对应表[T_TEACH_GROUP]的字段C_CREATETIME
     */
	private String cCreatetime;//创建时间

    /** 
     *对应表[T_TEACH_GROUP]的字段C_UPDATORID
     */
	private String cUpdatorid;//最后修改人

    /** 
     *对应表[T_TEACH_GROUP]的字段C_MODIFYTIME
     */
	private String cModifytime;//最后修改时间

    /** 
     *对应表[T_TEACH_GROUP]的字段C_REASON
     */
	private String cReason;//不通过，或者屏蔽意见

    /** 
     *对应表[T_TEACH_GROUP]的字段C_AGREEMENT
     */
	private Integer cAgreement;//是否同意协议

    /** 
     *对应表[T_TEACH_GROUP]的字段C_SORTNUM
     */
	private Integer cSortnum;//推荐排序号，不推荐默认0；推荐;大于100的数字

    /** 
     *对应表[T_TEACH_GROUP]的字段C_ORDERBYNUM
     */
	private Integer cOrderbynum;//自定义排序号，列表前面的序号

    /** 
     *对应表[T_TEACH_GROUP]的字段C_MEMBERNUM
     */
	private Integer cMembernum;//成员数量

    /** 
     *对应表[T_TEACH_GROUP]的字段C_CONTENTNUM
     */
	private Integer cContentnum;//文章数

    /** 
     *对应表[T_TEACH_GROUP]的字段C_UPLOADIMG
     */
	private String cUploadimg;//封面图地址

    /** 
     *对应表[T_TEACH_GROUP]的字段C_OPTIP
     */
	private String cOptip;//

    /** 
     *对应表[T_TEACH_GROUP]的字段C_DESC
     */
	private String cDesc;//简介

    /** 
     *对应表[T_TEACH_GROUP]的字段C_TYPE
     */
	private Integer cType;//教研类型(1教研室,2个人工作室)

    /** 
     *对应表[T_TEACH_GROUP]的字段C_AREA
     */
	private Integer cArea;//如果是教研室，必须有值，区域（省，市，区县，校）

    /** 
     *对应表[T_TEACH_GROUP]的字段C_LEARN_D
     */
	private Integer cLearnD;//学段

    /** 
     *对应表[T_TEACH_GROUP]的字段C_LEARN_K
     */
	private Integer cLearnK;//学科

    /** 
     *对应表[T_TEACH_GROUP]的字段C_CONDITION
     */
	private Integer cCondition;//加入条件 1允许任何人 2需要验证审核 3不允许加入

    /** 
     *对应表[T_TEACH_GROUP]的字段C_ACCESS_CONDITION
     */
	private Integer cAccessCondition;//访问条件 1仅注册用户可以访问 2都可以访问

    /** 
     *对应表[T_TEACH_GROUP]的字段C_AUDITNAME
     */
	private String cAuditname;//审核人

    /** 
     *对应表[T_TEACH_GROUP]的字段C_AUDITTIME
     */
	private String cAudittime;//审核时间

    /** 
     *对应表[T_TEACH_GROUP]的字段C_ACTIVITYNUM
     */
	private Integer cActivitynum;//活动数

    /** 
     *对应表[T_TEACH_GROUP]的字段C_NOTICENUM
     */
	private Integer cNoticenum;//公告数

    /** 
     *对应表[T_TEACH_GROUP]的字段C_VIDEONUM
     */
	private Integer cVideonum;//视频数

public boolean verify() throws ISchoolException {
		if (null == this) {
			throw new ISchoolException(ErrorCode.ISCHOOL_REQBODY_INVALID_MSGTYPE, "参数错误，入参为空。");
		}
		return true;
	}
	


  /**
	*
	* Default Empty Constructor for class TeachGroup
	*
	*/
	public TeachGroup() 
	{
		super();
	}
	
  /**
	*
	* Default All Fields Constructor for class TeachGroup
	*
	*/
    public TeachGroup (
		 String cSid
		,String cName
		,String cLable
		,String cExt1
		,String cExt2
		,String cExt3
		,Integer cIsused
		,String cCreatetime
		,String cUpdatorid
		,String cModifytime
		,String cReason
		,Integer cAgreement
		,Integer cSortnum
		,Integer cOrderbynum
		,Integer cMembernum
		,Integer cContentnum
		,String cUploadimg
		,String cOptip
		,String cDesc
		,Integer cType
		,Integer cArea
		,Integer cLearnD
		,Integer cLearnK
		,Integer cCondition
		,Integer cAccessCondition
		,String cAuditname
		,String cAudittime
		,Integer cActivitynum
		,Integer cNoticenum
		,Integer cVideonum
        ) {
		this.setCSid(cSid);
		this.setCName(cName);
		this.setCLable(cLable);
		this.setCExt1(cExt1);
		this.setCExt2(cExt2);
		this.setCExt3(cExt3);
		this.setCIsused(cIsused);
		this.setCCreatetime(cCreatetime);
		this.setCUpdatorid(cUpdatorid);
		this.setCModifytime(cModifytime);
		this.setCReason(cReason);
		this.setCAgreement(cAgreement);
		this.setCSortnum(cSortnum);
		this.setCOrderbynum(cOrderbynum);
		this.setCMembernum(cMembernum);
		this.setCContentnum(cContentnum);
		this.setCUploadimg(cUploadimg);
		this.setCOptip(cOptip);
		this.setCDesc(cDesc);
		this.setCType(cType);
		this.setCArea(cArea);
		this.setCLearnD(cLearnD);
		this.setCLearnK(cLearnK);
		this.setCCondition(cCondition);
		this.setCAccessCondition(cAccessCondition);
		this.setCAuditname(cAuditname);
		this.setCAudittime(cAudittime);
		this.setCActivitynum(cActivitynum);
		this.setCNoticenum(cNoticenum);
		this.setCVideonum(cVideonum);
    }

    
  /**
	*
	* @return String
	*/
	public String getCSid() 
	{
		return this.cSid;
	}
	
  /**
	* Set the cSid
	*/	
	public void setCSid(String aValue) 
	{
		this.cSid = aValue;
	}	
  /**
	*
	* @return String
	*/
	public String getCName() 
	{
		return this.cName;
	}
	
  /**
	* Set the cName
	*/	
	public void setCName(String aValue) 
	{
		this.cName = aValue;
	}	
  /**
	*
	* @return String
	*/
	public String getCLable() 
	{
		return this.cLable;
	}
	
  /**
	* Set the cLable
	*/	
	public void setCLable(String aValue) 
	{
		this.cLable = aValue;
	}	
  /**
	*
	* @return String
	*/
	public String getCExt1() 
	{
		return this.cExt1;
	}
	
  /**
	* Set the cExt1
	*/	
	public void setCExt1(String aValue) 
	{
		this.cExt1 = aValue;
	}	
  /**
	*
	* @return String
	*/
	public String getCExt2() 
	{
		return this.cExt2;
	}
	
  /**
	* Set the cExt2
	*/	
	public void setCExt2(String aValue) 
	{
		this.cExt2 = aValue;
	}	
  /**
	*
	* @return String
	*/
	public String getCExt3() 
	{
		return this.cExt3;
	}
	
  /**
	* Set the cExt3
	*/	
	public void setCExt3(String aValue) 
	{
		this.cExt3 = aValue;
	}	
  /**
	*
	* @return Integer
	*/
	public Integer getCIsused() 
	{
		return this.cIsused;
	}
	
  /**
	* Set the cIsused
	*/	
	public void setCIsused(Integer aValue) 
	{
		this.cIsused = aValue;
	}	
  /**
	*
	* @return String
	*/
	public String getCCreatetime() 
	{
		return this.cCreatetime;
	}
	
  /**
	* Set the cCreatetime
	*/	
	public void setCCreatetime(String aValue) 
	{
		this.cCreatetime = aValue;
	}	
  /**
	*
	* @return String
	*/
	public String getCUpdatorid() 
	{
		return this.cUpdatorid;
	}
	
  /**
	* Set the cUpdatorid
	*/	
	public void setCUpdatorid(String aValue) 
	{
		this.cUpdatorid = aValue;
	}	
  /**
	*
	* @return String
	*/
	public String getCModifytime() 
	{
		return this.cModifytime;
	}
	
  /**
	* Set the cModifytime
	*/	
	public void setCModifytime(String aValue) 
	{
		this.cModifytime = aValue;
	}	
  /**
	*
	* @return String
	*/
	public String getCReason() 
	{
		return this.cReason;
	}
	
  /**
	* Set the cReason
	*/	
	public void setCReason(String aValue) 
	{
		this.cReason = aValue;
	}	
  /**
	*
	* @return Integer
	*/
	public Integer getCAgreement() 
	{
		return this.cAgreement;
	}
	
  /**
	* Set the cAgreement
	*/	
	public void setCAgreement(Integer aValue) 
	{
		this.cAgreement = aValue;
	}	
  /**
	*
	* @return Integer
	*/
	public Integer getCSortnum() 
	{
		return this.cSortnum;
	}
	
  /**
	* Set the cSortnum
	*/	
	public void setCSortnum(Integer aValue) 
	{
		this.cSortnum = aValue;
	}	
  /**
	*
	* @return Integer
	*/
	public Integer getCOrderbynum() 
	{
		return this.cOrderbynum;
	}
	
  /**
	* Set the cOrderbynum
	*/	
	public void setCOrderbynum(Integer aValue) 
	{
		this.cOrderbynum = aValue;
	}	
  /**
	*
	* @return Integer
	*/
	public Integer getCMembernum() 
	{
		return this.cMembernum;
	}
	
  /**
	* Set the cMembernum
	*/	
	public void setCMembernum(Integer aValue) 
	{
		this.cMembernum = aValue;
	}	
  /**
	*
	* @return Integer
	*/
	public Integer getCContentnum() 
	{
		return this.cContentnum;
	}
	
  /**
	* Set the cContentnum
	*/	
	public void setCContentnum(Integer aValue) 
	{
		this.cContentnum = aValue;
	}	
  /**
	*
	* @return String
	*/
	public String getCUploadimg() 
	{
		return this.cUploadimg;
	}
	
  /**
	* Set the cUploadimg
	*/	
	public void setCUploadimg(String aValue) 
	{
		this.cUploadimg = aValue;
	}	
  /**
	*
	* @return String
	*/
	public String getCOptip() 
	{
		return this.cOptip;
	}
	
  /**
	* Set the cOptip
	*/	
	public void setCOptip(String aValue) 
	{
		this.cOptip = aValue;
	}	
  /**
	*
	* @return String
	*/
	public String getCDesc() 
	{
		return this.cDesc;
	}
	
  /**
	* Set the cDesc
	*/	
	public void setCDesc(String aValue) 
	{
		this.cDesc = aValue;
	}	
  /**
	*
	* @return Integer
	*/
	public Integer getCType() 
	{
		return this.cType;
	}
	
  /**
	* Set the cType
	*/	
	public void setCType(Integer aValue) 
	{
		this.cType = aValue;
	}	
  /**
	*
	* @return Integer
	*/
	public Integer getCArea() 
	{
		return this.cArea;
	}
	
  /**
	* Set the cArea
	*/	
	public void setCArea(Integer aValue) 
	{
		this.cArea = aValue;
	}	
  /**
	*
	* @return Integer
	*/
	public Integer getCLearnD() 
	{
		return this.cLearnD;
	}
	
  /**
	* Set the cLearnD
	*/	
	public void setCLearnD(Integer aValue) 
	{
		this.cLearnD = aValue;
	}	
  /**
	*
	* @return Integer
	*/
	public Integer getCLearnK() 
	{
		return this.cLearnK;
	}
	
  /**
	* Set the cLearnK
	*/	
	public void setCLearnK(Integer aValue) 
	{
		this.cLearnK = aValue;
	}	
  /**
	*
	* @return Integer
	*/
	public Integer getCCondition() 
	{
		return this.cCondition;
	}
	
  /**
	* Set the cCondition
	*/	
	public void setCCondition(Integer aValue) 
	{
		this.cCondition = aValue;
	}	
  /**
	*
	* @return Integer
	*/
	public Integer getCAccessCondition() 
	{
		return this.cAccessCondition;
	}
	
  /**
	* Set the cAccessCondition
	*/	
	public void setCAccessCondition(Integer aValue) 
	{
		this.cAccessCondition = aValue;
	}	
  /**
	*
	* @return String
	*/
	public String getCAuditname() 
	{
		return this.cAuditname;
	}
	
  /**
	* Set the cAuditname
	*/	
	public void setCAuditname(String aValue) 
	{
		this.cAuditname = aValue;
	}	
  /**
	*
	* @return String
	*/
	public String getCAudittime() 
	{
		return this.cAudittime;
	}
	
  /**
	* Set the cAudittime
	*/	
	public void setCAudittime(String aValue) 
	{
		this.cAudittime = aValue;
	}	
  /**
	*
	* @return Integer
	*/
	public Integer getCActivitynum() 
	{
		return this.cActivitynum;
	}
	
  /**
	* Set the cActivitynum
	*/	
	public void setCActivitynum(Integer aValue) 
	{
		this.cActivitynum = aValue;
	}	
  /**
	*
	* @return Integer
	*/
	public Integer getCNoticenum() 
	{
		return this.cNoticenum;
	}
	
  /**
	* Set the cNoticenum
	*/	
	public void setCNoticenum(Integer aValue) 
	{
		this.cNoticenum = aValue;
	}	
  /**
	*
	* @return Integer
	*/
	public Integer getCVideonum() 
	{
		return this.cVideonum;
	}
	
  /**
	* Set the cVideonum
	*/	
	public void setCVideonum(Integer aValue) 
	{
		this.cVideonum = aValue;
	}	
   /**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object)
	{
		if (!(object instanceof TeachGroup)) 
		{
			return false;
		}
		TeachGroup rhs = (TeachGroup) object;
		return new EqualsBuilder()
				.append(this.cSid, rhs.cSid)
				.append(this.cName, rhs.cName)
				.append(this.cLable, rhs.cLable)
				.append(this.cExt1, rhs.cExt1)
				.append(this.cExt2, rhs.cExt2)
				.append(this.cExt3, rhs.cExt3)
				.append(this.cIsused, rhs.cIsused)
				.append(this.cCreatetime, rhs.cCreatetime)
				.append(this.cUpdatorid, rhs.cUpdatorid)
				.append(this.cModifytime, rhs.cModifytime)
				.append(this.cReason, rhs.cReason)
				.append(this.cAgreement, rhs.cAgreement)
				.append(this.cSortnum, rhs.cSortnum)
				.append(this.cOrderbynum, rhs.cOrderbynum)
				.append(this.cMembernum, rhs.cMembernum)
				.append(this.cContentnum, rhs.cContentnum)
				.append(this.cUploadimg, rhs.cUploadimg)
				.append(this.cOptip, rhs.cOptip)
				.append(this.cDesc, rhs.cDesc)
				.append(this.cType, rhs.cType)
				.append(this.cArea, rhs.cArea)
				.append(this.cLearnD, rhs.cLearnD)
				.append(this.cLearnK, rhs.cLearnK)
				.append(this.cCondition, rhs.cCondition)
				.append(this.cAccessCondition, rhs.cAccessCondition)
				.append(this.cAuditname, rhs.cAuditname)
				.append(this.cAudittime, rhs.cAudittime)
				.append(this.cActivitynum, rhs.cActivitynum)
				.append(this.cNoticenum, rhs.cNoticenum)
				.append(this.cVideonum, rhs.cVideonum)
				.isEquals();
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.cSid) 
				.append(this.cName) 
				.append(this.cLable) 
				.append(this.cExt1) 
				.append(this.cExt2) 
				.append(this.cExt3) 
				.append(this.cIsused) 
				.append(this.cCreatetime) 
				.append(this.cUpdatorid) 
				.append(this.cModifytime) 
				.append(this.cReason) 
				.append(this.cAgreement) 
				.append(this.cSortnum) 
				.append(this.cOrderbynum) 
				.append(this.cMembernum) 
				.append(this.cContentnum) 
				.append(this.cUploadimg) 
				.append(this.cOptip) 
				.append(this.cDesc) 
				.append(this.cType) 
				.append(this.cArea) 
				.append(this.cLearnD) 
				.append(this.cLearnK) 
				.append(this.cCondition) 
				.append(this.cAccessCondition) 
				.append(this.cAuditname) 
				.append(this.cAudittime) 
				.append(this.cActivitynum) 
				.append(this.cNoticenum) 
				.append(this.cVideonum) 
				.toHashCode();
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
				.append("cSid", this.cSid) 
				.append("cName", this.cName) 
				.append("cLable", this.cLable) 
				.append("cExt1", this.cExt1) 
				.append("cExt2", this.cExt2) 
				.append("cExt3", this.cExt3) 
				.append("cIsused", this.cIsused) 
				.append("cCreatetime", this.cCreatetime) 
				.append("cUpdatorid", this.cUpdatorid) 
				.append("cModifytime", this.cModifytime) 
				.append("cReason", this.cReason) 
				.append("cAgreement", this.cAgreement) 
				.append("cSortnum", this.cSortnum) 
				.append("cOrderbynum", this.cOrderbynum) 
				.append("cMembernum", this.cMembernum) 
				.append("cContentnum", this.cContentnum) 
				.append("cUploadimg", this.cUploadimg) 
				.append("cOptip", this.cOptip) 
				.append("cDesc", this.cDesc) 
				.append("cType", this.cType) 
				.append("cArea", this.cArea) 
				.append("cLearnD", this.cLearnD) 
				.append("cLearnK", this.cLearnK) 
				.append("cCondition", this.cCondition) 
				.append("cAccessCondition", this.cAccessCondition) 
				.append("cAuditname", this.cAuditname) 
				.append("cAudittime", this.cAudittime) 
				.append("cActivitynum", this.cActivitynum) 
				.append("cNoticenum", this.cNoticenum) 
				.append("cVideonum", this.cVideonum) 
				.toString();
	}

   /**
	 * Return the name of the first key column
	 */
	public String getFirstKeyColumnName() 
	{
		return "cSid";
	}
	
}