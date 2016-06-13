/**
 * 版权所有：版权所有(C) 2016，学酷网络 
 * 文件名称: C2SCmsColumn.java 
 * 设计作者: 
 * 完成日期: 
 * 内容摘要:
 *
 * 修改记录: 
 * 修改日期:
 * 修 改 人:
 * 修改内容:
 */
package com.tsb.ischool.challenge.webservice.c2sbean;

import java.io.Serializable;
import com.tsb.ischool.framework.common.ErrorCode;
import com.tsb.ischool.framework.exception.ISchoolException;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
* 类 名 称：C2SCmsColumn
* 内容摘要：
* 完成日期：
* 编码作者：
*/
public class C2SCmsColumn implements Serializable 
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

    private int curpage;//当前页码
	private int pagesize;//每页个数
	
	public boolean verify() throws ISchoolException {
		if (null == this) {
			throw new ISchoolException(ErrorCode.ISCHOOL_REQBODY_INVALID_MSGTYPE, "参数错误，入参为空。");
		}
		return true;
	}
	
	public int getCurpage() {
		return curpage;
	}

	public void setCurpage(int curpage) {
		this.curpage = curpage;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
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
				.append("curpage", this.curpage).append("pagesize", this.pagesize) 
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