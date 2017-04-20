package cn.eshore.mismp.service;

import cn.eshore.mismp.business.service.PubBusinessService;
import cn.eshore.mismp.customer.service.CustomerService;
import cn.eshore.mismp.spmanager.service.SpService;
import cn.eshore.mismp.system.service.SystemService;
import cn.eshore.mismp.wqs.service.WqsTravelService;

public abstract interface BusinessSupportService
{
  public abstract SystemService getSystemService();

  public abstract SpService getSpService();

  public abstract CustomerService getCustomerService();
  
  public abstract WqsTravelService getWqsTravelService();
  
  public PubBusinessService getPubBusinessService();

}