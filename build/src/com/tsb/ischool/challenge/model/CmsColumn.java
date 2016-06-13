/**
 * 版权所有：版权所有(C) 2016，学酷网络 
 * 文件名称: CmsColumn.java 
 * 设计作者: 
 * 完成日期: 
 * 内容摘要:对应表[T_CMS_COLUMN],映射配置文件为CmsColumn.xml 
 *
 * 修改记录: 
 * 修改日期:
 * 修 改 人:
 * 修改内容:
 */
package com.tsb.ischool.challenge.model;

import java.io.Serializable;
import com.tsb.ischool.challenge.model.CmsColumn;
import com.tsb.ischool.framework.common.ErrorCode;
import com.tsb.ischool.framework.exception.ISchoolException;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
* 类 名 称：CmsColumn
* 内容摘要：
* 完成日期：
* 编码作者：
*/
public class CmsColumn implements Serializable 
{

    /** 
     *PRIMARY KEY,对应表[T_CMS_COLUMN]的主键C_SID
     */
    private String cSid;//主键

    /** 
     *对应表[T_CMS_COLUMN]的字段C_NAME
     */
	private String cName;//

    /** 
     *对应表[T_CMS_COLUMN]的字段C_LEVEL
     */
	private Integer cLevel;//

    /** 
     *对应表[T_CMS_COLUMN]的字段C_SORT
     */
	private Integer cSort;//

    /** 
     *对应表[T_CMS_COLUMN]的字段C_DESC
     */
	private String cDesc;//

    /** 
     *对应表[T_CMS_COLUMN]的字段C_PARENTID
     */
	private String cParentid;//

    /** 
     *对应表[T_CMS_COLUMN]的字段C_URL
     */
	private String cUrl;//

    /** 
     *对应表[T_CMS_COLUMN]的字段C_CITYID
     */
	private String cCityid;//

    /** 
     *对应表[T_CMS_COLUMN]的字段C_CITYNAME
     */
	private String cCityname;//

    /** 
     *对应表[T_CMS_COLUMN]的字段C_AREAID
     */
	private String cAreaid;//

    /** 
     *对应表[T_CMS_COLUMN]的字段C_AREANAME
     */
	private String cAreaname;//

    /** 
     *对应表[T_CMS_COLUMN]的字段C_SCHOOLID
     */
	private String cSchoolid;//

    /** 
     *对应表[T_CMS_COLUMN]的字段C_SCHOOLNAME
     */
	private String cSchoolname;//

    /** 
     *对应表[T_CMS_COLUMN]的字段C_ISUSE
     */
	private Integer cIsuse;//是否可用<0=不可用,1=可用>

    /** 
     *对应表[T_CMS_COLUMN]的字段C_SUMMARY1
     */
	private String cSummary1;//

    /** 
     *对应表[T_CMS_COLUMN]的字段C_SUMMARY2
     */
	private String cSummary2;//

    /** 
     *对应表[T_CMS_COLUMN]的字段C_SUMMARY3
     */
	private String cSummary3;//

    /** 
     *对应表[T_CMS_COLUMN]的字段C_CREATORID
     */
	private String cCreatorid;//

    /** 
     *对应表[T_CMS_COLUMN]的字段C_CREATETIME
     */
	private String cCreatetime;//

    /** 
     *对应表[T_CMS_COLUMN]的字段C_STATICURL
     */
	private String cStaticurl;//

    /** 
     *对应表[T_CMS_COLUMN]的字段C_ISTATIC
     */
	private Integer cIstatic;//是否需要静态化<0=否(默认),1=是>

    /** 
     *对应表[T_CMS_COLUMN]的字段C_PAGETYPE
     */
	private Integer cPagetype;//0=无,1=首页,2=依赖于下级,31=带图文章列表,32=不带图文章列表,4=相册,5=单一文章,7=带回复的

    /** 
     *对应表[T_CMS_COLUMN]的字段C_ISHOME
     */
	private Integer cIshome;//

    /** 
     *对应表[T_CMS_COLUMN]的字段C_HOMEPOS
     */
	private Integer cHomepos;//

public boolean verify() throws ISchoolException {
		if (null == this) {
			throw new ISchoolException(ErrorCode.ISCHOOL_REQBODY_INVALID_MSGTYPE, "参数错误，入参为空。");
		}
		return true;
	}
	


  /**
	*
	* Default Empty Constructor for class CmsColumn
	*
	*/
	public CmsColumn() 
	{
		super();
	}
	
  /**
	*
	* Default All Fields Constructor for class CmsColumn
	*
	*/
    public CmsColumn (
		 String cSid
		,String cName
		,Integer cLevel
		,Integer cSort
		,String cDesc
		,String cParentid
		,String cUrl
		,String cCityid
		,String cCityname
		,String cAreaid
		,String cAreaname
		,String cSchoolid
		,String cSchoolname
		,Integer cIsuse
		,String cSummary1
		,String cSummary2
		,String cSummary3
		,String cCreatorid
		,String cCreatetime
		,String cStaticurl
		,Integer cIstatic
		,Integer cPagetype
		,Integer cIshome
		,Integer cHomepos
        ) {
		this.setCSid(cSid);
		this.setCName(cName);
		this.setCLevel(cLevel);
		this.setCSort(cSort);
		this.setCDesc(cDesc);
		this.setCParentid(cParentid);
		this.setCUrl(cUrl);
		this.setCCityid(cCityid);
		this.setCCityname(cCityname);
		this.setCAreaid(cAreaid);
		this.setCAreaname(cAreaname);
		this.setCSchoolid(cSchoolid);
		this.setCSchoolname(cSchoolname);
		this.setCIsuse(cIsuse);
		this.setCSummary1(cSummary1);
		this.setCSummary2(cSummary2);
		this.setCSummary3(cSummary3);
		this.setCCreatorid(cCreatorid);
		this.setCCreatetime(cCreatetime);
		this.setCStaticurl(cStaticurl);
		this.setCIstatic(cIstatic);
		this.setCPagetype(cPagetype);
		this.setCIshome(cIshome);
		this.setCHomepos(cHomepos);
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
	* @return Integer
	*/
	public Integer getCLevel() 
	{
		return this.cLevel;
	}
	
  /**
	* Set the cLevel
	*/	
	public void setCLevel(Integer aValue) 
	{
		this.cLevel = aValue;
	}	
  /**
	*
	* @return Integer
	*/
	public Integer getCSort() 
	{
		return this.cSort;
	}
	
  /**
	* Set the cSort
	*/	
	public void setCSort(Integer aValue) 
	{
		this.cSort = aValue;
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
	* @return String
	*/
	public String getCParentid() 
	{
		return this.cParentid;
	}
	
  /**
	* Set the cParentid
	*/	
	public void setCParentid(String aValue) 
	{
		this.cParentid = aValue;
	}	
  /**
	*
	* @return String
	*/
	public String getCUrl() 
	{
		return this.cUrl;
	}
	
  /**
	* Set the cUrl
	*/	
	public void setCUrl(String aValue) 
	{
		this.cUrl = aValue;
	}	
  /**
	*
	* @return String
	*/
	public String getCCityid() 
	{
		return this.cCityid;
	}
	
  /**
	* Set the cCityid
	*/	
	public void setCCityid(String aValue) 
	{
		this.cCityid = aValue;
	}	
  /**
	*
	* @return String
	*/
	public String getCCityname() 
	{
		return this.cCityname;
	}
	
  /**
	* Set the cCityname
	*/	
	public void setCCityname(String aValue) 
	{
		this.cCityname = aValue;
	}	
  /**
	*
	* @return String
	*/
	public String getCAreaid() 
	{
		return this.cAreaid;
	}
	
  /**
	* Set the cAreaid
	*/	
	public void setCAreaid(String aValue) 
	{
		this.cAreaid = aValue;
	}	
  /**
	*
	* @return String
	*/
	public String getCAreaname() 
	{
		return this.cAreaname;
	}
	
  /**
	* Set the cAreaname
	*/	
	public void setCAreaname(String aValue) 
	{
		this.cAreaname = aValue;
	}	
  /**
	*
	* @return String
	*/
	public String getCSchoolid() 
	{
		return this.cSchoolid;
	}
	
  /**
	* Set the cSchoolid
	*/	
	public void setCSchoolid(String aValue) 
	{
		this.cSchoolid = aValue;
	}	
  /**
	*
	* @return String
	*/
	public String getCSchoolname() 
	{
		return this.cSchoolname;
	}
	
  /**
	* Set the cSchoolname
	*/	
	public void setCSchoolname(String aValue) 
	{
		this.cSchoolname = aValue;
	}	
  /**
	*
	* @return Integer
	*/
	public Integer getCIsuse() 
	{
		return this.cIsuse;
	}
	
  /**
	* Set the cIsuse
	*/	
	public void setCIsuse(Integer aValue) 
	{
		this.cIsuse = aValue;
	}	
  /**
	*
	* @return String
	*/
	public String getCSummary1() 
	{
		return this.cSummary1;
	}
	
  /**
	* Set the cSummary1
	*/	
	public void setCSummary1(String aValue) 
	{
		this.cSummary1 = aValue;
	}	
  /**
	*
	* @return String
	*/
	public String getCSummary2() 
	{
		return this.cSummary2;
	}
	
  /**
	* Set the cSummary2
	*/	
	public void setCSummary2(String aValue) 
	{
		this.cSummary2 = aValue;
	}	
  /**
	*
	* @return String
	*/
	public String getCSummary3() 
	{
		return this.cSummary3;
	}
	
  /**
	* Set the cSummary3
	*/	
	public void setCSummary3(String aValue) 
	{
		this.cSummary3 = aValue;
	}	
  /**
	*
	* @return String
	*/
	public String getCCreatorid() 
	{
		return this.cCreatorid;
	}
	
  /**
	* Set the cCreatorid
	*/	
	public void setCCreatorid(String aValue) 
	{
		this.cCreatorid = aValue;
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
	public String getCStaticurl() 
	{
		return this.cStaticurl;
	}
	
  /**
	* Set the cStaticurl
	*/	
	public void setCStaticurl(String aValue) 
	{
		this.cStaticurl = aValue;
	}	
  /**
	*
	* @return Integer
	*/
	public Integer getCIstatic() 
	{
		return this.cIstatic;
	}
	
  /**
	* Set the cIstatic
	*/	
	public void setCIstatic(Integer aValue) 
	{
		this.cIstatic = aValue;
	}	
  /**
	*
	* @return Integer
	*/
	public Integer getCPagetype() 
	{
		return this.cPagetype;
	}
	
  /**
	* Set the cPagetype
	*/	
	public void setCPagetype(Integer aValue) 
	{
		this.cPagetype = aValue;
	}	
  /**
	*
	* @return Integer
	*/
	public Integer getCIshome() 
	{
		return this.cIshome;
	}
	
  /**
	* Set the cIshome
	*/	
	public void setCIshome(Integer aValue) 
	{
		this.cIshome = aValue;
	}	
  /**
	*
	* @return Integer
	*/
	public Integer getCHomepos() 
	{
		return this.cHomepos;
	}
	
  /**
	* Set the cHomepos
	*/	
	public void setCHomepos(Integer aValue) 
	{
		this.cHomepos = aValue;
	}	
   /**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object)
	{
		if (!(object instanceof CmsColumn)) 
		{
			return false;
		}
		CmsColumn rhs = (CmsColumn) object;
		return new EqualsBuilder()
				.append(this.cSid, rhs.cSid)
				.append(this.cName, rhs.cName)
				.append(this.cLevel, rhs.cLevel)
				.append(this.cSort, rhs.cSort)
				.append(this.cDesc, rhs.cDesc)
				.append(this.cParentid, rhs.cParentid)
				.append(this.cUrl, rhs.cUrl)
				.append(this.cCityid, rhs.cCityid)
				.append(this.cCityname, rhs.cCityname)
				.append(this.cAreaid, rhs.cAreaid)
				.append(this.cAreaname, rhs.cAreaname)
				.append(this.cSchoolid, rhs.cSchoolid)
				.append(this.cSchoolname, rhs.cSchoolname)
				.append(this.cIsuse, rhs.cIsuse)
				.append(this.cSummary1, rhs.cSummary1)
				.append(this.cSummary2, rhs.cSummary2)
				.append(this.cSummary3, rhs.cSummary3)
				.append(this.cCreatorid, rhs.cCreatorid)
				.append(this.cCreatetime, rhs.cCreatetime)
				.append(this.cStaticurl, rhs.cStaticurl)
				.append(this.cIstatic, rhs.cIstatic)
				.append(this.cPagetype, rhs.cPagetype)
				.append(this.cIshome, rhs.cIshome)
				.append(this.cHomepos, rhs.cHomepos)
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
				.append(this.cLevel) 
				.append(this.cSort) 
				.append(this.cDesc) 
				.append(this.cParentid) 
				.append(this.cUrl) 
				.append(this.cCityid) 
				.append(this.cCityname) 
				.append(this.cAreaid) 
				.append(this.cAreaname) 
				.append(this.cSchoolid) 
				.append(this.cSchoolname) 
				.append(this.cIsuse) 
				.append(this.cSummary1) 
				.append(this.cSummary2) 
				.append(this.cSummary3) 
				.append(this.cCreatorid) 
				.append(this.cCreatetime) 
				.append(this.cStaticurl) 
				.append(this.cIstatic) 
				.append(this.cPagetype) 
				.append(this.cIshome) 
				.append(this.cHomepos) 
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
				.append("cLevel", this.cLevel) 
				.append("cSort", this.cSort) 
				.append("cDesc", this.cDesc) 
				.append("cParentid", this.cParentid) 
				.append("cUrl", this.cUrl) 
				.append("cCityid", this.cCityid) 
				.append("cCityname", this.cCityname) 
				.append("cAreaid", this.cAreaid) 
				.append("cAreaname", this.cAreaname) 
				.append("cSchoolid", this.cSchoolid) 
				.append("cSchoolname", this.cSchoolname) 
				.append("cIsuse", this.cIsuse) 
				.append("cSummary1", this.cSummary1) 
				.append("cSummary2", this.cSummary2) 
				.append("cSummary3", this.cSummary3) 
				.append("cCreatorid", this.cCreatorid) 
				.append("cCreatetime", this.cCreatetime) 
				.append("cStaticurl", this.cStaticurl) 
				.append("cIstatic", this.cIstatic) 
				.append("cPagetype", this.cPagetype) 
				.append("cIshome", this.cIshome) 
				.append("cHomepos", this.cHomepos) 
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