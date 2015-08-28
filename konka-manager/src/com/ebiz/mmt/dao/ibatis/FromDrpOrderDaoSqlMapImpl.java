package com.ebiz.mmt.dao.ibatis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.FromDrpOrderDao;
import com.ebiz.mmt.domain.FromDrpOrder;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;


@Service
public class FromDrpOrderDaoSqlMapImpl extends EntityDaoSqlMapImpl<FromDrpOrder> implements FromDrpOrderDao {
    @Override
    public List<FromDrpOrder> selectFDrpAndQdOrderList(FromDrpOrder fo) {
        List<FromDrpOrder> list = new ArrayList<FromDrpOrder>();
        list = super.getSqlMapClientTemplate().queryForList("selectFDrpAndQdOrderList", fo);
        if (list == null) {
            return Collections.EMPTY_LIST;
        }
        return list;
    }

}
