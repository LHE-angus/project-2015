package com.ebiz.mmt.dao;

import java.util.List;

import com.ebiz.mmt.domain.ToDrpOrder;
import com.ebiz.ssi.dao.EntityDao;

public interface ToDrpOrderDao extends EntityDao<ToDrpOrder> {

    /**
     * 获取需要同步到DRP系统的订单<br>
     * 
     * 此时的订单已经从渠道系统复制<br>
     * 
     * 同步状态为null,或同步状态为-1的订单都会被同步<br>
     * 
     * @return
     */
    List<ToDrpOrder> selectShouldbeSYNCEntityList();
}
