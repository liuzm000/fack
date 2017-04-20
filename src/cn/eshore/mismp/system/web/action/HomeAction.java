package cn.eshore.mismp.system.web.action;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.eshore.mismp.dao.domain.ModuleVO;
import cn.eshore.mismp.dao.domain.UserSPVO;
import cn.eshore.mismp.dao.domain.UserVO;
import cn.eshore.mismp.web.action.BaseAction;

/**
 * <p>Title: HomeAction</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: poson</p>
 * <p><a href="HomeAction.java.html"><i>View Source</i></a></p>
 * @author <a href="Yusm@gsta.com">Yu Songming</a>
 * @version 1.0
 * Modified at:
 * Modified by:
 */
public class HomeAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	String account = null;
	String password = null;
	String validateCode = null;
	String errorInfo = null;

	public String leftFrame(){		
		return SUCCESS;
	}
	
	public String mainFrame(){		
		return SUCCESS;
	}
	
	public String login() {		
		HttpServletRequest req = this.getRequest();
		
		String sessionCode = (String) req.getSession().getAttribute(
				"validateCode");
		if (validateCode == null || "".equals(validateCode)
				|| !(validateCode.equals(sessionCode))) {
			errorInfo = "验证码输入有误,请重试!";
			req.getSession().setAttribute("errorInfo", errorInfo);
			return "fail";
		}

		if (account == null || account.equals("")) {
			errorInfo = "帐户不能为空";
			req.getSession().setAttribute("errorInfo", errorInfo);
			return "fail";
		}

		UserVO vo = this.businessSupportService.getSystemService().getUser(account.trim());
		if (vo.getAccount() == null) {
			errorInfo = "帐户不存在";
			req.getSession().setAttribute("errorInfo", errorInfo);
			return "fail";
		} else if (password.equals(vo.getPassword())) {
			req.getSession().setAttribute("account", account);
			req.getSession().setAttribute("roleName", vo.getRoleName());
			req.getSession().setAttribute("roleLevel", vo.getRoleLevel());
			req.getSession().setAttribute("UserVO", vo);
			
			List<ModuleVO> haveModuleList = this.getBusinessSupportService().getSystemService().getModulesByRoleId(vo.getRoleId());			
			req.getSession().setAttribute("haveModuleList", haveModuleList);
			Map<String, String> map = new HashMap<String, String>();
			for (int i = 0; i < haveModuleList.size(); i++) {
				ModuleVO mvo = haveModuleList.get(i);
				if(mvo.getReadOnly() == 1){
					map.put(mvo.getModule_url(), "1");
				}
			}
			req.getSession().setAttribute("ReadOnlyModuleMap", map);
			
			//add by jingfei 20090610
			UserSPVO userSPVO = this.getBusinessSupportService().getSystemService().getUserSPVO(Long.parseLong(vo.getUserId()));
			req.getSession().setAttribute("UserSPVO", userSPVO);
			
			
			return SUCCESS;
		} else {
			errorInfo = "密码错误!";
			req.getSession().setAttribute("errorInfo", errorInfo);
			return "fail";
		}
		
	}
	
	public String logout() {		
		String operatorName = getRequest().getSession().getAttribute("account").toString();
		getRequest().getSession().removeAttribute("account");
		getRequest().getSession().removeAttribute("roleName");
		getRequest().getSession().removeAttribute("haveModuleList");
		return SUCCESS;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}
	
}
