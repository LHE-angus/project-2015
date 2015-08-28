package com.ebiz.mmt.dao.jdbc;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.stereotype.Component;

import com.ebiz.mmt.dao.JdbcDao;
import com.ebiz.mmt.domain.MmtEntpShop;

@Component
public class JdbcDaoSqlMapImpl extends SimpleJdbcDaoSupport implements JdbcDao {

	@Override
	public Long insertMmtKonkaEntpShop(MmtEntpShop t) throws DataAccessException {
		StringBuffer sb = new StringBuffer();
		sb.append(" insert into konka.konka_entp_shop ");
		sb.append("   (SHOP_ID, ");
		sb.append("    ENTP_ID, ");
		sb.append("    SHOP_NAME, ");
		sb.append("    SHOP_DESC, ");
		sb.append("    SHOP_TYPE, ");
		sb.append("    LOGO_PIC, ");
		sb.append("    P_INDEX, ");
		sb.append("    C_INDEX, ");
		sb.append("    MAIN_PD, ");
		sb.append("    IS_RURAL, ");
		sb.append("    IS_AUTH, ");
		sb.append("    CREDIT, ");
		sb.append("    ACCESS_NUM, ");
		sb.append("    HOST_ID, ");
		sb.append("    ADD_DATE, ");
		sb.append("    DEL_MAN2, ");
		sb.append("    STATE, ");
		sb.append("    AUDITOR_ID, ");
		sb.append("    AUDIT_DATE, ");
		sb.append("    ONLINE_QQ, ");
		sb.append("    ALIPAY_EMAIL, ");
		sb.append("    IS_COMMEND, ");
		sb.append("    ORDER_VALUE, ");
		sb.append("    ROYALTY_RATE, ");
		sb.append("    KEY_SEQUENCE, ");
		sb.append("    LOGIN_SETTINGS, ");
		sb.append("    P_COUNT, ");
		sb.append("    O_COUNT, ");
		sb.append("    IS_OTN, ");
		sb.append("    G_LNG, ");
		sb.append("    G_LAT, ");
		sb.append("    LINK_USER, ");
		sb.append("    LINK_PHONE, ");
		sb.append("    STREET_ADDR, ");
		sb.append("    POST_CODE, ");
		sb.append("    MAIN_PD2, ");
		sb.append("    STD_ENTP_MAIN_ID, ");
		sb.append("    SHOP_LEVEL, ");
		sb.append("    IS_SALL, ");
		sb.append("    IS_MAINT, ");
		sb.append("    IS_CALLB, ");
		sb.append("    G_LAT_T, ");
		sb.append("    G_LNG_T, ");
		sb.append("    G_IS_AUDIT, ");
		sb.append("    G_RE_COUNT, ");
		sb.append("    MMT) ");
		sb.append("   select a.SHOP_ID, ");
		sb.append("          a.ENTP_ID, ");
		sb.append("          a.SHOP_NAME, ");
		sb.append("          a.SHOP_DESC, ");
		sb.append("          a.SHOP_TYPE, ");
		sb.append("          a.LOGO_PIC, ");
		sb.append("          a.P_INDEX, ");
		sb.append("          a.C_INDEX, ");
		sb.append("          a.MAIN_PD, ");
		sb.append("          a.IS_RURAL, ");
		sb.append("          a.IS_AUTH, ");
		sb.append("          a.CREDIT, ");
		sb.append("          a.ACCESS_NUM, ");
		sb.append("          a.HOST_ID, ");
		sb.append("          a.ADD_DATE, ");
		sb.append("          a.DEL_MAN2, ");
		sb.append("          a.STATE, ");
		sb.append("          a.AUDITOR_ID, ");
		sb.append("          a.AUDIT_DATE, ");
		sb.append("          a.ONLINE_QQ, ");
		sb.append("          a.ALIPAY_EMAIL, ");
		sb.append("          a.IS_COMMEND, ");
		sb.append("          a.ORDER_VALUE, ");
		sb.append("          a.ROYALTY_RATE, ");
		sb.append("          a.KEY_SEQUENCE, ");
		sb.append("          a.LOGIN_SETTINGS, ");
		sb.append("          a.P_COUNT, ");
		sb.append("          a.O_COUNT, ");
		sb.append("          a.IS_OTN, ");
		sb.append("          a.G_LNG, ");
		sb.append("          a.G_LAT, ");
		sb.append("          a.LINK_USER, ");
		sb.append("          a.LINK_PHONE, ");
		sb.append("          a.STREET_ADDR, ");
		sb.append("          a.POST_CODE, ");
		sb.append("          a.MAIN_PD2, ");
		sb.append("          a.STD_ENTP_MAIN_ID, ");
		sb.append("          a.SHOP_LEVEL, ");
		sb.append("          a.IS_SALL, ");
		sb.append("          a.IS_MAINT, ");
		sb.append("          a.IS_CALLB, ");
		sb.append("          a.G_LAT_T, ");
		sb.append("          a.G_LNG_T, ");
		sb.append("          a.G_IS_AUDIT, ");
		sb.append("          a.G_RE_COUNT, ");
		sb.append("          a.MMT ");
		sb.append("     from (select * ");
		sb.append("             from entp_shop t ");
		sb.append("            where t.shop_id in ");
		sb.append("                  (select distinct (shop_id) ");
		sb.append("                     from ( ");

		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;

		for (int i = 2011; i < year + 1; i++) {
			for (int j = 1; j < month + 1; j++) {
				if (!(i == 2011 && j == 1)) {
					sb.append(" union all ");
				}
				sb.append(" select * from chea_fill.mdas_shop_brandsales_").append(i);
				if (j < 10) {
					sb.append("0");
				}
				sb.append(j).append(" ");
			}
		}
		sb.append("                           ) ");
		sb.append("                    where brand_id = 114)) a ");
		sb.append("     left join konka.konka_entp_shop b ");
		sb.append("       on a.shop_id = b.shop_id ");
		sb.append("    where b.shop_id is null ");

		return Long.valueOf(super.getSimpleJdbcTemplate().update(sb.toString(), new HashMap<Object, Object>()));
	}

	@Override
	public int deleteEntity(MmtEntpShop arg0) throws DataAccessException {
		
		return 0;
	}

	@Override
	public Long insertEntity(MmtEntpShop arg0) throws DataAccessException {
		
		return null;
	}

	@Override
	public MmtEntpShop selectEntity(MmtEntpShop arg0) throws DataAccessException {
		
		return null;
	}

	@Override
	public Long selectEntityCount(MmtEntpShop arg0) throws DataAccessException {
		
		return null;
	}

	@Override
	public List<MmtEntpShop> selectEntityList(MmtEntpShop arg0) throws DataAccessException {
		
		return null;
	}

	@Override
	public List<MmtEntpShop> selectEntityPaginatedList(MmtEntpShop arg0) throws DataAccessException {
		
		return null;
	}

	@Override
	public int updateEntity(MmtEntpShop arg0) throws DataAccessException {
		
		return 0;
	}
}
