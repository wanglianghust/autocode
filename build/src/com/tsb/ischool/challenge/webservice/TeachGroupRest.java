/**
 * 版权所有：版权所有(C) 2016，学酷网络 
 * 文件名称: TeachGroupRest.java 
 * 设计作者: 
 * 完成日期: 
 * 内容摘要:
 *
 * 修改记录: 
 * 修改日期:
 * 修 改 人:
 * 修改内容:
 */
package com.tsb.ischool.challenge.webservice;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import com.tsb.ischool.challenge.model.TeachGroup;
import com.tsb.ischool.challenge.service.ITeachGroupService;
import com.tsb.ischool.challenge.webservice.c2sbean.C2STeachGroup;
import com.tsb.ischool.framework.bean.comm.PageBean;
import com.tsb.ischool.framework.bean.comm.ResultBean;
import com.tsb.ischool.framework.common.ErrorCode;
import com.tsb.ischool.framework.common.ISchoolConstants;
import com.tsb.ischool.framework.exception.ISchoolException;
import com.tsb.ischool.utils.UuidUtil;

/**
 * 类 编 号： 
 * 类 名 称：TeachGroupRest
 * 内容摘要：
 * 完成日期：
 * 编码作者：
 */
@Controller
@Path("/teachgroup")
public class TeachGroupRest
{
 
    private Logger logger = Logger.getLogger(TeachGroupRest.class);
	@Resource
	private ITeachGroupService teachGroupService;

	private void log(String message) {
		String operation = ISchoolConstants.LOGGER_PREFIX_INFO + "THREADID = "
				+ Thread.currentThread().getId() + ".|"+this.getClass().getName()+"." + message
				+ ".|";
		logger.info(operation);
	}
	
	/**
	 * 新增或者编辑保存TeachGroup
	 * @throws ISchoolException
	 */
	@POST
	@Path("/insertorupdate")
	@Consumes(value = { MediaType.APPLICATION_JSON })
	@Produces(value = { MediaType.APPLICATION_JSON })
	public ResultBean insertorupdate(TeachGroup bean,@Context HttpServletRequest request) throws ISchoolException {
		int i = 0;
		if(bean.getCSid()==null){
			bean.setCSid(UuidUtil.generateUUID());
			i = teachGroupService.insert(bean);
		}else{
			i = teachGroupService.update(bean);
		}
		return new ResultBean(ResultBean.CODE_SUCCESS, 0, i, "");
	}
	
	/**
	 * 删除TeachGroup
	 * @throws ISchoolException
	 */
	@POST
	@Path("/delete")
	@Consumes(value = { MediaType.APPLICATION_JSON })
	@Produces(value = { MediaType.APPLICATION_JSON })
	public ResultBean delete(TeachGroup bean,@Context HttpServletRequest request) throws ISchoolException {
		int i = 0;
		if(bean.getCSid()!=null){
			i = teachGroupService.deleteById(bean.getCSid());
		}
		return new ResultBean(ResultBean.CODE_SUCCESS, 0, i, "");
	}
	
	/**
	 * 逻辑删除TeachGroup
	 * @throws ISchoolException
	 */
	@POST
	@Path("/deletelogic")
	@Consumes(value = { MediaType.APPLICATION_JSON })
	@Produces(value = { MediaType.APPLICATION_JSON })
	public ResultBean deletelogic(TeachGroup bean,@Context HttpServletRequest request) throws ISchoolException {
		int i = 0;
		if(bean.getCSid()!=null){
			i = teachGroupService.deletelogicById(bean.getCSid());
		}
		return new ResultBean(ResultBean.CODE_SUCCESS, 0, i, "");
	}
	
	/**
	 * 通过id查询TeachGroup详细
	 * 
	 * @throws ISchoolException
	 */
	@POST
	@Path("/querybyid")
	@Consumes(value = { MediaType.APPLICATION_JSON })
	@Produces(value = { MediaType.APPLICATION_JSON })
	public ResultBean querybyid(TeachGroup bean)
			throws ISchoolException {
		String opration = ISchoolConstants.LOGGER_PREFIX_DEBUG + "THREADID = "
				+ Thread.currentThread().getId() + ".|通过id查询TeachGroup详细.";
		logger.debug(opration + "| 开始校验参数 | TeachGroup="
				+ bean.toString());
		if (bean.verify()) {
			TeachGroup  teachGroup = teachGroupService.queryById(bean.getCSid());
			logger.debug(opration + "| 完成查询 | =TeachGroup"
					+ teachGroup);
			return new ResultBean(ResultBean.CODE_SUCCESS, 0,teachGroup, "");
		}
		return new ResultBean(ResultBean.CODE_ERROR,
				ErrorCode.ISCHOOL_REQJSON_PARSEMSG_EXCEPTION, 1, "");
	}
	
	/**
	 * 查询TeachGroup
	 * 
	 * @throws ISchoolException
	 */
	@POST
	@Path("/query")
	@Consumes(value = { MediaType.APPLICATION_JSON })
	@Produces(value = { MediaType.APPLICATION_JSON })
	public ResultBean query(C2STeachGroup bean)
			throws ISchoolException {
		String opration = ISchoolConstants.LOGGER_PREFIX_DEBUG + "THREADID = "
				+ Thread.currentThread().getId() + ".|获取TeachGroup列表.";
		logger.debug(opration + "| 开始校验参数 | bean="
				+ bean.toString());
		if (bean.verify()) {
			PageBean<TeachGroup> list = teachGroupService
					.query(bean);
			logger.debug(opration + "| 完成查询 | =TeachGroup"
					+ list);
			return new ResultBean(ResultBean.CODE_SUCCESS, 0,list, "");
		}
		return new ResultBean(ResultBean.CODE_ERROR,
				ErrorCode.ISCHOOL_REQJSON_PARSEMSG_EXCEPTION, 1, "");
	}
	
	/**
	 * 排序保存接口
	 * @throws ISchoolException
	 */
	//@POST
	//@Path("/setordernum")
	//@Consumes(value = { MediaType.APPLICATION_JSON })
	//@Produces(value = { MediaType.APPLICATION_JSON })
	//public ResultBean setordernum(TeachGroup bean,@Context HttpServletRequest request) throws ISchoolException {
	//	int i = 0;
	//	if(bean.getCSid()!=null&&bean.getOrdernum()!=null&&bean.getOrdernum().intValue()>0){
	//		i = teachGroupService.update(bean);
	//	}
	//	return new ResultBean(ResultBean.CODE_SUCCESS, 0, i, "");
	//}

	
 	
}
