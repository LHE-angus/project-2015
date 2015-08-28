package com.ebiz.mmt.dao.ibatis;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.EcVouchersDao;
import com.ebiz.mmt.domain.EcVouchers;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-12-25 15:36:42
 */
@Service
public class EcVouchersDaoSqlMapImpl extends EntityDaoSqlMapImpl<EcVouchers> implements EcVouchersDao {

	/**
	 * @author tudp
	 * @throws 2013-12-29
	 */
	public int modifyEcVouchersByOrderId(EcVouchers t) throws DataAccessException {
		int i = this.getSqlMapClientTemplate().update("updateEcVouchersByOrderId", t);
		return i;
	}

	@Override
	public Long insertBatch(EcVouchers t) {
		String num1 = (String) t.getMap().get("num");
		String has_pwd = (String) t.getMap().get("has_pwd");
		Integer num = Integer.valueOf(num1);
		Long ids = 0L;
		for (int i = 0; i < num.intValue(); i++) {
			String v_code = GetRandomNumber();
			t.setVouchers_code(v_code);
			if (has_pwd.equals("1")) {
				String c_pwd = GetRandomNumber2();
				t.setVouchers_pwd(c_pwd);
			}
			ids++;
			this.getSqlMapClientTemplate().insert("insertEcVouchers", t);
		}

		return ids;
	}

	@SuppressWarnings("unchecked")
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
		List<EcVouchers> eeList = this.getSqlMapClientTemplate().queryForList("selectEcVouchersList", ee);
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
