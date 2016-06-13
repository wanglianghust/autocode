package com.codegenerator.db;

import com.codegenerator.common.ApplicationObject;
import com.codegenerator.common.FieldTypeResolver;
import com.codegenerator.common.Functions;

public class SqlColumn extends ApplicationObject {
	private String 	colname;
	private String  businessAttributeName;
	private short 	coltype;
	private String  attType = null;
	private int 	colsize;
	private int 	digits;
	private String 	coltypname;
	private boolean nullable    = false;
	private boolean withDefault = false;
	private boolean key = false;
	private String comment;//注释
	
	public SqlColumn
			(String colname,
			 String attName,
			 short coltype,
			 int colsize,
			 int digits,
			 String coltypname,
			 boolean nullable,
			 boolean withDefault,
			 String comment) 
	{
		this.colname = colname;
		
		if (attName == null || attName.equals("")) {
			this.businessAttributeName = colname;
		} else {
			this.businessAttributeName = attName;
		}
		this.coltype = coltype;
		this.colsize = colsize;
		this.coltypname = coltypname;
		this.digits  = digits;
		this.nullable = nullable;
		this.withDefault = withDefault;
		this.comment = comment;
		initAttType();
	}
	/**
	 * @return Returns the key.
	 */
	public boolean isKey() {
		return key;
	}
	/**
	 * @return Returns the key.
	 */
	public boolean isVersion() {
		return getColname().equalsIgnoreCase("version");
	}
	/**
	 * @param key The key to set.
	 */
	public void setKey(boolean key) {
		this.key = key;
	}
	/**
	 * @return Returns the colname.
	 */
	public String getColname() {
		return colname;
	}
	public String getAttType() {
		
		 if (attType == null) 
		 	initAttType();
	 
		return attType;
	}
	/**
	 * @param attType
	 * @return
	 */
	private void initAttType() {
		/* 
		 * NOTE - if new Java types are added in this method, they must also
		 * be added to Functions.makeSampleDataWithDatePattern()
		 *  :NPA: 
		 */
		
		// default attribute Type to String
		attType = "String";
		
		if (Functions.hasMask(coltypname,"char"))
		    attType = "String";
		    	
	    else if (Functions.hasMask(coltypname,"date"))
			attType = "java.util.Date";
	    
		else if (Functions.hasMask(coltypname,"decimal"))
			//attType = "java.math.BigDecimal";
			attType = "Integer";
		else if (Functions.hasMask(coltypname,"numeric")) // :NPA: needed for PostgreSQL
			attType = "java.math.BigDecimal";
		
		else if (Functions.hasMask(coltypname,"timestamp"))
			//attType = "java.sql.Timestamp"; 
		    attType = "String"; 
		
		else if (Functions.hasMask(coltypname,"small"))
		    attType = "Short";
		
		else if (Functions.hasMask(coltypname,"bigint"))
			attType = "Long";
		
		else if (Functions.hasMask(coltypname,"int8")) // :NPA: needed for PostgreSQL
			attType = "Long";
		
		else if (Functions.hasMask(coltypname,"bigserial")) // :NPA: needed for PostgreSQL
			attType = "Long";
		
		else if (Functions.hasMask(coltypname,"int"))
			attType = "Integer";
		
		else if (Functions.hasMask(coltypname,"serial")) // :NPA: needed for PostgreSQL
			attType = "Integer";

		else if (Functions.hasMask(coltypname,"double"))
			attType = "Double";
		
		else if (Functions.hasMask(coltypname,"Float"))
			attType = "Float";
		else if(Functions.hasMask(coltypname, "NUMBER")) //NEED FOR ORACLE
		{
			if(this.digits == 0  ) 
			{
//				
				if(this.colsize < 9)//32bits means 9.6..e9
				{
					attType="Integer";
				}
				else if(this.colsize < 19) //64 bits means 1.xxe19
				{ 
					attType="Long";
				}
				else {
					//key should use string
					attType="java.math.BigInteger";
				}
			}
		
			else {
				attType = "java.math.BigDecimal";
			}
		}
		// :TODO: There are many more types that are likely to be necessary for complete
		//        PostgreSQL support
		
	}
	public String getHibernateType() {
		if (isKey()){
			return "id";
		}
		else {
			return "property";
		}
	}
	public String getNotNullable() {
		if (!nullable)
			return "true";
		else
			return "false";
	}
	public String getNullable() {
		if (!nullable)
			return "false";
		else
			return "true";
	}
	public String getAttName() {
		return Functions.makeVarName(colname);
	}
	public String getAttNameUC() {
		String tmp = getAttName();
		return Functions.makeFirstLetterUpperCase(tmp);
	}
	public String getColnameLC() {
		return colname.toLowerCase();
	}
	public boolean isStringColumn() {
		return Functions.hasMask(getAttType(), "String");
	}
	public boolean isDecimal() {
		return Functions.hasMask(getAttType(),"Decimal");
	}
	public boolean isType(String aType) {
		return Functions.hasMask(getAttType(), aType);
	}
	public String getDecimalMask() {
		String mask = "";
		if (!isDecimal()) {
			return mask;
		}
		mask = Functions.getFmtString(getBusinessAttributeVar(),
			   getColsize(),getDigits());
		return mask;
	}
	public boolean isRequired() {
	
		if (colname.equalsIgnoreCase("version"))
			return false;
		else
		if (isKey()) {
			return true;
		}
		else
		if (!isNullable())
			return true;
		else 
		return false;
	}
	/**
	 * @return Returns the colsize.
	 */
	public int getColsize() {
		return colsize;
	}
	/**
	 * @return Returns the coltype.
	 */
	public short getColtype() {
		return coltype;
	}
	public String getJavaType() {
		String javaType = getAttType();
		if (!(javaType.indexOf(".") > 0)) {
			javaType = "java.lang."+getAttType();
		}
		return javaType;
	}
	/**
	 * @return Returns the coltypname.
	 */
	public String getColtypname() {
		return coltypname;
	}
	/**
	 * @return Returns the digits.
	 */
	public int getDigits() {
		return digits;
	}
	/**
	 * @return Returns the nullable.
	 */
	public boolean isNullable() {
		return nullable;
	}
	/**
	 * @return Returns the withDefault.
	 */
	public boolean isWithDefault() {
		return withDefault;
	}
	
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	/**
	 * @return Returns the businessAttributeName.
	 */
	public String getBusinessAttributeName() {
		return businessAttributeName;
	}
	public String getResolvedAttType() {
		String typ = getAttType();
		String resolvedType = FieldTypeResolver.getResolver().getPropertyValue(
				getBusinessAttributeVar(), typ);
		return resolvedType;
	}
	public String getBusinessAttributeVar() {
		return Functions.makeVarName(businessAttributeName);
	}
	public String getBusinessAttribute() {
		return Functions.makeClassName(businessAttributeName);
	}
}
