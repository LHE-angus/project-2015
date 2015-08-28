package com.ebiz.mmt.web.listerner;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang.StringUtils;

import com.ebiz.mmt.web.Constants;

/**
 * @author Jin,QingHua
 */
public class ResourceListener implements ServletContextListener {
	private String mmtFileServerContextConfig = "";
	
//	private MobileInitDataInstaller installer = new MobileInitDataInstaller();

	public void contextDestroyed(ServletContextEvent sec) {
		ServletContext application = sec.getServletContext();
		application.removeAttribute("g_java_n");
		application.removeAttribute("g_html_br");
		application.removeAttribute("g_encoding");
		// application.removeAttribute("sysRoleInfoList");
		// application.removeAttribute("g_sys_role_infos");
		
//		installer.uninstall(application);

		application.removeAttribute(Constants.MMT_FILE_SERVER_CONTEXT);
	}

	public void contextInitialized(ServletContextEvent sec) {
		ServletContext application = sec.getServletContext();

		String fsccParam = application.getInitParameter("mmtFileServerContextConfig");
		if (StringUtils.isNotBlank(fsccParam)) {
			mmtFileServerContextConfig = StringUtils.removeEnd(StringUtils.removeEnd(fsccParam, "/"), File.separator);
		}
		application.setAttribute(Constants.MMT_FILE_SERVER_CONTEXT, mmtFileServerContextConfig);

		application.setAttribute("g_java_n", "\n");
		application.setAttribute("g_html_br", "<br />");
		application.setAttribute("g_encoding", "UTF-8");

		// sysRoleInfoList
		// RoleInfo t = new RoleInfo();
		// t.setIs_lock(1);
		// t.setIs_del(0);
		//
		//
		// List<RoleInfo> sysRoleInfoList =
		// facade.getRoleInfoService().getRoleInfoList(t);
		// application.setAttribute("sysRoleInfoList", sysRoleInfoList);
		//
		// String[] g_sys_role_infos = new String[100];
		// for (RoleInfo sri : sysRoleInfoList) {
		// g_sys_role_infos[sri.getId().intValue()] = sri.getRole_name();
		// }
		// application.setAttribute("g_sys_role_infos", g_sys_role_infos);

//		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(application);
//		Facade facade = (Facade) wac.getBean("facade");
//		
//		installer.install(application, facade);
	}

}
