package com.ebiz.mmt.web.struts;

import com.ebiz.mmt.domain.*;
import com.ebiz.mmt.web.Constants;
import com.ebiz.mmt.web.util.DESPlus;
import com.ebiz.ssi.web.struts.bean.UploadFile;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.poi.util.SystemOutLogger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Derek
 */
public class MobileSubmitAction extends MobileBaseAction {

	SimpleDateFormat df = new SimpleDateFormat("yyyy/M/d");

	SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.unknow(mapping, form, request, response);
	}

	public ActionForward unknow(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.renderText(response, "unknow option");
		return null;
	}

	protected PeProdUser checkUserid(String userid, String userpass) throws Exception {
		if (StringUtils.isBlank(userid) || StringUtils.isBlank(userpass)) {
			return null;
		}

		logger.info("User_name:{}, Pass_word:{}", userid, userpass);

		PeProdUser user = new PeProdUser();
		user.setId(new Long(userid.trim()));
		user.setIs_del(0);
		user.setPass_word(new DESPlus().encrypt(userpass));
		List<PeProdUser> userList = super.getFacade().getPeProdUserService().getPeProdUserList(user);
		if (null == userList || userList.size() < 1) {
			return null;
		} else if (userList.size() > 1) {
			return null;
		}
		return userList.get(0);
	}
	protected void generateHis(KonkaMobileSailData e1, KonkaMobileShopStore e2, KonkaMobileSailsReturn e3,
			KonkaMobileTestData e4, KonkaMobileEquStore e5, KonkaMobileFightReport e6, KonkaMobileSailDatas e9) {
		KonkaMobileReportHistory entity = new KonkaMobileReportHistory();
		// 销售数据
		if (null != e1) {
			entity.setType_id(1l);
			entity.setReport_time(e1.getReport_date());
			entity.setReport_man(e1.getReport_id());
			entity.setContent("售出：" + e1.getModel_name() + " 顾客：" + e1.getRealname());
		}
		// 门店库存
		if (null != e2) {
			entity.setType_id(2l);
			entity.setReport_time(e2.getReport_date());
			entity.setReport_man(e2.getReport_id());
			entity.setContent("库存：" + e2.getModel_name() + " 数量：" + e2.getNum());
		}
		// 退货登记
		if (null != e3) {
			entity.setType_id(3l);
			entity.setReport_time(e3.getReport_date());
			entity.setReport_man(e3.getReport_id());
			entity.setContent("退回：" + e3.getModel_name() + " 数量：" + e3.getNum());
		}
		// 样机管理 4
		if (null != e4) {
			entity.setType_id(4l);
			entity.setReport_time(e4.getReport_date());
			entity.setReport_man(e4.getReport_id());
			entity.setContent("样机：" + e4.getModel_name() + " 数量：" + e4.getNum());
		}
		// 物料终端 5
		if (null != e5) {
			entity.setType_id(5l);
			entity.setReport_time(e5.getReport_date());
			entity.setReport_man(e5.getReport_id());
			entity.setContent("物料：" + e5.getEqu_id() + " 数量：" + e5.getNum());
		}
		// 竞品上报 6
		if (null != e6) {
			entity.setType_id(6l);
			entity.setReport_time(e6.getReport_time());
			entity.setReport_man(e6.getReport_man());
			entity.setContent("品牌：" + e6.getBrand_id() + " 型号：" + e6.getModel_id() + " 销量：" + e6.getNum());
		}
		// 销售汇总 9
		if (null != e9) {
			entity.setType_id(9l);
			entity.setReport_time(e9.getReport_date());
			entity.setReport_man(e9.getReport_id());
			entity.setContent("型号：" + e9.getModel_id() + " 销量：" + e9.getNum());
		}

		super.getFacade().getKonkaMobileReportHistoryService().createKonkaMobileReportHistory(entity);
	}

	// 销售数据
	public ActionForward DoSubmit01(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		// 用户名
		String username = (String) dynaBean.get("username");
		// 密码
		String userpass = (String) dynaBean.get("userpass");
		String version = (String) dynaBean.get("version");
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		String md_name = "";
		// 判断是否是静态页面来的数据，目的处理乱码问题
		String from_html = (String) dynaBean.get("from_html");
		if ("1".equals(from_html)) {
			username = URLDecoder.decode(username, "utf-8");
			userpass = URLDecoder.decode(userpass, "utf-8");
		}

		PeProdUser peProdUser = checkUser(username, userpass,android_version,ios_version);

		if (null == peProdUser) {
			super.renderTextOrJsonp(request, response, "用户信息出错，请联系管理员！");
			return null;
		}

		// 修改的逻辑
		String id = (String) dynaBean.get("id");
		if (StringUtils.isNotEmpty(id)) {
			KonkaMobileSailData _entity = new KonkaMobileSailData();
			_entity.setId(Long.parseLong(id));
			// 消费者姓名
			String realname = (String) dynaBean.get("realname");
			// 消费者电话
			String phonenum = (String) dynaBean.get("phonenum");
			// 消费者住址
			String addresss = (String) dynaBean.get("addresss");
			// 消费者身份证号
			String mastercode = (String) dynaBean.get("mastercode");
			// 型号
			String pd_id = (String) dynaBean.get("select-choice-2");
			// 备注
			String memo = (String) dynaBean.get("memo");
			// 样机类型report_type
			// String report_type = (String) dynaBean.get("report_type");
			if ("1".equals(from_html)) {
				realname = URLDecoder.decode(realname, "utf-8");
				addresss = URLDecoder.decode(addresss, "utf-8");
				memo = URLDecoder.decode(memo, "utf-8");
			}

			if (StringUtils.isNotEmpty(pd_id)) {
				pd_id = pd_id.trim();
				_entity.setModel_id(new Long(pd_id));
				PePdModel pePdModel = new PePdModel();
				pePdModel.setPd_id(Long.valueOf(pd_id));
				pePdModel = super.getFacade().getPePdModelService().getPePdModel(pePdModel);
				if (pePdModel != null) {
					_entity.setModel_name(pePdModel.getMd_name());
					md_name = pePdModel.getMd_name();
					_entity.setCategory_id(pePdModel.getCls_id());
					_entity.setPct_code(pePdModel.getMat_num());
					if (null != pePdModel.getMd_size() && !"".equals(pePdModel.getMd_size()))
						_entity.setMeasure_id(Long.parseLong(pePdModel.getMd_size()));
					_entity.setMeasure_name(pePdModel.getMd_size());
				} else {
					super.renderTextOrJsonp(request, response, "型号选择出错，请重新选择型号并填报！");
					return null;
				}
			} else {
				super.renderTextOrJsonp(request, response, "请选择型号！");
				return null;
			}

			if (StringUtils.isNotEmpty(mastercode) && mastercode.length() > 18) {
				super.renderTextOrJsonp(request, response, "身份证号码格式错误！");
				return null;
			}

			// 销量
			String count = (String) dynaBean.get("sales_count");
			if (StringUtils.isNotEmpty(count)) {
				if (NumberUtils.isNumber(count)) {

					_entity.setNum(Long.parseLong(count));
					if (Long.parseLong(count) < -500L || Long.parseLong(count) > 500L) {
						super.renderTextOrJsonp(request, response, "请确认下销售数量是否正确，数量需要包含在-500台至500台之间！");
						return null;
					}
				} else {
					super.renderTextOrJsonp(request, response, "销量必须为数字！");
					return null;
				}
			} else {
				super.renderTextOrJsonp(request, response, "请填写数量！");
				return null;
			}

			// 价格（总价）
			String price = (String) dynaBean.get("sales_price");
			if (StringUtils.isNotEmpty(price)) {
				if (NumberUtils.isNumber(price)) {
					_entity.setAll_price(BigDecimal.valueOf(Double.parseDouble(price)));
					if (Double.parseDouble(price) / Long.parseLong(count) < 500
							|| Double.parseDouble(price) / Long.parseLong(count) > 90000) {
						super.renderTextOrJsonp(request, response, "请确认下销售单价是否正确，销售单价需要在500元至90000元之间！");
						return null;
					}
				} else {
					super.renderTextOrJsonp(request, response, "金额必须为数字！");
					return null;
				}
			} else {
				super.renderTextOrJsonp(request, response, "请填写金额，金额不能为空！");
				return null;
			}

			if (GenericValidator.isLong(count)) {
				_entity.setNum(Long.valueOf(count));
			}
			if (GenericValidator.isDouble(price)) {
				_entity.setAll_price(new BigDecimal(price));

			}
			// 单价
			String single_price = (String) dynaBean.get("single_price");
			if (StringUtils.isNotBlank(single_price)) {
				_entity.setSingle_price(new BigDecimal(single_price));
			} else {
				if (GenericValidator.isDouble(price) && GenericValidator.isLong(count) && !("0".equals(count))) {
					_entity.setSingle_price(BigDecimal.valueOf(Double.parseDouble(price)).divide(
							BigDecimal.valueOf(Double.parseDouble(count))));
				}
			}

			// 零售机型
			// if (GenericValidator.isInt(report_type)) {
			// _entity.setReport_type(Integer.parseInt(report_type));
			//
			// }
			_entity.setMemo(memo);
			// 消费者信息
			_entity.setRealname(realname);
			_entity.setPhonenum(phonenum);
			_entity.setAddresss(addresss);
			_entity.setMastercode(mastercode);
			if (StringUtils.isNotBlank(realname) && StringUtils.isNotBlank(phonenum)) {
				_entity.setStatus(1);
			}

			// 门店/客户
			String store_id = (String) dynaBean.get("store_id");
			if (StringUtils.isBlank(store_id)) {
				super.renderTextOrJsonp(request, response, "请选择门店！");
				return null;
			}

			store_id = store_id.trim();
			KonkaR3Store r3s = new KonkaR3Store();
			r3s.setStore_id(Long.parseLong(store_id));
			r3s = super.getFacade().getKonkaR3StoreService().getKonkaR3Store(r3s);

			if (null != r3s.getR3_code()) {
				Integer issales = 0;
				issales = super.getR3IsSales(r3s.getR3_code());// 判断R3客户是否允许负卖
				if (null != issales && issales == 1) {// 如果不允许负卖

					// 获取实时库存

					Long stock = 0L;
					// stock = super.getKhStocksOld(r3s.getR3_code(), md_name,
					// null);
					stock = super.getKhStocksWithMoney(r3s.getR3_code(), md_name, null);
					logger.info("参数：r3编码=" + r3s.getR3_code() + "，型号=" + md_name);
					KonkaMobileSailData kmsd = new KonkaMobileSailData();
					kmsd.setId(Long.parseLong(id));
					kmsd = super.getFacade().getKonkaMobileSailDataService().getKonkaMobileSailData(kmsd);
					Long num = 0L;
					if (kmsd != null) {
						num = kmsd.getNum();
					}
					// 如果修改的話，需要減掉修改之前的數量
					if (Long.valueOf(count) - num > stock) {// 判断销量是否大于库存
						super.renderTextOrJsonp(request, response, "销售数量大于库存数量，不能销售，请联系业务人员执行下订单后再进行销售，剩余库存数量为" + stock
								+ "台");
						return null;
					}
				}
				if (null == r3s.getCk_id()) {
					_entity.setSale_cost(new BigDecimal(0));
				} else {
					BigDecimal sale_cost = getSaleCost(r3s.getR3_code(), _entity.getModel_name(),
							Long.valueOf(r3s.getCk_id()), new BigDecimal(count));
					_entity.setSale_cost(sale_cost);
				}
				if (null != r3s.getCk_name()) {
					_entity.setChc_name(r3s.getCk_name());// 出货仓名称
				}
			}

			// if(null!=r3s.getR3_code() && null!=md_name){
			// _entity.setPrice_ref(BigDecimal.valueOf(getKonkaR3PdPrice2(md_name,r3s.getR3_code())));
			// }





			BigDecimal single_price_big= new BigDecimal (0);
			if (StringUtils.isNotBlank(single_price)) {
				single_price_big= new BigDecimal(single_price) ;
			} else {
				if (GenericValidator.isDouble(price) && GenericValidator.isLong(count) && !("0".equals(count))) {
					single_price_big=	(BigDecimal.valueOf(Double.parseDouble(price)).divide(
							BigDecimal.valueOf(Double.parseDouble(count))));
				}}
            //这是先进先出的东西
            KonkaMobileSailData old_entity = new KonkaMobileSailData();
            old_entity.setId(Long.parseLong(id));
            old_entity = super.getFacade().getKonkaMobileSailDataService().getKonkaMobileSailData(old_entity);

			if (null != old_entity && null != old_entity.getSingle_price() && null != old_entity.getNum()) {
				String stock_in_batch = super.getFacade().getJxcFifoStocksService()
						.getJxcFifoStockInBatch();
				BigDecimal single_price_old = old_entity.getSingle_price();
				Long number_old = old_entity.getNum();

				BigDecimal single_price_new = single_price_big;
				Long number_new = _entity.getNum();
				int num_diff = number_old.compareTo(number_new);
				int price_diff = single_price_new.compareTo(single_price_old);
				if (price_diff == 0) {//价格没变
					System.out.println("价格没有裱花");
					if (num_diff < 0) {
						// 销售量变大，多处的部分继续出仓
						System.out.println("变大了");
						super.getFacade()
								.getJxcFifoStocksService()
								.stock_out(new Long(store_id), new Long(pd_id), md_name, number_new - number_old,
										single_price_big, r3s.getR3_code(), r3s.getCk_id(), 530, peProdUser.getId());

					} else if (num_diff > 0) {
						//销量变小，相当于销售退货了
						System.out.println("变小了");

						super.getFacade()
								.getJxcFifoStocksService()
								.stock_in(r3s.getR3_code(), stock_in_batch, md_name, single_price_big,
										(int) (number_old-number_new), new Date(), 110, r3s.getR3_code());

					}
				} else {
					System.out.println("价格变化了");
					//价格已经变化
					if (number_old > 0) {

						super.getFacade()
								.getJxcFifoStocksService()
								.stock_in(r3s.getR3_code(), stock_in_batch, md_name, single_price_old,
										number_old.intValue(), new Date(), 110, r3s.getR3_code());
						if (number_new > 0) {
							super.getFacade()
									.getJxcFifoStocksService()
									.stock_out(new Long(store_id), new Long(pd_id), md_name, number_new,
											single_price_new, r3s.getR3_code(), r3s.getCk_id(), 530, peProdUser.getId());

						} else if (number_new < 0) {
							super.getFacade()
									.getJxcFifoStocksService()
									.stock_in(r3s.getR3_code(), stock_in_batch, md_name, single_price_new,
											number_new.intValue() * -1, new Date(), 110, r3s.getR3_code());
						}


					} else if (number_old < 0) {

						super.getFacade()
								.getJxcFifoStocksService()
								.stock_out(new Long(store_id), new Long(pd_id), md_name, number_old * -1,
										single_price_old, r3s.getR3_code(), r3s.getCk_id(), 530, peProdUser.getId());


						if (number_new > 0) {
							super.getFacade()
									.getJxcFifoStocksService()
									.stock_out(new Long(store_id), new Long(pd_id), md_name, number_new,
											single_price_new, r3s.getR3_code(), r3s.getCk_id(), 530, peProdUser.getId());

						} else if (number_new < 0) {
							super.getFacade()
									.getJxcFifoStocksService()
									.stock_in(r3s.getR3_code(), stock_in_batch, md_name, single_price_new,
											number_new.intValue() * -1, new Date(), 110, r3s.getR3_code());
						}


					}

				}
			}
			//这是先进先出的东西
			super.getFacade().getKonkaMobileSailDataService().modifyKonkaMobileSailData(_entity);
			super.renderTextOrJsonp(request, response, "success:" + id);
			return null;
		}

		KonkaMobileSailData entity = new KonkaMobileSailData();
		// 样机类型
		// String report_type = (String) dynaBean.get("report_type");
		// if (GenericValidator.isInt(report_type)){
		// entity.setReport_type(Integer.parseInt(report_type));
		// }
		// 备注
		String memo = (String) dynaBean.get("memo");
		if ("1".equals(from_html)) {
			memo = URLDecoder.decode(memo, "utf-8");
		}
		// 备注
		entity.setMemo(memo);

		// 销售日期
		// String sale_date = (String) dynaBean.get("sale_date");
		// 销售日期，默认当前日期

		entity.setSale_date(new java.util.Date());
		entity.setAudit_state(0);// 状态

		// 分公司
		KonkaDept fgs = super.getSuperiorForDeptType(peProdUser.getDept_id(), 3);
		if (null != fgs) {
			entity.setCust_subcomp_id(fgs.getDept_id());
			entity.setCust_subcomp_name(fgs.getDept_name());
		} else {
			entity.setCust_subcomp_id(peProdUser.getDept_id());
			entity.setCust_subcomp_name(peProdUser.getDepartment());
		}

		// 销量
		String count = (String) dynaBean.get("sales_count");
		if (StringUtils.isNotEmpty(count)) {
			if (NumberUtils.isNumber(count)) {

				entity.setNum(Long.parseLong(count));
				if (Long.parseLong(count) < -500L || Long.parseLong(count) > 500L) {
					super.renderTextOrJsonp(request, response, "请确认下销售数量是否正确，数量需要包含在-500台至500台之间！");
					return null;
				}
			} else {
				super.renderTextOrJsonp(request, response, "销量必须为数字！");
				return null;
			}
		} else {
			super.renderTextOrJsonp(request, response, "请填写数量！");
			return null;
		}

		// 型号
		String pd_id = (String) dynaBean.get("select-choice-2");
		// 型号名称
		if (!"0".equalsIgnoreCase(count)) {
			if (StringUtils.isNotEmpty(pd_id)) {
				pd_id = pd_id.trim();
				entity.setModel_id(new Long(pd_id));
				PePdModel pePdModel = new PePdModel();

				pePdModel.setIs_del(0);
				pePdModel.setPd_id(Long.valueOf(pd_id));
				pePdModel = super.getFacade().getPePdModelService().getPePdModel(pePdModel);
				if (pePdModel != null) {
					entity.setModel_name(pePdModel.getMd_name());
					entity.setCategory_id(pePdModel.getCls_id());
					md_name = pePdModel.getMd_name();
					entity.setPct_code(pePdModel.getMat_num());
					if (null != pePdModel.getMd_size() && !"".equals(pePdModel.getMd_size()))
						entity.setMeasure_id(Long.parseLong(pePdModel.getMd_size()));
					entity.setMeasure_name(pePdModel.getMd_size());
				} else {
					super.renderTextOrJsonp(request, response, "型号选择出错，请重新选择型号并填报！");
					return null;
				}
			} else {
				super.renderTextOrJsonp(request, response, "请选择型号！");
				return null;
			}
		}

		// 门店/客户
		String store_id = (String) dynaBean.get("store_id");
		if (StringUtils.isBlank(store_id)) {
			super.renderTextOrJsonp(request, response, "请选择门店！");
			return null;
		}

		store_id = store_id.trim();
		entity.setDept_id(new Long(store_id));
		KonkaR3Store r3s = new KonkaR3Store();
		r3s.setStore_id(Long.parseLong(store_id));
		r3s = super.getFacade().getKonkaR3StoreService().getKonkaR3Store(r3s);
		if (r3s != null) {
			entity.setDept_name(r3s.getStore_name());
			// 塞送达方r3编码
			if (StringUtils.isNotBlank(r3s.getMf_sn())) {
				entity.setSdf_r3_code(r3s.getMf_sn());
			} else if (StringUtils.isNotBlank(r3s.getR3_code())) {
				entity.setSdf_r3_code(r3s.getR3_code());
			}

			// 根据门店找分公司，经办
			VOrgOfDept vOrgOfDept = new VOrgOfDept();
			if (null != r3s.getDept_id()) {
				vOrgOfDept.setCur_dept_id(Long.valueOf(r3s.getDept_id()));
				List<VOrgOfDept> vOrgOfDeptList = super.getFacade().getVOrgOfDeptService()
						.getVOrgOfDeptList(vOrgOfDept);
				if (null != vOrgOfDeptList && vOrgOfDeptList.size() > 0) {
					vOrgOfDept = vOrgOfDeptList.get(0);
					if (null != vOrgOfDept.getDept_id() && GenericValidator.isLong(vOrgOfDept.getDept_id().toString())) {
						entity.setSubcomp_id(Long.valueOf(vOrgOfDept.getDept_id().toString()));
					}
					if (null != vOrgOfDept.getDept_name()) {
						entity.setSubcomp_name(vOrgOfDept.getDept_name());
					}
				}
			}
			VOrgOfDept vOrgOfDept1 = new VOrgOfDept();
			if (null != r3s.getJb_job_id() && GenericValidator.isLong(r3s.getJb_job_id())) {
				vOrgOfDept1.setCur_dept_id(Long.valueOf(r3s.getJb_job_id()));
				List<VOrgOfDept> vOrgOfDeptList = super.getFacade().getVOrgOfDeptService()
						.getVOrgOfDeptList(vOrgOfDept1);
				if (null != vOrgOfDeptList && vOrgOfDeptList.size() > 0) {
					vOrgOfDept = vOrgOfDeptList.get(0);
					if (null != vOrgOfDept.getCur_dept_id()) {
						entity.setOffice_id(vOrgOfDept.getCur_dept_id());
					}
					if (null != vOrgOfDept.getCur_dept_name()) {
						entity.setOffice_name(vOrgOfDept.getCur_dept_name());
					}
				}
			}

			// 根据客户找经办
			// entity.setCust_office_id(r3s.getJb_job_id());
			entity.setCust_office_name(r3s.getJb_name());
			if (null != r3s.getR3_code()) {
				Integer issales = 0;
				issales = super.getR3IsSales(r3s.getR3_code());// 判断R3客户是否允许负卖
				if (null != issales && issales == 1) {// 如果不允许负卖

					// 获取实时库存

					Long stock = 0L;
					stock = super.getKhStocksWithMoney(r3s.getR3_code(), md_name, null);
					logger.info("参数：r3编码=" + r3s.getR3_code() + "，型号=" + md_name);
					if (Long.valueOf(count) > stock) {// 判断销量是否大于库存
						super.renderTextOrJsonp(request, response, "销售数量大于库存数量，不能销售，请联系业务人员执行下订单后再进行销售，剩余库存数量为" + stock
								+ "台");
						return null;
					}
				}
			}
			// 查询客户信息
			if (null != r3s.getR3_code()) {
				KonkaR3Shop s = new KonkaR3Shop();
				s.setR3_code(r3s.getR3_code());
				s = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(s);

				if (null != s) {
					entity.setCustomer_id(s.getId());
					entity.setCustomer_name(s.getCustomer_name());
					entity.setCustomer_r3_code(s.getR3_code());
					entity.setCust_office_name(s.getHandle_name());

					if (null != s.getR3_code()) {
						MvOrgOfCustomer vOrgOfCustomer = new MvOrgOfCustomer();
						vOrgOfCustomer.setR3_code(s.getR3_code());
						List<MvOrgOfCustomer> vlist = super.getFacade().getMvOrgOfCustomerService()
								.getMvOrgOfCustomerList(vOrgOfCustomer);
						if (null != vlist && vlist.size() > 0) {
							vOrgOfCustomer = vlist.get(0);
							entity.setCust_office_id(vOrgOfCustomer.getCur_dept_id());// 客户经办id
							entity.setCust_office_name(vOrgOfCustomer.getCur_dept_name());// 客户经办名称
						}
					}
					if (GenericValidator.isLong(s.getCustomer_type())) {
						entity.setCustomer_cate_id(new Long(s.getCustomer_type()));

						KonkaCategory kc = new KonkaCategory();
						kc.setC_index(entity.getCustomer_cate_id());
						kc.setC_type(10);
						kc = super.getFacade().getKonkaCategoryService().getKonkaCategory(kc);

						if (null != kc) {
							entity.setCustomer_cate_name("[" + kc.getC_comm() + "]" + kc.getC_name());
						}
					}
					if (null == r3s.getCk_id() || null == entity.getModel_name() || null == r3s.getR3_code()) {
						entity.setSale_cost(new BigDecimal(0));
					} else {
						BigDecimal sale_cost = getSaleCost(r3s.getR3_code(), entity.getModel_name(),
								Long.valueOf(r3s.getCk_id()), new BigDecimal(count));
						entity.setSale_cost(sale_cost);
					}
					if (null != r3s.getCk_name()) {
						entity.setChc_name(r3s.getCk_name());// 出货仓名称
					}
				}
			}
		}
		// if(null!=r3s.getR3_code() && null!=md_name){
		// entity.setPrice_ref(BigDecimal.valueOf(getKonkaR3PdPrice2(md_name,r3s.getR3_code())));
		// }
		// 价格
		String price = (String) dynaBean.get("sales_price");
		if (StringUtils.isNotEmpty(price)) {
			if (NumberUtils.isNumber(price)) {
				entity.setAll_price(BigDecimal.valueOf(Double.parseDouble(price)));
				if (Double.parseDouble(price) / Long.parseLong(count) < 500
						|| Double.parseDouble(price) / Long.parseLong(count) > 90000) {
					super.renderTextOrJsonp(request, response, "请确认下销售单价是否正确，销售单价需要在500元至90000元之间！");
					return null;
				}
			} else {
				super.renderTextOrJsonp(request, response, "金额必须为数字！");
				return null;
			}
		} else {
			super.renderTextOrJsonp(request, response, "请填写金额！");
			return null;
		}

		// 单价
		String single_price = (String) dynaBean.get("single_price");
		if (StringUtils.isNotBlank(single_price)) {
			entity.setSingle_price(new BigDecimal(single_price));
		} else {
			if (GenericValidator.isDouble(price) && GenericValidator.isLong(count) && !("0".equals(count))) {
				entity.setSingle_price(BigDecimal.valueOf(Double.parseDouble(price)).divide(
						BigDecimal.valueOf(Double.parseDouble(count))));
			}
		}

		// 消费者姓名
		String realname = (String) dynaBean.get("realname");
		// 消费者电话
		String phonenum = (String) dynaBean.get("phonenum");
		// 消费者住址
		String addresss = (String) dynaBean.get("addresss");
		// 消费者身份证号
		String mastercode = (String) dynaBean.get("mastercode");

		if ("1".equals(from_html)) {
			realname = URLDecoder.decode(realname, "utf-8");
			addresss = URLDecoder.decode(addresss, "utf-8");
		}

		if (StringUtils.isNotEmpty(mastercode) && mastercode.length() > 18) {
			super.renderTextOrJsonp(request, response, "身份证号码格式错误！");
			return null;
		}

		// 消费者信息
		entity.setRealname(realname);
		entity.setPhonenum(phonenum);
		entity.setAddresss(addresss);
		entity.setMastercode(mastercode);
		if (StringUtils.isNotBlank(realname) && StringUtils.isNotBlank(phonenum)) {
			entity.setStatus(1);
		} else {
			entity.setStatus(0);
		}

		entity.setReport_id(peProdUser.getId());
		entity.setReport_date(new java.util.Date());
		entity.setReport_name(peProdUser.getUser_name());

		if (!"0".equalsIgnoreCase(count) && null != fgs) {
			KonkaMobileMdPercent kmm = new KonkaMobileMdPercent();
			kmm.setStatus(0);
			kmm.setPd_id(entity.getModel_id());
			kmm.setDept_id(fgs.getDept_id());
			kmm = super.getFacade().getKonkaMobileMdPercentService().getKonkaMobileMdPercent(kmm);
			if (kmm != null) {
				if (kmm.getType() == 0) {
					BigDecimal deduct = new BigDecimal(0);
					deduct = entity.getAll_price().multiply(kmm.getPercent());
					deduct = deduct.divide(new BigDecimal(100));
					entity.setDeduct(deduct);
					entity.setRule_id(kmm.getId());
				} else if (kmm.getType() == 1) {
					entity.setDeduct(kmm.getPercent());
					entity.setRule_id(kmm.getId());
				}
			} else {
				entity.setDeduct(new BigDecimal(0));
			}
		} else {
			entity.setDeduct(new BigDecimal(0));
		}

		if ("1".equals(from_html)) {
			entity.setData_source(1);
		}

		Long lid = super.getFacade().getKonkaMobileSailDataService().createKonkaMobileSailData(entity);

		BigDecimal single_price_big= new BigDecimal (0);
		if (StringUtils.isNotBlank(single_price)) {
			 single_price_big= new BigDecimal(single_price) ;
		} else {
			if (GenericValidator.isDouble(price) && GenericValidator.isLong(count) && !("0".equals(count))) {
				 single_price_big=	(BigDecimal.valueOf(Double.parseDouble(price)).divide(
						BigDecimal.valueOf(Double.parseDouble(count))));
			}}
//		先进先出的东西
        if (entity.getNum() > 0) {
                super.getFacade()
                        .getJxcFifoStocksService()
                        .stock_out(new Long(store_id), new Long(pd_id), md_name, entity.getNum(),
								single_price_big, r3s.getR3_code(), r3s.getCk_id(), 530, peProdUser.getId());

        } else {
                JBaseStore jBaseStore = new JBaseStore();
                jBaseStore.getMap().put("sale_r3_code", r3s.getR3_code());
                jBaseStore.getMap().put("r3_code", r3s.getR3_code());
                jBaseStore.setIs_del(0);
                List<JBaseStore> storeList = super.getFacade().getJBaseStoreService()
                        .getJBaseStoreForR3List(jBaseStore);
                if (null != storeList && storeList.size() > 0) {
                    jBaseStore = storeList.get(0);
                }
                super.getFacade()
                        .getJxcFifoStocksService()
                        .stock_in(jBaseStore.getStore_id(), new Long(pd_id),single_price_big,entity.getNum().intValue()*-1,
                                new Date(), 60);
        }
//		先进先出的东西

		generateHis(entity, null, null, null, null, null, null);
		String success = "success";
		// 如果是第二个版本，则需要返回lid，方便手机端上传附件
		if ("1".equals(from_html)) {// 如果是从web端方式，必须增加lid返回
			success = success + ":" + lid;
		} else {// 如果不是，则需要判断，如果老版本号，不需要增加lid返回
			if (version != null) {
				success = success + ":" + lid;
			}
		}
		super.renderTextOrJsonp(request, response, success);

		return null;
	}

	public ActionForward SaveAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String lid = (String) dynaBean.get("lid");
		String username = (String) dynaBean.get("username");
		String userpass = (String) dynaBean.get("userpass");
		if (!GenericValidator.isLong(lid)) {
			this.saveError(request, "errors.long", new String[] { lid });
			return null;
		}
		logger.info("--------------------lid==" + lid);
		logger.info("--------------------username==" + username);
		logger.info("--------------------userpass==" + userpass);
		KonkaMobileSailData kbs = new KonkaMobileSailData();
		
		super.copyProperties(kbs, form);
		// 附件处理
		List<UploadFile> uploadFileList = super
				.uploadFile(form, MmtFilePathConfig.LST_PATH, true, 0, new int[] { 960 });
		List<Attachment> filesAttachmentList = new ArrayList<Attachment>();
		Attachment filesAttachment = null;
		if (null != uploadFileList && uploadFileList.size() > 0) {
			for (UploadFile uploadFile : uploadFileList) {
				filesAttachment = new Attachment();
				filesAttachment.setFile_name(uploadFile.getFileName());
				filesAttachment.setFile_type(uploadFile.getContentType());
				filesAttachment.setFile_size(new Integer(uploadFile.getFileSize()));
				filesAttachment.setSave_path(uploadFile.getFileSavePath());
				filesAttachment.setSave_name(uploadFile.getFileSaveName());
				filesAttachment.setDel_mark(new Short("0"));
				filesAttachmentList.add(filesAttachment);
			}
		}
		kbs.setAttachmentList(filesAttachmentList);
		super.getFacade().getKonkaMobileSailDataService().createKonkaMobileSailData(kbs, Long.valueOf(lid));

		DESPlus des = new DESPlus();
		PeProdUser u = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		String url = super.getCtxPath(request) + "/mobile/webstatic/shuj.html?from_pc=1&success=" + lid + "&username="
				+ this.escape(username) + "&userpass=" + escape(userpass) + "&timestamp=" + new Date().getTime();
		logger.info("----url--->{}", url);

		// super.renderTextJsonOrJsonp(request, response, "success:"+lid);
		super.renderJavaScript(response, "window.onload=function(){ location.href = '" + url + "'; }");
		return null;

	}
	public ActionForward SaveAttachmentForJingp(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String lid = (String) dynaBean.get("lid");
		String username = (String) dynaBean.get("username");
		String userpass = (String) dynaBean.get("userpass");
		if (!GenericValidator.isLong(lid)) {
			this.saveError(request, "errors.long", new String[] { lid });
			return null;
		}
		logger.info("--------------------lid==" + lid);
		logger.info("--------------------username==" + username);
		logger.info("--------------------userpass==" + userpass);
		KonkaMobileSailData kbs = new KonkaMobileSailData();
		
		super.copyProperties(kbs, form);
		// 附件处理
		List<UploadFile> uploadFileList = super
				.uploadFile(form, MmtFilePathConfig.LST_PATH, true, 0, new int[] { 960 });
		Attachment filesAttachment = null;
		if (null != uploadFileList && uploadFileList.size() > 0) {
			for (UploadFile uploadFile : uploadFileList) {
				filesAttachment = new Attachment();
				filesAttachment.setFile_name(uploadFile.getFileName());
				filesAttachment.setFile_type(uploadFile.getContentType());
				filesAttachment.setFile_size(new Integer(uploadFile.getFileSize()));
				filesAttachment.setSave_path(uploadFile.getFileSavePath());
				filesAttachment.setSave_name(uploadFile.getFileSaveName());
				filesAttachment.setDel_mark(new Short("0"));
				filesAttachment.setLink_tab("KONKA_MOBILE_FIGHT_REPORT");
				filesAttachment.setLink_id(Long.valueOf(lid));
				super.getFacade().getAttachmentService().createAttachment(filesAttachment);
			}
		}
//		DESPlus des = new DESPlus();
		PeProdUser u = (PeProdUser) super.getSessionAttribute(request, Constants.USER_INFO);
		String url = super.getCtxPath(request) + "/mobile/webstatic/jingp.html?from_pc=1&success=" + lid + "&username="
				+ this.escape(username) + "&userpass=" + escape(userpass) + "&timestamp=" + new Date().getTime();

		super.renderJavaScript(response, "window.onload=function(){ location.href = '" + url + "'; }");
		return null;

	}
	
	public ActionForward SaveAttachmentForBillForMobile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String lid = (String) dynaBean.get("lid");
		String username = (String) dynaBean.get("username");
		String userpass = (String) dynaBean.get("userpass");
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		String isQxtj = (String) dynaBean.get("isQxtj");

		if (!GenericValidator.isLong(lid)) {
			this.saveError(request, "errors.long", new String[] { lid });
			return null;
		}
		if (StringUtils.isBlank(username) || StringUtils.isBlank(userpass)) {
			this.saveError(request, "用户名密码错误", new String[] { username, userpass });
			return null;
		}

		// 判断是否是静态页面来的数据，目的处理乱码问题
		String from_html = (String) dynaBean.get("from_html");
		if ("1".equals(from_html)) {
			username = URLDecoder.decode(username, "utf-8");
			userpass = URLDecoder.decode(userpass, "utf-8");
		}

		PeProdUser peProdUser = checkUser(username, userpass,android_version,ios_version);
		String bill_id = (String) dynaBean.get("bill_id");// 票据ID
		String bill_no = (String) dynaBean.get("bill_no");// 票据编号
		String dec_money = (String) dynaBean.get("dec_money");// 申请提成金额
		String audit_money = (String) dynaBean.get("audit_money");// 初审金额
		String final_audit_money = (String) dynaBean.get("final_audit_money");// 终审金额
		String bill_mem = (String) dynaBean.get("bill_mem");// 备注
		String is_valid_for_pay = (String) dynaBean.get("is_valid_for_pay");// 是否参与提成核算
		// 0表示参与
		// 1表示不参与

		// id
		String id = (String) dynaBean.get("id");
		String url = request.getRequestURI();
		//System.out.println("URL------------>" + url);

		KonkaMobileSailDataBill konkaMobileSailDataBill = new KonkaMobileSailDataBill();
		if (StringUtils.isNotBlank(isQxtj) && "1".equals(isQxtj)) {
			// 修改或者重新提交时什么都不做
		} else {
			konkaMobileSailDataBill.setBill_no(bill_no);
			konkaMobileSailDataBill.setIs_valid(0);
			List<KonkaMobileSailDataBill> konkaMobileSailDataBillList = super.getFacade()
					.getKonkaMobileSailDataBillService().getKonkaMobileSailDataBillList(konkaMobileSailDataBill);
			if (null != konkaMobileSailDataBillList && konkaMobileSailDataBillList.size() > 0) {
				super.renderTextJsonOrJsonp(request, response, "{\"status\":\"1\", \"msg\":\"该发票编号已经存在！\"}");
				// super.renderTextJsonOrJsonp(request, response, "该发票编号已经存在！");
				return null;
			}
		}
		konkaMobileSailDataBill = new KonkaMobileSailDataBill();
		if (StringUtils.isNotBlank(bill_id) && GenericValidator.isLong(bill_id)) {
			konkaMobileSailDataBill.setBill_id(Long.valueOf(bill_id));
			konkaMobileSailDataBill = super.getFacade().getKonkaMobileSailDataBillService()
					.getKonkaMobileSailDataBill(konkaMobileSailDataBill);

			// 该bill_id不为空时表示是修改并要删除之前的附件
			if (null != konkaMobileSailDataBill && null != konkaMobileSailDataBill.getAdjunct_link_id()) {
				Attachment attachment = new Attachment();
				attachment.setId(konkaMobileSailDataBill.getAdjunct_link_id());
				super.getFacade().getAttachmentService().removeAttachment(attachment);
			}
		} else {
			konkaMobileSailDataBill.setIs_valid(0);// 是否有效
			konkaMobileSailDataBill.setState(0);
			if (null != peProdUser.getId()) {
				konkaMobileSailDataBill.setUpload_user_id(peProdUser.getId());// 上传人ID
			}
			konkaMobileSailDataBill.setData_source(1);// 数据来源:0-手机端 1-WEB端
			// 2-IOS手机端
			konkaMobileSailDataBill.setUpload_time(new Date());
		}
		konkaMobileSailDataBill.setSail_id(Long.valueOf(lid));
		if (StringUtils.isNotBlank(bill_no)) {
			konkaMobileSailDataBill.setBill_no(bill_no);// 票据编号
		}
		if (StringUtils.isNotBlank(dec_money) && GenericValidator.isLong(dec_money)) {
			konkaMobileSailDataBill.setDec_money(new BigDecimal(dec_money));// 申请提成金额
		}
		if (StringUtils.isNotBlank(audit_money) && GenericValidator.isLong(audit_money)) {
			konkaMobileSailDataBill.setAudit_money(new BigDecimal(audit_money));// 初审金额
		}
		if (StringUtils.isNotBlank(final_audit_money) && GenericValidator.isLong(final_audit_money)) {
			konkaMobileSailDataBill.setFinal_audit_money(new BigDecimal(final_audit_money));// 终审金额
		}
		if (StringUtils.isNotBlank(bill_mem)) {
			konkaMobileSailDataBill.setBill_mem(bill_mem);// 票据备注
		}
		if (StringUtils.isNotBlank(is_valid_for_pay) && GenericValidator.isInt(is_valid_for_pay)) {
			konkaMobileSailDataBill.setIs_valid_for_pay(Integer.parseInt(is_valid_for_pay));
		}
		logger.info("--------------------lid==" + lid);
		logger.info("--------------------username==" + username);
		logger.info("--------------------userpass==" + userpass);
		KonkaMobileSailData kbs = new KonkaMobileSailData();

		super.copyProperties(kbs, form);
		// 附件处理
		List<UploadFile> uploadFileList = super
				.uploadFile(form, MmtFilePathConfig.LST_PATH, true, 0, new int[] { 960 });
		List<Attachment> filesAttachmentList = new ArrayList<Attachment>();
		Attachment filesAttachment = null;
		if (null != uploadFileList && uploadFileList.size() > 0) {
			for (UploadFile uploadFile : uploadFileList) {
				filesAttachment = new Attachment();
				filesAttachment.setFile_name(uploadFile.getFileName());
				filesAttachment.setFile_type(uploadFile.getContentType());
				filesAttachment.setFile_size(new Integer(uploadFile.getFileSize()));
				filesAttachment.setSave_path(uploadFile.getFileSavePath());
				filesAttachment.setSave_name(uploadFile.getFileSaveName());
				filesAttachment.setDel_mark(new Short("0"));
				filesAttachmentList.add(filesAttachment);
				konkaMobileSailDataBill.setAttachment(filesAttachment);
			}
		}
		kbs.setAttachmentList(filesAttachmentList);
		List<KonkaMobileSailDataBill> KonkaMobileSailDataBillList = new ArrayList<KonkaMobileSailDataBill>();
		KonkaMobileSailDataBillList.add(konkaMobileSailDataBill);
		kbs.setKonkaMobileSailDataBillList(KonkaMobileSailDataBillList);
		super.getFacade().getKonkaMobileSailDataService().createKonkaMobileSailDataAndBill(kbs, Long.valueOf(lid));
//		DESPlus des = new DESPlus();
		// PeProdUser u = (PeProdUser) super.getSessionAttribute(request,
		// Constants.USER_INFO);
		String url1 = super.getCtxPath(request) + "/mobile/webstatic/shuj.html?from_pc=1&username="
				+ this.escape(username) + "&userpass=" + escape(userpass) + "&timestamp=" + new Date().getTime();
		logger.info("----url--->{}", url1);
		// super.renderJavaScript(response,
		// "window.onload=function(){ location.href = '" + url1 + "'; }");
		String success = "success";
		success = success + ":" + lid;
		super.renderTextOrJsonp(request, response, success);
		return null;

	}

	public ActionForward SaveAttachmentForBill(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String lid = (String) dynaBean.get("lid");
		String username = (String) dynaBean.get("username");
		String userpass = (String) dynaBean.get("userpass");
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		PeProdUser peProdUser = checkUser(username, userpass,android_version,ios_version);

		String ext_url = (String) dynaBean.get("ext_url");
		String[] bill_ids = request.getParameterValues("bill_id");// 票据ID
		String[] bill_nos = request.getParameterValues("bill_no");// 票据编号
		String[] dec_moneys = request.getParameterValues("dec_money");// 提成金额
		String[] bill_mems = request.getParameterValues("bill_mem");// 备注
		String[] is_valid_for_pays = request.getParameterValues("is_valid_for_pay");// 是否参与提成核算
		// 0表示参与
		// 1表示不参与

		if (!GenericValidator.isLong(lid)) {
			this.saveError(request, "errors.long", new String[] { lid });
			return null;
		}
		logger.info("--------------------lid==" + lid);
		logger.info("--------------------username==" + username);
		logger.info("--------------------userpass==" + userpass);
		KonkaMobileSailData kbs = new KonkaMobileSailData();

		super.copyProperties(kbs, form);
		// 附件处理
		List<UploadFile> uploadFileList = super
				.uploadFile(form, MmtFilePathConfig.LST_PATH, true, 0, new int[] { 240 });
		Attachment filesAttachment = null;
		List<KonkaMobileSailDataBill> konkaMobileSailDataBillList = new ArrayList<KonkaMobileSailDataBill>();

		if (null != bill_nos && bill_nos.length > 0) {
			for (int i = 1; i < bill_nos.length; i++) {
				KonkaMobileSailDataBill konkaMobileSailDataBill = new KonkaMobileSailDataBill();
				if (null != bill_ids && null != bill_ids[i] && GenericValidator.isLong(bill_ids[i])) {
					konkaMobileSailDataBill.setBill_id(Long.valueOf(bill_ids[i]));
					konkaMobileSailDataBill = super.getFacade().getKonkaMobileSailDataBillService()
							.getKonkaMobileSailDataBill(konkaMobileSailDataBill);
				} else {
					konkaMobileSailDataBill.setIs_valid(0);// 是否有效
					konkaMobileSailDataBill.setState(0);
					konkaMobileSailDataBill.setUpload_user_id(peProdUser.getId());// 上传人ID
					konkaMobileSailDataBill.setData_source(1);// 数据来源:0-手机端
					// 1-WEB端
					// 2-IOS手机端
					konkaMobileSailDataBill.setUpload_time(new Date());
				}
				konkaMobileSailDataBill.setSail_id(Long.valueOf(lid));
				if (null != bill_nos && null != bill_nos[i]) {
					konkaMobileSailDataBill.setBill_no(bill_nos[i]);// 票据编号
				}
				if (null != dec_moneys && StringUtils.isNotBlank(dec_moneys[i])) {
					konkaMobileSailDataBill.setDec_money(new BigDecimal(dec_moneys[i]));// 申请提成金额
				}
				if (null != bill_mems && null != bill_mems[i]) {
					konkaMobileSailDataBill.setBill_mem(bill_mems[i]);// 票据备注
				}
				if (null != is_valid_for_pays && null != is_valid_for_pays[i]) {
					konkaMobileSailDataBill.setIs_valid_for_pay(Integer.parseInt(is_valid_for_pays[i]));
				}
				if (null != uploadFileList && uploadFileList.size() > 0) {
					for (UploadFile uploadFile : uploadFileList) {
						if (("file_show_" + i).endsWith(uploadFile.getFormName())) {
							filesAttachment = new Attachment();
							logger.info("FormName:-------------->" + uploadFile.getFormName());
							filesAttachment.setFile_name(uploadFile.getFileName());
							filesAttachment.setFile_type(uploadFile.getContentType());
							filesAttachment.setFile_size(new Integer(uploadFile.getFileSize()));
							filesAttachment.setSave_path(uploadFile.getFileSavePath());
							filesAttachment.setSave_name(uploadFile.getFileSaveName());
							filesAttachment.setDel_mark(new Short("0"));
							konkaMobileSailDataBill.setAttachment(filesAttachment);
						}
					}
				}
				konkaMobileSailDataBillList.add(konkaMobileSailDataBill);
			}
		}
		kbs.setKonkaMobileSailDataBillList(konkaMobileSailDataBillList);
		super.getFacade().getKonkaMobileSailDataService().createKonkaMobileSailDataAndBill(kbs, Long.valueOf(lid));

//		DESPlus des = new DESPlus();
		// PeProdUser u = (PeProdUser) super.getSessionAttribute(request,
		// Constants.USER_INFO);
		String url_for_is_null = "shuj_bill_audit_his.tml";
		String url = super.getCtxPath(request) + "/mobile/webstatic/" + (null == ext_url ? url_for_is_null : ext_url)
				+ "?from_pc=1&username=" + this.escape(username) + "&userpass=" + escape(userpass) + "&timestamp="
				+ new Date().getTime();
		logger.info("----url--->{}", url);
		super.renderJavaScript(response, "window.onload=function(){ location.href = '" + url + "'; }");

		return null;

	}

	// 添加页使用的保存
	public ActionForward SaveAttachmentForBill1(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String lid = (String) dynaBean.get("lid");
		String username = (String) dynaBean.get("username");
		String userpass = (String) dynaBean.get("userpass");
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		PeProdUser peProdUser = checkUser(username, userpass,android_version,ios_version);

		String ext_url = (String) dynaBean.get("ext_url");

		String[] bill_ids = request.getParameterValues("bill_id");// 票据ID
		String[] bill_nos = request.getParameterValues("bill_no");// 票据编号
		String[] dec_moneys = request.getParameterValues("dec_money");// 提成金额
		String[] bill_mems = request.getParameterValues("bill_mem");// 备注
		String[] is_valid_for_pays = request.getParameterValues("is_valid_for_pay");// 是否参与提成核算
		// 0表示参与
		// 1表示不参与

		String is_update = (String) dynaBean.get("is_update");

		if (!GenericValidator.isLong(lid)) {
			this.saveError(request, "errors.long", new String[] { lid });
			return null;
		}
		logger.info("--------------------lid==" + lid);
		logger.info("--------------------username==" + username);
		logger.info("--------------------userpass==" + userpass);
		KonkaMobileSailData kbs = new KonkaMobileSailData();

		super.copyProperties(kbs, form);
		// 附件处理
		List<UploadFile> uploadFileList = super
				.uploadFile(form, MmtFilePathConfig.LST_PATH, true, 0, new int[] { 240 });
		Attachment filesAttachment = null;
		List<KonkaMobileSailDataBill> konkaMobileSailDataBillList = new ArrayList<KonkaMobileSailDataBill>();

		if (null != bill_nos && bill_nos.length > 0) {
			for (int i = 1; i < bill_nos.length; i++) {
				KonkaMobileSailDataBill konkaMobileSailDataBill = new KonkaMobileSailDataBill();
				if (null != bill_ids && null != bill_ids[i] && GenericValidator.isLong(bill_ids[i])) {
					konkaMobileSailDataBill.setBill_id(Long.valueOf(bill_ids[i]));
					konkaMobileSailDataBill = super.getFacade().getKonkaMobileSailDataBillService()
							.getKonkaMobileSailDataBill(konkaMobileSailDataBill);
				} else {
					konkaMobileSailDataBill.setIs_valid(0);// 是否有效
					konkaMobileSailDataBill.setState(0);
					konkaMobileSailDataBill.setUpload_user_id(peProdUser.getId());// 上传人ID
					konkaMobileSailDataBill.setData_source(1);// 数据来源:0-手机端
					// 1-WEB端
					// 2-IOS手机端
					konkaMobileSailDataBill.setUpload_time(new Date());
				}
				konkaMobileSailDataBill.setSail_id(Long.valueOf(lid));
				if (null != bill_nos && null != bill_nos[i]) {
					konkaMobileSailDataBill.setBill_no(bill_nos[i]);// 票据编号
				}
				if (null != dec_moneys && StringUtils.isNotBlank(dec_moneys[i])) {
					konkaMobileSailDataBill.setDec_money(new BigDecimal(dec_moneys[i]));// 申请提成金额
				}
				if (null != bill_mems && null != bill_mems[i]) {
					konkaMobileSailDataBill.setBill_mem(bill_mems[i]);// 票据备注
				}
				if (null != is_valid_for_pays && null != is_valid_for_pays[i]) {
					konkaMobileSailDataBill.setIs_valid_for_pay(Integer.parseInt(is_valid_for_pays[i]));
				}
				if (StringUtils.isEmpty(is_update)) {
					if (null != uploadFileList && uploadFileList.size() > i - 1) {
						UploadFile uploadFile = uploadFileList.get(i - 1);
						filesAttachment = new Attachment();
						filesAttachment.setFile_name(uploadFile.getFileName());
						filesAttachment.setFile_type(uploadFile.getContentType());
						filesAttachment.setFile_size(new Integer(uploadFile.getFileSize()));
						filesAttachment.setSave_path(uploadFile.getFileSavePath());
						filesAttachment.setSave_name(uploadFile.getFileSaveName());
						filesAttachment.setDel_mark(new Short("0"));
						konkaMobileSailDataBill.setAttachment(filesAttachment);
					}
				} else {
					if (null != uploadFileList && uploadFileList.size() > 0) {
						for (UploadFile uploadFile : uploadFileList) {
							if (("file_show_" + i).endsWith(uploadFile.getFormName())) {
								filesAttachment = new Attachment();
								logger.info("FormName:-------------->" + uploadFile.getFormName());
								filesAttachment.setFile_name(uploadFile.getFileName());
								filesAttachment.setFile_type(uploadFile.getContentType());
								filesAttachment.setFile_size(new Integer(uploadFile.getFileSize()));
								filesAttachment.setSave_path(uploadFile.getFileSavePath());
								filesAttachment.setSave_name(uploadFile.getFileSaveName());
								filesAttachment.setDel_mark(new Short("0"));
								konkaMobileSailDataBill.setAttachment(filesAttachment);
							}
						}
					}
				}

				konkaMobileSailDataBillList.add(konkaMobileSailDataBill);
			}
		}
		kbs.setKonkaMobileSailDataBillList(konkaMobileSailDataBillList);
		super.getFacade().getKonkaMobileSailDataService().createKonkaMobileSailDataAndBill(kbs, Long.valueOf(lid));

//		DESPlus des = new DESPlus();
		// PeProdUser u = (PeProdUser) super.getSessionAttribute(request,
		String url_for_is_null = "shuj_bill_audit_his.tml";
		String url = super.getCtxPath(request) + "/mobile/webstatic/" + (null == ext_url ? url_for_is_null : ext_url)
				+ "?from_pc=1&username=" + this.escape(username) + "&userpass=" + escape(userpass) + "&timestamp="
				+ new Date().getTime();
		logger.info("----url--->{}", url);
		super.renderJavaScript(response, "window.onload=function(){ location.href = '" + url + "'; }");

		return null;

	}

	// 门店库存
	public ActionForward DoSubmit02(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		// 用户名
		String username = (String) dynaBean.get("username");
		// 密码
		String userpass = (String) dynaBean.get("userpass");
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		PeProdUser peProdUser = checkUser(username, userpass,android_version,ios_version);

		if (null == peProdUser) {
			super.renderText(response, "用户信息出错，请联系管理员！");
			return null;
		}

		KonkaMobileShopStore entity = new KonkaMobileShopStore();

		// 办事处
		entity.setSubcomp_id(peProdUser.getDept_id());
		entity.setSubcomp_name(peProdUser.getDepartment());

		// 商品条码
		String goodscode = (String) dynaBean.get("goodscode");
		// 商品条码
		if (!StringUtils.isEmpty(goodscode))
			entity.setPct_code(goodscode);
		else {
			super.renderText(response, "请录入商品条码");
			return null;
		}

		// 备注
		String memo = (String) dynaBean.get("memo");
		entity.setMemo(memo);

		// 品类
		// String cls_id = (String) dynaBean.get("select-choice-1");
		// // 品类名称
		// BasePdClazz basePdClazz = new BasePdClazz();
		// if (StringUtils.isNotEmpty(cls_id)) {
		// entity.setCategory_id(new Long(cls_id));
		//
		// basePdClazz.setCls_id(Long.valueOf(cls_id));
		// basePdClazz = super.getFacade().getBasePdClazzService()
		// .getBasePdClazz(basePdClazz);
		// if (basePdClazz != null) {
		// String[] name = basePdClazz.getFull_name().split(",");
		// entity.setCategory_name(name[name.length - 1]);
		// }
		// } else {
		// super.renderText(response, "请选择品类！");
		// return null;
		// }

		// 型号
		String pd_id = (String) dynaBean.get("select-choice-2");
		// 型号名称
		if (StringUtils.isNotEmpty(pd_id)) {
			entity.setModel_id(new Long(pd_id));
			PePdModel pePdModel = new PePdModel();
			pePdModel.setPd_id(Long.valueOf(pd_id));
			pePdModel = super.getFacade().getPePdModelService().getPePdModel(pePdModel);
			if (pePdModel != null) {
				entity.setModel_name(pePdModel.getMd_name());
			}
		} else {
			super.renderText(response, "请选择型号！");
			return null;
		}

		// // 尺寸
		// String pro_id = (String) dynaBean.get("select-choice-3");
		// // 尺寸名称
		// entity.setMeasure_id(Long.parseLong(pro_id));
		// entity.setMeasure_name(pro_id);

		// 数量
		String count = (String) dynaBean.get("sales_count");
		if (StringUtils.isNotEmpty(count))
			entity.setNum(Long.parseLong(count));
		else {
			super.renderText(response, "请填写数量");
			return null;
		}

		// 门店
		String store_id = (String) dynaBean.get("store_id");
		if (StringUtils.isNotEmpty(store_id))
			entity.setDept_id(new Long(store_id));
		else {
			super.renderText(response, "请选择门店！");
			return null;
		}

		// 截止时间
		String gene_date = (String) dynaBean.get("gene_date");
		if (StringUtils.isNotEmpty(gene_date))
			entity.setGene_date(df1.parse(gene_date));
		else {
			entity.setGene_date(new java.util.Date());
		}
		entity.setReport_id(peProdUser.getId());
		entity.setReport_date(new java.util.Date());
		entity.setReport_name(peProdUser.getUser_name());

		super.getFacade().getKonkaMobileShopStoreService().createKonkaMobileShopStore(entity);
		generateHis(null, entity, null, null, null, null, null);
		super.renderText(response, "success");
		return null;
	}

	// 退货登记
	public ActionForward DoSubmit03(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		// 用户名
		String username = (String) dynaBean.get("username");
		// 密码
		String userpass = (String) dynaBean.get("userpass");

		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		PeProdUser peProdUser = checkUser(username, userpass,android_version,ios_version);

		if (null == peProdUser) {
			super.renderText(response, "用户信息出错，请重新登陆后提交！");
			return null;
		}

		KonkaMobileSailsReturn entity = new KonkaMobileSailsReturn();

		// 商品条码
		String goodscode = (String) dynaBean.get("goodscode");
		// 商品条码
		if (!StringUtils.isEmpty(goodscode))
			entity.setPct_code(goodscode);
		else {
			super.renderText(response, "请录入商品条码");
			return null;
		}

		// 备注
		String memo = (String) dynaBean.get("memo");
		// 备注
		entity.setMemo(memo);

		String return_reason = (String) dynaBean.get("return_reason");
		if (!StringUtils.isEmpty(return_reason))
			entity.setReturn_reason(Integer.parseInt(return_reason));
		else {
			super.renderText(response, "请选择退货原因");
			return null;
		}

		// 办事处
		entity.setSubcomp_id(peProdUser.getDept_id());
		entity.setSubcomp_name(peProdUser.getDepartment());

		// // 品类
		// String cls_id = (String) dynaBean.get("select-choice-1");
		// // 品类名称
		// BasePdClazz basePdClazz = new BasePdClazz();
		// if (StringUtils.isNotEmpty(cls_id)) {
		// entity.setCategory_id(new Long(cls_id));
		//
		// basePdClazz.setCls_id(Long.valueOf(cls_id));
		// basePdClazz = super.getFacade().getBasePdClazzService()
		// .getBasePdClazz(basePdClazz);
		// if (basePdClazz != null) {
		// String[] name = basePdClazz.getFull_name().split(",");
		// entity.setCategory_name(name[name.length - 1]);
		// }
		// } else {
		// super.renderText(response, "请选择品类！");
		// return null;
		// }

		// 型号
		String pd_id = (String) dynaBean.get("select-choice-2");
		// 型号名称
		if (StringUtils.isNotEmpty(pd_id)) {
			entity.setModel_id(new Long(pd_id));
			PePdModel pePdModel = new PePdModel();
			pePdModel.setPd_id(Long.valueOf(pd_id));
			pePdModel = super.getFacade().getPePdModelService().getPePdModel(pePdModel);
			if (pePdModel != null) {
				entity.setModel_name(pePdModel.getMd_name());
			}
		} else {
			super.renderText(response, "请选择型号！");
			return null;
		}

		// // 尺寸
		// String pro_id = (String) dynaBean.get("select-choice-3");
		// // 尺寸名称
		// entity.setMeasure_id(Long.parseLong(pro_id));
		// entity.setMeasure_name(pro_id);

		// 门店
		String store_id = (String) dynaBean.get("store_id");
		if (StringUtils.isNotEmpty(store_id))
			entity.setDept_id(new Long(store_id));
		else {
			super.renderText(response, "请选择门店！");
			return null;
		}

		// 数量
		String count = (String) dynaBean.get("sales_count");
		if (StringUtils.isNotEmpty(count)) {
			if (NumberUtils.isNumber(count))
				entity.setNum(Long.parseLong(count));
			else {
				super.renderText(response, "数量必须为数字！");
				return null;
			}
		} else {
			super.renderText(response, "请填写数量！");
			return null;
		}

		// 消费者姓名
		String realname = (String) dynaBean.get("realname");
		// 消费者电话
		String phonenum = (String) dynaBean.get("phonenum");
		// 消费者住址
		String addresss = (String) dynaBean.get("addresss");
		// 消费者身份证号
		String mastercode = (String) dynaBean.get("mastercode");
		// 消费者信息
		entity.setRealname(realname);
		entity.setPhonenum(phonenum);
		entity.setAddresss(addresss);
		entity.setMastercode(mastercode);
		entity.setReport_id(peProdUser.getId());
		entity.setReport_date(new java.util.Date());
		entity.setReport_name(peProdUser.getUser_name());

		super.getFacade().getKonkaMobileSailsReturnService().createKonkaMobileSailsReturn(entity);
		generateHis(null, null, entity, null, null, null, null);

		super.renderText(response, "success");
		return null;
	}

	// 样机管理
	public ActionForward DoSubmit04(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		// 用户名
		String username = (String) dynaBean.get("username");
		// 密码
		String userpass = (String) dynaBean.get("userpass");

		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		PeProdUser peProdUser = checkUser(username, userpass,android_version,ios_version);

		if (null == peProdUser) {
			super.renderText(response, "用户信息出错，请联系管理员！");
			return null;
		}

		// 修改的逻辑
		String id = (String) dynaBean.get("id");
		if (StringUtils.isNotEmpty(id)) {
			KonkaMobileTestData _entity = new KonkaMobileTestData();
			_entity.setId(Long.parseLong(id));
			String up_date = (String) dynaBean.get("up_date");
			String down_date = (String) dynaBean.get("down_date");
			String down_cause = (String) dynaBean.get("down_cause");
			// 型号
			String pd_id = (String) dynaBean.get("pd_id");
			// 备注
			String memo = (String) dynaBean.get("memo");

			String code = (String) dynaBean.get("code");
			if (StringUtils.isNotEmpty(pd_id)) {
				pd_id = pd_id.trim();
				_entity.setModel_id(new Long(pd_id));
				PePdModel pePdModel = new PePdModel();
				pePdModel.setPd_id(Long.valueOf(pd_id));
				pePdModel = super.getFacade().getPePdModelService().getPePdModel(pePdModel);
				if (pePdModel != null) {
					_entity.setModel_name(pePdModel.getMd_name());
					_entity.setCategory_id(pePdModel.getCls_id());
					_entity.setPct_code(pePdModel.getMat_num());
					if (null != pePdModel.getMd_size() && !"".equals(pePdModel.getMd_size()))
						_entity.setMeasure_id(Long.parseLong(pePdModel.getMd_size()));
					_entity.setMeasure_name(pePdModel.getMd_size());
				} else {
					super.renderTextOrJsonp(request, response, "型号选择出错，请重新选择型号并填报！");
					return null;
				}
			} else {
				super.renderTextOrJsonp(request, response, "请选择型号！");
				return null;
			}

			// 销量
			String count = (String) dynaBean.get("count");
			// 价格（总价）
			String price = (String) dynaBean.get("price");
			if (GenericValidator.isLong(count)) {
				_entity.setNum(Long.valueOf(count));
			}
			if (GenericValidator.isDouble(price)) {
				_entity.setPrice(new BigDecimal(price));
			}
			if (StringUtils.isNotBlank(up_date)) {
				_entity.setUp_date(DateUtils.parseDate(up_date, new String[] { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss" }));
			}
			if (StringUtils.isNotBlank(down_date)) {
				_entity.setDown_date(DateUtils.parseDate(down_date,
						new String[] { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss" }));
			}
			if (StringUtils.isNotBlank(down_cause)) {
				_entity.setDown_cause(Integer.valueOf(down_cause));
			}
			if (null != code) {
				_entity.setCode(code);
			}
			_entity.setMemo(memo);
			super.getFacade().getKonkaMobileTestDataService().modifyKonkaMobileTestData(_entity);

			super.renderTextOrJsonp(request, response, "success:" + id);
			return null;
		}

		KonkaMobileTestData entity = new KonkaMobileTestData();
		// 备注
		String memo = (String) dynaBean.get("memo");
		// 串码
		String code = (String) dynaBean.get("code");
		// 计划性上样的id
		String plan_fp_id = (String) dynaBean.get("plan_fp_id");

		// 销量
		String count = (String) dynaBean.get("count");
		// 价格
		String price = (String) dynaBean.get("price");
		String up_date = (String) dynaBean.get("up_date");
		String down_date = (String) dynaBean.get("down_date");
		// 下架原因
		String down_cause = (String) dynaBean.get("down_cause");
		// 型号
		String pd_id = (String) dynaBean.get("pd_id");

		if (StringUtils.isNotBlank(plan_fp_id)) {
			KonkaYjglPlanFp fp = new KonkaYjglPlanFp();
			fp.setId(Long.valueOf(plan_fp_id));
			fp = super.getFacade().getKonkaYjglPlanFpService().getKonkaYjglPlanFp(fp);

			if (fp == null) {
				super.renderTextOrJsonp(request, response, "样机计划id丢失，请联系管理员！");
				return null;
			}

			int total_num = fp.getNum() == null ? 0 : fp.getNum().intValue();
			int sy_num = 0;
			int xy_num = 0;

			KonkaMobileTestData mt = new KonkaMobileTestData();
			mt.setPlan_fp_id(Long.valueOf(plan_fp_id));
			mt.setStatus(0);
			List<KonkaMobileTestData> mtList = super.getFacade().getKonkaMobileTestDataService()
					.getKonkaMobileTestDataList(mt);
			if (null == mtList || mtList.size() == 0) {
				sy_num = 0;
				xy_num = 0;
			} else {
				for (KonkaMobileTestData kd : mtList) {
					if (kd.getUp_date() != null) {
						if (kd.getUp_date().getTime() < new Date().getTime()
								&& (kd.getDown_date() == null || new Date().getTime() < kd.getDown_date().getTime())) {
							sy_num++;
						} else if (kd.getUp_date().getTime() > new Date().getTime()
								|| kd.getDown_date().getTime() < new Date().getTime()) {
							xy_num++;
						}
					}
				}
			}

			if ((total_num - sy_num - xy_num) <= 0) {
				super.renderTextOrJsonp(request, response, "已经没有样机可上样了，请不要重复操作！");
				return null;
			}

		}

		if (StringUtils.isNotBlank(up_date)) {
			entity.setUp_date(DateUtils.parseDate(up_date, new String[] { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss" }));
		}
		if (StringUtils.isNotBlank(down_date)) {
			entity.setDown_date(DateUtils.parseDate(down_date, new String[] { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss" }));
		}
		if (StringUtils.isNotBlank(down_cause)) {
			entity.setDown_cause(Integer.valueOf(down_cause));
		}
		if (null != code) {
			entity.setCode(code);
		}
		if (StringUtils.isNotBlank(plan_fp_id)) {
			entity.setPlan_fp_id(Long.valueOf(plan_fp_id));
		}
		entity.setMemo(memo);
		entity.setReport_date(new Date());

		// 分公司
		KonkaDept fgs = super.getSuperiorForDeptType(peProdUser.getDept_id(), 3);
		if (null != fgs) {
			entity.setSubcomp_id(fgs.getDept_id());
			entity.setSubcomp_name(fgs.getDept_name());
		} else {
			entity.setSubcomp_id(peProdUser.getDept_id());
			entity.setSubcomp_name(peProdUser.getDepartment());
		}

		// 销量
		if (StringUtils.isNotEmpty(count)) {
			if (NumberUtils.isNumber(count)) {
				entity.setNum(Long.parseLong(count));
				if (Long.parseLong(count) <= 0L) {
					super.renderTextOrJsonp(request, response, "数量必须为大于0！");
					return null;
				}
			} else {
				super.renderTextOrJsonp(request, response, "数量必须为数字！");
				return null;
			}
		} else {
			super.renderTextOrJsonp(request, response, "请填写数量！");
			return null;
		}

		// 型号名称
		if (!"0".equalsIgnoreCase(count)) {
			if (StringUtils.isNotEmpty(pd_id)) {
				pd_id = pd_id.trim();
				entity.setModel_id(new Long(pd_id));
				PePdModel pePdModel = new PePdModel();
				pePdModel.setPd_id(Long.valueOf(pd_id));
				pePdModel = super.getFacade().getPePdModelService().getPePdModel(pePdModel);
				if (pePdModel != null) {
					entity.setModel_name(pePdModel.getMd_name());
					entity.setCategory_id(pePdModel.getCls_id());
					entity.setPct_code(pePdModel.getMat_num());
					if (null != pePdModel.getMd_size() && !"".equals(pePdModel.getMd_size()))
						entity.setMeasure_id(Long.parseLong(pePdModel.getMd_size()));
					entity.setMeasure_name(pePdModel.getMd_size());
				} else {
					super.renderTextOrJsonp(request, response, "型号选择出错，请重新选择型号并填报！");
					return null;
				}
			} else {
				super.renderTextOrJsonp(request, response, "请选择型号！");
				return null;
			}
		}

		// 门店/客户
		String store_id = (String) dynaBean.get("store_id");
		if (StringUtils.isBlank(store_id)) {
			super.renderTextOrJsonp(request, response, "请选择门店！");
			return null;
		}

		store_id = store_id.trim();
		entity.setDept_id(new Long(store_id));
		KonkaR3Store r3s = new KonkaR3Store();
		r3s.setStore_id(Long.parseLong(store_id));
		r3s = super.getFacade().getKonkaR3StoreService().getKonkaR3Store(r3s);
		if (r3s != null) {
			entity.setDept_name(r3s.getStore_name());

			// 经办
			// entity.setOffice_id(r3s.getJb_job_id());
			entity.setOffice_name(r3s.getJb_name());

			// 查询客户信息
			if (null != r3s.getR3_code()) {
				KonkaR3Shop s = new KonkaR3Shop();
				s.setR3_code(StringUtils.upperCase(r3s.getR3_code()));
				s = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(s);

				if (null != s) {
					entity.setChannel_a_id(s.getId());
					entity.setChannel_a_name(s.getCustomer_name());
					entity.setChannel_b_name(s.getR3_code());

					if (GenericValidator.isLong(s.getCustomer_type())) {
						entity.setChannel_b_id(new Long(s.getCustomer_type()));

						KonkaCategory kc = new KonkaCategory();
						kc.setC_index(entity.getChannel_b_id());
						kc.setC_type(10);
						kc = super.getFacade().getKonkaCategoryService().getKonkaCategory(kc);

						if (null != kc) {
							entity.setOrg_name("[" + kc.getC_comm() + "]" + kc.getC_name());
						}
					}
				}
			}
		}

		// 单价
		if (StringUtils.isNotBlank(price)) {
			entity.setPrice(new BigDecimal(price));
		}

		entity.setStatus(0);
		entity.setReport_id(peProdUser.getId());
		entity.setReport_name(peProdUser.getReal_name());

		Long newid = super.getFacade().getKonkaMobileTestDataService().createKonkaMobileTestData(entity);
		generateHis(null, null, null, entity, null, null, null);

		super.renderText(response, "success:" + newid);
		return null;
	}

	// 物料终端
	public ActionForward DoSubmit05(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		// 用户名
		String username = (String) dynaBean.get("username");
		// 密码
		String userpass = (String) dynaBean.get("userpass");

		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		PeProdUser peProdUser = checkUser(username, userpass,android_version,ios_version);

		if (null == peProdUser) {
			super.renderText(response, "用户信息出错，请联系管理员！");
			return null;
		}

		KonkaMobileEquStore entity = new KonkaMobileEquStore();

		// 物料编码
		String equid = (String) dynaBean.get("select-choice-1");
		// 物料编码
		if (!StringUtils.isEmpty(equid)) {
			equid = equid.trim();
			entity.setEqu_id(Long.parseLong(equid));
		} else {
			super.renderText(response, "请录入物料编码");
			return null;
		}

		// 分公司
		entity.setSubcomp_id(peProdUser.getDept_id());
		entity.setSubcomp_name(peProdUser.getDepartment());

		// 门店
		String store_id = (String) dynaBean.get("store_id");
		if (StringUtils.isNotEmpty(store_id))
			entity.setDept_id(new Long(store_id));
		else {
			super.renderText(response, "请选择门店！");
			return null;
		}

		// 数量
		String count = (String) dynaBean.get("sales_count");
		if (StringUtils.isNotEmpty(count)) {
			if (NumberUtils.isNumber(count))
				entity.setNum(Long.parseLong(count));
			else {
				super.renderText(response, "销量必须为数字！");
				return null;
			}
		} else {
			super.renderText(response, "请填写数量！");
			return null;
		}

		entity.setReport_id(peProdUser.getId());
		entity.setReport_date(new java.util.Date());
		entity.setReport_name(peProdUser.getUser_name());
		super.getFacade().getKonkaMobileEquStoreService().createKonkaMobileEquStore(entity);

		generateHis(null, null, null, null, entity, null, null);

		super.renderText(response, "success");
		return null;
	}

	// 竞品上报
	public ActionForward DoSubmit06(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		// 用户名
		String username = (String) dynaBean.get("username");
		// 密码
		String userpass = (String) dynaBean.get("userpass");

		// 判断是否是静态页面来的数据，目的处理乱码问题
		String from_html = (String) dynaBean.get("from_html");
		if ("1".equals(from_html)) {
			username = URLDecoder.decode(username, "utf-8");
			userpass = URLDecoder.decode(userpass, "utf-8");
		}

		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		PeProdUser peProdUser = checkUser(username, userpass,android_version,ios_version);

		if (null == peProdUser) {
			super.renderTextOrJsonp(request, response, "用户信息出错，请联系管理员！");
			return null;
		}

		KonkaMobileFightReport entity = new KonkaMobileFightReport();

		// 门店
		String store_id = (String) dynaBean.get("store_id");
		if (StringUtils.isNotEmpty(store_id)) {
			store_id = store_id.trim();
			entity.setDept_id(new Long(store_id));
			KonkaR3Store r3s = new KonkaR3Store();
			r3s.setStore_id(Long.parseLong(store_id));
			r3s = super.getFacade().getKonkaR3StoreService().getKonkaR3Store(r3s);
			if (r3s != null)
				entity.setDept_name(r3s.getStore_name());
		} else {
			super.renderTextOrJsonp(request, response, "请选择门店！");
			return null;
		}

		// 型号
		String model_id = (String) dynaBean.get("model");
		String brand_id= (String) dynaBean.get("brand_id");
		
		if (StringUtils.isNotEmpty(model_id)) {
			if(model_id.equals("1001")){
				entity.setModel_id("1001");
				logger.info("brand_id",brand_id+"++++++++++++++++++++++++");
				if(StringUtils.isNotEmpty(brand_id))
				{
					entity.setBrand_id(Long.valueOf(brand_id));
				}
			}else{
				KonkaMobilePd t1 = new KonkaMobilePd();
				t1.setId(Long.parseLong(model_id));
	
				KonkaMobilePd pdn = super.getFacade().getKonkaMobilePdService().getKonkaMobilePd(t1);
	
				entity.setModel_id(pdn.getMd_name());
				if (pdn != null) {
					entity.setBrand_id(pdn.getBrand_id().longValue());
				}
			}
		} else {
			super.renderTextOrJsonp(request, response, "请填写型号！");
			return null;
		}
		
		// 规格段
		String par_type_id = (String) dynaBean.get("par_type_id");

		if(StringUtils.isNotEmpty(par_type_id))
		{
			entity.setPar_type_id(Long.valueOf(par_type_id));
			
		} else {
			
			KonkaMobilePd t1 = new KonkaMobilePd();
			t1.setId(Long.parseLong(model_id));

			KonkaMobilePd pdn = super.getFacade().getKonkaMobilePdService().getKonkaMobilePd(t1);
			if(null != pdn){
				//规格段根据尺寸添加
				int size=pdn.getScreen_size();
				if(size>65)
				{
					entity.setPar_type_id(Long.valueOf(10002508));
				}else if(size==65)
				{
					entity.setPar_type_id(Long.valueOf(10002507));
				}else if(size>=58 && size<=60)
				{
					entity.setPar_type_id(Long.valueOf(10002506));
				}else if(size==55)
				{
					entity.setPar_type_id(Long.valueOf(10002505));
				}else if(size>=46 && size<=50)
				{
					entity.setPar_type_id(Long.valueOf(10002504));
				}else if(size==42 || size==43)
				{
					entity.setPar_type_id(Long.valueOf(10002503));
				}else if(size==39 || size==40)
				{
					entity.setPar_type_id(Long.valueOf(10002502));
				}else if(size>=32)
				{
					entity.setPar_type_id(Long.valueOf(10002501));
				}else
				{
					entity.setPar_type_id(Long.valueOf(10002509));
				}
			}else
			{
				super.renderTextOrJsonp(request, response, "获取不到规格段！");
				return null;
			}
		}
		// 总价
		String price = (String) dynaBean.get("sales_price");
		if (StringUtils.isNotEmpty(price)) {
			if (NumberUtils.isNumber(price)) {
				entity.setPrice(BigDecimal.valueOf(Double.parseDouble(price)));
				if (entity.getPrice().compareTo(BigDecimal.valueOf(0)) == 0) {
					super.renderTextOrJsonp(request, response, "金额填写有误，金额不能为0或负数！");
					return null;
				}
			} else {
				super.renderTextOrJsonp(request, response, "金额必须为数字！");
				return null;
			}
		} else {
			super.renderTextOrJsonp(request, response, "请填写价格！");
			return null;
		}

		// 单价
		String single_price = (String) dynaBean.get("single_price");
		if (StringUtils.isNotBlank(single_price)) {
			entity.setSingle_price(new BigDecimal(single_price));
		}

		// 销量
		String count = (String) dynaBean.get("sales_count");
		if (StringUtils.isNotEmpty(count)) {
			if (NumberUtils.isNumber(count)) {

				entity.setNum(Long.parseLong(count));
				// if (entity.getNum() <= 0) {
				// super.renderTextOrJsonp(request, response,
				// "数量填写有误，数量不能为0或负数！");
				// return null;
				// }
			} else {
				super.renderTextOrJsonp(request, response, "数量必须为数字！");
				return null;
			}
		} else {
			super.renderTextOrJsonp(request, response, "请填写销量！");
			return null;
		}

		if (null != peProdUser) {
			entity.setReport_man(peProdUser.getId());
			entity.setReport_time(new java.util.Date());
			entity.setReport_name(peProdUser.getUser_name());// 上报人名

			// 分公司
			entity.setSubcomp_id(peProdUser.getDept_id());
			entity.setSubcomp_name(peProdUser.getDepartment());
		}

		// 备注
		String memo = (String) dynaBean.get("memo");
		if ("1".equals(from_html)) {
			memo = URLDecoder.decode(memo, "utf-8");
		}
		
		//数据来源
		String  ds= (String) dynaBean.get("data_source");
		if (StringUtils.isNotBlank(ds)) {
			if (NumberUtils.isNumber(ds)) {
				Integer data_source=Integer.parseInt(ds);
				entity.setData_source(data_source);
			}
		}

		entity.setMemo(memo);

		entity.setReport_man(peProdUser.getId());
		entity.setReport_time(new java.util.Date());
		entity.setReport_name(peProdUser.getUser_name());
		String id = (String) dynaBean.get("id");
		Long	lid =0L;
		if (StringUtils.isNotEmpty(id)) {
			lid=Long.valueOf(id);
			entity.setId(Long.parseLong(id));
				super.getFacade().getKonkaMobileFightReportService().modifyKonkaMobileFightReport(entity);
		} else {
		lid=(Long)	super.getFacade().getKonkaMobileFightReportService().createKonkaMobileFightReport(entity);
			
		}
		

		super.renderTextOrJsonp(request, response, "success:" + lid);
		return null;
	}

	// 请假申请
	public ActionForward DoSubmit07(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		// 用户名
		String username = (String) dynaBean.get("username");
		// 密码
		String userpass = (String) dynaBean.get("userpass");
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		PeProdUser peProdUser = checkUser(username, userpass,android_version,ios_version);

		if (null == peProdUser) {
			super.renderText(response, "用户信息出错，请联系管理员！");
			return null;
		}

		String date = (String) dynaBean.get("date");
		String retail_desc = (String) dynaBean.get("retail_desc");
		KonkaMobileRetailRest retailRest = new KonkaMobileRetailRest();
		if (null != peProdUser) {
			retailRest.setAdd_id(peProdUser.getId());
			retailRest.setAdd_name(peProdUser.getReal_name());
			retailRest.setAdd_date(new Date());
			retailRest.setRetail_id(peProdUser.getId());
			retailRest.setRetail_name(peProdUser.getReal_name());

			// 分公司
			retailRest.setSubcomp_id(peProdUser.getDept_id());
			retailRest.setSubcomp_name(peProdUser.getDepartment());
		}
		if (StringUtils.isNotEmpty(retail_desc)) {
			retailRest.setRetail_desc(retail_desc);
		} else {
			super.renderText(response, "请休假原因！");
			return null;
		}
		if (StringUtils.isNotEmpty(date)) {
			SimpleDateFormat DataFormat = new SimpleDateFormat("yyyy/M/d");
			retailRest.setRetail_date(DataFormat.parse(date));
		} else {
			super.renderText(response, "请填写休假日期！");
			return null;
		}
		getFacade().getKonkaMobileRetailRestService().createKonkaMobileRetailRest(retailRest);

		super.renderText(response, "success");
		return null;
	}

	// 信息反馈
	public ActionForward DoSubmit08(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		// 用户名
		String username = (String) dynaBean.get("username");
		// 密码
		String userpass = (String) dynaBean.get("userpass");
		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		PeProdUser peProdUser = checkUser(username, userpass,android_version,ios_version);

		if (null == peProdUser) {
			super.renderText(response, "用户信息出错，请联系管理员！");
			return null;
		}

		KonkaMobileTerminalFeedback tfb = new KonkaMobileTerminalFeedback();

		String message_type = (String) dynaBean.get("message_type");

		if (StringUtils.isNotBlank(message_type)) {
			message_type = message_type.trim();
			tfb.setMessage_type(Integer.parseInt(message_type));
		}

		String content = (String) dynaBean.get("content");
		if (StringUtils.isNotEmpty(content)) {
			tfb.setContent(content);
		} else {
			super.renderText(response, "请反馈内容！");
			return null;
		}
		if (null != peProdUser) {
			tfb.setQuestion_id(peProdUser.getId());
			tfb.setQuestion_person(peProdUser.getReal_name());
			tfb.setAdd_date(new Date());

			// 分公司
			tfb.setSubcomp_id(peProdUser.getDept_id());
			tfb.setSubcomp_name(peProdUser.getDepartment());
		}
		super.getFacade().getKonkaMobileTerminalFeedbackService().createKonkaMobileTerminalFeedback(tfb);

		super.renderText(response, "success");
		return null;
	}

	// 销售汇总
	public ActionForward DoSubmit09(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		// 用户名
		String username = (String) dynaBean.get("username");
		// 密码
		String userpass = (String) dynaBean.get("userpass");

		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		PeProdUser peProdUser = checkUser(username, userpass,android_version,ios_version);

		if (null == peProdUser) {
			super.renderText(response, "用户信息出错，请联系管理员！");
			return null;
		}

		KonkaMobileSailDatas entity = new KonkaMobileSailDatas();

		// // 商品条码
		// String goodscode = (String) dynaBean.get("goodscode");
		// // 商品条码
		// if (!StringUtils.isEmpty(goodscode))
		// entity.setPct_code(goodscode);
		// else {
		// super.renderText(response, "请录入商品条码");
		// return null;
		// }

		// 备注
		String memo = (String) dynaBean.get("memo");
		// 备注
		entity.setMemo(memo);

		// 销售日期
		// String sale_date = (String) dynaBean.get("sale_date");
		// 销售日期,默认当前日期

		entity.setSale_date(new java.util.Date());
		// 办事处
		entity.setOffice_id(peProdUser.getDept_id());
		entity.setOffice_name(peProdUser.getDepartment());

		// 分公司
		if (peProdUser.getPar_dept_id() != null) {
			KonkaDept konkaDept = new KonkaDept();
			konkaDept.setDept_id(peProdUser.getPar_dept_id());
			konkaDept = super.getFacade().getKonkaDeptService().getKonkaDept(konkaDept);
			if (konkaDept != null) {
				entity.setSubcomp_id(konkaDept.getDept_id());
				entity.setSubcomp_name(konkaDept.getDept_name());
			}
		} else {
			super.renderText(response, "请重新登录！");
			return null;
		}

		// // 品类
		// String cls_id = (String) dynaBean.get("select-choice-1");
		// // 品类名称
		// BasePdClazz basePdClazz = new BasePdClazz();
		// if (StringUtils.isNotEmpty(cls_id)) {
		// cls_id = cls_id.trim();
		// entity.setCategory_id(new Long(cls_id));
		//
		// basePdClazz.setCls_id(Long.valueOf(cls_id));
		// basePdClazz = super.getFacade().getBasePdClazzService()
		// .getBasePdClazz(basePdClazz);
		// if (basePdClazz != null) {
		// String[] name = basePdClazz.getFull_name().split(",");
		// entity.setCategory_name(name[name.length - 1]);
		// }
		// } else {
		// super.renderText(response, "请选择品类！");
		// return null;
		// }

		// 型号
		String pd_id = (String) dynaBean.get("select-choice-2");
		// 型号名称
		if (StringUtils.isNotEmpty(pd_id)) {
			pd_id = pd_id.trim();
			entity.setModel_id(new Long(pd_id));
			PePdModel pePdModel = new PePdModel();
			pePdModel.setPd_id(Long.valueOf(pd_id));
			pePdModel = super.getFacade().getPePdModelService().getPePdModel(pePdModel);
			if (pePdModel != null) {
				entity.setModel_name(pePdModel.getMd_name());
				entity.setCategory_id(pePdModel.getCls_id());
				entity.setPct_code(pePdModel.getMat_num());
				entity.setMeasure_id(Long.parseLong(pePdModel.getMd_size()));
				entity.setMeasure_name(pePdModel.getMd_size());
			} else {
				super.renderText(response, "型号选择出错，请重新选择型号并填报！");
				return null;
			}
		} else {
			super.renderText(response, "请选择型号！");
			return null;
		}

		// // 尺寸
		// String pro_id = (String) dynaBean.get("select-choice-3");
		// // 尺寸名称
		// BasePropValItem basePropValItem = new BasePropValItem();
		// if (StringUtils.isNotEmpty(pro_id)) {
		// pro_id = pro_id.trim();
		// entity.setMeasure_id(new Long(pro_id));
		//
		// basePropValItem.setProp_item_id(Long.valueOf(pro_id));
		// basePropValItem = super.getFacade().getBasePropValItemService()
		// .getBasePropValItem(basePropValItem);
		// if (basePropValItem != null) {
		// entity.setMeasure_name(basePropValItem.getProp_item_name());
		// }
		// } else {
		// super.renderText(response, "请选择尺寸！");
		// return null;
		// }

		// 门店
		String store_id = (String) dynaBean.get("store_id");
		if (StringUtils.isNotEmpty(store_id)) {
			store_id = store_id.trim();
			entity.setDept_id(new Long(store_id));
		} else {
			super.renderText(response, "请选择门店！");
			return null;
		}

		// 销量
		String count = (String) dynaBean.get("sales_count");
		if (StringUtils.isNotEmpty(count)) {
			if (NumberUtils.isNumber(count))
				entity.setNum(Long.parseLong(count));
			else {
				super.renderText(response, "销量必须为数字！");
				return null;
			}
		} else {
			super.renderText(response, "请填写数量！");
			return null;
		}

		// 单价
		String price = (String) dynaBean.get("sales_price");
		if (StringUtils.isNotEmpty(price)) {
			if (NumberUtils.isNumber(price))
				entity.setSingle_price(BigDecimal.valueOf(Double.parseDouble(price)));
			else {
				super.renderText(response, "价格必须为数字！");
				return null;
			}
		}

		entity.setAll_price(BigDecimal.valueOf(Double.parseDouble(price)).multiply(
				BigDecimal.valueOf(Long.parseLong(count))));
		entity.setReport_id(peProdUser.getId());
		entity.setReport_date(new java.util.Date());
		entity.setReport_name(peProdUser.getUser_name());

		super.getFacade().getKonkaMobileSailDatasService().createKonkaMobileSailDatas(entity);

		generateHis(null, null, null, null, null, null, entity);
		super.renderText(response, "success");
		return null;
	}

	// 密码修改
	public ActionForward ModifyPass(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		// 用户名
		String username = (String) dynaBean.get("username");
		// 密码
		String userpass = (String) dynaBean.get("userpass");
		// 新密码
		String new_userpass = (String) dynaBean.get("new_userpass");
		// 新密码 确认
		String new_userpass_r = (String) dynaBean.get("new_userpass_r");

		String link_tel = (String) dynaBean.get("link_tel");

		String link_phone = (String) dynaBean.get("link_phone");

		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		PeProdUser peProdUser = checkUser(username, userpass,android_version,ios_version);

		if (null == peProdUser) {
			super.renderText(response, "验证信息出错！");
			return null;
		}
		if (StringUtils.isEmpty(new_userpass) || StringUtils.isEmpty(new_userpass_r)) {
			super.renderText(response, "新密码不可为空！");
			return null;
		}
		if (!new_userpass.equals(new_userpass_r)) {
			super.renderText(response, "新密码验证出错！");
			return null;
		}

		DESPlus des = new DESPlus();
		PeProdUser ui = new PeProdUser();
		ui.setId(peProdUser.getId());
		ui.setPass_word(des.encrypt(new_userpass));
		if (StringUtils.isNotBlank(link_tel)) {
			ui.setLink_tel(link_tel);
		}
		if (StringUtils.isNotBlank(link_phone)) {
			ui.setLink_phone(link_phone);
		}
		super.getFacade().getPeProdUserService().modifyPeProdUser(ui);
		super.renderText(response, "success");
		return null;
	}

	// 手机终端信息
	public ActionForward SaveHanPhone(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		// 用户名
		String username = (String) dynaBean.get("username");
		// 密码
		String userpass = (String) dynaBean.get("userpass");

		String android_version=(String) dynaBean.get("android_version");
		String ios_version=(String) dynaBean.get("ios_version");
		PeProdUser peProdUser = checkUser(username, userpass,android_version,ios_version);

		if (null == peProdUser) {
			super.renderText(response, "用户信息出错，请联系管理员！");
			return null;
		}
		// 分辨率
		String ppi = (String) dynaBean.get("ppi");

		// 操作系统版本号
		String os = (String) dynaBean.get("os");

		// CPU型号
		String cpu = (String) dynaBean.get("cpu");

		// 内存的大小
		String ram = (String) dynaBean.get("ram");

		// 其他信息
		String memo = (String) dynaBean.get("memo");

		KonkaMobileHandphones kmh = new KonkaMobileHandphones();
		kmh.setCpu(cpu);
		kmh.setMemo(memo);
		kmh.setOs(os);
		kmh.setPpi(ppi);
		kmh.setRam(ram);
		kmh.setReport_id(peProdUser.getId());
		super.getFacade().getKonkaMobileHandphonesService().createKonkaMobileHandphones(kmh);

		return null;
	}

	// GPS信息存储
	public ActionForward SaveGPSInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaBean dynaBean = (DynaBean) form;
		String mobile_number = (String) dynaBean.get("mobile_number");
		String username = (String) dynaBean.get("username");
		String x = (String) dynaBean.get("x");// 经度
		String y = (String) dynaBean.get("y");// 纬度

		if (StringUtils.isBlank(mobile_number) && StringUtils.isBlank(username)) {
			logger.error("mobile_number and username is null.");
			return null;
		}
		if (StringUtils.isBlank(x) || StringUtils.isBlank(y)) {
			logger.error("x/Lng or y/Lat is null., x={}, y={}", x, y);
			return null;
		}
		// 2.解密数据
		// String t_no = no;// Encrypt.decrypt(no, null);
		// String t_x = x;// Encrypt.decrypt(x, null);
		// String t_y = y;// Encrypt.decrypt(y, null);
		// 接收手机GPS数据
		// KonkaUserGpsInfo gpsInfo = new KonkaUserGpsInfo();
		// gpsInfo.setMobile_no(t_no);
		// gpsInfo.setGps_longitude(t_x);
		// gpsInfo.setGps_latitude(t_y);
		// gpsInfo.setGps_type(0L);
		// gpsInfo.setUpdate_time(new Date());
		// super.getFacade().getKonkaUserGpsInfoService().createKonkaUserGpsInfo(gpsInfo);

		if (StringUtils.isBlank(mobile_number)) {
			PeProdUser user = new PeProdUser();
			user.setUser_name(username);
			user.setIs_del(0);
			List<PeProdUser> userList = super.getFacade().getPeProdUserService().getPeProdUserList(user);
			if (null != userList && userList.size() == 1) {
				user = userList.get(0);
			} else {
				super.renderText(response, "用户信息验证出错！");
				return null;
			}

			if (null != user) {
				mobile_number = user.getLink_phone();
			}
		}

		if (StringUtils.isBlank(mobile_number)) {
			// logger.error("mobile_number is blank. username = {}, x = {}, y = {}",
			// username, x, y);
			return null;
		}
		if (mobile_number.length() != 11) {
			// logger.error("mobile_number is illegal. username = {}, x = {}, y = {}",
			// username, x, y);
			super.renderText(response, "用户联系电话非法！");
			return null;
		}

		mobile_number = StringUtils.trim(mobile_number);

		KonkaMobileUserGps gps = new KonkaMobileUserGps();
		gps.setMp_sn(mobile_number);
		Long counts = super.getFacade().getKonkaMobileUserGpsService().getKonkaMobileUserGpsCount(gps);

		gps.setLng(x);
		gps.setLat(y);

		if (counts == 1L) {
			super.getFacade().getKonkaMobileUserGpsService().modifyKonkaMobileUserGps(gps);
		} else if (counts == 0L) {
			super.getFacade().getKonkaMobileUserGpsService().createKonkaMobileUserGps(gps);
		}

		// 创建轨迹
		KonkaMobileUserGpsTrack track = new KonkaMobileUserGpsTrack();
		track.setLat(y);
		track.setLng(x);
		track.setMp_sn(mobile_number);
		track.setC_ip(super.getClientRealIp(request));
		super.getFacade().getKonkaMobileUserGpsTrackService().createKonkaMobileUserGpsTrack(track);

		return null;
	}

	public boolean isMobileNO(String mobiles) {
		if (null == mobiles) {
			return false;
		}
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	// 拿参考价
	public Double getKonkaR3PdPrice2(String pd_sn, String r3_code) throws Exception {

		// String pd_sn = (String) dynaBean.get("pd_sn");// 机型编码
		// String r3_code = (String) dynaBean.get("r3_code");// 客户编码
		Double price = 0d;
		// 根据客户找到唯一的客户群组
		Long deptid = super.getFacade().getKonkaR3ShopService().getDeptIdOfKonkaR3Code(r3_code);
		KonkaR3Shop ks = new KonkaR3Shop();
		ks.setR3_code(r3_code);
		ks.setIs_del(0l);
		ks = super.getFacade().getKonkaR3ShopService().getKonkaR3Shop(ks);
		CrmCustomerGroup group = new CrmCustomerGroup();
		// group 不能多于2条数据
		if (ks == null) {
			return 0d;
		} else {
			group = super.getFacade().getCrmCustomerGroupService()
					.getCrmCustomerGroupByCustomerIdAndDeptId(String.valueOf(ks.getId()), deptid);

			Map<String, Double> map = new HashMap<String, Double>();

			// 确认是否为该客户群组维护了价格表<限定在时间段内>
			if (group != null) {
				CrmPriceHeader header = new CrmPriceHeader();
				header.setDept_id(deptid);
				header.setGroupcode(group.getGroupcode());// group code 是唯一的
				header.getMap().put("effective_flag", "yes");// 加上时间过滤
				header = super.getFacade().getCrmPriceHeaderService().getCrmPriceHeader(header);
				if (header != null) {
					// 价格表下是否维护了所需机型
					CrmPriceLines line = new CrmPriceLines();
					line.setHeadid(header.getId());
					line.setModelcode(pd_sn);
					line = super.getFacade().getCrmPriceLinesService().getCrmPriceLines(line);
					if (line != null) {
						price = line.getMarketprice() == null ? 0d : line.getMarketprice();
						// map.put("discount", line.getDiscount() == null ? 0d : line.getDiscount());
					}
				}

			}

			return price;
		}
	}

}
