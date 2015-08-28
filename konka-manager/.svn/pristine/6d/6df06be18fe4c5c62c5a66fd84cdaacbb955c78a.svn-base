package com.ebiz.mmt.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcVouchersApplyDao;
import com.ebiz.mmt.dao.EcVouchersDao;
import com.ebiz.mmt.domain.EcVouchers;
import com.ebiz.mmt.domain.EcVouchersApply;
import com.ebiz.mmt.service.EcVouchersApplyService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-19 16:29:48
 */
@Service
public class EcVouchersApplyServiceImpl implements EcVouchersApplyService {

	@Resource
	private EcVouchersApplyDao ecVouchersApplyDao;

	@Resource
	private EcVouchersDao ecVouchersDao;

	public Long createEcVouchersApply(EcVouchersApply t) {
		return this.ecVouchersApplyDao.insertEntity(t);
	}

	public EcVouchersApply getEcVouchersApply(EcVouchersApply t) {
		return this.ecVouchersApplyDao.selectEntity(t);
	}

	public Long getEcVouchersApplyCount(EcVouchersApply t) {
		return this.ecVouchersApplyDao.selectEntityCount(t);
	}

	public List<EcVouchersApply> getEcVouchersApplyList(EcVouchersApply t) {
		return this.ecVouchersApplyDao.selectEntityList(t);
	}

	public int modifyEcVouchersApply(EcVouchersApply t) {
		return this.ecVouchersApplyDao.updateEntity(t);
	}

	public int removeEcVouchersApply(EcVouchersApply t) {
		return this.ecVouchersApplyDao.deleteEntity(t);
	}

	public List<EcVouchersApply> getEcVouchersApplyPaginatedList(EcVouchersApply t) {
		return this.ecVouchersApplyDao.selectEntityPaginatedList(t);
	}

	@Override
	public Long createEcVouchersApplyBatch(EcVouchersApply t) {
		t.setIs_send(1);
		this.ecVouchersApplyDao.updateEntity(t);

		EcVouchers ev = new EcVouchers();
		ev.setAdd_date(new Date());
		ev.setCreate_u_id(t.getCreate_u_id());
		ev.setDept_id(t.getDept_id());
		ev.setEffective_date(t.getEffective_date());
		ev.setGoods_id(t.getGoods_id());
		ev.setGoods_type(t.getGoods_type());
		ev.setInfo_state(0);
		ev.setIs_other(t.getIs_other());
		ev.setM_menoy(t.getM_menoy());
		ev.setGoods_sn(t.getGoods_sn());
		ev.setPlat_sys(t.getPlat_sys());
		if (t.getM_num() != null) {
			ev.setM_num(new BigDecimal(t.getM_num()));
		}
		ev.setMemo(t.getMemo());
		ev.setOwn_sys(t.getOwn_sys());
		ev.setPd_type(t.getPd_type());
		ev.setPrice(t.getPrice());
		ev.setStart_date(t.getStart_date());
		ev.setTitle(t.getTitle());
		ev.setLink_id(t.getId());

		Long ids = 0L;
		for (int i = 0; i < t.getApply_num().intValue(); i++) {
			String v_code = GetRandomNumber();
			ev.setVouchers_code(v_code);
			if (t.getHas_pwd().intValue() == 1) {
				String c_pwd = GetRandomNumber2();
				ev.setVouchers_pwd(c_pwd);
			}
			ids++;
			this.ecVouchersDao.insertEntity(ev);
		}

		return ids;

	}

	public String GetRandomNumber() {

		// 使用SET以此保证写入的数据不重复
		List<String> list = new ArrayList<String>();
		String[] arg = new String[] { "A", "B", "C", "D", "6", "E", "4", "F", "G", "8", "H", "J", "5", "K", "L", "M",
				"N", "3", "P", "Q", "R", "S", "T", "2", "U", "V", "W", "7", "X", "Y", "9" };//
		// 随机数
		Random random = new Random();
		int ss = 0;
		for (int i = 0; i < arg.length + 1; i++) {
			while (list.size() < 12) {
				// nextInt返回一个伪随机数，它是取自此随机数生成器序列的、在 0（包括）
				// 和指定值（不包括）之间均匀分布的 int 值。
				ss = random.nextInt(arg.length);
				list.add(arg[ss]);
			}
		}

		String code = "";
		for (String string : list) {
			code = code + string;
		}
		//System.out.println(code);// 0123

		EcVouchers ee = new EcVouchers();
		ee.getMap().put("code_is_not_null", true);
		List<EcVouchers> eeList = this.ecVouchersDao.selectEntityList(ee);
		List<String> cc = new ArrayList<String>();
		if (eeList != null && eeList.size() > 0) {
			for (EcVouchers ecVouchCode : eeList) {
				if (ecVouchCode.getVouchers_code() != null) {
					cc.add(ecVouchCode.getVouchers_code());
				}
			}
		}

		String codeList = StringUtils.join(cc, ",");
		if (codeList != null && codeList.equals(""))
			if (codeList.contains(code)) {
				GetRandomNumber();
			}

		return code;
	}

	public String GetRandomNumber2() {

		// 使用SET以此保证写入的数据不重复
		List<String> list = new ArrayList<String>();
		String[] arg = new String[] { "R", "S", "A", "B", "C", "D", "6", "E", "4", "F", "G", "8", "H", "J", "5", "K",
				"L", "M", "N", "3", "P", "Q", "T", "2", "U", "V", "W", "7", "X", "Y", "9" };//
		// 随机数
		Random random = new Random();
		int ss = 0;
		for (int i = 0; i < arg.length + 1; i++) {
			while (list.size() < 8) {
				// nextInt返回一个伪随机数，它是取自此随机数生成器序列的、在 0（包括）
				// 和指定值（不包括）之间均匀分布的 int 值。
				ss = random.nextInt(arg.length);
				list.add(arg[ss]);
			}
		}

		String code = "";
		for (String string : list) {
			code = code + string;
		}
		//System.out.println(code);// 0123
		return code;
	}

}
