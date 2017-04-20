package cn.eshore.mismp.business.service.framework.base;

import cn.eshore.mismp.service.BusinessSupportService;

public  abstract class BusinessProcessDispacher implements BusinessProcess{
	protected  BusinessSupportService   businessSupportService;
	

	public BusinessSupportService getBusinessSupportService() {
		return businessSupportService;
	}

	public void setBusinessSupportService(
			BusinessSupportService businessSupportService) {
		this.businessSupportService = businessSupportService;
	} 
	
	

}
