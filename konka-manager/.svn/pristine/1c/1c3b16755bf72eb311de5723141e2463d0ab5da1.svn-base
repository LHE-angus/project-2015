package com.ebiz.mmt.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaOrderAuditProcessNodeDao;
import com.ebiz.mmt.dao.KonkaOrderInfoAuditDao;
import com.ebiz.mmt.dao.KonkaOrderInfoDao;
import com.ebiz.mmt.dao.KonkaOrderInfoDetailsDao;
import com.ebiz.mmt.dao.KonkaOrderInfoUpdateRecordDao;
import com.ebiz.mmt.dao.PePdModelDao;
import com.ebiz.mmt.domain.KonkaOrderAuditProcessNode;
import com.ebiz.mmt.domain.KonkaOrderInfo;
import com.ebiz.mmt.domain.KonkaOrderInfoAudit;
import com.ebiz.mmt.domain.KonkaOrderInfoDetails;
import com.ebiz.mmt.domain.KonkaOrderInfoUpdateRecord;
import com.ebiz.mmt.domain.PePdModel;
import com.ebiz.mmt.service.KonkaOrderInfoAuditService;
import com.ebiz.org.apache.commons.lang3.StringUtils;

/**
 * @author Wu,Yang
 * @version 2011-11-26 11:39
 */
@Service
public class KonkaOrderInfoAuditServiceImpl implements KonkaOrderInfoAuditService {

	@Resource
	private KonkaOrderInfoAuditDao konkaOrderInfoAuditDao;

	@Resource
	private KonkaOrderInfoDao konkaOrderInfoDao;

	@Resource
	private KonkaOrderInfoDetailsDao konkaOrderInfoDetailsDao;

	@Resource
	private KonkaOrderAuditProcessNodeDao konkaOrderAuditProcessNodeDao;

	@Resource
	private KonkaOrderInfoUpdateRecordDao konkaOrderInfoUpdateRecordDao;
	@Resource
	private PePdModelDao pePdModelDao;

	// @Resource
	// private TodoListDao todoListDao;

	public Long createKonkaOrderInfoAudit(KonkaOrderInfoAudit t) {
		return this.konkaOrderInfoAuditDao.insertEntity(t);
	}

	public KonkaOrderInfoAudit getKonkaOrderInfoAudit(KonkaOrderInfoAudit t) {
		return this.konkaOrderInfoAuditDao.selectEntity(t);
	}

	public Long getKonkaOrderInfoAuditCount(KonkaOrderInfoAudit t) {
		return this.konkaOrderInfoAuditDao.selectEntityCount(t);
	}

	public List<KonkaOrderInfoAudit> getKonkaOrderInfoAuditList(KonkaOrderInfoAudit t) {
		return this.konkaOrderInfoAuditDao.selectEntityList(t);
	}

	public int modifyKonkaOrderInfoAudit(KonkaOrderInfoAudit t) {
		return this.konkaOrderInfoAuditDao.updateEntity(t);
	}

	public int removeKonkaOrderInfoAudit(KonkaOrderInfoAudit t) {
		return this.konkaOrderInfoAuditDao.deleteEntity(t);
	}

	public List<KonkaOrderInfoAudit> getKonkaOrderInfoAuditPaginatedList(KonkaOrderInfoAudit t) {
		return this.konkaOrderInfoAuditDao.selectEntityPaginatedList(t);
	}

	/** 订单对应的审核记录包含角色名称和ID */
	public List<KonkaOrderInfoAudit> getKonkaOrderInfoAuditWithRoleInfoList(KonkaOrderInfoAudit t) {
		return this.konkaOrderInfoAuditDao.selectKonkaOrderInfoAuditWithRoleInfoList(t);
	}

	/** 订单对应的审核记录中最高的审核级别 */
	public Long getKonkaOrderInfoAuditWithMaxAuditLevel(KonkaOrderInfoAudit t) {
		return this.konkaOrderInfoAuditDao.selectKonkaOrderInfoAuditWithMaxAuditLevel(t);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2013-08-05
	 */
	@SuppressWarnings("unchecked")
	public Long createKonkaOrderInfoAuditNew(KonkaOrderInfoAudit t) {
		KonkaOrderInfo order = new KonkaOrderInfo();
		order.setId(t.getLink_id());
		order = this.konkaOrderInfoDao.selectEntity(order);

		if (t.getNext_node_id().equals(-1L)) {
			order.setAudit_state(3); // 审核成功

		} else {
			order.setAudit_state(1); // 审核中
		}

		order.setNext_node_id(t.getNext_node_id());

		KonkaOrderAuditProcessNode node = new KonkaOrderAuditProcessNode();
		node.setId(t.getNext_node_id());
		node = this.konkaOrderAuditProcessNodeDao.selectEntity(node);
		if (null != node) {
			Long next_role_id = node.getRole_id(); // 下一个审核角色
			order.setNext_audit_role_id(next_role_id);
		} else {
			order.setNext_audit_role_id(-1L);
		}

		// 审批结果：1同意，-1驳回，0-驳回（到客户，重新制单）， -9 撤回（客户在客户端操作）
		if (t.getAudit_result() == 0) {
			// 驳回到制单
			order.setAudit_state(0);
			order.setIs_submit(0);
			// 清空下面3个字段
			KonkaOrderAuditProcessNode cur_order_first_node = (KonkaOrderAuditProcessNode) t.getMap().get(
					"cur_order_first_node");
			if (null != cur_order_first_node) {
				// order.setNext_audit_role_id(cur_order_first_node.getId());
				// order.setNext_node_id(cur_order_first_node.getRole_id());

				order.setNext_audit_role_id(cur_order_first_node.getRole_id());
				order.setNext_node_id(cur_order_first_node.getId());
			}
		}

		if (null != t.getMap().get("order_num")) {
			order.setOrder_num(Long.valueOf(t.getMap().get("order_num").toString()));
		}
		if (null != t.getMap().get("money")) {
			order.setMoney(new BigDecimal(t.getMap().get("money").toString()));
		}
		if (null != t.getMap().get("good_discount_price")) {
			order.setGood_discount_price(new BigDecimal(t.getMap().get("good_discount_price").toString()));
		}

		List<KonkaOrderInfoDetails> orderDetailsList = (List<KonkaOrderInfoDetails>) t.getMap().get("orderDetailsList");
		if (null != orderDetailsList) {
			boolean is_updated = null != t.getMap().get("is_update_flag") && (Boolean) t.getMap().get("is_update_flag");

			if (is_updated) {
                order.setKh_confirm_state(-1);
                // -1订单已被修改（等待客户确认），
                // 0订单未被修改（初始状态），
                // 1客户已确认.此状态在客户撤回后需要重置
			}

			Date now = new Date(); // 时间戳
			for (KonkaOrderInfoDetails details : orderDetailsList) {

				KonkaOrderInfoDetails details_before = new KonkaOrderInfoDetails();
				details_before.setId(details.getId());
				details_before = this.konkaOrderInfoDetailsDao.selectEntity(details_before);

				// 订单审核修改记录表
				if (is_updated) {
					KonkaOrderInfoUpdateRecord updateRecord = new KonkaOrderInfoUpdateRecord();
					updateRecord.setTrade_index(order.getTrade_index());
					updateRecord.setOrder_id(order.getId());
					updateRecord.setPd_type_id(details.getPd_type_id());
					updateRecord.setPd_type_name(details.getPd_type_name());
					updateRecord.setBrand_id(details.getBrand_id());
					updateRecord.setBrand_name(details.getBrand_name());
					updateRecord.setPd_id(details.getPd_id());
					updateRecord.setPd_name(details.getPd_name());
					updateRecord.setNum_before(Long.valueOf(details_before.getGood_count().toString()));
					updateRecord.setNum_after(Long.valueOf(details.getGood_count().toString()));
					updateRecord.setPrice_before(details_before.getGood_price());
					updateRecord.setPrice_after(details.getGood_price());

					updateRecord.setDiscount_before(details_before.getGood_discount_price());// 修改前的折让金额
					updateRecord.setDiscount_after(details.getGood_discount_price()); // 修改后的折让金额

					updateRecord.setBf_discount(details_before.getGood_discount());// 修改前的折让率
					updateRecord.setAf_discount(details.getGood_discount()); // 修改后的折让率

					updateRecord.setUpdate_user_id(t.getAudit_user_id());
					updateRecord.setUpdate_user_name(t.getAudit_user());
					updateRecord.setUpdate_user_dept_id(t.getAudit_dept_id());
					updateRecord.setUpdate_user_dept_name(t.getAudit_dept_name());
					updateRecord.setUpdate_role_id((Long) t.getMap().get("audit_role_id"));
					updateRecord.setUpdate_role_name((String) t.getMap().get("audit_role_name"));
					updateRecord.setAdd_date(now);
					updateRecord.setUpdate_type(6); // 修改数据类型：1： 数量 2：单价 3：数量和单价
					// 4：增加 5： 删除 4：增加时：修改前数量0
					// ，修改后实际数量5：删除时：修改前数量实际数量，修改后数量0，6:数量和单价和单台折让

					this.konkaOrderInfoUpdateRecordDao.insertEntity(updateRecord);
				}

				this.konkaOrderInfoDetailsDao.updateEntity(details);
			}
		}

		// 下面的这段代码是用来重置订单的“是否被修改”状态，这个工作在客户端进行“撤回”操作也需要做
		if (t.getAudit_result() == 0) {
			// 审批结果：1同意，-1驳回，0-驳回（到客户，重新制单）， -9 撤回（客户在客户端操作）
			// 当订单被驳回到重新制单后，重置“订单修改标识”
			order.setKh_confirm_state(0);
		}

		this.konkaOrderInfoDao.updateEntity(order);

		// 将下一个审核信息添加到待办事项

		// TodoList todo = new TodoList();
		// todo.setTodo_type(3); // 事项分类:1-OA系统协同办公，2-专卖店，3-客户进销存，4-客户管理
		// todo.setTodo_event(301);// 301-进销存订单审核;
		// todo.setTodo_title(t.getMap().get("todo_title").toString());
		// todo.setTodo_from(t.getAudit_user());
		// todo.setTodo_from_user_id(t.getAudit_user_id());
		// todo.setTodo_rece(node.getRole_name());
		// todo.setTodo_rece_user_id(next_role_id);
		// todo.setTodo_url("/manager/admin/KonkaOrderAudit.do?method=audit&id="
		// + t.getLink_id());
		// todo.setTodo_state(0); // 处理状态:0-未处理，1-已处理
		//
		// this.todoListDao.insertEntity(todo);

		return this.konkaOrderInfoAuditDao.insertEntity(t);
	}

	public Long createKonkaOrderInfoAuditNewForNewProcess(KonkaOrderInfoAudit t) {
		KonkaOrderInfo order = new KonkaOrderInfo();
		order.setId(t.getLink_id());
		order = this.konkaOrderInfoDao.selectEntity(order);

		// 审核流程列表
		KonkaOrderAuditProcessNode node1 = new KonkaOrderAuditProcessNode();
		node1.setAudit_proces_id(order.getProcess_id());
		node1.getMap().put("audit_level_gt", t.getMap().get("audit_level_gt"));
		List<KonkaOrderAuditProcessNode> nodeList = this.konkaOrderAuditProcessNodeDao.selectEntityList(node1);

		if (t.getNext_node_id().equals(-1L)) {
			order.setAudit_state(3); // 审核成功

		} else {
			order.setAudit_state(1); // 审核中
		}
		List<KonkaOrderInfoDetails> orderDetailsList = (List<KonkaOrderInfoDetails>) t.getMap().get("orderDetailsList");

		order.setNext_node_id(t.getNext_node_id());

		KonkaOrderAuditProcessNode node = new KonkaOrderAuditProcessNode();
		node.setId(t.getNext_node_id());
		node = this.konkaOrderAuditProcessNodeDao.selectEntity(node);

		if (null != node) {
			if (t.getAudit_result() == 1) {
				for (KonkaOrderAuditProcessNode kapn : nodeList) {
					if (StringUtils.isNotBlank(kapn.getAudit_proc_cond().toString()) && kapn.getAudit_proc_cond() > 0) {// 判断是否具有条件
						Boolean flag_ = this.is_Four_Week(order, orderDetailsList,kapn.getAudit_proc_cond());
						if (!flag_) {// 零售量-库存 > 订单数量
							KonkaOrderAuditProcessNode node3 = new KonkaOrderAuditProcessNode();
							node3.setAudit_proces_id(kapn.getAudit_proces_id());
							node3.setIs_del(0);
							node3.setAudit_level(kapn.getAudit_level() + 1);
							node3 = this.konkaOrderAuditProcessNodeDao.selectEntity(node3);
							if (null != node3) {// 如果有
								Long next_role_id = node3.getRole_id(); // 下一个审核角色
								order.setNext_audit_role_id(next_role_id);
								order.setNext_node_id(node3.getId());// 改变next_node_id
							} else {// 没有下一个节点
								order.setNext_audit_role_id(-1L);
								order.setNext_node_id(-1L);
								order.setAudit_state(3);
								break;
							}
						} else {// 正常流程
							Long next_role_id = kapn.getRole_id(); // 下一个审核角色
							order.setNext_audit_role_id(next_role_id);
							break;
						}
					} else {// 下一个角色没有条件
						Long next_role_id = kapn.getRole_id(); // 下一个审核角色
						order.setNext_audit_role_id(next_role_id);
						break;
					}
				}
			} else if (t.getAudit_result() == -1) {// 驳回
				Long next_role_id = node.getRole_id(); // 下一个审核角色
				order.setNext_audit_role_id(next_role_id);
			}

		} else {// 没有下一个节点
			order.setNext_audit_role_id(-1L);
			order.setNext_node_id(-1L);
		}
		// 审批结果：1同意，-1驳回，0-驳回（到客户，重新制单）， -9 撤回（客户在客户端操作）
		if (t.getAudit_result() == 0) {
			// 驳回到制单
			order.setAudit_state(0);
			order.setIs_submit(0);

			// 清空下面3个字段
			KonkaOrderAuditProcessNode cur_order_first_node = (KonkaOrderAuditProcessNode) t.getMap().get(
					"cur_order_first_node");
			if (null != cur_order_first_node) {
				// order.setNext_audit_role_id(cur_order_first_node.getId());
				// order.setNext_node_id(cur_order_first_node.getRole_id());

				order.setNext_audit_role_id(cur_order_first_node.getRole_id());
				order.setNext_node_id(cur_order_first_node.getId());
			}
		}

		if (null != t.getMap().get("order_num")) {
			order.setOrder_num(Long.valueOf(t.getMap().get("order_num").toString()));
		}
		if (null != t.getMap().get("money")) {
			order.setMoney(new BigDecimal(t.getMap().get("money").toString()));
		}
		if (null != t.getMap().get("good_discount_price")) {
			order.setGood_discount_price(new BigDecimal(t.getMap().get("good_discount_price").toString()));
		}

		if (null != orderDetailsList) {
			boolean is_updated = null != t.getMap().get("is_update_flag") && (Boolean) t.getMap().get("is_update_flag");

			if (is_updated) {
                order.setKh_confirm_state(-1);
                // -1订单已被修改（等待客户确认），
                // 0订单未被修改（初始状态），
                // 1客户已确认.此状态在客户撤回后需要重置
			}

			Date now = new Date(); // 时间戳
			for (KonkaOrderInfoDetails details : orderDetailsList) {

				KonkaOrderInfoDetails details_before = new KonkaOrderInfoDetails();
				details_before.setId(details.getId());
				details_before = this.konkaOrderInfoDetailsDao.selectEntity(details_before);

				// 订单审核修改记录表
				if (is_updated) {
					KonkaOrderInfoUpdateRecord updateRecord = new KonkaOrderInfoUpdateRecord();
					updateRecord.setTrade_index(order.getTrade_index());
					updateRecord.setOrder_id(order.getId());
					updateRecord.setPd_type_id(details.getPd_type_id());
					updateRecord.setPd_type_name(details.getPd_type_name());
					updateRecord.setBrand_id(details.getBrand_id());
					updateRecord.setBrand_name(details.getBrand_name());
					updateRecord.setPd_id(details.getPd_id());
					updateRecord.setPd_name(details.getPd_name());
					updateRecord.setNum_before(Long.valueOf(details_before.getGood_count().toString()));
					updateRecord.setNum_after(Long.valueOf(details.getGood_count().toString()));
					updateRecord.setPrice_before(details_before.getGood_price());
					updateRecord.setPrice_after(details.getGood_price());

					updateRecord.setDiscount_before(details_before.getGood_discount_price());// 修改前的折让金额
					updateRecord.setDiscount_after(details.getGood_discount_price()); // 修改后的折让金额

					updateRecord.setBf_discount(details_before.getGood_discount());// 修改前的折让率
					updateRecord.setAf_discount(details.getGood_discount()); // 修改后的折让率

					updateRecord.setUpdate_user_id(t.getAudit_user_id());
					updateRecord.setUpdate_user_name(t.getAudit_user());
					updateRecord.setUpdate_user_dept_id(t.getAudit_dept_id());
					updateRecord.setUpdate_user_dept_name(t.getAudit_dept_name());
					updateRecord.setUpdate_role_id((Long) t.getMap().get("audit_role_id"));
					updateRecord.setUpdate_role_name((String) t.getMap().get("audit_role_name"));
					updateRecord.setAdd_date(now);
					updateRecord.setUpdate_type(6); // 修改数据类型：1： 数量 2：单价 3：数量和单价
					// 4：增加 5： 删除 4：增加时：修改前数量0
					// ，修改后实际数量5：删除时：修改前数量实际数量，修改后数量0，6:数量和单价和单台折让

					this.konkaOrderInfoUpdateRecordDao.insertEntity(updateRecord);
				}

				this.konkaOrderInfoDetailsDao.updateEntity(details);
			}
		}

		// 下面的这段代码是用来重置订单的“是否被修改”状态，这个工作在客户端进行“撤回”操作也需要做
		if (t.getAudit_result() == 0) {
			// 审批结果：1同意，-1驳回，0-驳回（到客户，重新制单）， -9 撤回（客户在客户端操作）
			// 当订单被驳回到重新制单后，重置“订单修改标识”
			order.setKh_confirm_state(0);
		}

		this.konkaOrderInfoDao.updateEntity(order);

		// 将下一个审核信息添加到待办事项

		// TodoList todo = new TodoList();
		// todo.setTodo_type(3); // 事项分类:1-OA系统协同办公，2-专卖店，3-客户进销存，4-客户管理
		// todo.setTodo_event(301);// 301-进销存订单审核;
		// todo.setTodo_title(t.getMap().get("todo_title").toString());
		// todo.setTodo_from(t.getAudit_user());
		// todo.setTodo_from_user_id(t.getAudit_user_id());
		// todo.setTodo_rece(node.getRole_name());
		// todo.setTodo_rece_user_id(next_role_id);
		// todo.setTodo_url("/manager/admin/KonkaOrderAudit.do?method=audit&id="
		// + t.getLink_id());
		// todo.setTodo_state(0); // 处理状态:0-未处理，1-已处理
		//
		// this.todoListDao.insertEntity(todo);

		return this.konkaOrderInfoAuditDao.insertEntity(t);
	}

	public Long createKonkaOrderInfoAuditNewForChange(KonkaOrderInfoAudit t) {
		KonkaOrderInfo order = new KonkaOrderInfo();
		order.setId(t.getLink_id());
		order = this.konkaOrderInfoDao.selectEntity(order);
		order.setAudit_state(1); // 审核中

		List<KonkaOrderInfoDetails> orderDetailsList = (List<KonkaOrderInfoDetails>) t.getMap().get("orderDetailsList");

		order.setNext_node_id(t.getNext_node_id());
		order.setIs_change(1);

		// order.setAudit_state(0);
		order.setIs_submit(1);

		if (null != t.getMap().get("order_num")) {
			order.setOrder_num(Long.valueOf(t.getMap().get("order_num").toString()));
		}
		if (null != t.getMap().get("money")) {
			order.setMoney(new BigDecimal(t.getMap().get("money").toString()));
		}
		if (null != t.getMap().get("good_discount_price")) {
			order.setGood_discount_price(new BigDecimal(t.getMap().get("good_discount_price").toString()));
		}

		if (null != orderDetailsList) {
			boolean is_updated = null != t.getMap().get("is_update_flag") && (Boolean) t.getMap().get("is_update_flag");

			if (is_updated) {
				// order.setKh_confirm_state(-1); // -1订单已被修改（等待客户确认），0
				// 订单未被修改（初始状态），1
				// 客户已确认.此状态在客户撤回后需要重置
			}

			Date now = new Date(); // 时间戳
			for (KonkaOrderInfoDetails details : orderDetailsList) {

				KonkaOrderInfoDetails details_before = new KonkaOrderInfoDetails();
				details_before.setId(details.getId());
				details_before = this.konkaOrderInfoDetailsDao.selectEntity(details_before);

				// 订单审核修改记录表
				if (is_updated) {
					KonkaOrderInfoUpdateRecord updateRecord = new KonkaOrderInfoUpdateRecord();
					updateRecord.setTrade_index(order.getTrade_index());
					updateRecord.setOrder_id(order.getId());
					updateRecord.setPd_type_id(details.getPd_type_id());
					updateRecord.setPd_type_name(details.getPd_type_name());
					updateRecord.setBrand_id(details.getBrand_id());
					updateRecord.setBrand_name(details.getBrand_name());
					updateRecord.setPd_id(details.getPd_id());
					updateRecord.setPd_name(details.getPd_name());
					updateRecord.setNum_before(Long.valueOf(details_before.getGood_count().toString()));
					updateRecord.setNum_after(Long.valueOf(details.getGood_count().toString()));
					updateRecord.setPrice_before(details_before.getGood_price());
					updateRecord.setPrice_after(details.getGood_price());

					updateRecord.setDiscount_before(details_before.getGood_discount_price());// 修改前的折让金额
					updateRecord.setDiscount_after(details.getGood_discount_price()); // 修改后的折让金额

					updateRecord.setBf_discount(details_before.getGood_discount());// 修改前的折让率
					updateRecord.setAf_discount(details.getGood_discount()); // 修改后的折让率

					updateRecord.setUpdate_user_id(t.getAudit_user_id());
					updateRecord.setUpdate_user_name(t.getAudit_user());
					updateRecord.setUpdate_user_dept_id(t.getAudit_dept_id());
					updateRecord.setUpdate_user_dept_name(t.getAudit_dept_name());
					updateRecord.setUpdate_role_id((Long) t.getMap().get("audit_role_id"));
					updateRecord.setUpdate_role_name((String) t.getMap().get("audit_role_name"));
					updateRecord.setAdd_date(now);
					updateRecord.setUpdate_type(6); // 修改数据类型：1： 数量 2：单价 3：数量和单价
					// 4：增加 5： 删除 4：增加时：修改前数量0
					// ，修改后实际数量5：删除时：修改前数量实际数量，修改后数量0，6:数量和单价和单台折让

					this.konkaOrderInfoUpdateRecordDao.insertEntity(updateRecord);
				}

				this.konkaOrderInfoDetailsDao.updateEntity(details);
			}
		}
		this.konkaOrderInfoDao.updateEntity(order);
		return this.konkaOrderInfoAuditDao.insertEntity(t);
	}

	public List<KonkaOrderInfoAudit> getKonkaOrderInfoAuditAndRoleList(KonkaOrderInfoAudit t) {
		return this.konkaOrderInfoAuditDao.selectKonkaOrderInfoAuditAndRoleList(t);
	}

	public Boolean is_Four_Week(KonkaOrderInfo order, List<KonkaOrderInfoDetails> orderDetailsList,int audit_proc_cond) {
		Boolean flag_ = false;// 标志位
		Long order_fourweek_count = 0l;
		// 获得库存
		for (KonkaOrderInfoDetails odt : orderDetailsList) {
			switch(audit_proc_cond){
			case 1:
				order_fourweek_count = odt.getSale_count();
				break;
			case 2:
				order_fourweek_count =  (odt.getSale_count_4()==null?0L:odt.getSale_count_4().longValue());
				break;
			case 3:
				order_fourweek_count =  (odt.getSale_count_6()==null?0L:odt.getSale_count_6().longValue());
				break;
			case 4:
				order_fourweek_count =  (odt.getSale_count_8()==null?0L:odt.getSale_count_8().longValue());
				break;
			case 5:
				order_fourweek_count=(null==odt.getSale_count_01_add()?0L:odt.getSale_count_01_add().longValue());
				break;
			}
			// 前四周
			//Long order_fourweek_count = odt.getSale_count();
			// 库存
			Long curr_ku_count = odt.getStore_num();
			// 获得订单数量
			Long order_num = Long.valueOf(odt.getGood_count());
			// 找到对应的商品。
			PePdModel pdPdModel = new PePdModel();
			pdPdModel.setPd_id(odt.getPd_id());
			pdPdModel = this.pePdModelDao.selectEntity(pdPdModel);
			// 只有不是配件的才进行四周销量的判断
			if (null != pdPdModel
					&& (null == pdPdModel.getIs_parts() || (null != pdPdModel.getIs_parts() && pdPdModel.getIs_parts() == 0))) {
				if (order_fourweek_count - curr_ku_count < order_num) {
					flag_ = true;// 只要其中一个满足就跳出 证明走正常流程
					break;
				}
			}
		}
		return flag_;
	}

	@Override
	public Boolean is_Four_Week(KonkaOrderInfo order,
			List<KonkaOrderInfoDetails> orderDetailsList) {

		return is_Four_Week(order,orderDetailsList,1);
	}

}
