package cn.eshore.mismp.service.impl;

import cn.eshore.mismp.business.service.PubBusinessService;
import cn.eshore.mismp.customer.service.CustomerService;
import cn.eshore.mismp.service.BusinessSupportService;
import cn.eshore.mismp.spmanager.service.SpService;
import cn.eshore.mismp.system.service.SystemService;
import cn.eshore.mismp.wqs.service.WqsTravelService;

public class BusinessSupportServiceImpl implements BusinessSupportService {
	private SystemService systemService;
	private SpService spService;
	private CustomerService customerService;
	private WqsTravelService wqsTravelService;
	private PubBusinessService pubBusinessService;
	

	public SpService getSpService() {
		return this.spService;
	}

	public void setSpService(SpService spService) {
		this.spService = spService;
	}

	public SystemService getSystemService() {
		return this.systemService;
	}

	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	public CustomerService getCustomerService() {
		return this.customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public WqsTravelService getWqsTravelService() {
		return wqsTravelService;
	}

	public void setWqsTravelService(WqsTravelService wqsTravelService) {
		this.wqsTravelService = wqsTravelService;
	}

	public PubBusinessService getPubBusinessService() {
		return pubBusinessService;
	}

	public void setPubBusinessService(PubBusinessService pubBusinessService) {
		this.pubBusinessService = pubBusinessService;
	}
	
	

}