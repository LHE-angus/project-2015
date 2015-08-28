package com.ebiz.mmt.dao.ibatis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.ToDrpOrderDao;
import com.ebiz.mmt.domain.ToDrpOrder;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

@Service
public class ToDrpOrderDaoSqlMapImpl extends EntityDaoSqlMapImpl<ToDrpOrder> implements ToDrpOrderDao {

    @Override
    public List<ToDrpOrder> selectShouldbeSYNCEntityList() {
        ToDrpOrder tdo = new ToDrpOrder();
        tdo.getMap().put("tobe_sync_flag", "not null");
        List<ToDrpOrder> list = new ArrayList<ToDrpOrder>();
        list = super.selectEntityList(tdo);
        if (list != null) {
            return list;
        } else {
            return Collections.emptyList();
        }
    }

}
