package cn.eshore.mismp.version.common;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FcBizBeanFactory {

	private static ApplicationContext ac = null;

	public static void init()
	{
		if (ac == null) {
			ac = new ClassPathXmlApplicationContext("classpath*:context/applicationContext-*.xml");
		}
	}

	public static synchronized Object getBean(String beanName)
	{
		if (ac == null) {
			ac = new ClassPathXmlApplicationContext("classpath*:context/applicationContext-*.xml");
		}
		return ac.getBean(beanName);
	}
}
