package com.ebiz.mmt.web.struts.jxcnokey;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ebiz.mmt.domain.BaseProvinceListFour;
import com.ebiz.mmt.domain.KonkaBranchCategory;
import com.ebiz.mmt.domain.KonkaPePublicScope;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.domain.PeRoleInfo;
import com.ebiz.mmt.domain.PeShopMsg;
import com.ebiz.mmt.domain.UserInfo;
import com.ebiz.ssi.web.struts.bean.Pager;

/**
 * @author Chen,Shunhua
 * @version 2011-10-10
 */
@SuppressWarnings("unchecked")
public class JxcReceivePeShopMsgAction extends BaseJxcAction {

	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
										HttpServletResponse response) throws Exception {
		return this.list(mapping, form, request, response);
	}

	/**
	 * @desc 收件箱
	 */
	@SuppressWarnings("unused")
	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
							  HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		String title = (String) dynaBean.get("title");
		Pager pager = (Pager) dynaBean.get("pager");

		PeShopMsg entity = new PeShopMsg();
		super.copyProperties(entity, form);
		entity.getMap().put("title", title);
		// entity.setIs_del(0l); //收件和发件箱来自同一个表，防止发件人删除收件人也看不到了邮件(20120214)
		entity.setTitle(title);

		UserInfo user = super.getUserInfoFromWebService(request, response);
		if (null == user) {
			return null;
		}

		BaseProvinceListFour bplf = new BaseProvinceListFour();
		bplf.setP_index(new Long(super.getStdEntpMainByShopId(user.getEntp_shop().getShop_id()).getP_index()));
		bplf = super.getFacade().getBaseProvinceListFourService().getBaseProvinceListFour(bplf);
		if (null == bplf) {
			super.invalidOper(request, response, "errors.parm");
			return null;
		}

		// 是否经销商列表
		KonkaBranchCategory konkaBranchCategoryJxs = new KonkaBranchCategory();
		konkaBranchCategoryJxs.setCategory_id(20l);
		konkaBranchCategoryJxs.setMmt_shop_id(user.getEntp_shop().getShop_id());
		List<KonkaBranchCategory> konkaBranchCategoryList = getFacade().getKonkaBranchCategoryService()
				.getKonkaBranchCategoryList(konkaBranchCategoryJxs);

		HashMap<String, String> result = super.getKonkaDeptIdAndType(user.getEntp_shop().getShop_id(), false);
		String isDls = result.get("result_code");// 0：失败   10：代理商   20：经销商(直供)

		if (StringUtils.equals("10", isDls)) {
			entity.getMap().put("public_target", "1");// 代理商
			entity.getMap().put("shop_type", 10L);
		} else if (StringUtils.equals("20", isDls) || (null != konkaBranchCategoryList && konkaBranchCategoryList.size() > 0)) {
			entity.getMap().put("public_target", "2");// 经销商
			entity.getMap().put("shop_type", 20L);
		} else {// R3网点
			entity.getMap().put("public_target", "0");
			entity.getMap().put("shop_type", 0L);
		}

		entity.getMap().put("par_index", bplf.getP_index());// 网点p_index
		entity.getMap().put("ssqy_p_index", bplf.getPar_index());// 网点所属区域p_index
		entity.getMap().put("shop_id", user.getEntp_shop().getShop_id());// 网点类别
		entity.getMap().put("receive_user_id", user.getEntp_shop().getShop_id());// 查询回复的信息

		Long recordCount = getFacade().getPeShopMsgService().getPeShopMsgCountForReceive(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());

		List<PeShopMsg> entityList = getFacade().getPeShopMsgService().getPeShopMsgPaginatedListForReceive(entity);
		request.setAttribute("entityList", entityList);
		request.setAttribute("tag_id", StringUtils.isNotEmpty((String) dynaBean.get("tag_id")) ? (String) dynaBean
				.get("tag_id") : "a");
		return mapping.findForward("list");
	}

	@SuppressWarnings("unused")
	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
							  HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		UserInfo user = super.getUserInfoFromWebService(request, response);
		if (null == user) {
			return null;
		}
		super.setNaviStringToRequestScope(form, request);

		String id = (String) dynaBean.get("id");
		String tag_id = (String) dynaBean.get("tag_id");

		if (GenericValidator.isLong(id)) {
			PeShopMsg en = new PeShopMsg();
			en.setId(Long.valueOf(id));
			PeShopMsg entity = super.getFacade().getPeShopMsgService().getPeShopMsg(en);
			// 如果是发件箱查看回复的信显示收件人
			if (null != entity && StringUtils.equals("b", tag_id)) {
				PeShopMsg parEntity = new PeShopMsg();
				parEntity.setId(entity.getPar_id());
				parEntity = super.getFacade().getPeShopMsgService().getPeShopMsg(parEntity);
				if (null != parEntity) {
					entity.getMap().put("receive_name", parEntity.getSend_user_name());
				}
			}
			request.setAttribute("entity", entity);

			request.setAttribute("entity", entity);
			request.setAttribute("tag_id", tag_id);
			return mapping.findForward("view");
		} else {
			saveError(request, "errors.long", id);
			return mapping.findForward("list");
		}
	}

	public ActionForward reply(ActionMapping mapping, ActionForm form, HttpServletRequest request,
							   HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		UserInfo user = super.getUserInfoFromWebService(request, response);
		if (null == user) {
			return null;
		}
		String id = (String) dynaBean.get("id");
		if (GenericValidator.isLong(id)) {
			PeShopMsg en = new PeShopMsg();
			en.setId(Long.valueOf(id));

			en.setSend_user_type(1);
			PeShopMsg entity = super.getFacade().getPeShopMsgService().getPeShopMsg(en);
			request.setAttribute("entity", entity);

			request.setAttribute("tag_id", "d");
			dynaBean.set("keySeq", keySeq);
			return new ActionForward("/JxcReceivePeShopMsg/replyForm.jsp");
		} else {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}
	}

	public ActionForward saveReply(ActionMapping mapping, ActionForm form, HttpServletRequest request,
								   HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		UserInfo user = super.getUserInfoFromWebService(request, response);
		if (null == user) {
			return null;
		}
		String par_id = (String) dynaBean.get("par_id");// 站内信原件ID
		String id = (String) dynaBean.get("id");// 回复信ID
		if (StringUtils.isNotBlank(id)) { // 更新回复信
			PeShopMsg entity = new PeShopMsg();
			super.copyProperties(entity, form);

			PeShopMsg en = new PeShopMsg();
			en.setId(Long.valueOf(par_id));
			en.setSend_user_type(1);
			en = super.getFacade().getPeShopMsgService().getPeShopMsg(en);// 原件内容
			if (null != en) {
				super.getFacade().getPeShopMsgService().modifyPeShopMsg(entity);// 更新回复信
				dynaBean.set("keySeq", keySeq);
				saveMessage(request, "entity.inserted");
				// save_type : 1已发送， 0暂存
				if (entity.getState() == 1) {
					dynaBean.set("tag_id", "b");
					return this.listOut(mapping, form, request, response);
				} else {
					dynaBean.set("tag_id", "c");
					return this.listDraft(mapping, form, request, response);
				}
			}
			saveMessage(request, "entity.missing");
			dynaBean.set("tag_id", "c");
			return this.listDraft(mapping, form, request, response);
		} else { // 新插入回复信
			if (GenericValidator.isLong(par_id)) {
				PeShopMsg en = new PeShopMsg();
				en.setId(Long.valueOf(par_id));
				en.setSend_user_type(1);
				PeShopMsg entity = super.getFacade().getPeShopMsgService().getPeShopMsg(en);// 原件内容
				if (null != entity) {

					PeShopMsg peShopMsg = new PeShopMsg();
					super.copyProperties(peShopMsg, form);
					if (peShopMsg.getState() == 1) {
						// entity.setState(3);//更新为已回复 --->(20120214)信件应该可以多次回复
						peShopMsg.getMap().put("parMsg", entity);
					}
					peShopMsg.setPar_id(entity.getId());// 回复的原件ID
					// peShopMsg.setSend_user_id(user.getUser_id());
					peShopMsg.setSend_user_id(user.getEntp_shop().getShop_id());// 发件人ID：用商铺ID或者userid其中一个
					// peShopMsg.setSend_user_name(user.getUser_name());
					// peShopMsg.setSend_user_name(user.getStdEntpMain().getEntp_name());//发件姓名：用商铺ID或者userid其中一个
					peShopMsg.setSend_user_name(super.getKonkaShopById(user.getEntp_shop().getShop_id()).getShop_name());
					peShopMsg.setReceive_user_id(entity.getSend_user_id());
					peShopMsg.setReceive_user_name(entity.getSend_user_name());
					peShopMsg.setSend_date(new Date());

					// 回复的网点类型
					KonkaPePublicScope konkaPePublicScope = new KonkaPePublicScope();
					konkaPePublicScope.setPublic_type(5L);// 随便存不影响管理端收件箱查询
					konkaPePublicScope.setPublic_scope(entity.getSend_user_id());// 存收件人的ID，方便管理端在收件箱内进行查看
					// 是否经销商（非直供）列表
					KonkaBranchCategory konkaBranchCategoryJxs = new KonkaBranchCategory();
					konkaBranchCategoryJxs.setCategory_id(20l);
					konkaBranchCategoryJxs.setMmt_shop_id(user.getEntp_shop().getShop_id());
					List<KonkaBranchCategory> konkaBranchCategoryList = getFacade().getKonkaBranchCategoryService()
							.getKonkaBranchCategoryList(konkaBranchCategoryJxs);

					HashMap<String, String> result = super.getKonkaDeptIdAndType(user.getEntp_shop().getShop_id(), false);
					String isDls = result.get("result_code");// 0：失败   10：代理商   20：经销商(直供)

					if (StringUtils.equals("10", isDls)) {
						konkaPePublicScope.setPublic_target(1);// 代理商
					} else if (StringUtils.equals("20", isDls) || (null != konkaBranchCategoryList && konkaBranchCategoryList.size() > 0)) {
						konkaPePublicScope.setPublic_target(2);// 经销商
					} else {// R3网点
						konkaPePublicScope.setPublic_target(0);
					}

					peShopMsg.getMap().put("konkaPePublicScope", konkaPePublicScope);

					super.getFacade().getPeShopMsgService().createPeShopMsgReply(peShopMsg);// 插入回复的信件、更新原件为已回复
					dynaBean.set("keySeq", keySeq);
					saveMessage(request, "entity.inserted");

					// save_type : 1已发送， 0暂存
					if (peShopMsg.getState() == 1) {
						dynaBean.set("tag_id", "b");
						return this.listOut(mapping, form, request, response);
					} else {
						dynaBean.set("tag_id", "c");
						return this.listDraft(mapping, form, request, response);
					}
				}
				saveMessage(request, "entity.missing");
				return mapping.findForward("list");
			} else {
				saveMessage(request, "entity.missing");
				// return mapping.findForward("list");
				return this.list(mapping, form, request, response);
			}
		}
	}

	public ActionForward editReply(ActionMapping mapping, ActionForm form, HttpServletRequest request,
								   HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		UserInfo user = super.getUserInfoFromWebService(request, response);
		if (null == user) {
			return null;
		}
		String id = (String) dynaBean.get("id");// 回复信件ID
		if (GenericValidator.isLong(id)) {
			PeShopMsg en = new PeShopMsg();
			en.setId(Long.valueOf(id));
			PeShopMsg entity = super.getFacade().getPeShopMsgService().getPeShopMsg(en);
			request.setAttribute("replyEntity", entity);

			if (null != entity) {
				PeShopMsg proPeShopeMsg = new PeShopMsg();
				proPeShopeMsg.setId(entity.getPar_id());
				proPeShopeMsg = super.getFacade().getPeShopMsgService().getPeShopMsg(proPeShopeMsg);
				request.setAttribute("entity", proPeShopeMsg);
				request.setAttribute("tag_id", "d");// 到发信息
			}

			dynaBean.set("keySeq", keySeq);
			return new ActionForward("/JxcReceivePeShopMsg/replyForm.jsp");
		} else {
			saveMessage(request, "entity.missing");
			return mapping.findForward("list");
		}
	}

	public ActionForward editMsg(ActionMapping mapping, ActionForm form, HttpServletRequest request,
								 HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		UserInfo user = super.getUserInfoFromWebService(request, response);
		if (null == user) {
			return null;
		}
		super.setNaviStringToRequestScope(form, request);

		String id = (String) dynaBean.get("id");
		// 根据网点id（shop_id） 获取分公司Id

		HashMap map = super.getKonkaDeptIdAndType(user.getEntp_shop().getShop_id(), true);
		String dept_id_fgs = "";
		if (null != map) {
			dept_id_fgs = map.get("r3_dept_ids").toString();
		}
		request.setAttribute("dept_id_fgs", dept_id_fgs);
		if (GenericValidator.isLong(id)) {// 编辑
			String user_ids = "";
			String user_names = "";
			String role_names = "";
			String role_ids = "";
			PeShopMsg entity = new PeShopMsg();
			entity.setId(new Long(id));
			entity = getFacade().getPeShopMsgService().getPeShopMsg(entity);
			if (null != entity) {// 根据id查询信息接收人列表 根据接收人查询对应列表角色
				KonkaPePublicScope kps = new KonkaPePublicScope();
				kps.setArticle_id(entity.getId());
				List<KonkaPePublicScope> konkaPePublicScopeList = getFacade().getKonkaPePublicScopeService()
						.getKonkaPePublicScopeList(kps);
				if (null != konkaPePublicScopeList && konkaPePublicScopeList.size() > 0) {
					for (KonkaPePublicScope kkpps : konkaPePublicScopeList) {
						user_ids += kkpps.getPublic_scope().toString() + ",";
						if (null != kkpps.getPublic_scope()
								&& GenericValidator.isLong(kkpps.getPublic_scope().toString())) {// 根据id取用户名称
							PeProdUser ppUser = new PeProdUser();
							ppUser.setId(kkpps.getPublic_scope());
							ppUser = getFacade().getPeProdUserService().getPeProdUser(ppUser);
							if (null != ppUser) {
								user_names += ppUser.getUser_name() + ",";
							}
						}
					}
				}
				if (null != user_ids && user_ids.length() > 0) {// 根据用户id取对应角色
					user_ids = user_ids.substring(0, user_ids.lastIndexOf(","));
					PeRoleInfo peRoleInfo = new PeRoleInfo();
					peRoleInfo.getMap().put("user_ids", user_ids);
					List<PeRoleInfo> peRoleInfoList = getFacade().getPeRoleInfoService().getPeRoleInfoByUserIdsList(
							peRoleInfo);
					if (null != peRoleInfoList && peRoleInfoList.size() > 0) {
						for (PeRoleInfo pr : peRoleInfoList) {
							role_ids += pr.getRole_id() + ",";
							role_names += pr.getRole_name() + ",";
						}
					}

				}
				if (null != user_names && user_names.length() > 0) {
					user_names = user_names.substring(0, user_names.lastIndexOf(","));
				}
				if (null != role_ids && role_ids.length() > 0) {
					role_ids = role_ids.substring(0, role_ids.lastIndexOf(","));
				}
				if (null != role_names && role_names.length() > 0) {
					role_names = role_names.substring(0, role_names.lastIndexOf(","));
				}

			}

			dynaBean.set("user_ids", user_ids);
			dynaBean.set("user_names", user_names);
			dynaBean.set("role_ids", role_ids);
			dynaBean.set("role_names", role_names);
			dynaBean.set("keySeq", keySeq);
			super.copyProperties(dynaBean, entity);

		} else {// 新增
			dynaBean.set("receive_user_type", "1");
		}
		request.setAttribute("tag_id", StringUtils.isNotEmpty((String) dynaBean.get("tag_id")) ? (String) dynaBean
				.get("tag_id") : "d");
		return new ActionForward("/JxcReceivePeShopMsg/form_send.jsp");

	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
							  HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		String id = (String) dynaBean.get("id");
		UserInfo user = super.getUserInfoFromWebService(request, response);
		if (null == user) {
			return null;
		}

		String receive_user_type = (String) dynaBean.get("receive_user_type");
		String title = (String) dynaBean.get("title");
		String content = (String) dynaBean.get("content");
		String role_ids = (String) dynaBean.get("role_ids");// 接收人Id
		String user_ids = (String) dynaBean.get("user_ids");// 接收人Id
		String[] user_id = null;// 分公司人员Id
		String dept_id_fgs = "";// 分公司Id

		PeShopMsg peShopMsg = new PeShopMsg();
		super.copyProperties(peShopMsg, form);

		peShopMsg.setPar_id(new Long(0));// 回复的原件ID 未用到字段
		peShopMsg.setSend_user_type(0);// 发送人类型：0-网点用户，1-生产企业用户
		peShopMsg.setSend_ip(request.getRemoteAddr());

		peShopMsg.setSend_user_id(user.getEntp_shop().getShop_id());// 发件人ID：用商铺ID或者userid其中一个

		peShopMsg.setSend_user_name(super.getKonkaShopById(user.getEntp_shop().getShop_id()).getShop_name());// 发件姓名：用商铺ID或者userid其中一个
		peShopMsg.setContent(content);
		peShopMsg.setTitle(title);
		peShopMsg.setReceive_user_type(new Integer(receive_user_type));// 接收人用户类型：0-所有用户，1-指定用户
		// peShopMsg.setReceive_user_id());
		// peShopMsg.setReceive_user_name();
		peShopMsg.setSend_date(new Date());
		List<KonkaPePublicScope> konkaPePublicScopeList = new ArrayList<KonkaPePublicScope>();
		if (StringUtils.equals("1", receive_user_type)) {// 指定接收人员发送方式
			if (StringUtils.isBlank(role_ids)) {
				String msg = "请选择收件分公司角色！";
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}

			if (StringUtils.isBlank(user_ids)) {
				String msg = "请选择收件分公司人员！";
				super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
				return null;
			}

			// 是否经销商列表
			KonkaBranchCategory konkaBranchCategoryJxs = new KonkaBranchCategory();
			konkaBranchCategoryJxs.setCategory_id(20l);
			konkaBranchCategoryJxs.setMmt_shop_id(user.getEntp_shop().getShop_id());
			List<KonkaBranchCategory> konkaBranchCategoryList = getFacade().getKonkaBranchCategoryService()
					.getKonkaBranchCategoryList(konkaBranchCategoryJxs);

			HashMap<String, String> result = super.getKonkaDeptIdAndType(user.getEntp_shop().getShop_id(), false);
			String isDls = result.get("result_code");// 0：失败   10：代理商   20：经销商(直供)

			int target = 0;// R3网点
			if (StringUtils.equals("10", isDls)) {
				target = 1;// 代理商
			} else if (StringUtils.equals("20", isDls) || (null != konkaBranchCategoryList && konkaBranchCategoryList.size() > 0)) {
				target = 2;;// 经销商
			}

			user_id = user_ids.split(",");
			if (user_id.length > 0) {
				for (int i = 0; i < user_id.length; i++) {// 对分公司指定人发送 PUBLIC_TYPE=4、PUBLIC_SCOPE存分公司用户ID
					KonkaPePublicScope kps = new KonkaPePublicScope();
					kps.setPublic_type(4l);
					kps.setPublic_scope(new Long(user_id[i]));
					kps.setPublic_target(target);
					konkaPePublicScopeList.add(kps);

				}
			}
		} else {
			if (StringUtils.equals("0", receive_user_type)) {// 对所有人发送
				HashMap map = super.getKonkaDeptIdAndType(user.getEntp_shop().getShop_id(), true);

				if (null != map) {
					dept_id_fgs = map.get("r3_dept_ids").toString();
				}
				if (StringUtils.isBlank(dept_id_fgs)) {
					String msg = "您所在的网点没有对应的分公司！";
					super.renderJavaScript(response, "window.onload=function(){alert('" + msg + "');history.back();}");
					return null;
				} else {
					if (GenericValidator.isLong(dept_id_fgs)) {// 对分公司所有人发送 子表 PUBLIC_TYPE=5、PUBLIC_SCOPE存该网点对应分公司id
						KonkaPePublicScope kps = new KonkaPePublicScope();
						kps.setPublic_type(5l);
						kps.setPublic_scope(new Long(dept_id_fgs));

						konkaPePublicScopeList.add(kps);

						// 是否经销商（非直供）列表
						KonkaBranchCategory konkaBranchCategoryJxs = new KonkaBranchCategory();
						konkaBranchCategoryJxs.setCategory_id(20l);
						konkaBranchCategoryJxs.setMmt_shop_id(user.getEntp_shop().getShop_id());
						List<KonkaBranchCategory> konkaBranchCategoryList = getFacade().getKonkaBranchCategoryService()
								.getKonkaBranchCategoryList(konkaBranchCategoryJxs);

						HashMap<String, String> result = super.getKonkaDeptIdAndType(user.getEntp_shop().getShop_id(), false);
						String isDls = result.get("result_code");// 0：失败   10：代理商   20：经销商(直供)

						if (StringUtils.equals("10", isDls)) {// 代理商
							kps.setPublic_target(1);
						} else if (StringUtils.equals("20", isDls) || (null != konkaBranchCategoryList && konkaBranchCategoryList.size() > 0)) {// 经销商
							kps.setPublic_target(2);
						} else {// R3网点
							kps.setPublic_target(0);
						}
					}
				}
			}
		}
		peShopMsg.setKonkaPePublicScopeList(konkaPePublicScopeList);
		if (GenericValidator.isLong(id)) {// 修改

			peShopMsg.setId(new Long(id));
			super.getFacade().getPeShopMsgService().modifyPeShopMsg(peShopMsg);

		} else {// 添加

			super.getFacade().getPeShopMsgService().createPeShopMsg(peShopMsg);
			saveMessage(request, "entity.inserted");

		}

		dynaBean.set("keySeq", keySeq);
		super.copyProperties(form, new PeShopMsg());

		// save_type : 1已发送， 0暂存
		if (peShopMsg.getState() == 1) {
			dynaBean.set("tag_id", "b");
			return this.listOut(mapping, form, request, response);
		} else {
			dynaBean.set("tag_id", "c");
			return this.listDraft(mapping, form, request, response);
		}
	}

	/**
	 * @desc 发件箱
	 */
	@SuppressWarnings("unused")
	public ActionForward listOut(ActionMapping mapping, ActionForm form, HttpServletRequest request,
								 HttpServletResponse response) throws Exception {

		super.setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		Pager pager = (Pager) dynaBean.get("pager");

		UserInfo user = super.getUserInfoFromWebService(request, response);
		if (null == user) {
			return null;
		}

		PeShopMsg entity = new PeShopMsg();
		// super.copyProperties(entity, form);

		entity.setSend_user_id(user.getEntp_shop().getShop_id());
		// entity.setReceive_user_type(new Integer(receive_user_type));
		entity.getMap().put("order_by_send_date", true);
		entity.getMap().put("not_eq_state", 0);
		entity.setIs_del(new Long(0));

		Long recordCount = super.getFacade().getPeShopMsgService().getPeShopMsgCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<PeShopMsg> entityList = super.getFacade().getPeShopMsgService().getPeShopMsgPaginatedList(entity);

		request.setAttribute("tag_id", StringUtils.isNotEmpty((String) dynaBean.get("tag_id")) ? (String) dynaBean
				.get("tag_id") : "b");

		request.setAttribute("entityList", entityList);
		return new ActionForward("/JxcReceivePeShopMsg/list_send.jsp");

	}

	@SuppressWarnings("unused")
	public ActionForward listDraft(ActionMapping mapping, ActionForm form, HttpServletRequest request,
								   HttpServletResponse response) throws Exception {

		super.setNaviStringToRequestScope(form, request);

		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		Pager pager = (Pager) dynaBean.get("pager");

		UserInfo user = super.getUserInfoFromWebService(request, response);
		if (null == user) {
			return null;
		}

		PeShopMsg entity = new PeShopMsg();

		entity.setSend_user_id(user.getEntp_shop().getShop_id());
		entity.getMap().put("order_by_send_date", true);
		entity.setState(0);
		entity.setIs_del(new Long(0));

		Long recordCount = super.getFacade().getPeShopMsgService().getPeShopMsgCount(entity);
		pager.init(recordCount, pager.getPageSize(), pager.getRequestPage());
		entity.getRow().setFirst(pager.getFirstRow());
		entity.getRow().setCount(pager.getRowCount());
		List<PeShopMsg> entityList = super.getFacade().getPeShopMsgService().getPeShopMsgPaginatedList(entity);

		request.setAttribute("tag_id", StringUtils.isNotEmpty((String) dynaBean.get("tag_id")) ? (String) dynaBean
				.get("tag_id") : "c");

		request.setAttribute("entityList", entityList);
		return new ActionForward("/JxcReceivePeShopMsg/list_draft.jsp");

	}

	@SuppressWarnings("unused")
	public ActionForward viewSend(ActionMapping mapping, ActionForm form, HttpServletRequest request,
								  HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		UserInfo user = super.getUserInfoFromWebService(request, response);
		if (null == user) {
			return null;
		}
		super.setNaviStringToRequestScope(form, request);

		String id = (String) dynaBean.get("id");

		if (GenericValidator.isLong(id)) {
			String user_ids = "";
			String user_names = "";
			String role_names = "";
			String role_ids = "";
			PeShopMsg en = new PeShopMsg();
			en.setId(Long.valueOf(id));
			PeShopMsg entity = super.getFacade().getPeShopMsgService().getPeShopMsg(en);
			if (null != entity) {// 根据id查询信息接收人列表 根据接收人查询对应列表角色
				KonkaPePublicScope kps = new KonkaPePublicScope();
				kps.setArticle_id(entity.getId());
				List<KonkaPePublicScope> konkaPePublicScopeList = getFacade().getKonkaPePublicScopeService()
						.getKonkaPePublicScopeList(kps);
				if (null != konkaPePublicScopeList && konkaPePublicScopeList.size() > 0) {
					for (KonkaPePublicScope kkpps : konkaPePublicScopeList) {
						user_ids += kkpps.getPublic_scope().toString() + ",";
						if (null != kkpps.getPublic_scope()
								&& GenericValidator.isLong(kkpps.getPublic_scope().toString())) {// 根据id取用户名称
							PeProdUser ppUser = new PeProdUser();
							ppUser.setId(kkpps.getPublic_scope());
							ppUser = getFacade().getPeProdUserService().getPeProdUser(ppUser);
							if (null != ppUser) {
								user_names += ppUser.getUser_name() + ",";
							}
						}
					}
				}
				if (null != user_ids && user_ids.length() > 0) {// 根据用户id取对应角色
					user_ids = user_ids.substring(0, user_ids.lastIndexOf(","));
					PeRoleInfo peRoleInfo = new PeRoleInfo();
					peRoleInfo.getMap().put("user_ids", user_ids);
					List<PeRoleInfo> peRoleInfoList = getFacade().getPeRoleInfoService().getPeRoleInfoByUserIdsList(
							peRoleInfo);
					if (null != peRoleInfoList && peRoleInfoList.size() > 0) {
						for (PeRoleInfo pr : peRoleInfoList) {
							role_ids += pr.getRole_id() + ",";
							role_names += pr.getRole_name() + ",";
						}
					}

				}
				if (null != user_names && user_names.length() > 0) {
					user_names = user_names.substring(0, user_names.lastIndexOf(","));
				}
				if (null != role_ids && role_ids.length() > 0) {
					role_ids = role_ids.substring(0, role_ids.lastIndexOf(","));
				}
				if (null != role_names && role_names.length() > 0) {
					role_names = role_names.substring(0, role_names.lastIndexOf(","));
				}

			}

			dynaBean.set("user_ids", user_ids);
			dynaBean.set("user_names", user_names);
			dynaBean.set("role_ids", role_ids);
			dynaBean.set("role_names", role_names);
			super.copyProperties(dynaBean, entity);
			request.setAttribute("tag_id", (String) dynaBean.get("tag_id"));
			return new ActionForward("/JxcReceivePeShopMsg/view_send.jsp");
		} else {
			saveError(request, "errors.long", id);
			request.setAttribute("tag_id", "b");
			return new ActionForward("/JxcReceivePeShopMsg/list_send.jsp");
		}
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
								HttpServletResponse response) throws Exception {
		super.setNaviStringToRequestScope(form, request);
		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		String id = (String) dynaBean.get("id");
		String[] pks = (String[]) dynaBean.get("pks");
		String tag_id = (String) dynaBean.get("tag_id");

		if (!StringUtils.isBlank(id) && GenericValidator.isLong(id)) {
			PeShopMsg entity = new PeShopMsg();
			entity.setId(new Long(id));
			entity.setDel_date(new Date());
			entity.setIs_del((long) 1);
			super.getFacade().getPeShopMsgService().preModifyPeShopMsg(entity);
			saveMessage(request, "entity.deleted");
		} else if (!ArrayUtils.isEmpty(pks)) {
			PeShopMsg entity = new PeShopMsg();
			entity.setDel_date(new Date());
			entity.setIs_del((long) 1);
			entity.getMap().put("pks", pks);
			super.getFacade().getPeShopMsgService().preModifyPeShopMsg(entity);
			saveMessage(request, "entity.deleted");
		}

		dynaBean.set("id", "");
		dynaBean.set("pks", null);
		dynaBean.set("keySeq", keySeq);

		if ("b".equals(tag_id)) {
			return this.listOut(mapping, form, request, response);
		} else {
			return this.listDraft(mapping, form, request, response);
		}
	}

	@SuppressWarnings("unused")
	public ActionForward list_role(ActionMapping mapping, ActionForm form, HttpServletRequest request,
								   HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		UserInfo user = super.getUserInfoFromWebService(request, response);
		if (null == user) {
			return null;
		}
		super.setNaviStringToRequestScope(form, request);

		String selectedIds = (String) dynaBean.get("selectedIds");
		if (StringUtils.isNotBlank(selectedIds)) {
			PeRoleInfo peRoleInfo = new PeRoleInfo();
			peRoleInfo.getMap().put("selectedIds", selectedIds);
			List<PeRoleInfo> peRoleInfoSelectedList = getFacade().getPeRoleInfoService().getPeRoleInfoList(peRoleInfo);
			if (peRoleInfoSelectedList != null && peRoleInfoSelectedList.size() > 0) {// 选择过的
				request.setAttribute("peRoleInfoSelectedList", peRoleInfoSelectedList);
			}
		}

		List<PeRoleInfo> peRoleInfoList = super.getPeRoleInfoListForProcess();
		request.setAttribute("peRoleInfoList", peRoleInfoList);
		return new ActionForward("/JxcReceivePeShopMsg/list_role.jsp");
	}

	// 根据分公司及角色所选角色获取列表
	@SuppressWarnings("unused")
	public ActionForward list_user(ActionMapping mapping, ActionForm form, HttpServletRequest request,
								   HttpServletResponse response) throws Exception {

		DynaBean dynaBean = (DynaBean) form;
		String keySeq = (String) dynaBean.get("keySeq");
		String selectedIds = (String) dynaBean.get("selectedIds");
		String role_ids = (String) dynaBean.get("role_ids");
		UserInfo user = super.getUserInfoFromWebService(request, response);
		if (null == user) {
			return null;
		}
		super.setNaviStringToRequestScope(form, request);
		HashMap map = super.getKonkaDeptIdAndType(user.getEntp_shop().getShop_id(), true);
		String dept_id_fgs = "";
		if (null != map) {
			dept_id_fgs = map.get("r3_dept_ids").toString();
		}

		if (StringUtils.isNotBlank(selectedIds)) {
			PeProdUser peProdUser_select = new PeProdUser();
			peProdUser_select.getMap().put("dept_id", dept_id_fgs);
			peProdUser_select.getMap().put("role_ids", role_ids);
			peProdUser_select.getMap().put("select_ids", selectedIds);
			List<PeProdUser> peProdUserSelectedList = getFacade().getPeProdUserService().getPeProdUserWithRoleNameList(
					peProdUser_select);
			if (null != peProdUserSelectedList && peProdUserSelectedList.size() > 0) {
				request.setAttribute("peProdUserSelectedList", peProdUserSelectedList);

			}
		}
		PeProdUser peProdUser = new PeProdUser();
		peProdUser.getMap().put("dept_id", dept_id_fgs);
		peProdUser.getMap().put("role_ids", role_ids);
		List<PeProdUser> peProdUserList = getFacade().getPeProdUserService().getPeProdUserWithRoleNameList(peProdUser);
		request.setAttribute("peProdUserList", peProdUserList);
		return new ActionForward("/JxcReceivePeShopMsg/list_user.jsp");
	}

}

