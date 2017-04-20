package cn.eshore.mismp.business.service.process;


import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import cn.eshore.mismp.business.model.PubReqParam;
import cn.eshore.mismp.business.service.framework.base.BusinessProcessDispacher;
import cn.eshore.mismp.util.Pagination;
import cn.eshore.mismp.wqs.servlet.WqsTravelServlet;

public class BaseIconBusinessProcess extends BusinessProcessDispacher {

	protected static final Logger log = Logger.getLogger(WqsTravelServlet.class);
	public String execute(PubReqParam param,String interNum) {
		String result = "";
		log.info("[VideoBusinessProcess]  请求参数:" + param.toString());
		String page = param.getPage();
		if(StringUtils.isEmpty(page)) {
			page = "1";
		}
		String pagesize= param.getPagesize();
		if(StringUtils.isEmpty(pagesize)) {
			pagesize = "20";
		}
		Pagination list = businessSupportService.getPubBusinessService().getPubVideoList(page,pagesize,interNum);
		
		result = WrapJsonUtils.paginationWarpJson(result, list,businessSupportService);
			
		return result;
		
		
	}
}
