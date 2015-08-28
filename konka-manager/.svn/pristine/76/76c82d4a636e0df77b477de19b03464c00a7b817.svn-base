package com.ebiz.mmt.dao.ibatis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.FromDrpOrderDetailDao;
import com.ebiz.mmt.domain.FromDrpOrderDetail;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

@Service
public class FromDrpOrderDetailDaoSqlMapImpl extends EntityDaoSqlMapImpl<FromDrpOrderDetail> implements FromDrpOrderDetailDao {

    @Override
    public List<FromDrpOrderDetail> selectFDrpOrderDetailAndQddata(String ds_order_id) {
        FromDrpOrderDetail fd = new FromDrpOrderDetail();
        fd.setDs_order_id(ds_order_id);
        List<FromDrpOrderDetail> list = new ArrayList<FromDrpOrderDetail>();
        list = super.getSqlMapClientTemplate().queryForList("selectFDrpOrderDetailAndQddataList", fd);
        if (list == null) {
            return Collections.EMPTY_LIST;
        }
        return list;
    }

}
