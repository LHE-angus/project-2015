package com.ebiz.mmt.web.struts.manager.spgl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.EcGroup;
import com.ebiz.mmt.domain.EcSallarea;
import com.ebiz.mmt.domain.EcSallareaTemplate;
import com.ebiz.mmt.domain.KonkaDept;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleUser;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.struts.BaseAction;

/**
 * @author Jiang,JiaYong
 * @version 2012-09-15
 */
public class EcSallareaTemplateAction extends BaseAction {
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		return this.list(mapping, form, request, response);
	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

		setNaviStringToRequestScope(form, request);

		BaseProvinceListFour bf = new BaseProvinceListFour();
		bf.setP_level(1);
		bf.setDel_mark(0);
		List<BaseProvinceListFour> bfList = super.getFacade().getBaseProvinceListFourService()
		        .getBaseProvinceListFourList(bf);
		for (BaseProvinceListFour baseProvinceListFour : bfList) {
			BaseProvinceListFour bf2 = new BaseProvinceListFour();
			bf2.setPar_index(baseProvinceListFour.getP_index());
			bf2.setDel_mark(0);
			List<BaseProvinceListFour> bf2List = super.getFacade().getBaseProvinceListFourService()
			        .getBaseProvinceListFourList(bf2);
			baseProvinceListFour.setBaseProvinceListFourList(bf2List);

		}

		request.setAttribute("bfList", bfList);

		return mapping.findForward("input");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String par_index = (String) dynaBean.get("par_index");
		String id = (String) dynaBean.get("id");

		// if (StringUtils.isBlank(id)) {
		// saveToken(request);
		// }

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}

		boolean zb = false;
		boolean fgs = false;
		PeRoleUser rUser = new PeRoleUser();
		rUser.setUser_id(user.getId());
		List<PeRoleUser> roleUserList = getFacade().getPeRoleUserService().getPeRoleUserList(rUser);
		if (null != roleUserList && roleUserList.size() > 0) {
			for (PeRoleUser pu : roleUserList) {
				if (pu.getRole_id() < 30L || pu.getRole_id().intValue() == 140317 || pu.getRole_id().intValue() == 1001) {
					zb = true;
				} else if (pu.getRole_id().intValue() >= 30 && pu.getRole_id().intValue() <= 188) {
					fgs = true;
				}
			}
		}

		if (zb) {
			EcGroup eg = new EcGroup();
			List<EcGroup> groupList = super.getFacade().getEcGroupService().getEcGroupList(eg);
			request.setAttribute("groupList", groupList);
		} else if (!zb && fgs) {
			if (null != user.getDept_id()) {
				KonkaDept konkaDept = new KonkaDept();
				konkaDept.setDept_id(user.getDept_id());
				konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
				// 分公司取得
				if (null != konkaDept.getDept_type() && konkaDept.getDept_type() > 2) {
					KonkaDept fgs_dept = super.getSuperiorForDeptType(user.getDept_id(), 3);
					EcGroup eg = new EcGroup();
					eg.setLink_dept_id(fgs_dept.getDept_id());
					List<EcGroup> groupList = super.getFacade().getEcGroupService().getEcGroupList(eg);
					request.setAttribute("groupList", groupList);
				}
			}

		} else {
			super.renderJavaScript(response, "window.onload=function(){alert('你没有权限！');history.back();}");
			return null;
		}

		if (StringUtils.isNotBlank(par_index)) {
			BaseProvinceListFour bf = new BaseProvinceListFour();
			bf.setPar_index(Long.valueOf(par_index));
			bf.setDel_mark(0);
			List<BaseProvinceListFour> coutryList = super.getFacade().getBaseProvinceListFourService()
			        .getBaseProvinceListFourList(bf);
			request.setAttribute("coutryList", coutryList);

			BaseProvinceListFour bf2 = new BaseProvinceListFour();
			bf2.setP_index(Long.valueOf(par_index));
			bf2.setDel_mark(0);
			bf2 = super.getFacade().getBaseProvinceListFourService().getBaseProvinceListFour(bf2);
			if (bf2 != null) {
				dynaBean.set("city", bf2.getFull_name());
			}

		}

		if (StringUtils.isNotBlank(id)) {
			dynaBean.set("id", id);
			EcSallareaTemplate tm = new EcSallareaTemplate();
			tm.setId(Long.valueOf(id));
			tm = super.getFacade().getEcSallareaTemplateService().getEcSallareaTemplate(tm);
			if (tm != null) {
				dynaBean.set("dept_id", tm.getDept_id().toString());
				dynaBean.set("state", tm.getState().toString());
				dynaBean.set("title", tm.getTitle());

				if (StringUtils.isNotBlank(par_index)) {
					EcSallarea er = new EcSallarea();
					er.setTemplate_id(Long.valueOf(id));
					List<EcSallarea> erList = super.getFacade().getEcSallareaService().getEcSallareaList(er);
					if (erList != null && erList.size() > 0) {
						EcSallarea e1 = new EcSallarea();
						e1.setTemplate_id(Long.valueOf(id));
						e1.setP_index(Long.valueOf(par_index));
						e1 = super.getFacade().getEcSallareaService().getEcSallarea(e1);
						if (e1 != null) {
							dynaBean.set("city_choose", e1.getP_index().toString());
						}

						EcSallarea e2 = new EcSallarea();
						e2.setTemplate_id(Long.valueOf(id));
						List<BaseProvinceListFour> proList = getParProvinceList(Long.valueOf(par_index), 1);
						e2.setP_index(proList.get(0).getP_index());
						e2 = super.getFacade().getEcSallareaService().getEcSallarea(e2);
						if (e2 != null) {
							dynaBean.set("province_choose", e2.getP_index().toString());
						}

						List<String> countrys = new ArrayList<String>();
						List<BaseProvinceListFour> countryList = getSonProvinceList(Long.valueOf(par_index), 3);
						if (countryList != null && countryList.size() > 0) {
							for (BaseProvinceListFour baseProvinceListFour : countryList) {
								EcSallarea e3 = new EcSallarea();
								e3.setTemplate_id(Long.valueOf(id));
								e3.setP_index(baseProvinceListFour.getP_index());
								e3 = super.getFacade().getEcSallareaService().getEcSallarea(e3);
								if (e3 != null) {
									countrys.add(e3.getP_index().toString());
								}
							}
							if (countrys != null && countrys.size() > 0) {
								String country_choose = StringUtils.join(countrys, ",");
								dynaBean.set("country_choose", country_choose);
							}

						}

					}
				}
			}
		}

		return new ActionForward("/../manager/spgl/EcSallareaTemplate/choose.jsp");
	}

	public ActionForward ajaxcountry(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		String par_index = (String) dynaBean.get("par_index");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}
		StringBuffer sb = new StringBuffer("{");
		sb = sb.append("\"status\":");

		if (!GenericValidator.isLong(id)) {
			sb = sb.append("-1");
			sb = sb.append("}");
			super.renderJson(response, sb.toString());
			return null;
		}

		EcSallarea er = new EcSallarea();
		er.setTemplate_id(Long.valueOf(id));
		List<EcSallarea> erList = super.getFacade().getEcSallareaService().getEcSallareaList(er);
		List<String> ccList = new ArrayList<String>();
		String cc = "";
		if (erList != null && erList.size() > 0) {
			EcSallarea e1 = new EcSallarea();
			e1.setTemplate_id(Long.valueOf(id));
			e1.setP_index(Long.valueOf(par_index));
			e1 = super.getFacade().getEcSallareaService().getEcSallarea(e1);
			if (e1 != null) {
				List<BaseProvinceListFour> countryList = getSonProvinceList(Long.valueOf(par_index), 3);
				for (BaseProvinceListFour baseProvinceListFour : countryList) {
					ccList.add(baseProvinceListFour.getP_index().toString());
				}
			}

			EcSallarea e2 = new EcSallarea();
			e2.setTemplate_id(Long.valueOf(id));
			List<BaseProvinceListFour> proList = getParProvinceList(Long.valueOf(par_index), 1);
			e2.setP_index(proList.get(0).getP_index());
			e2 = super.getFacade().getEcSallareaService().getEcSallarea(e2);
			if (e2 != null) {
				List<BaseProvinceListFour> countryList = getSonProvinceList(Long.valueOf(par_index), 3);
				for (BaseProvinceListFour baseProvinceListFour : countryList) {
					ccList.add(baseProvinceListFour.getP_index().toString());
				}
			}

			List<BaseProvinceListFour> countryList = getSonProvinceList(Long.valueOf(par_index), 3);
			if (countryList != null && countryList.size() > 0) {
				for (BaseProvinceListFour baseProvinceListFour : countryList) {
					EcSallarea e3 = new EcSallarea();
					e3.setTemplate_id(Long.valueOf(id));
					e3.setP_index(baseProvinceListFour.getP_index());
					e3 = super.getFacade().getEcSallareaService().getEcSallarea(e3);
					if (e3 != null) {
						ccList.add(baseProvinceListFour.getP_index().toString());
					}
				}
			}

		}
		cc = StringUtils.join(ccList, "-");
		sb = sb.append("1").append(",");
		sb = sb.append("\"cc\":\"").append(cc).append("\"");
		sb = sb.append("}");
		logger.info("sb {}", sb);
		super.renderJson(response, sb.toString());
		return null;
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String id = (String) dynaBean.get("id");
		// String mod_id = (String) dynaBean.get("mod_id");

		String rightstr = (String) dynaBean.get("rightstr");
		String citystr = (String) dynaBean.get("citystr");
		String coutry = (String) dynaBean.get("coutry");

		HttpSession session = request.getSession();
		PeProdUser user = (PeProdUser) session.getAttribute(Constants.USER_INFO);
		if (null == user) {
			return null;
		}
		// if (StringUtils.isBlank(id)) {
		// if (isCancelled(request)) {
		// return list(mapping, form, request, response);
		// }
		// if (!isTokenValid(request)) {
		// saveError(request, "errors.token");
		// return list(mapping, form, request, response);
		// }
		// resetToken(request);
		// }

		BaseProvinceListFour bf = new BaseProvinceListFour();

		Set<String> pindexList = new HashSet<String>();

		if (StringUtils.isNotBlank(coutry)) {
			String cc = coutry.substring(0, coutry.length() - 1);
			String[] ccList = cc.split("-");
			for (String string : ccList) {
				if (StringUtils.isBlank(citystr) && StringUtils.isBlank(rightstr)) {
					pindexList.add(string);

				} else if (StringUtils.isBlank(citystr) && StringUtils.isNotBlank(rightstr)) {
					List<BaseProvinceListFour> bbList = getParProvinceList(Long.valueOf(string), 1);
					bf = bbList.get(0);
					String pp = rightstr.substring(0, rightstr.length() - 1);
					String[] ppList = pp.split("-");
					List<String> pproList = Arrays.asList(ppList);
					pproList = new ArrayList<String>(pproList);
					if (pproList.contains(bf.getP_index().toString())) {
						pindexList.add(bf.getP_index().toString());
					} else {
						pindexList.add(string);
					}
				} else if (StringUtils.isNotBlank(citystr) && StringUtils.isBlank(rightstr)) {
					List<BaseProvinceListFour> bbList = getParProvinceList(Long.valueOf(string), 2);
					bf = bbList.get(0);
					String pp = citystr.substring(0, citystr.length() - 1);
					String[] ppList = pp.split("-");
					List<String> pproList = Arrays.asList(ppList);
					pproList = new ArrayList<String>(pproList);
					if (pproList.contains(bf.getP_index().toString())) {
						pindexList.add(bf.getP_index().toString());
					} else {
						pindexList.add(string);
					}
				} else if (StringUtils.isNotBlank(citystr) && StringUtils.isNotBlank(rightstr)) {
					List<BaseProvinceListFour> bbList = getParProvinceList(Long.valueOf(string), 2);
					bf = bbList.get(0);
					String pp = citystr.substring(0, citystr.length() - 1);
					String[] ppList = pp.split("-");
					List<String> pproList = Arrays.asList(ppList);
					pproList = new ArrayList<String>(pproList);
					if (pproList.contains(bf.getP_index().toString())) {
						List<BaseProvinceListFour> bbList2 = getParProvinceList(Long.valueOf(string), 1);
						bf = bbList2.get(0);
						String pp2 = rightstr.substring(0, rightstr.length() - 1);
						String[] ppList2 = pp2.split("-");
						List<String> pproList2 = Arrays.asList(ppList2);
						pproList2 = new ArrayList<String>(pproList2);
						if (pproList2.contains(bf.getP_index().toString())) {
							pindexList.add(bf.getP_index().toString());
						} else {
							pindexList.add(string);
						}
					} else {
						List<BaseProvinceListFour> bbList2 = getParProvinceList(Long.valueOf(string), 1);
						bf = bbList2.get(0);
						String pp2 = rightstr.substring(0, rightstr.length() - 1);
						String[] ppList2 = pp2.split("-");
						List<String> pproList2 = Arrays.asList(ppList2);
						pproList2 = new ArrayList<String>(pproList2);
						if (pproList2.contains(bf.getP_index().toString())) {
							pindexList.add(bf.getP_index().toString());
						} else {
							pindexList.add(string);
						}
					}
				}

			}
		}

		if (StringUtils.isNotBlank(citystr)) {
			String cc = citystr.substring(0, citystr.length() - 1);
			String[] ccList = cc.split("-");
			for (String string : ccList) {
				if (StringUtils.isBlank(rightstr)) {
					pindexList.add(string);
				} else if (StringUtils.isNotBlank(rightstr)) {
					List<BaseProvinceListFour> bbList = getParProvinceList(Long.valueOf(string), 1);
					bf = bbList.get(0);
					String pp = rightstr.substring(0, rightstr.length() - 1);
					String[] ppList = pp.split("-");
					List<String> pproList = Arrays.asList(ppList);
					pproList = new ArrayList<String>(pproList);
					if (pproList.contains(bf.getP_index().toString())) {
						pindexList.add(bf.getP_index().toString());
					} else {
						pindexList.add(string);
					}
				}

			}
		}

		if (StringUtils.isNotBlank(rightstr)) {
			String cc = rightstr.substring(0, rightstr.length() - 1);
			String[] ccList = cc.split("-");
			for (String string : ccList) {
				pindexList.add(string);
			}
		}

		logger.info("hashset--------->" + pindexList);
		EcSallareaTemplate entity = new EcSallareaTemplate();
		copyProperties(entity, form);
		entity.setPindexList(pindexList);

		if (StringUtils.isBlank(id)) {
			entity.setAdd_date(new Date());
			entity.setAdd_u_id(user.getId());
			Long temp_id = super.getFacade().getEcSallareaTemplateService().createEcSallareaTemplate(entity);
			id = temp_id.toString();
			saveMessage(request, "entity.inserted");
		} else {
			if (!GenericValidator.isLong(id)) {
				saveError(request, "errors.long", new String[] { id });
				return mapping.findForward("list");
			}
			super.getFacade().getEcSallareaTemplateService().modifyEcSallareaTemplate(entity);
			saveMessage(request, "entity.updated");
		}

		return new ActionForward("/../manager/spgl/EcSallareaTemplate.do?method=add&id=" + id);
	}

	public List<BaseProvinceListFour> getParProvinceList(Long p_index, Integer level) {

		BaseProvinceListFour bf = new BaseProvinceListFour();
		bf.setP_index(p_index);
		bf.getMap().put("level", level);

		List<BaseProvinceListFour> bfList = super.getFacade().getBaseProvinceListFourService()
		        .getBaseProvinceListFourParentList(bf);

		return bfList;
	}

	public List<BaseProvinceListFour> getSonProvinceList(Long p_index, Integer level) {

		BaseProvinceListFour bf = new BaseProvinceListFour();
		bf.setP_index(p_index);
		bf.getMap().put("level", level);

		List<BaseProvinceListFour> bfList = super.getFacade().getBaseProvinceListFourService()
		        .getBaseProvinceListFourSonTreeList(bf);

		return bfList;
	}

}
