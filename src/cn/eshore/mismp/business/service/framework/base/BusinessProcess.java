package cn.eshore.mismp.business.service.framework.base;

import cn.eshore.mismp.business.model.PubReqParam;
import cn.eshore.mismp.util.MobileGlobals;

public interface BusinessProcess {

	String PLAT_BASE_PATH = MobileGlobals.getProperty("plat.base.path");
	
	public String execute(PubReqParam param, String accessId);
}
