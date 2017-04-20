package cn.eshore.mismp.web.listener;

import cn.eshore.mismp.service.BusinessSupportService;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class StartupListener extends ContextLoaderListener implements
		ServletContextListener {
	private static final Log log = LogFactory.getLog(StartupListener.class);

	public void contextInitialized(ServletContextEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("initializing spring context...");
		}
		super.contextInitialized(event);
		ServletContext context = event.getServletContext();
		setupContext(context);
	}

	public static void setupContext(ServletContext context) {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(context);
		String[] beanDefinitionNames = ctx.getBeanDefinitionNames();
		log.debug("===================================");
		log.info("Count of beans in spring = " + beanDefinitionNames.length);
		for (int i = 0; i < beanDefinitionNames.length; i++) {
			log.debug("definitionBean" + i + "  = " + beanDefinitionNames[i]);
		}
		log.debug("===================================");
		if (log.isDebugEnabled())
			log.debug("initialization of spring context finished!");
		
	}

	private static void initSms(ApplicationContext ctx) {
		BusinessSupportService businessSupportService = (BusinessSupportService) ctx
				.getBean("businessSupportService");
	}

}
