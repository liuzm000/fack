package cn.eshore.mismp.business.servlet;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.eshore.mismp.business.model.InterfaceModel;
import cn.eshore.mismp.business.model.PubReqParam;
import cn.eshore.mismp.business.service.framework.base.BusinessProcess;
import cn.eshore.mismp.business.service.framework.base.MappingConfigException;
import cn.eshore.mismp.common.Consts;
import cn.eshore.mismp.util.MobileGlobals;
import cn.eshore.mismp.web.servlet.BaseHttpServlet;


public class BusinessServlet extends BaseHttpServlet {
	private static final long serialVersionUID = 1L;
	protected static final Logger log = Logger.getLogger(BusinessServlet.class);
	String PLAT_BASE_PATH = MobileGlobals.getProperty("plat.base.path");

	static {
		registerInvokeMethod("getBusinessData",PubReqParam.class);
	}
	
	public String getBusinessData(PubReqParam param) {
		log.debug("请求参数 :"+ param.toString());
		String result = "";
		if(param.isValid()) {
			String accessId = param.getAccessId();
			try {
				result = this.process(accessId,param);
			} catch (MappingConfigException e) {
				e.printStackTrace();
				result = Consts.XML_ERROR_JSON;
			}
		} else {
			result = Consts.XML_ERROR_JSON;
		}
		log.debug("手机端返回结果result:"+result);
		return result;
	}
    /**
     * 根据接口编码找到其对应处理类返回信息
     * @param accessId
     * @param param
     * @return
     * @throws MappingConfigException
     */
	private String process(String accessId,PubReqParam param) throws MappingConfigException {
		String result = "";
		try {
			BusinessProcess process = null;
			ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
			InterfaceModel model = this.businessSupportService.getPubBusinessService().getInterMsgById(accessId);
			if(model == null) {
				result = "没有其对应的接口";
			}
			process = (BusinessProcess)ctx.getBean(model.getInterName());
			result = process.execute(param,accessId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MappingConfigException("请检查接口配置是否正确！");
		}
		
		return result;
	}
}

