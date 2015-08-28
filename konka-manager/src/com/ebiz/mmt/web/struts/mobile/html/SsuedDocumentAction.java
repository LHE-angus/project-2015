package com.ebiz.mmt.web.struts.mobile.html;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

 import com.ebiz.mmt.domain.KonkaoaSsuedDocument;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.web.Constants;

public class SsuedDocumentAction extends MobileBaseAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		super.encodeCharacterForGetMethod(dynaBean, request);
		
		String title = (String)dynaBean.get("title");
		String file_code = (String)dynaBean.get("file_code");
		String mod_type = (String)dynaBean.get("mod_type");
		String page = (String) dynaBean.get("currentPage");
		String pagelimit = (String) dynaBean.get("pagelimit");
		
		PeProdUser ui = (PeProdUser) request.getSession().getAttribute(
				Constants.USER_MOBILE);
		KonkaoaSsuedDocument entity = new KonkaoaSsuedDocument();
		super.copyProperties(entity, form);
		
		String desc_type =ui.getUser_name()+"查询";
		if(StringUtils.isNotBlank(title)){
			desc_type+=("标题:"+title);
		}if(StringUtils.isNotBlank(file_code)){
			desc_type+=("编号:"+file_code);
		}if(StringUtils.isNotBlank(mod_type)){
			if("notice".equals(mod_type)){
				desc_type+="文件类别:通知公告";
			}else if("file".equals(mod_type)){
				desc_type+="文件类别:下发文件 ";
			}else{
				desc_type+="文件类别:费用申请 ";
			}
		}
		super.createMobileSysOperLog(request, "KONKA_SX_OPER_LOG", ui.getId(), "720500", desc_type, BeanUtils.describe(entity).toString());
		
		int currentPage = 1;
		int pageSize = 3;
		if (StringUtils.isNotEmpty(page)) {
			currentPage = Integer.parseInt(page);
		}
		if (StringUtils.isNotEmpty(pagelimit)) {
			pageSize = Integer.parseInt(pagelimit);
		}
		entity.getRow().setFirst((currentPage - 1) * pageSize);
		entity.getRow().setCount(pageSize);
		entity.getMap().put("uid", ui.getId());
		entity.getMap().put("receive_org_id", ui.getDept_id());
		Long recordCount = super.getFacade().getKonkaoaFilesService().getKonkaoaSsuedDocumentCount(entity);

		if (recordCount % pageSize > 0)
			request.setAttribute("pagelimit", (recordCount / pageSize) + 1);
		else
			request.setAttribute("pagelimit", (recordCount / pageSize));
		String str = "{htmlData:'";
		if (recordCount > 0) {
			List<KonkaoaSsuedDocument> entityList = super.getFacade().getKonkaoaFilesService()
			.getKonkaoaSsuedDocumentPaginatedList(entity);
			str += "<ul data-role=\"listview\" data-inset=\"true\" data-theme=\"b\" data-divider-theme=\"a\" >";
			for (KonkaoaSsuedDocument _t : entityList) {
				str +="<li data-role=\"list-divider\">编号：";
				str +=_t.getFile_code();
				str +="<span class=\"ui-li-count\">";
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				str +=simpleDateFormat.format(_t.getAdd_date());
				str+=" </span> </li>";
				str+="  <li>";
				if(_t.getMod_type().equals("notice")){
					str+="<b>标题：</b>"+_t.getTitle()+"</a>";
				}else if(_t.getMod_type().equals("file") || _t.getMod_type().equals("expense")){
					str+="<b>标题：</b>"+_t.getTitle()+"</a>";
				}
				str+="</li><li><p><b>文件大类：</b>";
				str+=_t.getMod_name();
				str+=" </p></li>";
			}
			str += "</ul>',";
			str += "currentPage:" + currentPage + ",recordCount:" + recordCount+ "}";
		} else {
			str += "',";
			str += "currentPage:" + currentPage + ",recordCount:" + recordCount+ "}";
		}
		super.renderText(response, str);
		return null;
	}
}