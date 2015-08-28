package com.ebiz.mmt.web.struts;

import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.upload.FormFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.alibaba.fastjson.JSON;
import com.ebiz.mmt.domain.BasePdClazz;
import com.ebiz.mmt.domain.BaseProvinceList;
import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.BranchAssign;
import com.ebiz.mmt.domain.Category;
import com.ebiz.mmt.domain.ChannelDataImport;
import com.ebiz.mmt.domain.CrmCustomerGroup;
import com.ebiz.mmt.domain.CrmPriceHeader;
import com.ebiz.mmt.domain.CrmPriceLines;
import com.ebiz.mmt.domain.JBaseGoods;
import com.ebiz.mmt.domain.JBaseGoodsInitStock;
import com.ebiz.mmt.domain.JBaseStore;
import com.ebiz.mmt.domain.JStocksVerify;
import com.ebiz.mmt.domain.JsStocks;
import com.ebiz.mmt.domain.KonkaBaseTypeData;
import com.ebiz.mmt.domain.KonkaBranchCategory;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.KonkaJxcStoreInfo;
import com.ebiz.mmt.domain.KonkaOrderAuditProcessNode;
import com.ebiz.mmt.domain.KonkaOrderInfo;
import com.ebiz.mmt.domain.KonkaOrderInfoAudit;
import com.ebiz.mmt.domain.KonkaR3MmtMatch;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.domain.KonkaSell;
import com.ebiz.mmt.domain.KonkaSellDetails;
import com.ebiz.mmt.domain.KonkaSpList;
import com.ebiz.mmt.domain.KonkaStockDetails;
import com.ebiz.mmt.domain.KonkaXxZmd;
import com.ebiz.mmt.domain.KonkaXxZmdUsers;
import com.ebiz.mmt.domain.MmtEntpShop;
import com.ebiz.mmt.domain.ModPopedom;
import com.ebiz.mmt.domain.OperLog;
import com.ebiz.mmt.domain.Organizationa;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleInfo;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.domain.SxExtendedPermissions;
import com.ebiz.mmt.domain.SysModule;
import com.ebiz.mmt.domain.SysSetting;
import com.ebiz.mmt.domain.UserLoginErrLogs;
import com.ebiz.mmt.r3.KHXD;
import com.ebiz.mmt.r3.ReturnInfo;
import com.ebiz.mmt.r3.StockCheckResult;
import com.ebiz.mmt.r3.ZLEBIN;
import com.ebiz.mmt.service.Facade;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.manager.oa.IpUtils;
import com.ebiz.mmt.web.util.BeanToMapUtil;
import com.ebiz.mmt.web.util.DESPlus;
import com.ebiz.mmt.web.util.FtpImageUtils;
import com.ebiz.mmt.web.util.StringHelper;
import com.ebiz.mmt.web.util.yixun.config.YixunConfig;
import com.ebiz.org.apache.commons.lang3.time.DateUtils;
import com.ebiz.ssi.ftp.FTPClientTemplate;
import com.ebiz.ssi.util.EncryptUtils;
import com.ebiz.ssi.web.struts.BaseSsiAction;
import com.ebiz.ssi.web.struts.bean.Pager;
import com.ebiz.ssi.web.struts.bean.UploadFile;

/**
 * @author Jin,QingHua
 */
public abstract class BaseAction extends BaseSsiAction {
	protected static final Logger logger = LoggerFactory.getLogger(BaseAction.class);

	private FTPClientTemplate ftpClientTemplate;

	private Facade facade;

	private JavaMailSenderImpl mailSender;

	public FTPClientTemplate getFtpClientTemplate() {
		return ftpClientTemplate;
	}

	public void setFtpClientTemplate(FTPClientTemplate ftpClientTemplate) {
		this.ftpClientTemplate = ftpClientTemplate;
	}

	/**
	 * the powerful method to return facade with all services(method)
	 * 
	 * @return facade
	 */
	protected Facade getFacade() {
		return this.facade;
	}

	public JavaMailSenderImpl getMailSender() {
		return mailSender;
	}

	@Override
	public void setServlet(ActionServlet actionServlet) {
		super.setServlet(actionServlet);
		Assert.notNull(actionServlet, "actionServlet is can not be null");
		ServletContext servletContext = actionServlet.getServletContext();
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		this.facade = (Facade) wac.getBean("facade");
		this.ftpClientTemplate = (FTPClientTemplate) wac.getBean("ftpClientTemplate");
		this.mailSender = (JavaMailSenderImpl) wac.getBean("mailSender");
	}

	public String getCtxPath(HttpServletRequest request) {
		StringBuffer ctx = new StringBuffer();
		ctx.append(request.getScheme()).append("://").append(request.getServerName()).append(":")
				.append(request.getServerPort());
		ctx.append(request.getContextPath());

		return ctx.toString();
	}

	/**
	 * 将导航信息放到 request 作用域
	 */
	protected void setNaviStringToRequestScope(ActionForm form, HttpServletRequest request) {
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");

		String naviString = "";
		if (StringUtils.isNotBlank(mod_id)) {
			SysModule sysModule = new SysModule();
			sysModule.getMap().put("mod_id", mod_id);
			sysModule.getMap().put("setnav", "yes");
			List<SysModule> sysModuleList = getFacade().getSysModuleService().getSysModuleList(sysModule);
			naviString = StringHelper.getNaviString(sysModuleList);
			String strString="<font style='font-size:12px;color:#3e3e3e'>"+ naviString.substring(0,naviString.lastIndexOf(";")+1)+"</font>";
			String endString="<font style='font-size:12px;color:#e60012'>"+ naviString.substring(naviString.lastIndexOf(";")+1)+"</font>";
			naviString=strString+endString;
			sysModule.getMap().put("mod_id", null);
			sysModule.setMod_id(Integer.parseInt(mod_id));
			sysModule = getFacade().getSysModuleService().getSysModule(sysModule);

			if (null != sysModule) {
				request.setAttribute("helpString", sysModule.getMod_desc());
			}

		}
		request.setAttribute("naviString", naviString);
	}

	protected void updateDataPatch() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmm");
		Date justNow = new java.util.Date();
		SysSetting ss = new SysSetting();
		ss.setTitle("datapatch");
		ss.setContent("50" + df.format(justNow));
		getFacade().getSysSettingService().modifySysSetting(ss);
	}

	/**
	 * 验证是否具有操作权限
	 */
	public Object checkUserModPopeDom(ActionForm form, HttpServletRequest request, String... popedoms) {
		boolean legitimate = false;
		String modPopedom = this.getModPopeDom(form, request);
		if ("+".equals(modPopedom)) {
			return null;
		}

		for (String popedom : popedoms) {
			popedom = popedom.concat("+");
			if (StringUtils.indexOf(modPopedom, popedom) == -1) {
				legitimate = false;
				break;
			}
			legitimate = true;
		}

		if (legitimate) {
			return legitimate;
		}
		return null;
	}

	public String getModPopeDom(ActionForm form, HttpServletRequest request) {
		DynaBean dynaBean = (DynaBean) form;
		String mod_id = (String) dynaBean.get("mod_id");
		StringBuffer popedom = new StringBuffer();

		if (!GenericValidator.isInt(mod_id)) {
			return null;
		}

		// 查询该模块是否是public
		SysModule mod = new SysModule();
		mod.setMod_id(Integer.valueOf(mod_id));
		mod = this.getFacade().getSysModuleService().getSysModule(mod);
		if (null == mod) {
			return null;
		}

		if (mod.getIs_public() == 1) {
			popedom.append("+0++1++2++4++8++16++128+");
			request.setAttribute("popedom", popedom.toString());
			return popedom.toString();
		}

		HttpSession session = request.getSession();
		PeProdUser userInfo = (PeProdUser) session.getAttribute(Constants.USER_INFO);

		PeRoleUser roleUser = new PeRoleUser();
		roleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> roleUserList = getFacade().getPeRoleUserService().getPeRoleUserList(roleUser);

		boolean legal = false;
		for (PeRoleUser roleUser1 : roleUserList) {
			if (null == roleUser1) {
				continue;
			}
			legal = true;

			// 查询角色的权限
			ModPopedom webModPopedom = new ModPopedom();
			webModPopedom.setMod_id(Long.valueOf(mod_id));
			webModPopedom.setRole_id(roleUser1.getRole_id());
			webModPopedom = this.getFacade().getModPopedomService().getModPopedom(webModPopedom);
			if (null != webModPopedom) {
				popedom.append(webModPopedom.getMap().get("ppdm_detail"));
			}
		}
		if (legal) {
			popedom.append("+");
		}

		// 查询用户的授权
		ModPopedom modPopedom = new ModPopedom();
		modPopedom.setMod_id(Long.valueOf(mod_id));
		modPopedom.setUser_id(userInfo.getId());
		modPopedom = this.getFacade().getModPopedomService().getModPopedom(modPopedom);
		if (null != modPopedom) {
			popedom.append(modPopedom.getMap().get("ppdm_detail"));
		}
		popedom.append("+");

		// add by zhou 2013/10/23
		if (roleUserList != null && roleUserList.size() > 0) {
			request.getSession().setAttribute("roleUserList", roleUserList);
		}
		request.setAttribute("popedom", popedom.toString());

		return popedom.toString();
	}

	public ActionForward checkPopedomInvalid(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String msg = super.getMessage(request, "popedom.check.invalid");
		super.renderJavaScript(response,
				"alert('".concat(msg).concat("');window.parent.frames['leftFrame'].location.reload(true);"));
		return null;
	}

	public ActionForward invalidOper(HttpServletRequest request, HttpServletResponse response, String message)
			throws IOException {

		String msg = super.getMessage(request, message);
		super.renderJavaScript(response, "alert('".concat(msg).concat("');history.back();"));
		return null;

	}

	/**
	 * @author Hui,Gang
	 * @version Build 2009.12.11
	 */
	public void encodeCharacterForGetMethod(Object object, HttpServletRequest request) throws Exception {
		if (!StringUtils.equalsIgnoreCase(request.getMethod(), "GET")) {
			return;
		}

		if (object instanceof DynaBean) {
			DynaBean dynaBean = (DynaBean) object;
			DynaProperty origDescriptors[] = dynaBean.getDynaClass().getDynaProperties();
			for (int i = 0; i < origDescriptors.length; i++) {
				String name = origDescriptors[i].getName();
				if (getBeanUtilsBean().getPropertyUtils().isWriteable(dynaBean, name)) {
					Object value = dynaBean.get(name);
					if (value instanceof String) {
						getBeanUtilsBean().copyProperty(dynaBean, name, URLDecoder.decode(value.toString(), "UTF-8"));
					}
				}
			}
		} else {// is a standard JavaBean
			PropertyDescriptor origDescriptors[] = getBeanUtilsBean().getPropertyUtils().getPropertyDescriptors(object);
			for (int i = 0; i < origDescriptors.length; i++) {
				String name = origDescriptors[i].getName();
				if ("class".equals(name)) {
					continue; // No point in trying to set an object's class
				}
				if (getBeanUtilsBean().getPropertyUtils().isReadable(object, name)
						&& getBeanUtilsBean().getPropertyUtils().isWriteable(object, name)) {
					Object value = getBeanUtilsBean().getPropertyUtils().getSimpleProperty(object, name);
					if (value instanceof String) {
						getBeanUtilsBean().copyProperty(object, name, URLDecoder.decode(value.toString(), "UTF-8"));
					}
				}
			}
		}
	}

	/**
	 * @author Hui,Gang
	 * @version Build 2009.12.11
	 */
	public BeanUtilsBean getBeanUtilsBean() {
		return BeanUtilsBean.getInstance();
	}

	/**
	 * 对BaseSsiAction的renderExcel方法进行修改，修改了原方法中英文字符大写自动转小写的问题
	 * 
	 * @author汪浩
	 */
	public void HtmltoExcel(HttpServletRequest request, HttpServletResponse response) {
		String hiddenHtml = request.getParameter("hiddenHtml");

		hiddenHtml = hiddenHtml.replaceAll("&lt;", "<");
		hiddenHtml = hiddenHtml.replaceAll("&gt;", ">");

		hiddenHtml = StringUtils.replace(hiddenHtml, "border=0", "border=1");
		hiddenHtml = StringUtils.replace(hiddenHtml, "border=\"0\"", "border=\"1\"");

		String fname = EncryptUtils.encodingFileName(request.getParameter("hiddenName"));

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=" + fname);

		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.println(hiddenHtml);

		out.flush();
		out.close();
	}

	/**
	 * @author Hui,Gang
	 * @version Build 2009.11.2
	 */
	public ActionForward showMsgBeforeToIndex(HttpServletRequest request, HttpServletResponse response, String message,
			Object[] objects) throws IOException {
		String cPath = "default.jsp";
		return this.showMsgBeforeToPage(request, response, message, objects, cPath);
	}

	/**
	 * @author Hui,Gang
	 * @version Build 2009.11.2
	 */
	public ActionForward showMsgBeforeToPage(HttpServletRequest request, HttpServletResponse response, String message,
			Object[] objects, String goToUrl) throws IOException {
		String msg = super.getMessage(request, message, objects);
		String url = request.getContextPath().concat("/").concat(goToUrl);
		renderJavaScript(response, "alert('".concat(msg).concat("');location.href='".concat(url).concat("'")));

		return null;
	}

	/**
	 * @author Hui,Gang
	 * @version Build 2009.11.2
	 */
	public ActionForward showMsgAndCloseWindow(HttpServletRequest request, HttpServletResponse response,
			String message, Object[] objects) throws IOException {
		String msg = super.getMessage(request, message, objects);
		renderJavaScript(response, "alert('".concat(msg).concat("');window.close();"));

		return null;
	}

	/**
	 * 取缩进的单位部门信息
	 * 
	 * @author wang,hao
	 */
	public List<Organizationa> getIndentationOrganizationaList(HttpServletRequest request) {
		Organizationa organizationa = new Organizationa();
		organizationa.setIs_del(0);
		List<Organizationa> organizationaList = getFacade().getOrganizationaService().getOrganizationaList(
				organizationa);
		for (Organizationa bai : organizationaList) {
			for (int i = 1; i < new Integer(bai.getMap().get("level").toString()); i++) {
				bai.setOrg_name("　" + bai.getOrg_name());
			}
		}
		return organizationaList;
	}

	public List<Category> getIndentationCategoryList(Integer c_type) {
		return getIndentationCategoryList(c_type, 0);
	}

	/**
	 * 取缩进的类别信息
	 * 
	 * @param c_type
	 * @param maxLevel
	 * @return
	 * @author Wang Hao
	 * @2010-12-12 下午04:12:56
	 */
	public List<Category> getIndentationCategoryList(Integer c_type, int maxLevel) {
		Category category = new Category();
		category.setIs_del(0);
		category.setC_type(c_type);
		if (maxLevel > 0)
			category.getMap().put("maxLevel", maxLevel);
		List<Category> categoryList = getFacade().getCategoryService().getCategoryList(category);
		for (Category c : categoryList) {
			for (int i = 1; i < new Integer(c.getMap().get("level").toString()); i++) {
				c.setC_name("　" + c.getC_name());
			}
		}
		return categoryList;
	}

	/**
	 * @param request
	 * @param mod_id
	 *            操作栏目mod_id
	 * @param oper_desc
	 *            操作说明
	 * @param link_tab
	 *            关联表
	 * @param link_id
	 *            关联ID
	 * @param oper_type
	 *            操作类型，如：添加，删除，修改，审核等
	 * @throws Exception
	 * @author Chen,LiDe
	 */
	public void createSysOperLog(HttpServletRequest request, String link_tab, Long link_id, String mod_id,
			String oper_type, String oper_desc) throws Exception {
		HttpSession session = request.getSession(false);
		PeProdUser userInfo = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		SysModule sysModule = new SysModule();
		sysModule.getMap().put("mod_id", mod_id);
		List<SysModule> sysModuleList = getFacade().getSysModuleService().getSysModuleList(sysModule);
		OperLog t = new OperLog();
		for (SysModule sm : sysModuleList) {
			t.setPpdm_name((t.getPpdm_name() != null ? t.getPpdm_name() + "-" : "") + sm.getMod_name());
		}
		t.setOper_time(new Timestamp(Calendar.getInstance().getTime().getTime()));
		t.setOper_ip(IpUtils.getIpAddr(request));
		t.setOper_desc(oper_desc);
		t.setLink_tab(link_tab);
		t.setLink_id(link_id);
		t.setOper_type(oper_type);
		if (userInfo != null) {
			t.setOper_uid(userInfo.getId());
			t.setOper_uname(userInfo.getUser_name());
		}
		getFacade().getOperLogService().createOperLog(t);
	}

	// 手机网页操作日志导入
	/**
	 * @param request
	 * @param mod_id
	 *            操作栏目mod_id
	 * @param oper_desc
	 *            操作说明
	 * @param link_tab
	 *            关联表
	 * @param link_id
	 *            关联ID
	 * @param oper_type
	 *            操作类型，如：添加，删除，修改，审核等
	 * @throws Exception
	 * @author Chen,LiDe
	 */
	public void createMobileSysOperLog(HttpServletRequest request, String link_tab, Long link_id, String mod_id,
			String oper_type, String oper_desc) throws Exception {
		HttpSession session = request.getSession(false);
		PeProdUser userInfo = (PeProdUser) session.getAttribute(Constants.USER_MOBILE);
		SysModule sysModule = new SysModule();
		sysModule.getMap().put("mod_id", mod_id);
		List<SysModule> sysModuleList = getFacade().getSysModuleService().getSysModuleList(sysModule);
		OperLog t = new OperLog();
		for (SysModule sm : sysModuleList) {
			t.setPpdm_name((t.getPpdm_name() != null ? t.getPpdm_name() + "-" : "") + sm.getMod_name());
		}
		t.setOper_time(new Timestamp(Calendar.getInstance().getTime().getTime()));
		t.setOper_ip(IpUtils.getIpAddr(request));
		t.setOper_desc(oper_desc);
		t.setLink_tab(link_tab);
		t.setLink_id(link_id);
		t.setOper_type(oper_type);
		if (userInfo != null) {
			t.setOper_uid(userInfo.getId());
			t.setOper_uname(userInfo.getUser_name());
		}
		getFacade().getOperLogService().createOperLog(t);
	}

	/**
	 * @param tiemstr
	 *            传入的字符型日期数据 /yyyy-MM-dd HH:mm:ss/
	 * @throws Exception
	 * @author Wang,Hao
	 * @return
	 * @throws Exception
	 */
	public Date convertTimeStr(String timestr) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.parse(timestr);
	}

	/**
	 * @param title
	 *            操作类型
	 * @param content
	 *            操作说明
	 * @throws Exception
	 * @author Wang,Hao
	 * @return
	 */
	public String getSysSetting(String title) throws Exception {
		SysSetting sysSetting = new SysSetting();
		sysSetting.setTitle(title);
		return getFacade().getSysSettingService().getSysSetting(sysSetting).getContent();
	}

	/**
	 * @author Hui,Gang
	 * @version Build 2010-12-29
	 * @desc 文件相关属性存放至RequestScope
	 */
	public void setCategoryListToRequestScope(HttpServletRequest request) {
		Category category = new Category();
		category.setIs_del(0);
		category.setC_type(11);
		request.setAttribute("category11List", getFacade().getCategoryService().getCategoryListForFiles(category));
		category.setC_type(12);
		request.setAttribute("category12List", getFacade().getCategoryService().getCategoryListForFiles(category));
		category.setC_type(13);
		request.setAttribute("category13List", getFacade().getCategoryService().getCategoryListForFiles(category));
		category.setC_type(14);
		request.setAttribute("category14List", getFacade().getCategoryService().getCategoryListForFiles(category));
	}

	public List<Category> getCategoryInfoList(int c_type) {
		Category category = new Category();
		category.setIs_del(0);
		category.setC_type(c_type);
		return getFacade().getCategoryService().getCategoryListForFiles(category);
	}

	public String jqGridFormat(int page, int limit, Long records) {
		String str = "";
		int iAll = new Integer(records.toString()) / limit + ((new Integer(records.toString()) % limit > 0) ? 1 : 0);
		str = "page:" + page + ",total:" + iAll + ",records:" + records + ",";
		return str;
	}

	public String jqGridFormat(List<Object> aList) {
		String str = "";
		int i = 0;
		for (Object object : aList) {
			i++;
			if (i < aList.size())
				str += JSON.toJSONString(object) + ",";
			else
				str += JSON.toJSONString(object);
		}
		return str;
	}

	// 根据松下用户ID，取得用户有权限的相应扩展类型（1:供应商 2:品牌 3:地区 4:品类）的值（数组）
	public String[] getPermissionsArrayFromSxEntendedPermissions(PeProdUser userInfo, Integer expandType) {
		// 取得当前业务员的品牌和品类权限，业务员只能看到自己有权限的记录
		SxExtendedPermissions sxExtendedPermissions = new SxExtendedPermissions();
		// 扩展类型：1:供应商 2:品牌 3:地区 4:品类
		sxExtendedPermissions.setExpand_type(expandType);
		sxExtendedPermissions.setUser_id(userInfo.getId());
		List<SxExtendedPermissions> lstSxExtendedPermissions = getFacade().getSxExtendedPermissionsService()
				.getSxExtendedPermissionsList(sxExtendedPermissions);
		String[] result = new String[lstSxExtendedPermissions.size()];
		for (int i = 0; i < lstSxExtendedPermissions.size(); i++) {
			result[i] = lstSxExtendedPermissions.get(i).getExpand_id().toString();
		}
		return result;
	}

	/**
	 * Check the given request for a session attribute of the given name.
	 * Returns null if there is no session or if the session has no such
	 * attribute. Does not create a new session if none has existed before!
	 * 
	 * @param request
	 *            current HTTP request
	 * @param name
	 *            the name of the session attribute
	 * @return the value of the session attribute, or <code>null</code> if not
	 *         found
	 * @author Xing,XiuDong
	 * @see org.springframework.web.util.WebUtils.getSessionAttribute
	 */
	protected Object getSessionAttribute(HttpServletRequest request, String name) {
		HttpSession session = request.getSession(false);
		return (session != null ? session.getAttribute(name) : null);
	}

    public ActionForward toPDF(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding(Constants.SYS_ENCODING);
        String name = request.getParameter("hiddenName");
        String html = request.getParameter("hiddenHtml");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        StringBuffer sb = new StringBuffer();
        if (name == null || "".equals(name)) {
            name = "Export" + sdf.format(new Date());
        }
        html = html.replaceAll("&lt;", "<");
        html = html.replaceAll("&gt;", ">");
        try {
            // 设置类型
            response.setContentType("application/pdf");
            // ! important
            response.setHeader("Content-disposition", "attachment;filename=\"" + URLEncoder.encode(name, Constants.SYS_ENCODING) + ".pdf\" ");
            PrintWriter out = response.getWriter();
            // out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
            // out.println("<html>");
            // out.println("<head>");
            // out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
            // out.println("</head>");
            // out.println("<body>");
            // out.println(html);
            // out.println("</body>");
            // out.println("</html>");
            sb.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
            sb.append("<html>");
            sb.append("<head>");
            sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=GBK\" />");
            sb.append("</head>");
            sb.append("<body>");
            sb.append(html);
            sb.append("</body>");
            sb.append("</html>");
            out.write(sb.toString());
            //System.out.println(sb.toString());
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public ActionForward toWord(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding(Constants.SYS_ENCODING);

        String name = request.getParameter("hiddenName");
        String html = request.getParameter("hiddenHtml");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (name == null || "".equals(name)) {
            name = "Export" + sdf.format(new Date());
        }
        html = html.replaceAll("&lt;", "<");
        html = html.replaceAll("&gt;", ">");
        try {

            // ! important
            response.setContentType("application/vnd.ms-word");
            response.addHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(name, Constants.SYS_ENCODING) + ".doc\"");

            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
            out.println("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
            out.println("<head>");
            out.println("<meta http-equiv=\"Content-Type\" content=\"text/html ; charset= utf-8\" />");
            out.println("<meta http-equiv=\"MSThemeCompatible\" content=\"no\" />");
            out.println("<meta name=\"MSSmartTagsPreventParsing\" content=\"true\" />");
            out.println("<title>" + name + "</title>");
            out.println("</head>");
            out.println("<body>");
            out.println(html);
            out.println("</body>");
            out.println("</html>");
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public ActionForward toExcel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        response.setCharacterEncoding(Constants.SYS_ENCODING);
        String name = request.getParameter("hiddenName");
        String html = request.getParameter("hiddenHtml");

        // 不判断是否为空，下面的 replaceAll方法报错
        if (StringUtils.isBlank(html)) {
            this.renderHtml(response, "no html content to export.");
            return null;
        }

        html = html.replaceAll("&lt;", "<");
        html = html.replaceAll("&gt;", ">");
        try {
            response.setContentType("application/vnd.ms-excel");
            response.addHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(name, Constants.SYS_ENCODING) + ".xls\"");

            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
            out.println("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
            out.println("<head>");
            out.println("<meta http-equiv=\"Content-Type\" content=\"text/html ; charset= utf-8\" />");
            out.println("<meta http-equiv=\"MSThemeCompatible\" content=\"no\" />");
            out.println("<meta name=\"MSSmartTagsPreventParsing\" content=\"true\" />");
            out.println("<title>" + name + "</title>");
            out.println("</head>");
            html = html.replace("border=0", "border=1");
            html = StringUtils.replace(html, "border=\"0\"", "border=\"1\"");
            // html = html.replace("<A href=\"[^>]*?\">([^<]*?)<\/A>", "$1");
            out.println("<body>");
            out.println(html);
            out.println("</body>");
            out.println("</html>");
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


	/**
	 * @author Xing,XiuDong
	 * @date 2009.12.21
	 */
	protected String getPIndexName(String p_index, Object separator) {
		StringBuffer fullName = new StringBuffer("");
		if (null != p_index) {
			BaseProvinceListFour baseProvince = new BaseProvinceListFour();
			baseProvince.setP_index(Long.valueOf(p_index));
			List<BaseProvinceListFour> baseProvinceList = getFacade().getBaseProvinceListFourService()
					.getBaseProvinceListFourParentList(baseProvince);
			for (BaseProvinceListFour t : baseProvinceList)
				fullName.append(t.getP_name()).append(separator);
		}
		return fullName.toString();
	}

	protected static String getTreeNodeString(Collection<BaseProvinceList> c, String property, Object separator) {
		if (CollectionUtils.isEmpty(c)) {
			return null;
		}

		ArrayList<String> stringBuffer = new ArrayList<String>();

		Iterator<BaseProvinceList> iter = c.iterator();
		while (iter.hasNext()) {
			Object o = iter.next();
			try {
				stringBuffer.add(BeanUtils.getProperty(o, property));
			} catch (IllegalAccessException e) {
				logger.error(e.getMessage());
			} catch (InvocationTargetException e) {
				logger.error(e.getMessage());
			} catch (NoSuchMethodException e) {
				logger.error(e.getMessage());
			}
		}

		return (StringUtils.join(stringBuffer, separator.toString()));
	}

	/**
	 * @desc 根据部门id获取所属部门id
	 * @param dept_type
	 *            2:事业部 3:分公司
	 * @author Hui,Gang
	 * @version 2011-11-10 下午2:45:29
	 */
	protected KonkaDept getSuperiorForDeptType(Long dept_id, Integer dept_type) {
		KonkaDept konkaDept = new KonkaDept();
		konkaDept.setDept_id(dept_id);
		konkaDept.getMap().put("dept_type_eq", dept_type);
		return this.getFacade().getKonkaDeptService().getKonkaDeptSuperiorByDeptId(konkaDept);
	}

	/**
	 * @author Wang,Yang
	 * @date 2011.10.10 根据user_id取分配给业务的网点
	 */
	protected List<BranchAssign> getBranchAssignByUserId(Long user_id) {
		return this.getBranchAssignList(1, null, null, null, null, null, user_id);
	}

	/**
	 * 获取当前用户的系统角色（相对于分公司）
	 */
	protected PeRoleUser getRoleInfoByThisLogin(HttpServletRequest request) {
		PeProdUser userInfo = (PeProdUser) this.getSessionAttribute(request, Constants.USER_INFO);
		return this.getRoleInfoByUserId(userInfo.getId());
	}

	/**
	 * 获取当前用户的系统角色（相对于分公司）
	 */
	protected PeRoleUser getRoleInfoByUserId(Long user_id) {
		PeRoleUser peRoleUser = new PeRoleUser();
		peRoleUser.setUser_id(user_id);
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(peRoleUser);

		PeRoleUser ret = null;
		if (null != peRoleUserList && peRoleUserList.size() > 0) {
			for (PeRoleUser c : peRoleUserList) {
				if (c.getRole_id() < 10000L) {
					// 10000以下的角色为系统角色，这些角色将为系统权限和控制起到作用
					ret = c;
					break;
				}
			}
		}
		return ret;
	}

	/**
	 * 获取所有用户角色
	 */
	protected List<PeRoleInfo> getRoleInfoList() {
		PeRoleInfo entity = new PeRoleInfo();
		entity.setIs_del(0);
		return this.getFacade().getPeRoleInfoService().getPeRoleInfoList(entity);
	}

	/**
	 * 获取当前用户相关联部门的管辖区域
	 */
	protected void setEntityProperty(KonkaR3Shop entity, KonkaDept dept, Long user_dept_id) {
		if (dept.getDept_type() == 3L) {
			if (StringUtils.isNotBlank(dept.getM_areas_names())) {
				entity.getMap().put("branch_area_name_like", dept.getM_areas_names());
			} else {
				entity.getMap().put("branch_area_name_like", "1");
			}
			entity.getMap().put("is_fengongsi", "true");
			entity.getMap().put("dept_id", user_dept_id);
		} else if (dept.getDept_type() == 4L || dept.getDept_type() == 5L) {
			if (StringUtils.isNotBlank(dept.getM_areas_names())) {
				entity.getMap().put("branch_area_name_like", dept.getM_areas_names());
			} else {
				entity.getMap().put("branch_area_name_like", "1");
			}
			entity.getMap().put("is_fengongsi", "true");
			entity.getMap().put("dept_id", user_dept_id);
		}
	}

	/**
	 * 获取当前用户相关联的直营网点的对象
	 */
	protected KonkaR3Shop getKonkaR3ShopForSelect(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response, Long dept_id) {
		DynaBean dynaBean = (DynaBean) form;
		String fgs_dept_id = (String) dynaBean.get("fgs_dept_id");
		String jyb_dept_id = (String) dynaBean.get("jyb_dept_id");
		String bsc_dept_id = (String) dynaBean.get("bsc_dept_id");
		String ywy_user_id = (String) dynaBean.get("ywy_user_id");

		PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		if (userInfo == null)
			userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_MOBILE);
		Long user_dept_id = userInfo.getDept_id();
		if (dept_id != null && dept_id > user_dept_id) {
			user_dept_id = dept_id;
		}

		KonkaR3Shop entity = new KonkaR3Shop();
		entity.setIs_del(new Long(0));

		entity.getMap().put("area_name_like", dynaBean.get("area_name_like"));
		entity.getMap().put("customer_name_like", dynaBean.get("customer_name_like"));
		entity.getMap().put("handle_name_like", dynaBean.get("handle_name_like"));
		entity.getMap().put("r3_code_like", dynaBean.get("r3_code_like"));
		entity.getMap().put("selects", dynaBean.get("selects"));
		entity.getMap().put("not_selects", dynaBean.get("not_selects"));
		entity.getMap().put("mmt_shop_id_selects", dynaBean.get("mmt_shop_id_selects"));
		entity.getMap().put("areas_ids", dynaBean.get("areas_ids"));
		entity.getMap().put("mmt_shop_name", dynaBean.get("mmt_shop_name"));
		entity.getMap().put("is_assign", "true");// 获取限定条件网点分配
		entity.getMap().put("code_like", dynaBean.get("code_like"));

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_ge_20 = false;
		boolean role_id_eq_60 = false;
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id() >= 30L) {
				role_id_ge_20 = true;
			}
			if (peRoleUser.getRole_id() == 60L) {
				role_id_eq_60 = true;
			}
		}

		if (role_id_ge_20) { // 不是系统管理员组全部要加上网点分配
			entity.getMap().put("dept_id", this.getSessionUserInfo(request).getDept_id());
			entity.getMap().put("user_id", this.getSessionUserInfo(request).getId());
			request.setAttribute("role_type", 1);
		}
		if (role_id_eq_60) { // 业务员只能看到分配给自己的
			entity.getMap().put("dept_id", -1);
			entity.getMap().put("user_id", this.getSessionUserInfo(request).getId());
		}
		if (StringUtils.isNotBlank(ywy_user_id)) {
			entity.getMap().put("ywy_user_id", ywy_user_id);
		} else {
			if (StringUtils.isNotBlank(fgs_dept_id)) {
				entity.getMap().put("konka_dept_id", fgs_dept_id);
			}
			if (StringUtils.isNotBlank(jyb_dept_id)) {
				entity.getMap().put("konka_dept_id", jyb_dept_id);
			}
			if (StringUtils.isNotBlank(bsc_dept_id)) {
				entity.getMap().put("konka_dept_id", bsc_dept_id);
			}
		}

		return entity;
	}

	/**
	 * 获取当前用户相关联的直营网点总数量
	 */
	protected Long getKonkaR3MMTShopCount(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response, Long dept_id) {
		KonkaR3Shop entity = this.getKonkaR3ShopForSelect(mapping, form, request, response, dept_id);
		return this.getFacade().getKonkaR3ShopService().getKonkaR3ShopCount(entity);
	}

	/**
	 * 获取当前用户相关联的直营网点分页列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @param dept_id
	 *            指定部门的ID，默认为空，不为空时供组织架构管理功能使用
	 * @return
	 */
	protected List<KonkaR3Shop> getKonkaR3MMTShopPaginatedList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response, Long dept_id) {
		DynaBean dynaBean = (DynaBean) form;
		Pager pager = (Pager) dynaBean.get("pager");

		KonkaR3Shop entity = this.getKonkaR3ShopForSelect(mapping, form, request, response, dept_id);

		Long recordCount = this.getKonkaR3MMTShopCount(mapping, form, request, response, dept_id);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		return getFacade().getKonkaR3ShopService().getKonkaR3ShopPaginatedList(entity);
	}

	/**
	 * 获取konka_r3_id获取KonkaR3Shop
	 * 
	 * @param id
	 * @return
	 */
	protected KonkaR3Shop getKonkaR3ShopById(Long id) {
		KonkaR3Shop t = new KonkaR3Shop();
		t.setId(id);
		return getFacade().getKonkaR3ShopService().getKonkaR3Shop(t);
	}

	protected List<BasePdClazz> getBasePdClazzList() {
		String[] strs = { "1010000", "1010100", "1010200", "1010300", "1010400", "1010500", "1010600" };
		BasePdClazz basePdClazz = new BasePdClazz();
		basePdClazz.setIs_del(0);
		basePdClazz.getMap().put("cls_ids", strs);
		List<BasePdClazz> list = facade.getBasePdClazzService().getBasePdClazzList(basePdClazz);

		basePdClazz.setCls_id(0l);
		basePdClazz.setCls_name("家电分类");
		basePdClazz.setTree_name("『家电分类』");
		basePdClazz.setIs_leaf(BigDecimal.valueOf(0));
		basePdClazz.setCls_level(BigDecimal.valueOf(1));
		basePdClazz.setPar_id(-1l);
		basePdClazz.setRoot_id(-1l);
		basePdClazz.setFull_name("家电分类");
		basePdClazz.setOrder_value(0);
		basePdClazz.setIs_del(0);

		list.add(0, basePdClazz);
		return list;
	}

	/**
	 * 获取mmt_shop_id获取其代理商的KonkaR3MmtMatch集合， 例：如果返回的list.size == 0时就没有匹配R3网点
	 * 判断是代理商 或者 经销商【直供】
	 */
	protected List<KonkaR3MmtMatch> getAgentKonkaR3IdByMmtShopId(Long mmt_shop_id) {
		KonkaR3MmtMatch konkaR3MmtMatch = new KonkaR3MmtMatch();
		konkaR3MmtMatch.setMmt_shop_id(mmt_shop_id);
		List<KonkaR3MmtMatch> konkaR3MmtMatchList = getFacade().getKonkaR3MmtMatchService().getKonkaR3MmtMatchList(
				konkaR3MmtMatch);
		return konkaR3MmtMatchList;
	}

	/**
	 * 根据康佳客户ID，获取其代理商的KonkaR3MmtMatch集合， 例：如果返回的list.size == 0时就没有匹配R3网点 判断是代理商
	 * 或者 经销商【直供】
	 */
	protected List<KonkaR3MmtMatch> getAgentKonkaR3IdByKonkaCustId(Long konka_cust_id) {
		KonkaR3MmtMatch konkaR3MmtMatch = new KonkaR3MmtMatch();
		konkaR3MmtMatch.setR3_shop_id(konka_cust_id);
		List<KonkaR3MmtMatch> konkaR3MmtMatchList = getFacade().getKonkaR3MmtMatchService().getKonkaR3MmtMatchList(
				konkaR3MmtMatch);

		// List<KonkaR3MmtMatch> konkaR3MmtMatchList_dls = new
		// ArrayList<KonkaR3MmtMatch>();
		// for (KonkaR3MmtMatch konkaR3MmtMatch1 : konkaR3MmtMatchList) {
		// KonkaBranchCategory t = new KonkaBranchCategory();
		// t.setCategory_id(10l);
		// t.setKonka_r3_id(konkaR3MmtMatch1.getR3_shop_id());
		// t = getFacade().getKonkaBranchCategoryService()
		// .getKonkaBranchCategory(t);
		// if (null != t) {
		// konkaR3MmtMatchList_dls.add(konkaR3MmtMatch1);
		// }
		// }
		// 如果konkaR3MmtMatchList_dls.size >0 就是代理商
		// 如果不是就是经销商【直供】

		return konkaR3MmtMatchList;
	}

	/**
	 * 获取mmt_shop_id获取其上级代理商的konka_r3_id 判断是否是代理商底下网点 判断是是否是经销商【非直供】
	 */
	protected Long getSuperiorAgentKonkaR3IdByKonkaCustId(Long mmt_shop_id) {

		KonkaBranchCategory t = new KonkaBranchCategory();
		t.setCategory_id(20l);
		t.setMmt_shop_id(mmt_shop_id);
		t = getFacade().getKonkaBranchCategoryService().getKonkaBranchCategory(t);
		if (null == t) {
			return -1l;
		}
		return t.getKonka_r3_id();
	}

	/**
	 * 获根据康佳客户ID，获取其上级代理商的konka_r3_id 判断是否是代理商底下网点 判断是是否是经销商【非直供】
	 */
	protected Long getSuperiorAgentKonkaR3IdByMmtShopId(Long konka_cust_id) {

		KonkaBranchCategory t = new KonkaBranchCategory();
		t.setCategory_id(20l);
		t.setKonka_r3_id(konka_cust_id);
		t = getFacade().getKonkaBranchCategoryService().getKonkaBranchCategory(t);
		if (null == t) {
			return -1l;
		}
		return t.getKonka_r3_id();
	}

	/**
	 * 根据konka_r3_id找代理商所分配的分公司
	 */
	protected Long getDeptIdByAgentAgentKonkaR3Id(Long konka_r3_id) {
		BranchAssign t = new BranchAssign();
		t.setSyb_id(1l);// 目前写死为多媒体事业本部
		t.setBranch_type(1);
		t.setKonka_r3_id(konka_r3_id);
		// 一个r3可能出现多个分配数据，因为经营部、办事处改为不同级了。但是多条数据对应同一个分公司，故随便取第一个的分公司ID
		List<BranchAssign> list = this.getFacade().getBranchAssignService().getBranchAssignList(t);
		if (null == list || list.size() < 1) {
			return -1l;
		}
		return list.get(0).getFgs_id();
	}

	/**
	 * 根据MMT SHOP_ID取上级代理商下面的所有网点(经销商)，取用集合中的mmt_shop_id
	 */
	protected List<KonkaBranchCategory> gettSuperiorAgentKonkaBranchCategoryListByMmtShopId(Long mmt_shop_id) {
		List<KonkaR3MmtMatch> konkaR3MmtMatchList = this.getAgentKonkaR3IdByMmtShopId(mmt_shop_id);

		List<KonkaBranchCategory> konkaBranchCategoryList = new ArrayList<KonkaBranchCategory>();

		for (KonkaR3MmtMatch t : konkaR3MmtMatchList) {
			KonkaBranchCategory konkaBranchCategory = new KonkaBranchCategory();
			konkaBranchCategory.setCategory_id(20l);
			konkaBranchCategory.setKonka_r3_id(t.getR3_shop_id());
			List<KonkaBranchCategory> list = getFacade().getKonkaBranchCategoryService().getKonkaBranchCategoryList(
					konkaBranchCategory);
			konkaBranchCategoryList.addAll(list);
		}

		return konkaBranchCategoryList;
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @param konka_r3_id
	 *            为空时调用当前登录用户的角色相关联的数据，不为空时按指定的r3_id调用数据回显
	 * @return
	 */
	protected List<KonkaDept> getDeptInfoList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response, Long konka_r3_id) {
		DynaBean dynaBean = (DynaBean) form;

		PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_eq_10 = false;
		boolean role_id_ge_20_le_29 = false;
		boolean role_id_ge_30_le_39 = false;
		boolean role_id_ge_40_le_49 = false;
		boolean role_id_ge_40_le_60 = false;
		boolean role_id_ge_50_le_59 = false;
		boolean role_id_eq_60 = false;
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id() == 10L) {
				role_id_eq_10 = true;
			}
			if (peRoleUser.getRole_id() >= 20L && peRoleUser.getRole_id() <= 29L) {
				role_id_ge_30_le_39 = true;
			}
			if (peRoleUser.getRole_id() >= 30L && peRoleUser.getRole_id() <= 39L) {
				role_id_ge_30_le_39 = true;
			}
			if (peRoleUser.getRole_id() >= 40L && peRoleUser.getRole_id() <= 49L) {
				role_id_ge_40_le_49 = true;
			}
			if (peRoleUser.getRole_id() >= 40L && peRoleUser.getRole_id() <= 60L) {
				role_id_ge_40_le_60 = true;
			}
			if (peRoleUser.getRole_id() >= 50L && peRoleUser.getRole_id() <= 59L) {
				role_id_ge_50_le_59 = true;
			}
			if (peRoleUser.getRole_id() == 60L) {
				role_id_eq_60 = true;
			}
		}

		if (konka_r3_id == null) { // konka_r3_id 为空时调用当前登录用户的角色相关联的数据
			if (role_id_ge_30_le_39) {// 分公司
				dynaBean.set("fgs_dept_id", userInfo.getDept_id().toString());
			} else if (role_id_ge_40_le_49) {// 经营部
				KonkaDept dept = new KonkaDept();
				dept.setDept_id(userInfo.getDept_id());
				dept = getFacade().getKonkaDeptService().getKonkaDept(dept);
				dynaBean.set("fgs_dept_id", dept.getPar_id().toString());
				dynaBean.set("jyb_dept_id", userInfo.getDept_id().toString());
			} else if (role_id_ge_50_le_59) {// 办事处
				KonkaDept dept = new KonkaDept();
				dept.setDept_id(userInfo.getDept_id());
				dept = getFacade().getKonkaDeptService().getKonkaDept(dept);
				dynaBean.set("fgs_dept_id", this.getSuperiorForDeptType(userInfo.getDept_id(), 3).getDept_id()
						.toString());
				dynaBean.set("jyb_dept_id", dept.getPar_id().toString());
				dynaBean.set("bsc_dept_id", userInfo.getDept_id().toString());
			} else if (role_id_eq_60) {// 业务员
				KonkaDept dept = new KonkaDept();
				dept.setDept_id(userInfo.getDept_id());
				dept = getFacade().getKonkaDeptService().getKonkaDept(dept);

				if (dept.getDept_type() == 3) {
					dynaBean.set("fgs_dept_id", dept.getDept_id().toString());
					dynaBean.set("ywy_user_id", userInfo.getId().toString());
				}
				if (dept.getDept_type() == 4) {
					dynaBean.set("fgs_dept_id", this.getSuperiorForDeptType(userInfo.getDept_id(), 3).getDept_id()
							.toString());
					dynaBean.set("jyb_dept_id", dept.getDept_id().toString());
					dynaBean.set("ywy_user_id", userInfo.getId().toString());
				}
				if (dept.getDept_type() == 5) {
					dynaBean.set("fgs_dept_id", this.getSuperiorForDeptType(userInfo.getDept_id(), 3).getDept_id()
							.toString());
					dynaBean.set("jyb_dept_id", dept.getPar_id().toString());
					dynaBean.set("bsc_dept_id", userInfo.getDept_id().toString());
					dynaBean.set("ywy_user_id", userInfo.getId().toString());
				}
			}
		} else {// konka_r3_id 不为空时按指定的r3_id调用数据回显
			BranchAssign kba = new BranchAssign();
			kba.setKonka_r3_id(konka_r3_id);
			List<BranchAssign> kbaList = getFacade().getBranchAssignService().getBranchAssignList(kba);
			if (kbaList.size() > 0) {
				for (BranchAssign BranchAssign : kbaList) {
					if (BranchAssign.getFgs_id() != null) {
						dynaBean.set("fgs_dept_id", BranchAssign.getFgs_id().toString());
					}
					if (BranchAssign.getJyb_id() != null) {
						dynaBean.set("jyb_dept_id", BranchAssign.getJyb_id().toString());
					}
					if (BranchAssign.getBsc_id() != null) {
						dynaBean.set("bsc_dept_id", BranchAssign.getBsc_id().toString());
					}
					if (BranchAssign.getUser_id() != null) {
						dynaBean.set("ywy_user_id", BranchAssign.getUser_id().toString());
					}
				}
			}

		}

		KonkaDept entity = new KonkaDept();
		entity.getMap().put("order_by_dept_name", true);
		List<KonkaDept> deptInfoList = new ArrayList<KonkaDept>();
		if (role_id_eq_10) {// 管理员
			entity.setPar_id(0L);
			entity.setDept_type(3);
			deptInfoList = getFacade().getKonkaDeptService().getKonkaDeptList(entity);
		}
		if (role_id_ge_20_le_29) {// 事业部
			entity.setPar_id(userInfo.getDept_id());
			deptInfoList = getFacade().getKonkaDeptService().getKonkaDeptList(entity);
		}
		if (role_id_ge_30_le_39) {// 分公司
			entity.setDept_id(userInfo.getDept_id());
			deptInfoList = getFacade().getKonkaDeptService().getKonkaDeptList(entity);
		}
		if (role_id_ge_40_le_60) {// 经营部
			// 或者办事处
			// 都通过父子查询级dept_type
			// 查出所属分公司
			entity.setDept_id(this.getSuperiorForDeptType(userInfo.getDept_id(), 3).getDept_id());
			deptInfoList = getFacade().getKonkaDeptService().getKonkaDeptList(entity);
		}
		// if(peRoleUser.getRole_id() ==60){
		//
		// }
		return deptInfoList;
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @param 去的登录人所在的分公司列表
	 * @return
	 */
	protected List<KonkaDept> getDeptInfoListWithOutLimit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DynaBean dynaBean = (DynaBean) form;
		// 如果不是epp调动可以传任意值过滤掉epp用到的伪分公司信息
		String isNotEpp = (String) dynaBean.get("isNotEpp");

		PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		// PeRoleUser peRoleUser = new PeRoleUser();
		// peRoleUser.setUser_id(userInfo.getId());
		// peRoleUser =
		// this.getFacade().getPeRoleUserService().getPeRoleUser(peRoleUser);

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_eq_10 = false;
		// boolean role_id_ge_20_le_29 = false;
		// boolean role_id_ge_30_le_39 = false;
		// boolean role_id_ge_40_le_49 = false;
		// boolean role_id_ge_40_le_60 = false;
		// boolean role_id_ge_50_le_59 = false;
		// boolean role_id_eq_60 = false;
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id() < 30L || peRoleUser.getRole_id().intValue() == 140317
					|| peRoleUser.getRole_id().intValue() == 1001
					|| (peRoleUser.getRole_id() >= 200 && peRoleUser.getRole_id() < 300)) {
				role_id_eq_10 = true;
			}
			// if (peRoleUser.getRole_id() >= 20L && peRoleUser.getRole_id() <=
			// 29L) {
			// role_id_ge_30_le_39 = true;
			// }
			// if (peRoleUser.getRole_id() >= 30L && peRoleUser.getRole_id() <=
			// 39L) {
			// role_id_ge_30_le_39 = true;
			// }
			// if (peRoleUser.getRole_id() >= 40L && peRoleUser.getRole_id() <=
			// 49L) {
			// role_id_ge_40_le_49 = true;
			// }
			// if (peRoleUser.getRole_id() >= 40L && peRoleUser.getRole_id() <=
			// 60L) {
			// role_id_ge_40_le_60 = true;
			// }
			// if (peRoleUser.getRole_id() >= 50L && peRoleUser.getRole_id() <=
			// 59L) {
			// role_id_ge_50_le_59 = true;
			// }
			// if (peRoleUser.getRole_id() == 60L) {
			// role_id_eq_60 = true;
			// }
		}

		Long fgs_dept_id = 0L;
		Long currentUserDeptId = userInfo.getDept_id();

		// if (role_id_ge_30_le_39) {// 分公司
		// fgs_dept_id = currentUserDeptId;
		// } else if (role_id_ge_40_le_49) {// 经营部
		// KonkaDept dept = new KonkaDept();
		// dept.setDept_id(currentUserDeptId);
		// dept = getFacade().getKonkaDeptService().getKonkaDept(dept);
		//
		// fgs_dept_id = dept.getPar_id();
		// } else if (role_id_ge_50_le_59) {// 办事处
		// KonkaDept dept = new KonkaDept();
		// dept.setDept_id(currentUserDeptId);
		// dept = getFacade().getKonkaDeptService().getKonkaDept(dept);
		//
		// KonkaDept dept_level3 =
		// this.getSuperiorForDeptType(currentUserDeptId, 3);
		// if (null != dept_level3 && null != dept_level3.getDept_id()) {
		// fgs_dept_id = dept_level3.getDept_id();
		// }
		// } else if (role_id_eq_60) {// 业务员
		// KonkaDept dept = new KonkaDept();
		// dept.setDept_id(currentUserDeptId);
		// dept = getFacade().getKonkaDeptService().getKonkaDept(dept);
		//
		// if (dept.getDept_type() == 3) {
		// fgs_dept_id = dept.getDept_id();
		// } else if (dept.getDept_type() == 4) {
		// KonkaDept _dept = this.getSuperiorForDeptType(currentUserDeptId, 3);
		// if (null != _dept && null != _dept.getDept_id()) {
		// fgs_dept_id = _dept.getDept_id();
		// }
		// } else if (dept.getDept_type() == 5) {
		// KonkaDept _dept = this.getSuperiorForDeptType(currentUserDeptId, 3);
		// if (null != _dept && null != _dept.getDept_id()) {
		// fgs_dept_id = _dept.getDept_id();
		// }
		// }
		// }
		KonkaDept dept_level3 = this.getSuperiorForDeptType(currentUserDeptId, 3);
		if (null != dept_level3 && null != dept_level3.getDept_id()) {
			fgs_dept_id = dept_level3.getDept_id();
		}
		dynaBean.set("fgs_dept_id", fgs_dept_id.toString());

		KonkaDept entity = new KonkaDept();
		entity.setIs_del(0);
		if (null != isNotEpp && StringUtils.isNotBlank(isNotEpp)) {
			entity.getMap().put("isNotEpp", "isNotEpp");
		}
		entity.getMap().put("order_by_dept_name", true);
		List<KonkaDept> deptInfoList = new ArrayList<KonkaDept>();
		if (role_id_eq_10) {// 管理员
			entity.setPar_id(0L);
			entity.setDept_type(3);
			deptInfoList = getFacade().getKonkaDeptService().getKonkaDeptList(entity);
			// } else if (role_id_ge_20_le_29) {// 事业部
			// entity.setPar_id(currentUserDeptId);
			// deptInfoList =
			// getFacade().getKonkaDeptService().getKonkaDeptList(entity);
			// } else if (role_id_ge_30_le_39) {// 分公司
			// entity.setDept_id(currentUserDeptId);
			// deptInfoList =
			// getFacade().getKonkaDeptService().getKonkaDeptList(entity);
			// } else if (role_id_ge_40_le_60) {// 经营部
		} else {
			// 或者办事处
			// 都通过父子查询级dept_type
			// 查出所属分公司
			KonkaDept _dept = this.getSuperiorForDeptType(currentUserDeptId, 3);
			if (null != _dept && null != _dept.getDept_id()) {
				entity.setDept_id(_dept.getDept_id());
				deptInfoList = getFacade().getKonkaDeptService().getKonkaDeptList(entity);
			}
		}
		return deptInfoList;
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response根据业务数据上报的时间顺序排序R3网点
	 *            并能够根据上报时间查询R3网点
	 * @return
	 */
	protected KonkaR3Shop getR3ShopByAddDate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DynaBean dynaBean = (DynaBean) form;

		String r3_code_like = (String) dynaBean.get("r3_code_like");
		String area_name_like = (String) dynaBean.get("area_name_like");
		String handle_name_like = (String) dynaBean.get("handle_name_like");
		String customer_name_like = (String) dynaBean.get("customer_name_like");
		String add_date_start = (String) dynaBean.get("add_date_start");
		String add_date_end = (String) dynaBean.get("add_date_end");
		String fgs_dept_id = (String) dynaBean.get("fgs_dept_id");
		String jyb_dept_id = (String) dynaBean.get("jyb_dept_id");
		String bsc_dept_id = (String) dynaBean.get("bsc_dept_id");
		String ywy_user_id = (String) dynaBean.get("ywy_user_id");
		String sell_type = (String) dynaBean.get("sell_type");

		dynaBean.set("add_date_start", add_date_start);
		dynaBean.set("add_date_end", add_date_end);

		// PeRoleUser peRoleUser = new PeRoleUser();
		// peRoleUser.setUser_id(this.getSessionUserInfo(request).getId());
		// peRoleUser =
		// this.getFacade().getPeRoleUserService().getPeRoleUser(peRoleUser);

		KonkaR3Shop entity = new KonkaR3Shop();
		entity.setIs_del(new Long(0));
		entity.getMap().put("area_name_like", area_name_like);
		entity.getMap().put("handle_name_like", handle_name_like);
		entity.getMap().put("customer_name_like", customer_name_like);
		entity.getMap().put("r3_code_like", r3_code_like);
		entity.getMap().put("tablename", "konka_sell");

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(this.getSessionUserInfo(request).getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_ge_20 = false;
		boolean role_id_eq_60 = false;
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id() >= 20L) {
				role_id_ge_20 = true;
			}
			if (peRoleUser.getRole_id() == 60L) {
				role_id_eq_60 = true;
			}
		}

		if (role_id_ge_20) { // 不是系统管理员组全部要加上网点分配
			entity.getMap().put("dept_id", this.getSessionUserInfo(request).getDept_id());
			entity.getMap().put("user_id", this.getSessionUserInfo(request).getId());
		}
		if (role_id_eq_60) { // 业务员只能看到分配给自己的
			entity.getMap().put("dept_id", -1);
			entity.getMap().put("user_id", this.getSessionUserInfo(request).getId());
		}

		if (StringUtils.isNotBlank(ywy_user_id)) {
			entity.getMap().put("ywy_user_id", ywy_user_id);
		} else {
			if (StringUtils.isNotBlank(fgs_dept_id)) {
				entity.getMap().put("konka_dept_id", fgs_dept_id);
			}
			if (StringUtils.isNotBlank(jyb_dept_id)) {
				entity.getMap().put("konka_dept_id", jyb_dept_id);
			}
			if (StringUtils.isNotBlank(bsc_dept_id)) {
				entity.getMap().put("konka_dept_id", bsc_dept_id);
			}
		}

		if (StringUtils.isNotBlank(add_date_start)) {
			entity.getMap().put("add_date_start", add_date_start + " 00:00:00");
			entity.getMap().put("is_serach_sbsj", "true");//
		}
		if (StringUtils.isNotBlank(add_date_end)) {
			entity.getMap().put("add_date_end", add_date_end + " 23:59:59");
			entity.getMap().put("is_serach_sbsj", "true");//
		}
		if (("1").equals(sell_type)) {
			entity.getMap().put("sell_type_1", "true");//
		} else if (("0").equals(sell_type)) {
			entity.getMap().put("sell_type_0", "true");//
		}
		return entity;
	}

	/**
	 * 获取当前用户相关联的直营网点的对象 业务员数据上报专用
	 */
	protected KonkaR3Shop getKonkaR3ShopForStockSell(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response, Long dept_id) {

		DynaBean dynaBean = (DynaBean) form;

		String area_name_like = (String) dynaBean.get("area_name_like");
		String customer_name_like = (String) dynaBean.get("customer_name_like");
		String handle_name_like = (String) dynaBean.get("handle_name_like");
		String r3_code_like = (String) dynaBean.get("r3_code_like");
		String fgs_dept_id = (String) dynaBean.get("fgs_dept_id");
		String jyb_dept_id = (String) dynaBean.get("jyb_dept_id");
		String bsc_dept_id = (String) dynaBean.get("bsc_dept_id");
		String ywy_user_id = (String) dynaBean.get("ywy_user_id");
		String sell_type = (String) dynaBean.get("sell_type");
		String stock_type = (String) dynaBean.get("stock_type");// 库存上报状态 0未上报 1
		// 已上报

		PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		Long user_dept_id = userInfo.getDept_id();
		if (dept_id != null && dept_id > user_dept_id) {
			user_dept_id = dept_id;
		}
		// PeRoleUser peRoleUser = new PeRoleUser();
		// peRoleUser.setUser_id(userInfo.getId());
		// peRoleUser =
		// this.getFacade().getPeRoleUserService().getPeRoleUser(peRoleUser);

		KonkaR3Shop entity = new KonkaR3Shop();
		entity.setIs_del(new Long(0));
		entity.getMap().put("is_assign", "true");// 获取限定条件网点分配
		entity.getMap().put("area_name_like", area_name_like);
		entity.getMap().put("customer_name_like", customer_name_like);
		entity.getMap().put("handle_name_like", handle_name_like);
		entity.getMap().put("r3_code_like", r3_code_like);
		entity.getMap().put("selects", dynaBean.get("selects"));
		entity.getMap().put("mmt_shop_id_selects", dynaBean.get("mmt_shop_id_selects"));
		entity.getMap().put("not_selects", dynaBean.get("not_selects"));
		entity.getMap().put("areas_ids", dynaBean.get("areas_ids"));
		entity.getMap().put("mmt_shop_name", dynaBean.get("mmt_shop_name"));

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_ge_20 = false;
		boolean role_id_eq_60 = false;
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id() >= 20L) {
				role_id_ge_20 = true;
			}
			if (peRoleUser.getRole_id() == 60L) {
				role_id_eq_60 = true;
			}
		}

		if (role_id_ge_20) { // 不是系统管理员组全部要加上网点分配
			entity.getMap().put("dept_id", this.getSessionUserInfo(request).getDept_id());
			entity.getMap().put("user_id", this.getSessionUserInfo(request).getId());
		}
		if (role_id_eq_60) { // 业务员只能看到分配给自己的
			entity.getMap().put("dept_id", -1);
			entity.getMap().put("user_id", this.getSessionUserInfo(request).getId());
		}

		if (StringUtils.isNotBlank(ywy_user_id)) {
			entity.getMap().put("ywy_user_id", ywy_user_id);
		} else {
			if (StringUtils.isNotBlank(fgs_dept_id)) {
				entity.getMap().put("konka_dept_id", fgs_dept_id);
			}
			if (StringUtils.isNotBlank(jyb_dept_id)) {
				entity.getMap().put("konka_dept_id", jyb_dept_id);
			}
			if (StringUtils.isNotBlank(bsc_dept_id)) {
				entity.getMap().put("konka_dept_id", bsc_dept_id);
			}
		}

		if (StringUtils.isNotBlank(sell_type)) {
			KonkaSell konkaSell = new KonkaSell();
			List<KonkaSell> sellList = getFacade().getKonkaSellService().getKonkaSellList(konkaSell);
			String[] s_ids = new String[sellList.size()];
			for (int i = 0; i < sellList.size(); i++) {
				s_ids[i] = "'" + sellList.get(i).getCus_sn() + "'";
			}
			if (("0").equals(sell_type)) {
				entity.getMap().put("sell_not_ids", StringUtils.join(s_ids, ","));
			} else if (("1").equals(sell_type)) {
				entity.getMap().put("sell_ids", StringUtils.join(s_ids, ","));
			}
		}
		if (StringUtils.isNotBlank(stock_type)) {
			if (("0").equals(stock_type)) {
				entity.getMap().put("stock_is_report", "true");
			} else if (("1").equals(stock_type)) {
				entity.getMap().put("stock_is_not_report", "true");
			}
		}

		return entity;
	}

	// 密码安全等级 0."太短";1."弱";2."一般";3."很好";4."极佳";
	protected int checkPassword(String pwd) {
		int j = 0;
		int score = 0;
		String[] regexArr = { "[0-9]", "[a-z]", "[A-Z]", "[\\W_]" };
		int repeatCount = 0;
		char prevChar = ' ';
		// 长度一个加一分，大于18按18算
		int len = pwd.length();
		score += len > 18 ? 18 : len;
		// 字符类型多一个加4分
		for (int i = 0, num = regexArr.length; i < num; i++) {
			Pattern pattern = Pattern.compile(regexArr[i]);
			Matcher matcher = pattern.matcher(pwd);
			if (matcher.matches()) {
				score += 4;
			}
		}
		for (int i = 0, num = regexArr.length; i < num; i++) {
			Pattern pattern = Pattern.compile("/" + regexArr[i] + "/g");
			Matcher matcher = pattern.matcher(pwd);
			if (matcher.groupCount() >= 2)
				score += 2;
			if (matcher.groupCount() >= 5)
				score += 2;
		}
		// 重复一次减一分
		for (int i = 0, num = pwd.length(); i < num; i++) {
			if (pwd.charAt(i) == prevChar) {
				repeatCount++;
			} else {
				prevChar = pwd.charAt(i);
			}
		}
		score -= repeatCount * 1;
		j = score;
		return j;
	}

	/**
	 * @desc 根据R3编码，产品ID得当前库存数量以及当前库存成本
	 * @param KonkaStockDetails
	 *            , 终止时间
	 * @author Wang,Yang
	 * @version 2011-11-20
	 */
	@SuppressWarnings("deprecation")
	public KonkaStockDetails getCurrentCountCost(KonkaStockDetails t, String endDate) throws Exception {
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat ss = new SimpleDateFormat("yyyy-MM-dd");

		// 查询业务员上报销售数量
		KonkaSell sell = new KonkaSell();
		sell.setCus_sn(t.getR3_code());
		sell.getMap().put("pd_id", t.getPd_id());
		sell.setState(1);
		sell.setIs_del(0);
		if (t.getStock_date().getSeconds() == 0 && t.getStock_date().getMinutes() == 0
				&& t.getStock_date().getHours() == 0) {
			sell.getMap().put("sell_date_start", ss.format(t.getStock_date()));
		} else {
			sell.getMap().put("sell_date_start", s.format(t.getStock_date()));
		}
		if (endDate != null) {
			sell.getMap().put("sell_date_end_lt", endDate);
		}
		Long sellCount = getFacade().getKonkaSellService().getKonkaSellCountByPd(sell);
		BigDecimal lastPrice = new BigDecimal(0);
		KonkaStockDetails details = new KonkaStockDetails();

		// PePdModel pePdModel = new PePdModel();
		// pePdModel.setPd_id(t.getPd_id());
		// pePdModel =
		// getFacade().getPePdModelService().getPePdModel(pePdModel);
		//
		// if (null != pePdModel) {

		// 初始库存时间之后的查询R3进货数
		Long channelDateSum = 0L;
		ChannelDataImport channelDate = new ChannelDataImport();
		channelDate.getMap().put("column", "column_12");// 查询数量
		channelDate.setColumn_1(t.getR3_code());// 送达方
		channelDate.setColumn_11(t.getMap().get("md_name").toString());// 型号
		if (t.getStock_date().getSeconds() == 0 && t.getStock_date().getMinutes() == 0
				&& t.getStock_date().getHours() == 0)
			channelDate.getMap().put("startDate", ss.format(t.getStock_date()));
		else
			channelDate.getMap().put("startDate", s.format(t.getStock_date()));

		if (endDate != null) {
			channelDate.getMap().put("endDate_lt", endDate);
		}
		if (getFacade().getChannelDataImportService().getChannelDataSumByPdId(channelDate) != null) {
			channelDateSum = getFacade().getChannelDataImportService().getChannelDataSumByPdId(channelDate).longValue();
		}

		if (null == t.getStock_count()) {
			t.setStock_count(0L);
		}

		if (null == sellCount) {
			sellCount = 0L;
		}

		log.info("===============================" + t.getStock_count());
		log.info("===============================" + channelDateSum);
		log.info("===============================" + sellCount);

		// 当前库存数量=业务员初始库存数量+R3进货数-业务员上报销售数量
		Long stock_count = t.getStock_count() + channelDateSum - sellCount;

		// 查询初始库存之后的R3销售订单某型号某网点的销售导入总金额
		BigDecimal channelDateMoneySum = new BigDecimal(0);
		ChannelDataImport channelDateImport = new ChannelDataImport();
		channelDateImport.getMap().put("column", "column_14");// 查询金额
		channelDateImport.setColumn_1(t.getR3_code());// 送达方
		channelDateImport.setColumn_11(t.getMap().get("md_name").toString());// 型号
		if (t.getStock_date().getSeconds() == 0 && t.getStock_date().getMinutes() == 0
				&& t.getStock_date().getHours() == 0)
			channelDateImport.getMap().put("startDate", ss.format(t.getStock_date()));
		else
			channelDateImport.getMap().put("startDate", s.format(t.getStock_date()));
		if (endDate != null) {
			channelDateImport.getMap().put("endDate_lt", endDate);
		}
		if (getFacade().getChannelDataImportService().getChannelDataSumByPdId(channelDateImport) != null) {
			channelDateMoneySum = getFacade().getChannelDataImportService().getChannelDataSumByPdId(channelDateImport);
		}

		// 查询网点【某型号】产品的销售上报记录
		KonkaSellDetails sellDetails = new KonkaSellDetails();
		sellDetails.setPd_id(t.getPd_id());
		sellDetails.getMap().put("state", 1);
		sellDetails.getMap().put("cus_sn", t.getR3_code());
		if (t.getStock_date().getSeconds() == 0 && t.getStock_date().getMinutes() == 0
				&& t.getStock_date().getHours() == 0)
			sellDetails.getMap().put("sell_date_start", ss.format(t.getStock_date()));
		else
			sellDetails.getMap().put("sell_date_start", s.format(t.getStock_date()));

		if (endDate != null) {
			sellDetails.getMap().put("sell_date_end_lt", endDate);
		}
		List<KonkaSellDetails> sellDetailsList = getFacade().getKonkaSellDetailsService().getKonkaSellDetailsList(
				sellDetails);
		BigDecimal sum = new BigDecimal(0);// 初始化 sum
		// 为业务员销售数据上报的数量*R3系统中从最早到现在的匹配的销售数据价格
		int z = 0;// 控制dataList循环的起始节点
		Long y = 0L;// y为R3销售订单中单笔销售数量超过对应的销售记录上报中单笔数量的值

		ChannelDataImport data = new ChannelDataImport();
		data.setColumn_1(t.getR3_code());// 送达方
		data.setColumn_11(t.getMap().get("md_name").toString());// 型号
		data.getMap().put("orderByDate", "true");
		if (t.getStock_date().getSeconds() == 0 && t.getStock_date().getMinutes() == 0
				&& t.getStock_date().getHours() == 0)
			data.getMap().put("startDate", ss.format(t.getStock_date()));
		else
			data.getMap().put("startDate", s.format(t.getStock_date()));
		if (endDate != null) {
			data.getMap().put("endDate_lt", endDate);
		}
		List<ChannelDataImport> List = getFacade().getChannelDataImportService().getChannelDataListByPdId(data);

		List<ChannelDataImport> dataList = new ArrayList<ChannelDataImport>();
		ChannelDataImport channelDataImport = new ChannelDataImport();
		channelDataImport.setColumn_12(t.getStock_count().toString());// 将初始库存记录塞入dataList的首位
		channelDataImport.setColumn_13(t.getStock_cost());
		dataList.add(channelDataImport);

		for (int j = 0; j < List.size(); j++) {
			dataList.add(List.get(j));
		}
		for (KonkaSellDetails i : sellDetailsList) {
			// log.info("2222222222222222222222222222");
			// 取网点R3销售订单中【某型号】产品的销售导入记录

			Long count = i.getSell_count();

			Long x = 0L;// x为销售记录上报中单笔数量超过R3销售订单中对应单笔销售数量的值

			for (int q = z; q < dataList.size(); q++) {// 控制dataList循环的起始节点

				if (q == dataList.size() - 1) {
					lastPrice = dataList.get(q).getColumn_13();
				}
				String num;
				if (count != 0L) {// 控制dataList循环
					if (x > 0) {
						count = x; // 如果上报的数量超过了对应的R3销售记录的数量，将超过的数量赋值给count，R3销售记录的列表循环对应下移，找下一单销售记录继续运算。
					}
					if (y > 0) {// 如果上报的数量小于对应的R3销售记录的数量，则将此条R3销售记录剩余的数量赋值给num，R3销售记录的列表循环不动，上报的销售记录列表循环下移
						num = y.toString();
					} else {// 否则将当前R3销售记录的数量赋值给num
						num = dataList.get(q).getColumn_12();
					}
					if (count > Long.valueOf(num)) {// 判断销售记录上报中单笔数量是否超过R3销售订单中对应单笔销售数量的值
						sum = sum.add(new BigDecimal(num).multiply(dataList.get(q).getColumn_13()));// 总金额等于之前的金额加上[此条R3销售记录的数量乘以单价]
						x = count - Long.valueOf(num);// 将销售记录上报中单笔数量超过R3销售订单中对应单笔销售数量的值赋值给x
						y = 0L;
						log.info("===================x=" + x);
					} else if (count < Long.valueOf(dataList.get(q).getColumn_12())) {// 如果此条R3销售订单中对应单笔销售数量的值超过销售记录上报中的单笔数量
						sum = sum.add(new BigDecimal(count).multiply(dataList.get(q).getColumn_13()));// 总金额等于之前的金额加上[上报的销售数量乘以对应的R3销售记录的单价]
						y = Long.valueOf(num) - count;// 将此条R3销售记录中剩余的数量赋值给y
						count = 0L;
						x = 0L;
						z = q;// dataList下次循环从本条开始
						log.info("===================y=" + y);
					} else {// 若正好某条r3销售记录的数量与上报的销售数量相等
						sum = sum.add(new BigDecimal(count).multiply(dataList.get(q).getColumn_13()));
						count = 0L;
						x = 0L;
						y = 0L;
						z = q + 1;// dataList下次循环从下一条开始
					}
					if (q == dataList.size() - 1 && x > 0) {
						sum = sum.add(new BigDecimal(x).multiply(dataList.get(q).getColumn_13()));
						// log.info("==============111111111111111111111111"+new
						// BigDecimal(x).multiply(dataList.get(q).getColumn_13()));
					}
					log.info("=================================" + sum);
				}
			}
		}

		if (null == channelDateMoneySum) {
			channelDateMoneySum = new BigDecimal(0);
		}

		BigDecimal stock_cost;

		if (stock_count.equals(0L)) {
			stock_cost = lastPrice;

		} else {
			// 当前库存总价格=（（第一次上报的上次库存记录*第一次上报的上次库存成本）+（R3销售订单 总金额1+...R3销售订单
			// 总金额n）-业务员销售数据上报的数量*R3系统中从最早到现在的匹配的销售数据价格）/当前库存数量
			stock_cost = ((t.getStock_cost().multiply(new BigDecimal(t.getStock_count()))).add(channelDateMoneySum)
					.subtract(sum)).divide(new BigDecimal(stock_count), 4, BigDecimal.ROUND_HALF_UP);
		}
		int key = stock_cost.compareTo(BigDecimal.ZERO);

		if (key == 0) { // 如果最后结果 得出单价为0，则取最后一次入库的单价 若无入库 则取初始的单价
			if (lastPrice != null) {
				stock_cost = lastPrice;
			}
		} else if (key < 0) {
			stock_cost = stock_cost.abs();
		}
		details.setCurrent_cost(stock_cost);
		details.setCurrent_count(stock_count);
		details.getMap().put("sellCostSum", sum);
		return details;
	}

	/**
	 * @desc 根据R3编码，产品ID得当前库存数量
	 * @param KonkaStockDetails
	 *            , 终止时间
	 * @author Wang,Yang
	 * @version 2012-3-28
	 */
	@SuppressWarnings("deprecation")
	public Long getCurrentCount(KonkaStockDetails t, String endDate) throws Exception {
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat ss = new SimpleDateFormat("yyyy-MM-dd");

		// 查询业务员上报销售数量
		KonkaSell sell = new KonkaSell();
		sell.setCus_sn(t.getR3_code());
		sell.getMap().put("pd_id", t.getPd_id());
		sell.setState(1);
		sell.setIs_del(0);
		if (t.getStock_date().getSeconds() == 0 && t.getStock_date().getMinutes() == 0
				&& t.getStock_date().getHours() == 0) {
			sell.getMap().put("sell_date_start", ss.format(t.getStock_date()));
		} else {
			sell.getMap().put("sell_date_start", s.format(t.getStock_date()));
		}
		if (endDate != null) {
			sell.getMap().put("sell_date_end_lt", endDate);
		}
		Long sellCount = getFacade().getKonkaSellService().getKonkaSellCountByPd(sell);

		// PePdModel pePdModel = new PePdModel();
		// pePdModel.setPd_id(t.getPd_id());
		// pePdModel =
		// getFacade().getPePdModelService().getPePdModel(pePdModel);
		//
		// if (null != pePdModel) {

		// 初始库存时间之后的查询R3进货数
		Long channelDateSum = 0L;
		ChannelDataImport channelDate = new ChannelDataImport();
		channelDate.getMap().put("column", "column_12");// 查询数量
		channelDate.setColumn_1(t.getR3_code());// 送达方
		channelDate.setColumn_11(t.getMap().get("md_name").toString());// 型号
		if (t.getStock_date().getSeconds() == 0 && t.getStock_date().getMinutes() == 0
				&& t.getStock_date().getHours() == 0)
			channelDate.getMap().put("startDate", ss.format(t.getStock_date()));
		else
			channelDate.getMap().put("startDate", s.format(t.getStock_date()));

		if (endDate != null) {
			channelDate.getMap().put("endDate_lt", endDate);
		}
		if (getFacade().getChannelDataImportService().getChannelDataSumByPdId(channelDate) != null) {
			channelDateSum = getFacade().getChannelDataImportService().getChannelDataSumByPdId(channelDate).longValue();
		}

		if (null == t.getStock_count()) {
			t.setStock_count(0L);
		}

		if (null == sellCount) {
			sellCount = 0L;
		}

		// 当前库存数量=业务员初始库存数量+R3进货数-业务员上报销售数量
		Long stock_count = t.getStock_count() + channelDateSum - sellCount;
		return stock_count;
	}

	/**
	 * @desc 根据登录人取分配给所在分公司的产品 , ids用于初始库存上报 列出没有初始的产品,cls_id
	 *       用于查询此产品类型包括其子产品类型的所有产品
	 * @param request
	 *            , response ,ids ,md_name_like,cls_id
	 * @author Wang,Yang
	 * @version 2011-11-20
	 */
	public List<PePdModel> getDeptLinkProduct(HttpServletRequest request,// 取分配给所在分公司的产品
			HttpServletResponse response, String ids, String md_name_like, String cls_id, Integer rowCount) {

		HttpSession session = request.getSession();
		PeProdUser ui = (PeProdUser) session.getAttribute(Constants.USER_INFO);

		// PeRoleUser peRoleUser = (PeRoleUser)
		// request.getSession().getAttribute(Constants.ROLE_INFO);

		PePdModel pepdModel = new PePdModel();
		pepdModel.setIs_del(0);
		pepdModel.getMap().put("OrderByMd", 1);

		logger.info("Your Name    : {}", ui.getUser_name());
		logger.info("Your User id : {}", ui.getId());
		logger.info("Your Dept id : {}", ui.getDept_id());

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(ui.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_ge_30 = false;
		boolean role_id_ge_30_le_39 = false;
		boolean role_id_ge_40_le_60 = false;
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id() >= 30L) {
				role_id_ge_30 = true;
			}
			if (peRoleUser.getRole_id() >= 30L && peRoleUser.getRole_id() <= 39L) {
				role_id_ge_30_le_39 = true;
			}
			if (peRoleUser.getRole_id() >= 40L && peRoleUser.getRole_id() <= 60L) {
				role_id_ge_40_le_60 = true;
			}
		}

		if (role_id_ge_30) {
			pepdModel.getMap().put("getOwnDept", "true");
			pepdModel.getMap().put("dept_id", ui.getDept_id());
		}
		if (role_id_ge_30_le_39) {// 若登录的是分公司用户或分公司领导
			pepdModel.getMap().put("dept_id", ui.getDept_id());
		}
		// 若登录的是经营部、办事处、业务员用户
		if (role_id_ge_40_le_60) {
			KonkaDept konkaDept = this.getSuperiorForDeptType(ui.getDept_id(), 3);
			pepdModel.getMap().put("dept_id", konkaDept.getDept_id());
		}
		if (StringUtils.isNotBlank(ids)) {// 为初始库存上报筛选掉已经上报过的产品
			pepdModel.getMap().put("not_selects", ids);
		}
		if (StringUtils.isNotBlank(md_name_like)) {
			pepdModel.getMap().put("md_name_like", md_name_like);
		}
		if (StringUtils.isNotBlank(cls_id)) {
			pepdModel.setCls_id(Long.valueOf(cls_id));
		}

		if (rowCount != null) {
			pepdModel.getRow().setCount(rowCount);
		}
		List<PePdModel> kpmList = getFacade().getPePdModelService().getPePdModelList(pepdModel);

		// // 取分配给自己所在分公司的产品型号
		// KonkaDeptPdLink deptPdLink = new KonkaDeptPdLink();
		//
		// if (peRoleUser.getRole_id() >= 30 && peRoleUser.getRole_id() <= 39)
		// {// 若登录的是分公司用户或分公司领导
		// deptPdLink.setDept_id(ui.getDept_id());
		// }
		// // 若登录的是经营部、办事处、业务员用户
		// if (peRoleUser.getRole_id() >= 40 && peRoleUser.getRole_id() <= 60) {
		// KonkaDept konkaDept = this.getSuperiorForDeptType(ui.getDept_id(),
		// 3);
		// deptPdLink.setDept_id(konkaDept.getDept_id());
		// }
		// if (StringUtils.isNotBlank(ids)) {// 为初始库存上报筛选掉已经上报过的产品
		// deptPdLink.getMap().put("PdIdNotin", ids);
		// }
		// List<KonkaDeptPdLink> deptPdLinkList =
		// getFacade().getKonkaDeptPdLinkService().getKonkaDeptPdLinkList(
		// deptPdLink);
		//
		// List<PePdModel> kpmList = new ArrayList<PePdModel>();
		//
		// for (KonkaDeptPdLink t : deptPdLinkList) {
		// PePdModel pePdModel = new PePdModel();
		// pePdModel.setPd_id(t.getPd_id());
		// pePdModel.setIs_del(0);
		// if (StringUtils.isNotBlank(md_name_like)) {
		// pePdModel.getMap().put("md_name_like", md_name_like);
		// }
		// if (StringUtils.isNotBlank(cls_id)) {
		// pePdModel.setCls_id(Long.valueOf(cls_id));
		// }
		// pePdModel =
		// getFacade().getPePdModelService().getPePdModel(pePdModel);
		// if (null != pePdModel) {
		// kpmList.add(pePdModel);
		// }
		// }

		return kpmList;
	}

	/**
	 * 获取R3网点或经销商网点的分配数据
	 * 
	 * @param branch_type
	 *            1:R3网点 2:经销商
	 * @param konka_r3_id
	 * @param mmt_shop_id
	 * @param fgs_id
	 * @param jyb_id
	 * @param bsc_id
	 * @param user_id
	 * @return
	 */
	protected List<BranchAssign> getBranchAssignList(Integer branch_type, Long konka_r3_id, Long mmt_shop_id,
			Long fgs_id, Long jyb_id, Long bsc_id, Long user_id) {
		BranchAssign t = new BranchAssign();
		// t.setSyb_id(1l);// 目前写死为多媒体事业本部
		t.setBranch_type(branch_type);
		t.setKonka_r3_id(konka_r3_id);
		t.setMmt_shop_id(mmt_shop_id);
		t.setFgs_id(fgs_id);
		t.setJyb_id(jyb_id);
		t.setBsc_id(bsc_id);
		t.setUser_id(user_id);
		return this.getFacade().getBranchAssignService().getBranchAssignList(t);
	}

	/**
	 * 带有分公司、经营部、办事处、业务员名称分配信息的康佳R3网点列表数据
	 * 
	 * @param entityList
	 * @return
	 */
	protected List<KonkaR3Shop> setBranchNameForR3ShopListByKonkaR3ShopList(List<KonkaR3Shop> konkaR3ShopList) {
		for (KonkaR3Shop t : konkaR3ShopList) {
			List<BranchAssign> list = this.getBranchAssignList(1, t.getId(), null, null, null, null, null);
			logger.info("size=============={}", list.size());
			if (list.size() > 0) {
				for (BranchAssign branchAssign : list) {
					if (branchAssign.getFgs_id() != null) { // 分公司
						KonkaDept konkaDept = new KonkaDept();
						konkaDept.setDept_id(branchAssign.getFgs_id());
						konkaDept = this.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
						if (null != konkaDept) {
							t.getMap().put("fgs_name", konkaDept.getDept_name());
							t.getMap().put("fgs_id", konkaDept.getDept_id());
						}
					}
					if (branchAssign.getJyb_id() != null) { // 经营部
						KonkaDept konkaDept = new KonkaDept();
						konkaDept.setDept_id(branchAssign.getJyb_id());
						konkaDept = this.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
						if (null != konkaDept) {
							t.getMap().put("jyb_name", konkaDept.getDept_name());
							t.getMap().put("jyb_id", konkaDept.getDept_id());
						}
					}
					if (branchAssign.getBsc_id() != null) { // 办事处
						KonkaDept konkaDept = new KonkaDept();
						konkaDept.setDept_id(branchAssign.getBsc_id());
						konkaDept = this.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
						if (null != konkaDept) {
							t.getMap().put("bsc_name", konkaDept.getDept_name());
							t.getMap().put("bsc_id", konkaDept.getDept_id());
						}
					}
					if (branchAssign.getUser_id() != null) { // 业务员
						PeProdUser peProdUser1 = new PeProdUser();
						peProdUser1.setId(branchAssign.getUser_id());
						peProdUser1 = this.getFacade().getPeProdUserService().getPeProdUser(peProdUser1);
						if (null != peProdUser1) {
							t.getMap().put("ywy_name", peProdUser1.getReal_name());
							t.getMap().put("ywy_id", peProdUser1.getId());
						}
					}
				}
			}
		}
		return konkaR3ShopList;
	}

	/**
	 * 带有分公司、经营部、办事处、业务员名称分配信息的买卖提5.5W网点列表数据
	 * 
	 * @param entityList
	 * @return
	 */
	protected List<MmtEntpShop> setBranchNameForKonkaEntpShopListByKonkaEntpShopList(List<MmtEntpShop> konkaEntpShopList) {
		for (MmtEntpShop t : konkaEntpShopList) {
			List<BranchAssign> list = this.getBranchAssignList(2, null, t.getShop_id(), null, null, null, null);
			if (list.size() > 0) {
				for (BranchAssign branchAssign : list) {
					if (branchAssign.getFgs_id() != null) { // 分公司
						KonkaDept konkaDept = new KonkaDept();
						konkaDept.setDept_id(branchAssign.getFgs_id());
						konkaDept = this.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
						if (null != konkaDept) {
							t.getMap().put("fgs_name", konkaDept.getDept_name());
							t.getMap().put("fgs_id", konkaDept.getDept_id());
						}
					}
					if (branchAssign.getJyb_id() != null) { // 经营部
						KonkaDept konkaDept = new KonkaDept();
						konkaDept.setDept_id(branchAssign.getJyb_id());
						konkaDept = this.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
						if (null != konkaDept) {
							t.getMap().put("jyb_name", konkaDept.getDept_name());
							t.getMap().put("jyb_id", konkaDept.getDept_id());
						}
					}
					if (branchAssign.getBsc_id() != null) { // 办事处
						KonkaDept konkaDept = new KonkaDept();
						konkaDept.setDept_id(branchAssign.getBsc_id());
						konkaDept = this.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
						if (null != konkaDept) {
							t.getMap().put("bsc_name", konkaDept.getDept_name());
							t.getMap().put("bsc_id", konkaDept.getDept_id());
						}
					}
					if (branchAssign.getUser_id() != null) { // 业务员
						PeProdUser peProdUser1 = new PeProdUser();
						peProdUser1.setId(branchAssign.getUser_id());
						peProdUser1 = this.getFacade().getPeProdUserService().getPeProdUser(peProdUser1);
						if (null != peProdUser1) {
							t.getMap().put("ywy_name", peProdUser1.getReal_name());
							t.getMap().put("ywy_id", peProdUser1.getId());
						}
					}
				}
			}
		}
		return konkaEntpShopList;
	}

	/**
	 * 取当前登陆的用户
	 * 
	 * @param entityList
	 * @return
	 */
	protected PeProdUser getSessionUserInfo(HttpServletRequest request) {
		return (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
	}

	protected PeProdUser getSessionCustomerUserInfo(HttpServletRequest request) {
		return (PeProdUser) request.getSession().getAttribute(Constants.CUSTOMER_USER_INFO);
	}

	/**
	 * 根据分公司名称查询r3shop表中下属的经办名称
	 * 
	 * @param Long
	 *            jyb_id 事业部ID;Long fgs_id 分公司一下人员登录只显示自己的分公司，若需全部列表，传null即可
	 * @return List<KonkaDept> 分公司列表
	 */
	public List<KonkaDept> getBranchListByJybId(Long jyb_id, Long fgs_id) {
		KonkaDept konkaDept = new KonkaDept();
		if (fgs_id != null) {
			konkaDept.setDept_id(fgs_id);
		}
		konkaDept.setPar_id(jyb_id);
		List<KonkaDept> BranchList = getFacade().getKonkaDeptService().getKonkaDeptList(konkaDept);
		return BranchList;
	}

	/**
	 * 根据分公司名称查询r3shop表中下属的经办名称
	 * 
	 * @param entityList
	 * @return
	 */
	public ActionForward getHandleList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DynaBean dynaBean = (DynaBean) form;
		String branch_area_name = (String) dynaBean.get("branch_area_name");

		if (StringUtils.isEmpty(branch_area_name)) {
			return null;
		}
		KonkaDept dept = new KonkaDept();
		dept.setDept_id(this.getSessionUserInfo(request).getDept_id());
		dept = getFacade().getKonkaDeptService().getKonkaDept(dept);

		KonkaR3Shop r3 = new KonkaR3Shop();
		r3.setIs_del(0L);
		r3.setBranch_area_name(branch_area_name);
		// if (dept.getDept_type() == 4) {
		// r3.setHandle_name(dept.getDept_name());
		// } else if (dept.getDept_type() == 5) {
		// if (this.getSuperiorForDeptType(dept.getDept_id(), 4) != null) {
		// r3.setHandle_name(this.getSuperiorForDeptType(dept.getDept_id(),
		// 4).getDept_name());
		// }
		// }
		List<KonkaR3Shop> handleList = getFacade().getKonkaR3ShopService().getKonkaR3ShopGroupByHandleName(r3);
		StringBuffer sb = new StringBuffer("[");
		if (handleList != null && handleList.size() != 0) {
			for (KonkaR3Shop t : handleList) {
				sb.append("{\"id\":\"" + t.getHandle_name() + "\",");
				sb.append("\"name\":\"" + t.getHandle_name() + "\"},");
			}
		}
		sb.append("{\"author\":\"wy\"}]");
		log.info("=======sb.toString:  " + sb.toString());
		super.renderJson(response, sb.toString());
		return null;
	}

	/**
	 * @author Li,Ka
	 * @version 2011-11-17
	 * @desc 根据仓库名称和部门id获取仓库信息
	 */
	public KonkaJxcStoreInfo getKonkaStockByDeptIdAndStoreName(Long deptId, String name) {
		KonkaJxcStoreInfo store = new KonkaJxcStoreInfo();
		store.setStore_name(name);
		store.setAdd_dept_id(deptId);
		store = getFacade().getKonkaJxcStoreInfoService().getKonkaJxcStoreInfo(store);
		return store;
	}

	/**
	 * @author Li,Ka
	 * @version 2011-10-19
	 * @desc 根据类型id，取产品类型
	 */
	protected BasePdClazz getBasePdClazz(Long pdTypeId) {
		BasePdClazz basePdClass = new BasePdClazz();
		basePdClass.setCls_id(pdTypeId);
		basePdClass.setIs_del(0);
		basePdClass = this.getFacade().getBasePdClazzService().getBasePdClazz(basePdClass);
		return basePdClass;
	}

	/**
	 * @author du,zhiming
	 * @version 2011-10-13
	 * @desc 根据部门id获取部门信息
	 */
	public KonkaDept getKonkaDeptById(Long deptId) {
		KonkaDept peDept = new KonkaDept();
		peDept.setDept_id(deptId);
		peDept = getFacade().getKonkaDeptService().getKonkaDept(peDept);
		return peDept;
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2011-10-13
	 * @desc 工卡展示平台，触网平台，会员平台根据用户取部门信息
	 */
	public KonkaDept getKonkaDeptByUserId4KonkaBcompPd(Long user_id) {
		if (null == user_id)
			return null;
		PeProdUser ui = new PeProdUser();
		ui.setId(Long.valueOf(user_id));
		ui = this.getFacade().getPeProdUserService().getPeProdUser(ui);
		if (null == ui || null == ui.getDept_id())
			return null;

		// Dept_type : 1-总部，2-事业部，3-分公司，4-经营部，5-办事处，6-代理，7-终端
		KonkaDept peDept = new KonkaDept();
		peDept.setDept_id(ui.getDept_id());
		peDept = getFacade().getKonkaDeptService().getKonkaDept(peDept);
		if (null == peDept)
			return null;
		// 判断是不是分公司下面的办公室等
		if (null == peDept.getDept_type()) {
			KonkaDept t = new KonkaDept();
			t.setDept_id(peDept.getPar_id());
			t = getFacade().getKonkaDeptService().getKonkaDept(peDept);
			return t;
		}

		return peDept;
	}

	/**
	 * @author hu,hao
	 * @version 2013-07-16
	 * @desc 根据部门id获取分公司信息
	 */
	public KonkaDept getKonkaDeptForFgs(Long deptId) {
		return this.getSuperiorForDeptType(deptId, 3);
	}

	/**
	 * @author Zhang,xufeng
	 * @version 2011-10-17
	 * @desc 根据登录用户的id，取该用户的角色
	 */
	protected PeRoleInfo getPeRoleInfoByUserId(Long userId) {
		PeRoleUser peRoleUser = new PeRoleUser();
		peRoleUser.setUser_id(userId);
		peRoleUser = getFacade().getPeRoleUserService().getPeRoleUser(peRoleUser);
		PeRoleInfo peRoleInfo = new PeRoleInfo();
		peRoleInfo.setRole_id(peRoleUser.getRole_id());
		peRoleInfo.setIs_del(0);
		return this.getFacade().getPeRoleInfoService().getPeRoleInfo(peRoleInfo);
	}

	/**
	 * @author WuShangLong
	 * @version 2013-06-09
	 * @desc 根据登录用户的id，取该用户的角色
	 */
	protected List<PeRoleInfo> getPeRoleInfoListByUserId(Long userId) {
		List<PeRoleInfo> ret = new ArrayList<PeRoleInfo>();
		PeRoleUser peRoleUser = new PeRoleUser();
		peRoleUser.setUser_id(userId);
		List<PeRoleUser> peRoleUserList = getFacade().getPeRoleUserService().getPeRoleUserList(peRoleUser);
		for (PeRoleUser roleuser : peRoleUserList) {
			PeRoleInfo peRoleInfo = new PeRoleInfo();
			peRoleInfo.setRole_id(roleuser.getRole_id());
			peRoleInfo.setIs_del(0);
			PeRoleInfo p = this.getFacade().getPeRoleInfoService().getPeRoleInfo(peRoleInfo);
			if (p != null) {
				ret.add(p);
			}
		}

		return ret;
	}

	/**
	 * 根据pindex获取区域
	 * 
	 * @param p_index
	 * @return
	 */
	public BaseProvinceListFour getBaseProvinceListFourByPindex(Long p_index) {
		BaseProvinceListFour baseProvinceListFour = new BaseProvinceListFour();
		baseProvinceListFour.setP_index(p_index);
		return this.getFacade().getBaseProvinceListFourService().getBaseProvinceListFour(baseProvinceListFour);
	}

	/**
	 * @author Xing,XiuDong
	 * @version Build 2013.3.14
	 */
	protected void renderJsonp(HttpServletRequest request, HttpServletResponse response, String text) {
		String jsonpcallback = request.getParameter("jsonpcallback");
		logger.info("jsonpcallback : {}", jsonpcallback);
		if ("null".equals(jsonpcallback)) {
			jsonpcallback = "";
		}
		this.renderText(response, jsonpcallback + "(" + text + ")");
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2013-5-28
	 */
	protected void renderTextJsonOrJsonp(HttpServletRequest request, HttpServletResponse response, String text) {
		String jsonpcallback = request.getParameter("jsonpcallback");
		logger.info("jsonpcallback : {}", jsonpcallback);
		if (StringUtils.isBlank(jsonpcallback)) {
			this.renderText(response, text);
		} else {
			// 判断是否是JSON，
			if (StringUtils.indexOf(text, "{") != -1 || StringUtils.indexOf(text, "[") != -1) {
				this.renderText(response, jsonpcallback + "(" + text + ")");
			} else {
				this.renderText(response, jsonpcallback + "({\"status\":\"-1\", \"msg\":\"" + text + "\"})");
			}
		}
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2013-5-28
	 * @desc 如果包含 jsonpcallback 参数则返回jsonp类型,,json类型{"msg":"text"}
	 */
	protected void renderTextOrJsonp(HttpServletRequest request, HttpServletResponse response, String text) {
		String jsonpcallback = request.getParameter("jsonpcallback");
		logger.info("jsonpcallback : {}", jsonpcallback);
		if (StringUtils.isBlank(jsonpcallback)) {
			this.renderText(response, text);
		} else {
			this.renderText(response, jsonpcallback + "({\"msg\":\"" + text + "\"})");
		}
	}

	protected boolean isAdministrator(PeProdUser peProdUser) {
		return ArrayUtils.contains(new Long[] { 0L, 1L }, peProdUser.getId());
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2012-09-01
	 * @param encoding
	 *            编码，用GBK图形才能正常显示
	 */
	protected void renderXmlWithEncoding(HttpServletResponse response, String text, String encoding) {
		render(response, text, "text/xml;charset=".concat(encoding));
	}

	/**
	 * 通过访问者的Header信息获取真实IP
	 * 
	 * @param request
	 * @return
	 */
	protected String getClientRealIp(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	@Override
	protected List<UploadFile> uploadFile(ActionForm form, boolean isResizeImage) throws Exception {
		return uploadFile(form, null, isResizeImage);
	}

	@Override
	protected List<UploadFile> uploadFile(ActionForm form, boolean isResizeImage, int resizeType, int... resizeVersions)
			throws Exception {
        if (resizeVersions == null) {
            return uploadFile(form, null, false);
        }
		return uploadFile(form, null, isResizeImage, resizeType, resizeVersions);
	}

	@Override
	protected List<UploadFile> uploadFile(ActionForm form, String uploadDir) throws Exception {
		return uploadFile(form, uploadDir, false);
	}

	@Override
	protected List<UploadFile> uploadFile(ActionForm form, String uploadDir, boolean isResizeImage) throws Exception {
		return uploadFile(form, uploadDir, isResizeImage, 0, new int[] { 400 });
	}

	/**
	 * 由于215,216存储方案的改变<br>
	 * 215,216应用的目录更改为/Attachment_new/konka-files/files/模块/年/月/ 日<br>
	 * 
	 * 217开心猫应用保持原来的保存目录:/web/tomcat-root/files/ <br>
	 * 
	 * 为了保证文件存储不出问题,要求215,216的应用代码在使用上传方法里,必须指定保存路径
	 * 
	 * 具体@see com.ebiz.mmt.web.struts.Keys
	 * 
	 * <p>
	 * 215,216应用零售通文件保存根路径 public static final String LST_PATH =
	 * "/Attachment_new/konka-files/files/lst/";
	 * </p>
	 * <p>
	 * 215,216应用门店文件保存根路径 public static final String MD_PATH =
	 * "/Attachment_new/konka-files/files/md/";
	 * </p>
	 * <p>
	 * 215,216应用OA文件保存根路径 public static final String OA_PATH =
	 * "/Attachment_new/konka-files/files/oa/";
	 * </p>
	 * <p>
	 * 215,216应用业务通文件保存根路径 public static final String YWT_PATH =
	 * "/Attachment_new/konka-files/files/ywt/";
	 * </p>
	 * <p>
	 * 215,216应用默认文件保存根路径 public static final String OTHERS_PATH =
	 * "/Attachment_new/konka-files/files/others/";
	 * </p>
	 * </p>
	 * <p>
	 * 215,216应用触网文件保存根路径 public static final String CHUWANG_PATH =
	 * "/Attachment_new/konka-files/files/chuwang";
	 * </p>
	 */
	@Override
	protected List<UploadFile> uploadFile(ActionForm form, String uploadDir, boolean isResizeImage, int resizeType,
			int... resizeVersions) throws Exception {

		// web内容根目录
		String ctxDir = getServlet().getServletContext().getRealPath(File.separator);
		if (!ctxDir.endsWith(File.separator)) {
			ctxDir = ctxDir + File.separator;
		}

		// uploadDir 215,216 有可能指定/Attachment_new下;217不指定/Attachment_new
		// 只有217开心猫会使用默认上传的指定的模块目录,其余215,216应用必须指定上传路径
		if (StringUtils.isBlank(uploadDir)) {
			uploadDir = StringUtils.join(new String[] { "files", "upload", "" }, File.separator);
		} else {
			// 215,216 com.ebiz.mmt.web.struts.MmtFilePathConfig
			// 如果是重新规划的文件存储路径,把web根路径清空,避免放在/web/tomcat-root下
			if (StringUtils.contains(uploadDir, "Attachment_new/konka-files")) {
				ctxDir = "";
			}
			// files/lunbotu ...
		}

		// 按日期自动构建目录
		String[] folderPatterns = new String[] { "yyyy", "MM", "dd", "" };
		String autoCreatedDateDir = DateFormatUtils
				.format(new Date(), StringUtils.join(folderPatterns, File.separator));

		// 如果目录不存在,则创建目录
		// 文件将要保存的目录所在
		File savePath = new File(ctxDir + uploadDir + autoCreatedDateDir);
		logger.debug("===> save path is: {}", savePath);
		if (!savePath.exists()) {
			savePath.mkdirs();
		}

		List<UploadFile> uploadFileList = new ArrayList<UploadFile>();

		Hashtable filehashtable = form.getMultipartRequestHandler().getFileElements();

		for (Enumeration e = filehashtable.keys(); e.hasMoreElements();) {
			String key = (String) e.nextElement();
			FormFile formFile = (FormFile) filehashtable.get(key);
			String fileName = formFile.getFileName().trim();
			if (!"".equals(fileName)) {
				UploadFile uploadFile = new UploadFile();
				uploadFile.setContentType(formFile.getContentType());
				uploadFile.setFileSize(formFile.getFileSize());
				uploadFile.setFileName(formFile.getFileName().trim());
				uploadFile.setFormName(key);
				String fileSaveName = StringUtils.join(new String[] { UUID.randomUUID().toString(), ".",
						uploadFile.getExtension() });

				// 215,216 com.ebiz.mmt.web.struts.MmtFilePathConfig
				// 由于nginx之前映射的路径类似files/upload/2015/01/01/
				// 因此/Attachment_new/konka-files/files/md/2015/02/04/的保存路径截为files/md/2015/02/04/
				String fileSavePath = uploadDir + autoCreatedDateDir + fileSaveName;

				// 保存附件文件
				InputStream ins = formFile.getInputStream();
				OutputStream os = new FileOutputStream(ctxDir + fileSavePath);
				int bytesRead = 0;
				byte[] buffer = new byte[8192];
				while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
					os.write(buffer, 0, bytesRead);
				}
				os.close();
				ins.close();

				// ftp
				// 其它节点,可以通过ftp把文件集中存储
				// feckeditor ftp.upload = true && ftp配置正确
				// ftpClientTemplate.storeFile(fileSavePath, new File(ctxDir +
				// fileSavePath));

				// resize image
                if (isResizeImage && formFile.getContentType().indexOf("image/") != -1 && resizeVersions != null) {
					switch (resizeType) {
					case 0: // resizeByMaxSize
						for (int v = 0; v < resizeVersions.length; v++) {
							FtpImageUtils.resizeByMaxSize(ftpClientTemplate, fileSavePath, ctxDir + fileSavePath, null,
									resizeVersions[v]);
						}
						break;
					case 1:// resizeByRatio
						for (int v = 0; v < resizeVersions.length; v++) {
							FtpImageUtils.resizeByRatio(ftpClientTemplate, fileSavePath, ctxDir + fileSavePath, null,
									resizeVersions[v]);
						}
						break;
					case 2:// resizeByFixedWidth
						for (int v = 0; v < resizeVersions.length; v++) {
							FtpImageUtils.resizeByFixedWidth(ftpClientTemplate, fileSavePath, ctxDir + fileSavePath,
									null, resizeVersions[v]);
						}
						break;
					case 3:// resizeByFixedHeight
						for (int v = 0; v < resizeVersions.length; v++) {
							FtpImageUtils.resizeByFixedHeight(ftpClientTemplate, fileSavePath, ctxDir + fileSavePath,
									null, resizeVersions[v]);
						}
						break;
					}
				}

				// 返回文件列表用于在数据库保存路径
				if (StringUtils.contains(uploadDir, "Attachment_new/konka-files")) {
					String uploadDir1 = "";
					uploadDir1 = StringUtils.substringAfter(uploadDir, "Attachment_new/konka-files/");
					fileSavePath = uploadDir1 + autoCreatedDateDir + fileSaveName;
				} else {
					fileSavePath = uploadDir + autoCreatedDateDir + fileSaveName;
				}
				uploadFile.setFileSaveName(fileSaveName);
				// 转linux / 保存
				uploadFile.setFileSavePath(StringUtils.replace(fileSavePath, File.separator, "/"));
				uploadFileList.add(uploadFile);

			}
		}
		return uploadFileList;
	}

	public KonkaOrderAuditProcessNode getNextProcessNode(KonkaOrderInfo order, Long current_node_id) {
		if (null == order || order.getId() == null) {
			throw new NullPointerException("Parameter process_id and node_id is null!");
		}

		return this.getNextProcessNode(order.getProcess_id(), current_node_id, order);
	}

	public KonkaOrderAuditProcessNode getNextProcessNode(Long process_id, Long current_node_id) {
		return this.getNextProcessNode(process_id, current_node_id, null);
	}

	/**
	 * 返回流程的下一个节点
	 * 
	 * @param process_obj
	 *            流程对象，ID属性不可为空
	 * @param current_node_id
	 *            节点ID，可为空，表示取第一个节点
	 * @return null 表示没有下一个节点，即最后一个节点
	 * 
	 @exp <b>下一个节点表达式语法</b><br>
	 *      <p>
	 *      NodeId:field1[注释..]-operator-value[注释..],field2-operator-value,...@(
	 *      and|or);...
	 *      </p>
	 * <br>
	 *      <b>其中：</b><br>
	 *      field_operator_value为条件表达式，多个表达式取交集，具体如下：<br>
	 *      1）field为表单中字段（数据库中的列）名称；<br>
	 *      2）operator为运算符，值有：lt,le,gt,ge,eq,in,between,like,unlike；eq和in的值可以为字母
	 *      ； like和unlike的值可以为字符串；<br>
	 *      3）value为数字或用“|”连接的字符串；<br>
	 *      4）注释可以为空；<br>
	 *      5）field、value以及注释中不能含有以下字符“:”、“@”、“;”、“[”、“]”、“,”、“-”、“|”、“$”以及保留字符；<br>
	 *      6）以下字符为保留字符：“#”、“&”、“*”、“(”、“)”、“{”、“}”、“?”、“/”；<br>
	 *      7）NodeId为“$”表示当前节点为终点；<br>
	 *      8）表达式计算值为从左往右递归计算，当计算结果为“true”时，返回匹配节点。<br>
	 * <br>
	 *      <b>Examples</b>
	 *      1000:x[x1的注释]-gt-1000,x[x2的注释]-lt-10000,y-in-1|2|3,z-
	 *      eq-helloworld[z选项的注释],...@and;<br>
	 *      2000:......
	 * @author XingXiuDong
	 */
	public KonkaOrderAuditProcessNode getNextProcessNode(Long process_id, Long current_node_id, final Object obj) {
		if (null == process_id) {
			throw new NullPointerException("Parameter process_id and node_id is null!");
		}

		KonkaOrderAuditProcessNode node = new KonkaOrderAuditProcessNode();
		node.setAudit_proces_id(process_id);
		Long count = getFacade().getKonkaOrderAuditProcessNodeService().getKonkaOrderAuditProcessNodeCount(node);
		if (null == count || 0L == count) {
			throw new NullPointerException("Undefined process's nodes [id=" + process_id + "]!");
		}

		if (null == current_node_id) {
			// 取流程的第一个节点
			KonkaOrderAuditProcessNode next_node = new KonkaOrderAuditProcessNode();
			next_node.setAudit_level(1);
			next_node.setAudit_proces_id(process_id);
			next_node = getFacade().getKonkaOrderAuditProcessNodeService().getKonkaOrderAuditProcessNode(next_node);
			return next_node;
		}

		KonkaOrderAuditProcessNode current = new KonkaOrderAuditProcessNode();
		current.setId(current_node_id);
		current = getFacade().getKonkaOrderAuditProcessNodeService().getKonkaOrderAuditProcessNode(current);
		if (null == current || null == current.getAudit_level()) {
			throw new NullPointerException("Undefined process node(could be audit_level) [id=" + current_node_id + "]!");
		}

		String next_node_exp = current.getNext_node();
		if (null != obj && null != next_node_exp) {
			next_node_exp = next_node_exp.replaceAll("\\n", next_node_exp).replaceAll("\\r", next_node_exp)
					.replaceAll("\\s+", next_node_exp);
			if (StringUtils.isNotBlank(next_node_exp)) {
				String[] nodes = StringUtils.split(next_node_exp, ";");

				for (String n : nodes) {
					String[] node_exp = n.split(":");
					String node_id = node_exp[0];
					String node_ex = node_exp[1];

					if (calcBooleanExp(node_ex, null, obj)) {
						if ("$".equals(node_id)) {
							return null;
						}
						KonkaOrderAuditProcessNode next_node = new KonkaOrderAuditProcessNode();
						next_node.setId(Long.valueOf(node_id));
						next_node = getFacade().getKonkaOrderAuditProcessNodeService().getKonkaOrderAuditProcessNode(
								next_node);
						return next_node;
					}
				}
			}
		}

		KonkaOrderAuditProcessNode next_node = new KonkaOrderAuditProcessNode();
		next_node.setAudit_level(current.getAudit_level() + 1);
		next_node.setAudit_proces_id(process_id);
		next_node = getFacade().getKonkaOrderAuditProcessNodeService().getKonkaOrderAuditProcessNode(next_node);
		return next_node;
	}

	public Boolean calcBooleanExp(String exp, Boolean last, final Object obj) {
		// x-gt-1000,x-lt-10000,y-in-1|2|3,z-eq-helloworld,...@and
		if (StringUtils.isBlank(exp) && null != last) {
			return last;
		}

		if (null == obj) {
			return false;
		}

		String operator = StringUtils.substringAfterLast(exp, "@");

		String cur_exp = StringUtils.substringBefore(exp, ",");

		String[] cur_exps = cur_exp.split("-");
		if (cur_exps.length != 3) {
			logger.error("condition expression error! [{}]", cur_exps);
		}

		String key = cur_exps[0];
		String oprator = cur_exps[1];
		String compareValue = cur_exps[2];

		key = StringUtils.substringBefore(key, "["); // 移除注释
		compareValue = StringUtils.substringBefore(compareValue, "["); // 移除注释

		boolean result = false;

		try {
			String value = BeanUtils.getProperty(obj, StringUtils.lowerCase(key));
			value = StringUtils.substringBefore(value, ".");
			if ("eq".equalsIgnoreCase(oprator)) {
				result = StringUtils.equals(value, compareValue);
			} else if ("gt".equalsIgnoreCase(oprator)) {
				result = Long.valueOf(value) > Long.valueOf(compareValue);
			} else if ("lt".equalsIgnoreCase(oprator)) {
				result = Long.valueOf(value) < Long.valueOf(compareValue);
			} else if ("ge".equalsIgnoreCase(oprator)) {
				// value=StringUtils.substringBefore(value,".");
				result = Long.valueOf(value) >= Long.valueOf(compareValue);
				//System.out.println(result);
			} else if ("le".equalsIgnoreCase(oprator)) {
				result = Long.valueOf(value) <= Long.valueOf(compareValue);
			} else if ("between".equalsIgnoreCase(oprator)) {
				String[] vs = compareValue.split("|");
				String min = StringUtils.substringBefore(vs[0], "["); // 移除注释
				String max = StringUtils.substringBefore(vs[1], "["); // 移除注释
				result = GenericValidator.isInRange(Long.valueOf(value), Long.valueOf(min), Long.valueOf(max));
			} else if ("in".equalsIgnoreCase(oprator)) {
				String[] ss_note = StringUtils.split(compareValue, "|");
				String[] ss = new String[ss_note.length];
				for (int i = 0; i < ss_note.length; i++) {
					ss[i] = StringUtils.substringBefore(ss_note[i], "["); // 移除注释
				}
				result = ArrayUtils.contains(ss, value);
			} else if ("like".equalsIgnoreCase(oprator)) {
				result = StringUtils.contains(value, compareValue);
			} else if ("unlike".equalsIgnoreCase(oprator)) {
				result = !StringUtils.contains(value, compareValue);
			} else {
				return false;
			}

		} catch (IllegalAccessException e) {
		} catch (InvocationTargetException e) {
		} catch (NoSuchMethodException e) {
		}

		if ("and".equalsIgnoreCase(operator)) {
			result = null == last ? result : (result && last);
		} else if ("or".equalsIgnoreCase(operator)) {
			result = null == last ? result : (result || last);
		}
		last = result;
		exp = StringUtils.substringAfter(exp, ",");

		return this.calcBooleanExp(exp, last, obj);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2013-08-06
	 * @desc 进销存订单审核进度
	 */
	public void setOrderProgress(ActionForm form, HttpServletRequest request) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String order_id = (String) dynaBean.get("order_id");

		// 当前订单信息
		KonkaOrderInfo order = new KonkaOrderInfo();
		order.setId(Long.valueOf(order_id));
		order = getFacade().getKonkaOrderInfoService().getKonkaOrderInfo(order);
		request.setAttribute("order", order);

		if (null == order.getNext_audit_role_id() || order.getNext_node_id() == null) {
			return;
		}

		// 获取流程节点列表
		KonkaOrderAuditProcessNode node = new KonkaOrderAuditProcessNode();
		node.setAudit_proces_id(order.getProcess_id());
		List<KonkaOrderAuditProcessNode> nodeList = getFacade().getKonkaOrderAuditProcessNodeService()
				.getKonkaOrderAuditProcessNodeList(node);
		if (null == nodeList || nodeList.size() == 0) {
			return;
		}
		request.setAttribute("nodeList", nodeList);

		// 获取审核记录
		KonkaOrderInfoAudit audit = new KonkaOrderInfoAudit();
		audit.setLink_id(order.getId());
		List<KonkaOrderInfoAudit> auditList = getFacade().getKonkaOrderInfoAuditService()
				.getKonkaOrderInfoAuditAndRoleList(audit);
		request.setAttribute("doneList", auditList);

		if (null != order.getNext_audit_role_id()) {
			// 待审核角色
			PeRoleInfo doing_role = new PeRoleInfo();
			doing_role.setRole_id(order.getNext_audit_role_id());
			doing_role = getFacade().getPeRoleInfoService().getPeRoleInfo(doing_role);
			if (null != doing_role) {
				request.setAttribute("doing_audit_role", doing_role.getRole_name());
				request.setAttribute("doing_audit_role_id", order.getNext_audit_role_id());
			}
			if (order.getNext_node_id().longValue() == nodeList.get(nodeList.size() - 1).getId().longValue()) {// 判断当前订单中带审核的节点是不是最后一个节点
				request.setAttribute("doing_last", 1);
			}

			// 未处理角色列表
			List<KonkaOrderAuditProcessNode> todoList = new ArrayList<KonkaOrderAuditProcessNode>();
			if (null != nodeList) {
				for (KonkaOrderAuditProcessNode temp : nodeList) {
					if (-1L != order.getNext_node_id().longValue()
							&& temp.getId().longValue() > order.getNext_node_id().longValue()) {
						todoList.add(temp);
					}
				}
			}
			if (null != todoList && todoList.size() > 0) {
				request.setAttribute("todoList", todoList);
			}
		}
	}

	/**
	 * @author Li,Ka
	 * @version 2011-11-28
	 * @desc 根据区域编码取全称
	 */
	public String getPIndexFullName(Long p_index) {
		String fullName = "";
		if (null != p_index) {
			BaseProvinceList baseProvince = new BaseProvinceList();
			baseProvince.getMap().put("shop_p_index", p_index);
			List<BaseProvinceList> baseProvinceList = getFacade().getBaseProvinceListService()
					.getBaseProvinceListAllParPindexByPindex(baseProvince);
			for (BaseProvinceList bp : baseProvinceList) {
				if (bp.getP_level().intValue() == 1) {
					fullName = bp.getP_name();
				}
				if (bp.getP_level().intValue() == 2) {
					fullName = fullName + bp.getP_name();
				}
				if (bp.getP_level().intValue() == 3) {
					fullName = fullName + bp.getP_name();
				}
			}
		}
		return fullName;
	}

	/**
	 * @authur xingxiudong
	 * @desc 根据客户ID查询分公司部门对象
	 */
	protected KonkaDept getKonkaDeptByCustomerId(Long cust_id) {
		if (null == cust_id) {
			return null;
		}

		KonkaR3Shop s = new KonkaR3Shop();
		s.setId(cust_id);
		s = getFacade().getKonkaR3ShopService().getKonkaR3Shop(s);

		if (null != s && StringUtils.isNotBlank(s.getBranch_area_name_2())) {
			KonkaDept dept = new KonkaDept();
			dept.setDept_sn(s.getBranch_area_name_2().trim().toUpperCase());
			dept = getFacade().getKonkaDeptService().getKonkaDept(dept);
			return dept;
		}

		return null;
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2013-08-26
	 * @desc 根据客户ID，查询客户可用额度
	 */
	public ActionForward getCustomerCreditByR3CodeForAjax(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String r3_code = (String) dynaBean.get("r3_code");
		if (StringUtils.isEmpty(r3_code)) {
			super.renderJson(response, "{\"status\":\"-1\", \"msg\":\"R3 code is error\"}");
			return null;
		}

		KonkaR3Shop s = new KonkaR3Shop();
		s.setR3_code(r3_code);
		s = getFacade().getKonkaR3ShopService().getKonkaR3Shop(s);
		if (null == s) {
			super.renderJson(response, "{\"status\":\"-1\", \"msg\":\"根据客户编码" + r3_code
					+ "查询客户失败，可能是客户编码错误或系统暂无此客户，请联系分公司系统管理员！\"}");
			return null;
		}

		logger.info("Call r3 interface with r3_code '{}' starting...", r3_code);
		// List<KHXD> khxdList =
		// getFacade().getR3WebInterfaceService().getKhxd("KF01", null, "10",
		// "F1281047");
		// List<KHXD> khxdList =
		// getFacade().getR3WebInterfaceService().getKhxd("KF01", null, "10",
		// new String[] { r3_code });
		List<KHXD> khxdList = new ArrayList<KHXD>();

		ReturnInfo<KHXD> info = getFacade().getR3WebInterfaceService().getKhxd("KF01", null, "10",
				new String[] { r3_code });
		if (info.getSuccess() == true) {
			khxdList = info.getDataResult();
			if (khxdList.size() > 0 && null != khxdList.get(0)) {
				BigDecimal KLIMK = khxdList.get(0).getKLIMK(); // 信贷限额
				BigDecimal OBLIG = khxdList.get(0).getOBLIG(); // 信贷风险总额
				// logger.info("{}", BeanUtils.describe(khxdList.get(0)));
				KLIMK = null == KLIMK ? new BigDecimal("0") : KLIMK;
				OBLIG = null == OBLIG ? new BigDecimal("0") : OBLIG;
				super.renderJson(response,
						"{\"status\":\"1\", \"msg\":\"调用R3接口查询数据成功！\", \"data\":\"" + KLIMK.subtract(OBLIG) + "\"}");
				return null;
			}
		} else {
			super.renderJson(response, "{\"status\":\"-1\", \"msg\":\"调用R3接口查询数据失败，可能是客户编码" + r3_code
					+ "错误或系统暂无此客户，请联系分公司系统管理员！\"}");
		}
		return null;
	}

	protected String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip.contains(",")) {
			ip = ip.split(",")[0];
		}
		return ip;
	}

	protected boolean isCallR3Interface(HttpServletRequest request) {
		String val = super.getServlet().getServletContext().getInitParameter("call_r3_interface");
		return "true".equalsIgnoreCase(val);
	}

	protected boolean isDebugMode(HttpServletRequest request) {
		String val = super.getServlet().getServletContext().getInitParameter("dev_mode");
		return "true".equalsIgnoreCase(val);
	}

	public void renderHtml(HttpServletResponse response, String text) {
		this.renderHtml(response, text, "Unspecified Title.");
	}

	public void renderHtml(HttpServletResponse response, String text, String title) {
		StringBuilder html = new StringBuilder();
		html.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
		html.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
		html.append("<head>");
		html.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=").append(Constants.SYS_ENCODING)
				.append("\" />");
		html.append("<title>").append(title).append("</title>");
		html.append("<style type=\"text/css\">");
		html.append("body { margin:0px; padding:0px; }");
		html.append("</style>");
		html.append("</head>");
		html.append("<body>");
		html.append(text);
		html.append("</body>");
		html.append("</html>");
		super.render(response, html.toString(), "text/html;charset=" + Constants.SYS_ENCODING);
	}

	/**
	 * 获取用户的透视部门列表（包括行政部门）
	 * 
	 * @date 2013-09-11
	 * @author Xing,XiuDong
	 */
	public List<KonkaDept> getKonkaDeptListOfUser(Long user_id, Boolean contains_sub) {
		KonkaDept konkaDept = new KonkaDept();
		konkaDept.getMap().put("user_id_eq", user_id);
		konkaDept.getMap().put("contains_sub", (null == contains_sub || !contains_sub) ? null : contains_sub);
		return this.getFacade().getKonkaDeptService().getKonkaDeptListOfUser(konkaDept);
	}

	public boolean isFanLiZmd(PeProdUser user) {
		boolean role_id_eq_400 = false;
		PeRoleUser ru = new PeRoleUser();
		ru.setRole_id(400L);
		ru.setUser_id(user.getId());
		if (0L < this.getFacade().getPeRoleUserService().getPeRoleUserCount(ru)) {
			role_id_eq_400 = true;
		}

		// 验证是否是专卖店返利模式
		Boolean is_zmd_100200 = false;
		if (role_id_eq_400) {
			KonkaXxZmdUsers kUsers = new KonkaXxZmdUsers();
			kUsers.setUser_id(user.getId());
			List<KonkaXxZmdUsers> kList = this.getFacade().getKonkaXxZmdUsersService().getKonkaXxZmdUsersList(kUsers);
			if (kList.size() > 0) {
				KonkaXxZmd kZmd = new KonkaXxZmd();
				kZmd.setZmd_id(kList.get(0).getZmd_id());
				kZmd.setBusi_mod(100200L);
				kZmd = this.getFacade().getKonkaXxZmdService().getKonkaXxZmd(kZmd);
				if (null != kZmd) {
					is_zmd_100200 = true;
				}
			}
		}
		return is_zmd_100200;
	}

	/**
	 * 取出用户的最大数据级别，数据级别越大，权限越高。 9：集团及以下所有部门 8：分公司及以下所有部门 7：用户所在/指定部门 0: 无部门权限
	 * 
	 * @param user_id
	 *            用户ID
	 * @return 户的最大数据级别
	 * @author Xing,XiuDong
	 * @date 2013-10-29
	 */
	public int getMaxDLevel(Long user_id) {
		PeProdUser user = new PeProdUser();
		user.setId(Long.valueOf(user_id));
		user = this.getFacade().getPeProdUserService().getPeProdUser(user);
		if (user == null) {
			return 0;
		}

		int max = 0;
		List<PeRoleInfo> roleUserList = this.getPeRoleInfoListByUserId(user.getId());
		for (PeRoleInfo r : roleUserList) {
			max = Math.max(max, r.getD_level() != null ? r.getD_level() : 0);
		}

		PeRoleUser p = new PeRoleUser();
		p.setUser_id(user_id);
		List<PeRoleUser> pList = this.getFacade().getPeRoleUserService().getPeRoleUserList(p);

		Boolean role_id_gt_30_and_200_300 = false;// 总部人员
		if (pList.size() > 0) {
			for (PeRoleUser temp : pList) {
				if (temp.getRole_id() < 30 || (temp.getRole_id() >= 200 && temp.getRole_id() < 300))
					role_id_gt_30_and_200_300 = true;
			}
		}

		if (role_id_gt_30_and_200_300) {// 若是总部人员
			max = 9;
		} else {// 若是分公司人员，且最大权限
			if (max == 9) {
				max = 8;
			}
		}

		return max;
	}

	/**
	 * @author Jiang,JiaYong
	 * @version 2013-08-14
	 * @desc 根据区域编码取全称
	 */
	public String getPIndexName(Long p_index, String conectString) {
		StringBuffer fullName = new StringBuffer("");
		if (null != p_index) {
			BaseProvinceListFour baseProvince = new BaseProvinceListFour();
			baseProvince.setP_index(p_index);
			List<BaseProvinceListFour> baseProvinceList = this.getFacade().getBaseProvinceListFourService()
					.getBaseProvinceListFourParentList(baseProvince);
			for (BaseProvinceListFour t : baseProvinceList)
				fullName.append(t.getP_name()).append(conectString);
		}
		return fullName.toString();
	}

	public String validateLoginErrLogs(String user_name, Integer max_login_count, Integer minutes) throws Exception {
		max_login_count = max_login_count == null ? 5 : max_login_count; // 默认为5次
		minutes = minutes == null ? 5 : minutes; // 默认为5分钟

		String start_time = DateFormatUtils.format(DateUtils.addMinutes(new Date(), minutes * (-1)),
				"yyyy-MM-dd HH:mm:ss");

		UserLoginErrLogs log = new UserLoginErrLogs();
		log.setUser_name(user_name);
		log.getMap().put("add_date_gt", start_time);
		Long count = this.getFacade().getUserLoginErrLogsService().getUserLoginErrLogsCount(log);

		return (max_login_count <= count) ? "从 " + start_time + " 至今，您已连续错误登录超过 " + max_login_count + " 次，请 " + minutes
				+ " 分钟后再登录。" : null;
	}

	public String createLoginErrLogs(String user_name, String pass_word, Integer max_login_count, Integer minutes)
			throws Exception {
		max_login_count = max_login_count == null ? 5 : max_login_count; // 默认为5次
		minutes = minutes == null ? 5 : minutes; // 默认为5分钟

		String valiMsg = validateLoginErrLogs(user_name, max_login_count, minutes);
		if (null != valiMsg) {
			return valiMsg;
		}

		UserLoginErrLogs _log = new UserLoginErrLogs();
		_log.setUser_name(user_name);
		_log.setPass_word(pass_word);
		this.getFacade().getUserLoginErrLogsService().createUserLoginErrLogs(_log);

		return null;
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @param 登录人所在的分公司列表
	 * @return
	 */
	protected List<KonkaDept> getDeptInfoListWithOutLimit2(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DynaBean dynaBean = (DynaBean) form;

		PeProdUser userInfo = (PeProdUser) request.getSession().getAttribute(Constants.USER_INFO);
		// PeRoleUser peRoleUser = new PeRoleUser();
		// peRoleUser.setUser_id(userInfo.getId());
		// peRoleUser =
		// this.getFacade().getPeRoleUserService().getPeRoleUser(peRoleUser);

		PeRoleUser _peRoleUser = new PeRoleUser();
		_peRoleUser.setUser_id(userInfo.getId());
		List<PeRoleUser> peRoleUserList = this.getFacade().getPeRoleUserService().getPeRoleUserList(_peRoleUser);

		boolean role_id_eq_10 = false;
		// boolean role_id_ge_20_le_29 = false;
		// boolean role_id_ge_30_le_39 = false;
		// boolean role_id_ge_40_le_49 = false;
		// boolean role_id_ge_40_le_60 = false;
		// boolean role_id_ge_50_le_59 = false;
		// boolean role_id_eq_60 = false;
		for (PeRoleUser peRoleUser : peRoleUserList) {
			if (peRoleUser.getRole_id() < 30L) {
				role_id_eq_10 = true;
			}
			// if (peRoleUser.getRole_id() >= 20L && peRoleUser.getRole_id() <=
			// 29L) {
			// role_id_ge_30_le_39 = true;
			// }
			// if (peRoleUser.getRole_id() >= 30L && peRoleUser.getRole_id() <=
			// 39L) {
			// role_id_ge_30_le_39 = true;
			// }
			// if (peRoleUser.getRole_id() >= 40L && peRoleUser.getRole_id() <=
			// 49L) {
			// role_id_ge_40_le_49 = true;
			// }
			// if (peRoleUser.getRole_id() >= 40L && peRoleUser.getRole_id() <=
			// 60L) {
			// role_id_ge_40_le_60 = true;
			// }
			// if (peRoleUser.getRole_id() >= 50L && peRoleUser.getRole_id() <=
			// 59L) {
			// role_id_ge_50_le_59 = true;
			// }
			// if (peRoleUser.getRole_id() == 60L) {
			// role_id_eq_60 = true;
			// }
		}

		Long fgs_dept_id = 0L;
		Long currentUserDeptId = userInfo.getDept_id();

		// if (role_id_ge_30_le_39) {// 分公司
		// fgs_dept_id = currentUserDeptId;
		// } else if (role_id_ge_40_le_49) {// 经营部
		// KonkaDept dept = new KonkaDept();
		// dept.setDept_id(currentUserDeptId);
		// dept = getFacade().getKonkaDeptService().getKonkaDept(dept);
		//
		// fgs_dept_id = dept.getPar_id();
		// } else if (role_id_ge_50_le_59) {// 办事处
		// KonkaDept dept = new KonkaDept();
		// dept.setDept_id(currentUserDeptId);
		// dept = getFacade().getKonkaDeptService().getKonkaDept(dept);
		//
		// KonkaDept dept_level3 =
		// this.getSuperiorForDeptType(currentUserDeptId, 3);
		// if (null != dept_level3 && null != dept_level3.getDept_id()) {
		// fgs_dept_id = dept_level3.getDept_id();
		// }
		// } else if (role_id_eq_60) {// 业务员
		// KonkaDept dept = new KonkaDept();
		// dept.setDept_id(currentUserDeptId);
		// dept = getFacade().getKonkaDeptService().getKonkaDept(dept);
		//
		// if (dept.getDept_type() == 3) {
		// fgs_dept_id = dept.getDept_id();
		// } else if (dept.getDept_type() == 4) {
		// KonkaDept _dept = this.getSuperiorForDeptType(currentUserDeptId, 3);
		// if (null != _dept && null != _dept.getDept_id()) {
		// fgs_dept_id = _dept.getDept_id();
		// }
		// } else if (dept.getDept_type() == 5) {
		// KonkaDept _dept = this.getSuperiorForDeptType(currentUserDeptId, 3);
		// if (null != _dept && null != _dept.getDept_id()) {
		// fgs_dept_id = _dept.getDept_id();
		// }
		// }
		// }
		KonkaDept dept_level3 = this.getSuperiorForDeptType(currentUserDeptId, 3);
		if (null != dept_level3 && null != dept_level3.getDept_id()) {
			fgs_dept_id = dept_level3.getDept_id();
		}
		dynaBean.set("fgs_dept_id", fgs_dept_id.toString());

		KonkaDept entity = new KonkaDept();
		entity.getMap().put("order_by_dept_name", true);
		List<KonkaDept> deptInfoList = new ArrayList<KonkaDept>();
		if (role_id_eq_10) {// 管理员
			entity.setPar_id(0L);
			entity.setDept_type(3);
			deptInfoList = getFacade().getKonkaDeptService().getKonkaDeptList(entity);
			// } else if (role_id_ge_20_le_29) {// 事业部
			// entity.setPar_id(currentUserDeptId);
			// deptInfoList =
			// getFacade().getKonkaDeptService().getKonkaDeptList(entity);
			// } else if (role_id_ge_30_le_39) {// 分公司
			// entity.setDept_id(currentUserDeptId);
			// deptInfoList =
			// getFacade().getKonkaDeptService().getKonkaDeptList(entity);
			// } else if (role_id_ge_40_le_60) {// 经营部
		} else {
			// 或者办事处
			// 都通过父子查询级dept_type
			// 查出所属分公司
			KonkaDept _dept = this.getSuperiorForDeptType(currentUserDeptId, 3);
			if (null != _dept && null != _dept.getDept_id()) {
				entity.setDept_id(_dept.getDept_id());
				deptInfoList = getFacade().getKonkaDeptService().getKonkaDeptList(entity);
			}
		}
		return deptInfoList;
	}

	public void clearLoginErrLogs(String user_name) throws Exception {
		UserLoginErrLogs _log = new UserLoginErrLogs();
		_log.setUser_name(user_name);
		this.getFacade().getUserLoginErrLogsService().removeUserLoginErrLogs(_log);
	}

	/**
	 * @param r3_code
	 * @param String
	 * @return
	 */
	public Integer getR3IsSales(String r3_code) {
		KonkaR3Shop k = new KonkaR3Shop();
		k.setR3_code(r3_code);
		k.setIs_del(0L);
		List<KonkaR3Shop> kList = getFacade().getKonkaR3ShopService().getKonkaR3ShopList(k);

		if (kList.size() > 0) {
			return kList.get(0).getIs_minus_sales();
		} else {
			return null;
		}
	}

	/**
	 * @author Liu,ZhiXiang
	 * @version 2014-01-14
	 * @desc 根据R3_CODE,STORE_ID,MD_NAME获取库存量，金额
	 */
	protected JsStocks getJsStocks(String r3_code, String md_name, Long store_id) {

		JsStocks jsStocks = new JsStocks();
		jsStocks.setMd_name(md_name);
		jsStocks.setStore_id(Long.valueOf(store_id));
		jsStocks.getMap().put("r3_code", r3_code);
		List<JsStocks> list = getFacade().getJsStocksService().getJsStockForTotal(jsStocks);
		if (null != list && list.size() > 0) {
			jsStocks = list.get(0);
		}
		return jsStocks;
	}

	/**
	 * @author Liu,ZhiXiang
	 * @version 2014-01-16
	 * @desc 根据R3_CODE,STORE_ID,MD_NAME实时库存
	 */
	protected KonkaR3Shop getKonkaR3ShopStockByR3Code(String r3_code, String md_name, Long store_id) {

		KonkaR3Shop krs = new KonkaR3Shop();
		krs.setR3_code(r3_code);
		if (null != store_id) {
			krs.getMap().put("j_base_store_id", store_id);
		}
		krs.getMap().put("j_base_goods_name", md_name);
		List<KonkaR3Shop> entityList = getFacade().getKonkaR3ShopService().getKonkaR3ShopStockByR3CodeList(krs);
		if (null != entityList && entityList.size() > 0) {
			krs = entityList.get(0);
		}
		// map.initcount 期初库存
		// map.incount 进货数量
		// map.outcount 销售数量
		return krs;
	}

	/**
	 * @author Hu,Hao
	 * @version 2014-01-23
	 * @desc 根据年月取R3报表查询时间段
	 */
	protected KonkaSpList getR3Date(String year, String month) {

		KonkaSpList k = new KonkaSpList();
		k.setYear(Integer.valueOf(year));
		k.setMonth(Integer.valueOf(month));
		k.setIs_del(0);
		List<KonkaSpList> kList = getFacade().getKonkaSpListService().getKonkaSpListList(k);
		if (kList.size() > 0) {
			k = kList.get(0);
		} else {
			k = null;
		}
		return k;
	}

	/**
	 * @author Hu,Hao
	 * @version 2014-01-23
	 * @desc 根据年月取R3报表查询时间段实时库存
	 */
	protected Long getKhStocksOld(String r3_code, String md_name, Long store_id) {

		Long stocks = 0L;

		if (StringUtils.isBlank(md_name) || StringUtils.isBlank(r3_code)) {
			return stocks;
		}

		// 客户信息
		KonkaR3Shop kShop = new KonkaR3Shop();
		kShop.setR3_code(r3_code);
		List<KonkaR3Shop> kShopList = getFacade().getKonkaR3ShopService().getKonkaR3ShopList(kShop);

		if (kShopList.size() == 1) {
			kShop = kShopList.get(0);
		} else {
			logger.info("未找到R3客户");
			return stocks;
		}

		// 产品初始化信息
		JBaseGoods jGoods = new JBaseGoods();
		jGoods.setC_id(kShop.getId());
		jGoods.setGoods_name(md_name);
		List<JBaseGoods> jGoodsList = getFacade().getJBaseGoodsService().getJBaseGoodsList(jGoods);
		if (jGoodsList.size() == 1) {
			jGoods = jGoodsList.get(0);
		} else {
			logger.info("未知道商品");
			return stocks;
		}

		// 总库
		JBaseStore jStore = new JBaseStore();
		jStore.setC_id(kShop.getId());
		jStore.setIs_del(0);
		jStore.setStore_name("总库");
		List<JBaseStore> jStoreList = getFacade().getJBaseStoreService().getJBaseStoreList(jStore);
		if (jStoreList.size() >= 1) {
			jStore = jStoreList.get(0);
		} else {
			logger.info("未知道仓库");
			return stocks;
		}

		Date new_date = null;

		if (null != store_id && jStore.getStore_id().equals(store_id)) {

			// 取最后盘存时间
			JStocksVerify jstocks = new JStocksVerify();
			jstocks.getMap().put("max_date_id", true);
			jstocks.getMap().put("store_id_value", store_id);
			jstocks.getMap().put("goods_id_value", jGoods.getGoods_id());
			jstocks = getFacade().getJStocksVerifyService().getJStocksVerify(jstocks);

			if (jstocks == null) {
				return stocks;
			} else {
				logger.info("未找到库存");
				stocks = jstocks.getVer_stocks();
			}

		} else {

			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			// 取最后盘存时间
			JStocksVerify jstocks = new JStocksVerify();
			jstocks.getMap().put("max_date_id", true);
			jstocks.getMap().put("store_id_value", jStore.getStore_id());
			jstocks.getMap().put("goods_id_value", jGoods.getGoods_id());
			jstocks.setStore_id(jStore.getStore_id());
			jstocks.setGoods_id(jGoods.getGoods_id());
			jstocks = getFacade().getJStocksVerifyService().getJStocksVerify(jstocks);

			if (jstocks == null) {
				new_date = jGoods.getAdd_date();
				logger.info("初始化时间" + fmt.format(new_date));

			} else {
				new_date = jstocks.getOpr_date();
				logger.info("未找到库存" + fmt.format(new_date));

			}

			JBaseGoods entity = new JBaseGoods();
			entity.getMap().put("r3_code", r3_code);
			entity.getMap().put("r3_id", kShop.getId());
			entity.getMap().put("md_name", md_name);
			entity.getMap().put("goods_id", jGoods.getGoods_id());
			entity.getMap().put("s_date", fmt.format(new_date));

			entity = getFacade().getJBaseGoodsService().getJBaseGoodsForNum(entity);

			if (jstocks == null) {
				stocks = Long.valueOf(entity.getMap().get("num").toString()) + jGoods.getInit_count();
			} else {
				stocks = Long.valueOf(entity.getMap().get("num").toString()) + jstocks.getVer_stocks();
			}
		}

		return stocks;
	}

	/**
	 * @author Hu,Hao
	 * @version 2014-01-23
	 * @desc 根据年月取R3报表查询时间段盘存和销售和进货数量
	 * @desc 返回JBaseGoods但是里面只有三个值，分别是盘存stocks、进货数量 come_num、销售数量out_num,都用map获得
	 */
	protected JBaseGoods getKhStocksForInitOld(String r3_code, String md_name, Long store_id) {

		// Long stocks = 0L;
		JBaseGoods jbg = new JBaseGoods();
		if (StringUtils.isBlank(md_name) || StringUtils.isBlank(r3_code)) {
			jbg.getMap().put("stocks", "0");
			return jbg;
		}

		// 客户信息
		KonkaR3Shop kShop = new KonkaR3Shop();
		kShop.setR3_code(r3_code);
		List<KonkaR3Shop> kShopList = getFacade().getKonkaR3ShopService().getKonkaR3ShopList(kShop);

		if (kShopList.size() == 1) {
			kShop = kShopList.get(0);
		} else {
			logger.info("未找到R3客户");
			jbg.getMap().put("stocks", "0");
			return jbg;
		}

		// 产品初始化信息
		JBaseGoods jGoods = new JBaseGoods();
		jGoods.setC_id(kShop.getId());
		jGoods.setGoods_name(md_name);
		List<JBaseGoods> jGoodsList = getFacade().getJBaseGoodsService().getJBaseGoodsList(jGoods);
		if (jGoodsList.size() == 1) {
			jGoods = jGoodsList.get(0);
		} else {
			logger.info("未知道商品");
			jbg.getMap().put("stocks", "0");
			return jbg;
		}

		// 总库
		JBaseStore jStore = new JBaseStore();
		jStore.setC_id(kShop.getId());
		jStore.setIs_del(0);
		jStore.setStore_name("总库");
		List<JBaseStore> jStoreList = getFacade().getJBaseStoreService().getJBaseStoreList(jStore);
		if (jStoreList.size() >= 1) {
			jStore = jStoreList.get(0);
		} else {
			logger.info("未知道仓库");
			jbg.getMap().put("stocks", "0");
			return jbg;
		}

		Date new_date = null;

		if (null != store_id && jStore.getStore_id().equals(store_id)) {// 不是总库

			// 取最后盘存时间
			JStocksVerify jstocks = new JStocksVerify();
			jstocks.getMap().put("max_date_id", true);
			jstocks.getMap().put("store_id_value", store_id);
			jstocks.getMap().put("goods_id_value", jGoods.getGoods_id());
			jstocks = getFacade().getJStocksVerifyService().getJStocksVerify(jstocks);

			if (jstocks == null) {
				jbg.getMap().put("stocks", "0");
				return jbg;
			} else {
				logger.info("未找到库存");
				jbg.getMap().put("stocks", jstocks.getVer_stocks());
				return jbg;
			}

		} else {// 是总库

			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			// 取最后盘存时间
			JStocksVerify jstocks = new JStocksVerify();
			jstocks.getMap().put("max_date_id", true);
			jstocks.getMap().put("store_id_value", jStore.getStore_id());
			jstocks.getMap().put("goods_id_value", jGoods.getGoods_id());
			jstocks.setStore_id(jStore.getStore_id());
			jstocks.setGoods_id(jGoods.getGoods_id());
			jstocks = getFacade().getJStocksVerifyService().getJStocksVerify(jstocks);

			if (jstocks == null) {
				new_date = jGoods.getAdd_date();
				logger.info("初始化时间" + fmt.format(new_date));

			} else {
				new_date = jstocks.getOpr_date();
				logger.info("未找到库存" + fmt.format(new_date));

			}

			JBaseGoods entity = new JBaseGoods();
			entity.getMap().put("r3_code", r3_code);
			entity.getMap().put("r3_id", kShop.getId());
			entity.getMap().put("md_name", md_name);
			entity.getMap().put("goods_id", jGoods.getGoods_id());
			entity.getMap().put("s_date", fmt.format(new_date));

			entity = getFacade().getJBaseGoodsService().getJBaseGoodsForComeOutNum(entity);
			if (entity != null) {
				jbg.getMap().put("come_num", entity.getMap().get("come_num").toString());
				jbg.getMap().put("out_num", entity.getMap().get("out_num").toString());
			}

			if (jstocks == null) {
				jbg.getMap().put("stocks", jGoods.getInit_count());
			} else {
				jbg.getMap().put("stocks", jstocks.getVer_stocks());
			}
		}

		return jbg;
	}

	/**
	 * 
	 * @param r3_code
	 *            客户R3编码
	 * @param md_name
	 *            机型名称
	 * @param store_id
	 *            仓库ID
	 * @param is_dft_buy_store
	 *            是否是默认进货仓库 默认是false，不是默认进货仓库
	 * @return 库存 注：暂时没有用到
	 */
	protected Long getKhStocksNew1(String r3_code, String md_name, Long store_id) {

		Long stocks = 0L;

		if (StringUtils.isBlank(md_name) || StringUtils.isBlank(r3_code)) {
			return stocks;
		}

		// 客户信息
		KonkaR3Shop kShop = new KonkaR3Shop();
		kShop.setR3_code(r3_code);
		List<KonkaR3Shop> kShopList = getFacade().getKonkaR3ShopService().getKonkaR3ShopList(kShop);

		if (kShopList.size() == 1) {
			kShop = kShopList.get(0);
		} else {
			logger.info("未找到R3客户");
			return stocks;
		}

		// 产品初始化信息
		JBaseGoods jGoods = new JBaseGoods();
		jGoods.setC_id(kShop.getId());
		jGoods.setGoods_name(md_name);
		List<JBaseGoods> jGoodsList = getFacade().getJBaseGoodsService().getJBaseGoodsList(jGoods);
		if (jGoodsList.size() == 1) {
			jGoods = jGoodsList.get(0);
		} else {
			logger.info("未知道商品");
			return stocks;
		}

		// 总库
		// JBaseStore jStore = new JBaseStore();
		// jStore.setC_id(kShop.getId());
		// jStore.setIs_del(0);
		// jStore.setStore_name("总库");
		// List<JBaseStore> jStoreList =
		// getFacade().getJBaseStoreService().getJBaseStoreList(jStore);
		// if (jStoreList.size() >= 1) {
		// jStore = jStoreList.get(0);
		// } else {
		// logger.info("未知道仓库");
		// return stocks;
		// }

		Date new_date = null;

		if (null != store_id) {

			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			// 取最后盘存时间
			JStocksVerify jstocks = new JStocksVerify();
			jstocks.getMap().put("max_date_id", true);
			jstocks.getMap().put("store_id_value", store_id);
			jstocks.getMap().put("goods_id_value", jGoods.getGoods_id());
			jstocks.setStore_id(store_id);
			jstocks.setGoods_id(jGoods.getGoods_id());
			jstocks = getFacade().getJStocksVerifyService().getJStocksVerify(jstocks);

			if (jstocks == null) {
				new_date = jGoods.getAdd_date();
				logger.info("初始化时间" + fmt.format(new_date));

			} else {
				new_date = jstocks.getOpr_date();
				logger.info("未找到库存" + fmt.format(new_date));

			}

			JBaseGoods entity = new JBaseGoods();
			// entity.getMap().put("r3_code", r3_code);
			entity.getMap().put("store_id", store_id);
			entity.getMap().put("r3_id", kShop.getId());
			entity.getMap().put("md_name", md_name);
			entity.getMap().put("goods_id", jGoods.getGoods_id());
			entity.getMap().put("s_date", fmt.format(new_date));

			entity = getFacade().getJBaseGoodsService().getJBaseGoodsForNum(entity);
			if (jstocks == null) {
				JBaseGoodsInitStock initStock = new JBaseGoodsInitStock();
				initStock.setC_id(kShop.getId());
				initStock.setGoods_id(jGoods.getGoods_id());
				initStock.setStore_id(store_id);
				initStock.setInit_state(0);
				initStock = getFacade().getJBaseGoodsInitStockService().getJBaseGoodsInitStock(initStock);
				if (initStock != null) {
					stocks = Long.valueOf(entity.getMap().get("num").toString()) + initStock.getInit_count();
				} else {
					stocks = Long.valueOf(entity.getMap().get("num").toString());
				}
			} else {
				stocks = Long.valueOf(entity.getMap().get("num").toString()) + jstocks.getVer_stocks();
			}
		}

		return stocks;
	}

	/**
	 * @author Xiao,GuoJian
	 * @version 2014-07-08
	 * @desc 根据R3_CODE,md_name,store_id取库存
	 * @desc 返回库存=init_num+come_num-out_num
	 */
	protected Long getKhStocks(String r3_code, String md_name, Long store_id) {
		Long result = 0L;
		if (StringUtils.isEmpty(r3_code) || StringUtils.isEmpty(md_name)) {
			return 0L;
		}
		KonkaR3Shop kShop = new KonkaR3Shop();
		kShop.setR3_code(r3_code);
		List<KonkaR3Shop> kShopList = getFacade().getKonkaR3ShopService().getKonkaR3ShopList(kShop);
		if (kShopList != null && kShopList.size() > 0) {
			kShop = kShopList.get(0);
		} else {
			logger.info("未找到R3客户");
			return result;
		}
		// 产品初始化信息
		JBaseGoods jGoods = new JBaseGoods();
		jGoods.setC_id(kShop.getId());
		jGoods.setGoods_name(md_name);
		List<JBaseGoods> jGoodsList = getFacade().getJBaseGoodsService().getJBaseGoodsList(jGoods);
		if (jGoodsList.size() > 0) {
			jGoods = jGoodsList.get(0);
		} else {
			logger.info("未知道商品");
			return result;
		}
		JBaseGoods jbg = new JBaseGoods();
		if (null != store_id) {// 送达方
			jbg.getMap().put("store_id", store_id);
		} else {
			jbg.getMap().put("r3_code", r3_code);
		}
		jbg.getMap().put("goods_id", jGoods.getGoods_id());
		jbg.getMap().put("md_name", jGoods.getGoods_name());
		jbg.getMap().put("r3_id", kShop.getId());
		
		
		//jbg = getFacade().getJBaseGoodsService().getJBaseGoodsForComeOutNumNew(jbg);
		
		//原来的查库存
		//jbg = jBaseGoodsDao.selectJBaseGoodsForComeOutNumNew(jbg);
		
		//现在的查库存  wangkunlin 2015-01-23修改
		List<JBaseGoods> jBaseList=	getFacade().getJBaseGoodsService().getJBaseGoodsForComeOutNumWithMoney(jbg);
		
		
		if(null!=jBaseList && jBaseList.size()==1){
			jbg=jBaseList.get(0);
		
		if (jbg != null) {
			long come_num = Long.valueOf(jbg.getMap().get("come_num").toString());
			long out_num = Long.valueOf(jbg.getMap().get("out_num").toString());
			long init_num = Long.valueOf(jbg.getMap().get("init_num").toString());
			result = init_num + come_num - out_num;
		}
		}
		return result;
	}

	/**
	 * @author Xiao,GuoJian
	 * @version 2014-08-26
	 * @desc 根据R3_CODE,md_name,store_id取库存,返回库存=init_num+come_num-out_num
	 */
	protected long getKhStocksWithMoney(String r3_code, String md_name, Long store_id) {
		long stocks = 0l;
		if (StringUtils.isEmpty(r3_code) || StringUtils.isEmpty(md_name)) {
			return stocks;
		}
		KonkaR3Shop kShop = new KonkaR3Shop();
		kShop.setR3_code(r3_code);
		List<KonkaR3Shop> kShopList = getFacade().getKonkaR3ShopService().getKonkaR3ShopList(kShop);
		if (kShopList != null && kShopList.size() > 0) {
			kShop = kShopList.get(0);
		} else {
			logger.info("未找到R3客户");
			return stocks;
		}
		// 商品数据信息
		JBaseGoods jGoods = new JBaseGoods();
		jGoods.setC_id(kShop.getId());
		jGoods.setGoods_name(md_name);
		List<JBaseGoods> jGoodsList = getFacade().getJBaseGoodsService().getJBaseGoodsList(jGoods);
		if (jGoodsList != null && jGoodsList.size() > 0) {
			jGoods = jGoodsList.get(0);
		} else {
			logger.info("未知道商品");
			return stocks;
		}
		JBaseGoodsInitStock jstock = new JBaseGoodsInitStock();
		jstock.setGoods_id(jGoods.getGoods_id());
		if (store_id != null) {
			jstock.setStore_id(store_id);
		}
		jstock.setC_id(kShop.getId());
		List<JBaseGoodsInitStock> existstockList = this.facade.getJBaseGoodsInitStockService()
				.getJBaseGoodsInitStockList(jstock);
		if (null != existstockList && existstockList.size() > 0) {// 之前有初始化库存，直接查询库存
			JBaseGoods jBaseGoods = new JBaseGoods();
			if (store_id != null) {
				jBaseGoods.getMap().put("store_id", store_id);
			} else {
				jBaseGoods.getMap().put("r3_code", r3_code);
			}
			if (jGoods.getGoods_id() != null) {
				jBaseGoods.getMap().put("goods_id", jGoods.getGoods_id());
			}
			jBaseGoods.getMap().put("r3_id", kShop.getId());
			List<JBaseGoods> entityList = getFacade().getJBaseGoodsService().getJBaseGoodsForComeOutNumWithMoney(
					jBaseGoods);
			if (entityList != null && entityList.size() > 0) {
				jBaseGoods = entityList.get(0);
				if (jBaseGoods != null) {
					long init_num = Long.valueOf(jBaseGoods.getMap().get("init_num").toString());
					long come_num = Long.valueOf(jBaseGoods.getMap().get("come_num").toString());
					long out_num = Long.valueOf(jBaseGoods.getMap().get("out_num").toString());
					long other_num = Long.valueOf(jBaseGoods.getMap().get("other_num").toString());
					stocks = init_num + come_num - out_num+other_num;
				}
			}
		}
		return stocks;
	}

	/**
	 * @author Xiao,GuoJian
	 * @version 2014-08-26
	 * @desc 根据c_id,goods_id,store_id计算销售成本,销售成本=销售前金额/销售前数量*本次销售数量
	 */
	protected BigDecimal getSaleCost(Long c_id, Long goods_id, Long store_id, BigDecimal num) {
		BigDecimal sale_cost = new BigDecimal("0");
		if (null == c_id || goods_id == null || store_id == null || null == num
				|| num.compareTo(new BigDecimal(0)) != 0) {
			return sale_cost;
		}
		JBaseGoods jBaseGoods = new JBaseGoods();
		jBaseGoods.getMap().put("store_id", store_id);
		jBaseGoods.getMap().put("goods_id", goods_id);
		jBaseGoods.getMap().put("r3_id", c_id);
		List<JBaseGoods> entityList = getFacade().getJBaseGoodsService()
				.getJBaseGoodsForComeOutNumWithMoney(jBaseGoods);
		if (entityList != null && entityList.size() > 0) {
			jBaseGoods = entityList.get(0);
			if (jBaseGoods != null) {
				BigDecimal init_num = new BigDecimal(jBaseGoods.getMap().get("init_num").toString());
				BigDecimal come_num = new BigDecimal(jBaseGoods.getMap().get("come_num").toString());
				BigDecimal out_num = new BigDecimal(jBaseGoods.getMap().get("out_num").toString());
				BigDecimal other_num = new BigDecimal(jBaseGoods.getMap().get("other_num").toString());
				BigDecimal stocks = init_num.add(come_num).subtract(out_num).add(other_num);// 获取实时库存
				BigDecimal init_money = new BigDecimal(jBaseGoods.getMap().get("init_money").toString());
				BigDecimal come_money = new BigDecimal(jBaseGoods.getMap().get("come_money").toString());
				BigDecimal out_sale_cost = new BigDecimal(jBaseGoods.getMap().get("sale_cost").toString());
				BigDecimal money = init_money.add(come_money).subtract(out_sale_cost);// 获取实时金额
				if (null != stocks && stocks.compareTo(new BigDecimal(0)) != 0) {// 若库存不为0，则计算销售成本，销售成本=销售前金额/销售前数量*本次销售数量
					sale_cost = money.multiply(num).divide(stocks, 4, BigDecimal.ROUND_HALF_DOWN);
				}
			}
		}
		return sale_cost;
	}

	/**
	 * @author Xiao,GuoJian
	 * @version 2014-08-26
	 * @desc 根据r3_code,goods_name,store_id计算销售成本,销售成本=销售前金额/销售前数量*本次销售数量
	 */
	protected BigDecimal getSaleCost(String r3_code, String goods_name, Long store_id, BigDecimal num) {
		BigDecimal sale_cost = new BigDecimal("0");
		if (StringUtils.isEmpty(r3_code) || StringUtils.isEmpty(goods_name) || store_id == null || null == num
				|| num.compareTo(new BigDecimal(0)) != 0) {
			return sale_cost;
		}

		KonkaR3Shop konkar3shop = new KonkaR3Shop();
		konkar3shop.setR3_code(r3_code);
		konkar3shop.setIs_del(0L);
		konkar3shop = getFacade().getKonkaR3ShopService().getKonkaR3Shop(konkar3shop);
		if (null != konkar3shop) {
			if (null != konkar3shop.getId()) {
				// 根据goods_name查询客户端对于的goods_id
				JBaseGoods jbGoods = new JBaseGoods();
				jbGoods.setC_id(konkar3shop.getId());
				jbGoods.setGoods_name(goods_name);
				jbGoods.setGoods_stutes(0);
				jbGoods.getRow().setCount(1);
				List<JBaseGoods> jList = getFacade().getJBaseGoodsService().getJBaseGoodsList(jbGoods);
				if (null != jList && jList.size() > 0) {
					if (null != jList.get(0)) {
						JBaseGoods jBaseGoods = new JBaseGoods();
						jBaseGoods.getMap().put("store_id", store_id);
						jBaseGoods.getMap().put("goods_id", jList.get(0).getGoods_id());
						jBaseGoods.getMap().put("r3_id", konkar3shop.getId());
						List<JBaseGoods> entityList = getFacade().getJBaseGoodsService()
								.getJBaseGoodsForComeOutNumWithMoney(jBaseGoods);
						if (entityList != null && entityList.size() > 0) {
							jBaseGoods = entityList.get(0);
							if (jBaseGoods != null) {
								BigDecimal init_num = new BigDecimal(jBaseGoods.getMap().get("init_num").toString());
								BigDecimal come_num = new BigDecimal(jBaseGoods.getMap().get("come_num").toString());
								BigDecimal out_num = new BigDecimal(jBaseGoods.getMap().get("out_num").toString());
								BigDecimal stocks = init_num.add(come_num).subtract(out_num);// 获取实时库存
								BigDecimal init_money = new BigDecimal(jBaseGoods.getMap().get("init_money").toString());
								BigDecimal come_money = new BigDecimal(jBaseGoods.getMap().get("come_money").toString());
								BigDecimal out_sale_cost = new BigDecimal(jBaseGoods.getMap().get("sale_cost")
										.toString());
								BigDecimal money = init_money.add(come_money).subtract(out_sale_cost);// 获取实时金额
								if (null != stocks && stocks.compareTo(new BigDecimal(0)) != 0) {// 若库存不为0，则计算销售成本，销售成本=销售前金额/销售前数量*本次销售数量
									sale_cost = money.multiply(num).divide(stocks, 4, BigDecimal.ROUND_HALF_DOWN);
								}
							}
						}
					}
				}
			}
		}

		return sale_cost;
	}

	/**
	 * @author Pan,Gang
	 * @version 2014-04-17
	 * @desc 发送短信
	 */
	public Boolean getSendMessageResult(String mobile, String content) throws Exception {
		String paygateway = YixunConfig.paygateway;
		String CorpID = YixunConfig.CorpID;
		String Key = YixunConfig.key;
		String msg = URLEncoder.encode(content, "GBK");

		StringBuffer sb = new StringBuffer();
		sb.append(paygateway);
		sb.append("CorpID=").append(CorpID);
		sb.append("&Pwd=").append(Key);
		sb.append("&Mobile=").append(mobile);
		sb.append("&Content=").append(msg);
		String redirectUrl = sb.toString();

		logger.info("____________________________url1 = " + redirectUrl);
		String result = com.ebiz.mmt.web.util.yixun.GetApiUtils.getApiWithUrl(redirectUrl);
		// String result = "0";
		if ("0".equals(result)) {
			logger.info("____________________result.toString = 发送成功");
		} else if ("-1".equals(result)) {
			logger.info("____________________result.toString = 帐号未注册");
		} else if ("-2".equals(result)) {
			logger.info("____________________result.toString = 其他错误");
		} else if ("-3".equals(result)) {
			logger.info("____________________result.toString = 密码错误");
		} else if ("-4".equals(result)) {
			logger.info("____________________result.toString = 手机号格式不对");
		} else if ("-5".equals(result)) {
			logger.info("____________________result.toString = 余额不足");
		} else if ("-6".equals(result)) {
			logger.info("____________________result.toString = 定时发送时间不是有效的时间格式");
		}

		if ("0".equals(result)) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * @author Pan,Gang
	 * @version 2014-04-17
	 * @desc 群发短信，多个mobile用逗号分隔[1111,2222,333]，mobile最多支持600个
	 */
	public Boolean getSendMessageResultByBatch(String mobile, String content) throws Exception {
		String paygateway = YixunConfig.smsgateway_forBatch;
		String CorpID = YixunConfig.CorpID;
		String Key = YixunConfig.key;
		String msg = URLEncoder.encode(content, "GBK");

		StringBuffer sb = new StringBuffer();
		sb.append(paygateway);
		sb.append("CorpID=").append(CorpID);
		sb.append("&Pwd=").append(Key);
		sb.append("&Mobile=").append(mobile);
		sb.append("&Content=").append(msg);
		String redirectUrl = sb.toString();

		logger.info("____________________________url1 = " + redirectUrl);
		String result = com.ebiz.mmt.web.util.yixun.GetApiUtils.getApiWithUrl(redirectUrl);
		// String result = "0";
		if ("0".equals(result)) {
			logger.info("____________________result.toString = 发送成功");
		} else if ("-1".equals(result)) {
			logger.info("____________________result.toString = 帐号未注册");
		} else if ("-2".equals(result)) {
			logger.info("____________________result.toString = 其他错误");
		} else if ("-3".equals(result)) {
			logger.info("____________________result.toString = 密码错误");
		} else if ("-4".equals(result)) {
			logger.info("____________________result.toString = 手机号格式不对");
		} else if ("-5".equals(result)) {
			logger.info("____________________result.toString = 余额不足");
		} else if ("-6".equals(result)) {
			logger.info("____________________result.toString = 定时发送时间不是有效的时间格式");
		}

		if ("0".equals(result)) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * @author Pan,Gang
	 * @version 2014-04-17
	 * @desc epp平台取R3东莞凤岗仓库和合肥仓库某型号仓库总和
	 */
	public Double getStockCount(String pd_sn) {
		// 东莞分公司
		// 工厂(需要和分公司绑定) not null
		String zwerks = "L00E";
		// 库位 not null
		String zlgort = "P046";
		// 仓位
		String zlgpla = "F146ZT";
		// 物料
		List<ZLEBIN> list = new ArrayList<ZLEBIN>();

		double dgCount = 0.00;

		if (StringUtils.isNotBlank(zwerks) && StringUtils.isNotBlank(zlgort)) {
			// list = getFacade().getR3WebInterfaceService().getZles20(zwerks,
			// zlgort, zlgpla, pd_sn);
			ReturnInfo<ZLEBIN> info = null;
			info = getFacade().getR3WebInterfaceService().getZles20(zwerks, zlgort, zlgpla, pd_sn);
			list = info.getDataResult();
		}
		if (null != list && list.size() > 0) {
			ZLEBIN zlb = list.get(0);
			dgCount = dgCount + zlb.getVERME();
		}

		// 合肥分公司（滁州发货）
		// 工厂(需要和分公司绑定) not null
		String zwerks1 = "L00B";
		// 仓位 仓库地点 not null
		String zlgort1 = "F222";
		// 物料
		List<StockCheckResult> entitylist = new ArrayList<StockCheckResult>();
		if (StringUtils.isNotBlank(zwerks1) && StringUtils.isNotBlank(zlgort1)) {
			// entitylist = new Call_Get_Werks_Lgort2().doCall(zwerks1, zlgort1,
			// pd_sn);
			ReturnInfo<StockCheckResult> info = null;
			info = getFacade().getR3WebInterfaceService().getFGSKC(zwerks1, zlgort1, pd_sn);
			entitylist = info.getDataResult();
		}
		double hfCount = 0.00;
		if (null != entitylist && entitylist.size() > 0) {
			StockCheckResult scr = entitylist.get(0);
			hfCount = hfCount + scr.getLamount().doubleValue();
		}

		// 武汉分公司（武汉9001仓库发货）
		String zwerks2 = "L00D";// 工厂(需要和分公司绑定) not null
		String zlgort2 = "9001";// 仓位 仓库地点 not null
		double whCount = 0.00;
		List<StockCheckResult> entitylist2 = new ArrayList<StockCheckResult>();
		if (StringUtils.isNotBlank(zwerks2) && StringUtils.isNotBlank(zlgort2)) {
			// entitylist2 = facade.getR3WebInterfaceService().getFGSKC(zwerks2,
			// zlgort2, pd_sn);

			ReturnInfo<StockCheckResult> info = null;
			info = getFacade().getR3WebInterfaceService().getFGSKC(zwerks2, zlgort2, pd_sn);
			entitylist2 = info.getDataResult();
		}
		if (null != entitylist2 && entitylist2.size() > 0) {
			StockCheckResult scr = entitylist2.get(0);
			whCount = whCount + scr.getLamount().doubleValue();
		}

		// 武汉分公司（武汉孝感仓F113发货）
		String zwerks3 = "L00D";// 工厂(需要和分公司绑定) not null
		String zlgort3 = "F113";// 仓位 仓库地点 not null
		double whCount2 = 0.00; // 物料
		List<StockCheckResult> entitylist3 = new ArrayList<StockCheckResult>();
		if (StringUtils.isNotBlank(zwerks3) && StringUtils.isNotBlank(zlgort3)) {
			// entitylist3 = facade.getR3WebInterfaceService().getFGSKC(zwerks3,
			// zlgort3, pd_sn);

			ReturnInfo<StockCheckResult> info = null;
			info = getFacade().getR3WebInterfaceService().getFGSKC(zwerks3, zlgort3, pd_sn);
			entitylist3 = info.getDataResult();

		}
		if (null != entitylist3 && entitylist3.size() > 0) {
			StockCheckResult scr = entitylist3.get(0);
			whCount2 = whCount2 + scr.getLamount().doubleValue();
		}

		// 哈尔滨分公司（哈尔滨9034发货）
		String zwerks4 = "L00C";// 工厂(需要和分公司绑定) not null
		String zlgort4 = "9034";// 仓位 仓库地点 not null
		double hebCount = 0.00; // 物料
		List<StockCheckResult> entitylist4 = new ArrayList<StockCheckResult>();
		if (StringUtils.isNotBlank(zwerks4) && StringUtils.isNotBlank(zlgort4)) {
			// entitylist4 = facade.getR3WebInterfaceService().getFGSKC(zwerks4,
			// zlgort4, pd_sn);

			ReturnInfo<StockCheckResult> info = null;
			info = getFacade().getR3WebInterfaceService().getFGSKC(zwerks4, zlgort4, pd_sn);
			entitylist4 = info.getDataResult();
		}
		if (null != entitylist4 && entitylist4.size() > 0) {
			StockCheckResult scr = entitylist4.get(0);
			hebCount = hebCount + scr.getLamount().doubleValue();
		}

		return hfCount + dgCount + whCount + whCount2 + hebCount;
	}

	public static Integer getStockCount(String ctx, String pd_sn) {

		StringBuffer sb = new StringBuffer();
		Integer z = new Integer(0);
		try {
			java.net.URL url = new java.net.URL(ctx + "/login.shtml?method=ajaxGetR3StockHtml&pd_sn=" + pd_sn);
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String line;
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
			z = new Integer(sb.toString());
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return z;
	}

	/**
	 * @author TUDP
	 * @version 2014-04-17
	 * @desc epp平台取R3东莞凤岗仓库和合肥仓库某型号html text
	 */
	public ActionForward ajaxGetR3StockHtml(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String pd_sn = (String) dynaBean.get("pd_sn");
		response.getWriter().print("" + this.getStockCount(pd_sn).intValue());
		return null;
	}

	/**
	 * @author Pan,Gang
	 * @version 2014-04-17
	 * @desc epp平台取R3东莞凤岗仓库和合肥仓库某型号json
	 */
	public ActionForward ajaxGetStockCount(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String pd_sn = (String) dynaBean.get("pd_sn");
		// 东莞分公司
		// 工厂(需要和分公司绑定) not null
		String zwerks = "L00E";
		// 库位 not null
		String zlgort = "P046";
		// 仓位
		String zlgpla = "F146ZT";
		// 物料
		List<ZLEBIN> list = new ArrayList<ZLEBIN>();
		double dgCount = 0.00;

		if (StringUtils.isNotBlank(zwerks) && StringUtils.isNotBlank(zlgort)) {
			// list = getFacade().getR3WebInterfaceService().getZles20(zwerks,
			// zlgort, zlgpla, pd_sn);

			ReturnInfo<ZLEBIN> info = null;
			info = getFacade().getR3WebInterfaceService().getZles20(zwerks, zlgort, zlgpla, pd_sn);
			list = info.getDataResult();
		}
		if (null != list && list.size() > 0) {
			ZLEBIN zlb = list.get(0);
			dgCount = dgCount + zlb.getVERME();
		}

		// 合肥分公司（滁州发货）
		// 工厂(需要和分公司绑定) not null
		String zwerks1 = "L00B";
		// 仓位 仓库地点 not null
		String zlgort1 = "F222";
		// 物料
		List<StockCheckResult> entitylist = new ArrayList<StockCheckResult>();
		if (StringUtils.isNotBlank(zwerks) && StringUtils.isNotBlank(zlgort)) {
			// entitylist = facade.getR3WebInterfaceService().getFGSKC(zwerks1,
			// zlgort1, pd_sn);
			// entitylist = facade.getR3WebInterfaceService().getFGSKC(zwerks1,
			// zlgort1, pd_sn);

			ReturnInfo<StockCheckResult> info = getFacade().getR3WebInterfaceService()
					.getFGSKC(zwerks1, zlgort1, pd_sn);

			entitylist = info.getDataResult();
		}
		double hfCount = 0.00;
		if (null != entitylist && entitylist.size() > 0) {
			StockCheckResult scr = entitylist.get(0);
			hfCount = hfCount + scr.getLamount().doubleValue();
		}

		// 构建数据结构
		List<HashMap> listJson = new ArrayList<HashMap>();
		HashMap map1 = new HashMap();
		map1.put("stock_name", "东莞分公司");
		map1.put("stock_count", 111);
		listJson.add(map1);
		HashMap map2 = new HashMap();
		map2.put("stock_name", "合肥分公司");
		map2.put("stock_count", 2222);
		listJson.add(map2);

		logger.info("----ajaxGetStockCount---->{}", JSON.toJSONString(listJson));
		super.renderJson(response, JSON.toJSONString(listJson));
		return null;
	}

	/*
	 * @author WangKunlin
	 * 
	 * @version 2014-06-12
	 * 
	 * @method 查询作为检查库存的周数
	 */
	public int check_for_stock(String string) {
		SysSetting sysSetting = new SysSetting();
		sysSetting.setTitle(string);
		int weeksAmouts = 0;
		sysSetting = this.getFacade().getSysSettingService().getSysSetting(sysSetting);
		if (null != sysSetting && null != sysSetting.getContent()) {// 如果有记录，按照系统中设置的为准
			logger.info("检查库存的周数：+++++++++" + sysSetting.getContent());
			weeksAmouts = Integer.parseInt(sysSetting.getContent());
			// beginTime = DateUtils.addDays(today,daysAmount);
		}
		return weeksAmouts;

	}

	public Double getPdmodelPrice(String pd_sn, String r3_code) {
		// 根据客户找到唯一的客户群组
		Long deptid = getFacade().getKonkaR3ShopService().getDeptIdOfKonkaR3Code(r3_code);
		KonkaR3Shop ks = new KonkaR3Shop();
		ks.setR3_code(r3_code);
		ks.setIs_del(0l);
		ks = getFacade().getKonkaR3ShopService().getKonkaR3Shop(ks);
		CrmCustomerGroup group = new CrmCustomerGroup();
		group = getFacade().getCrmCustomerGroupService().getCrmCustomerGroupByCustomerIdAndDeptId(
				String.valueOf(ks.getId()), deptid);
		Double double_price = 0.0;
		// 确认是否为该客户群组维护了价格表<限定在时间段内>
		if (group != null) {
			CrmPriceHeader header = new CrmPriceHeader();
			header.setDept_id(deptid);
			header.setGroupcode(group.getGroupcode());// group code 是唯一的
			header.getMap().put("effective_flag", "yes");// 加上时间过滤
			header = getFacade().getCrmPriceHeaderService().getCrmPriceHeader(header);
			if (header != null) {
				// 价格表下是否维护了所需机型
				CrmPriceLines line = new CrmPriceLines();
				line.setHeadid(header.getId());
				line.setModelcode(pd_sn);
				line = getFacade().getCrmPriceLinesService().getCrmPriceLines(line);
				if (null != line && null != line.getForprice()) {
					double_price = line.getForprice();
				}
			}
		}
		return double_price;

	}

	public ActionForward noPersission(HttpServletRequest request, HttpServletResponse response) {
		String msg = super.getMessage(request, "popedom.check.invalid");
		// 封装成JSON字符串
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("msg", msg);
		JSONArray jsonArray = JSONArray.fromObject(m);
		int start = jsonArray.toString().indexOf("[");
		int end = jsonArray.toString().lastIndexOf("}");
		String jsonStr = jsonArray.toString().substring(start + 1, end + 1);
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(jsonStr);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String changeToUTF8(String str) {
		byte[] cc;
		String result = null;
		if (null != str) {
			try {
				cc = str.getBytes("iso-8859-1");
				result = new String(cc, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 根据导入的Excel获取类相对应的的list<br/>
	 * Map<Object, String> map：中文字段对应在Excel中的第几列，存入map中<br/>
	 * Class type：对象的类型，如JBaseGoodsInitStock.class即可<br/>
	 * File file：file文件，如果知道路径的话则new File(fileSavePath)即可 <br/>
	 * int startrow：Excel中的有效数据（汉字）是从第几行开始的，即Excel最左边对应的数字<br/>
	 * 注意：此有效数据第一列为序号，第二列开始就是有效数据了<br/>
	 * 示例如下：<br/>
	 * Map<Object, String> map = new HashMap<Object, String>(); <br/>
	 * map.put(1, "store_id"); <br/>
	 * map.put(2, "goods_id"); map.put(3, "init_count"); <br/>
	 * map.put(4, "buy_price"); map.put(5, "init_desc"); <br/>
	 * map.put(6, "init_date"); <br/>
	 * List<\Object\> list = BeanToMapUtil.getExcelList(map,
	 * JBaseGoodsInitStock.class, new File(fileSavePath), 1); <br/>
	 * if (null != list) { <br/>
	 * for (int i = 0; i < list.size(); i++) { <br/>
	 * JBaseGoodsInitStock s = (JBaseGoodsInitStock) list.get(i); <br/>
	 * //System.out.println("store_id="+s.getStore_id()+"goods_id="+s.getGoods_id(
	 * )+"init_count=" <br/>
	 * +s.getInit_count()+"buy_price="+s.getBuy_price()+"init_desc="+s.
	 * getInit_desc() <br/>
	 * +"init_date="+s.getInit_date()); <br/>
	 * } <br/>
	 * } <br/>
	 * 
	 * @param map
	 * @param type
	 * @param file
	 * @param startrow
	 * @return List<Object>
	 * @author Xiao,GuoJian
	 */
	public static List<Object> getExcelList(Map<Object, String> map, Class type, File file, int startrow) {
		return BeanToMapUtil.getExcelList(map, type, file, startrow);
	}

	/**
	 * 该方法用于 根据父节点和子节点获取类型节点。
	 * （1）当父节点不传值时获取子节点Id对应的节点，如果存在父节点不同但是子节点一样时不能区分你需要的是具体哪个
	 * （2）当子节点Id不传值时获取父节点对应的所有节点， （3）当父子节点同时传值时回去该父节点下的该子节点的那个节点。
	 * （4）两者都不传值时不予查询操作，因为作者认为同时获取所有类型所有节点的情况基本不存在。
	 * 如有这方面需要的请自行调用service中的getKonkaBaseTypeDataList()方法。
	 * 
	 * @param par_id
	 *            父节点Id
	 * @param son_id
	 *            子节点id
	 * @return list<?>
	 * @author Wang,KunLIn
	 */

	public List<KonkaBaseTypeData> getBaseTypeByParAndSonId(Long par_id, Long son_id) {

		if (null == par_id && null == son_id) {
			return null;
		}

		KonkaBaseTypeData konkaBaseTypeData = new KonkaBaseTypeData();
		if (null != par_id) {
			konkaBaseTypeData.setPar_type_id(par_id);// 传入的父节点
		}
		if (null != son_id) {
			konkaBaseTypeData.setPar_type_id(son_id);// 传入的父节点
		}
		konkaBaseTypeData.setIs_del(0);// 取出的节点必须是没有删除的

		return getFacade().getKonkaBaseTypeDataService().getKonkaBaseTypeDataList(konkaBaseTypeData);

	}

	/**
	 * 解密判断用户
	 * @param start
	 * @param end
	 * @return
	 */
	
	protected PeProdUser checkUser(String username, String userpass,String android_version,String ios_version) throws Exception {
		if (StringUtils.isBlank(username) || StringUtils.isBlank(userpass)) {
			return null;
		}
		PeProdUser user = new PeProdUser();
		if(StringUtils.isNotBlank(android_version) && Long.valueOf(android_version)>37)
		{
			user.setUser_name(new String(new DESPlus().decrypt(username).getBytes("iso-8859-1"),"utf-8"));
			user.setPass_word(userpass);
		}else if(StringUtils.isNotBlank(ios_version) && Long.valueOf(ios_version)>317){
			user.setUser_name(new String(new DESPlus().decrypt(username).getBytes("iso-8859-1"),"utf-8"));
			user.setPass_word(userpass);
		}else
		{
			user.setUser_name(username);
			user.setPass_word(new DESPlus().encrypt(userpass));
		}
		user.setIs_del(0);
		
		List<PeProdUser> userList = getFacade().getPeProdUserService().getPeProdUserList(user);
		if (null == userList || userList.size() < 1) {
			return null;
		} else if (userList.size() > 1) {
			return null;
		}
		return userList.get(0);
	}
	
	protected PeProdUser checkUserid(String userid, String userpass,String android_version,String ios_version) throws Exception {
		if (StringUtils.isBlank(userid) || StringUtils.isBlank(userpass)) {
			return null;
		}
		

		logger.info("User_name:{}, Pass_word:{}", userid, userpass);

		PeProdUser user = new PeProdUser();
		if(StringUtils.isNotBlank(android_version) && Long.valueOf(android_version)>37)
		{
			user.setId(new Long(userid.trim()));
			user.setPass_word(userpass);
		}else if(StringUtils.isNotBlank(ios_version) && Long.valueOf(ios_version)>317){
			user.setId(new Long(userid.trim()));
			user.setPass_word(userpass);
		}else
		{
			user.setId(new Long(userid.trim()));
			user.setPass_word(new DESPlus().encrypt(userpass));
		}
		user.setIs_del(0);
		List<PeProdUser> userList = getFacade().getPeProdUserService().getPeProdUserList(user);
		if (null == userList || userList.size() < 1) {
			return null;
		} else if (userList.size() > 1) {
			return null;
		}
		return userList.get(0);
	}
	
	
	public static void main(String[] args) {
		String x = File.separator;
		//System.out.println(x);
	}
	
	/**
	 * 统计耗时
	 * @param start
	 * @param end
	 * @return
	 */
	public static String getTimeInterval(Date start, Date end) {
		Long sec = (end.getTime() - start.getTime()) / 1000;
		return sec == 0 ? String.valueOf(end.getTime() - start.getTime()) + "ms" : sec.toString() + "s";
	}

}