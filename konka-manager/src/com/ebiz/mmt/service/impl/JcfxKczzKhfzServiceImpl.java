package com.ebiz.mmt.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.JcfxKczzKhfzDao;
import com.ebiz.mmt.dao.JcfxKczzKhfzLinkDao;
import com.ebiz.mmt.dao.KonkaR3ShopDao;
import com.ebiz.mmt.domain.JcfxKczzKhfz;
import com.ebiz.mmt.domain.JcfxKczzKhfzLink;
import com.ebiz.mmt.domain.KonkaR3Shop;
import com.ebiz.mmt.service.JcfxKczzKhfzService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-12-23 21:16:26
 */
@Service
public class JcfxKczzKhfzServiceImpl implements JcfxKczzKhfzService {

	@Resource
	private JcfxKczzKhfzDao jcfxKczzKhfzDao;
	
	@Resource
	private JcfxKczzKhfzLinkDao jcfxKczzKhfzLinkDao;
	
	public Long createJcfxKczzKhfz(JcfxKczzKhfz t) {
		return this.jcfxKczzKhfzDao.insertEntity(t);
	}

	public JcfxKczzKhfz getJcfxKczzKhfz(JcfxKczzKhfz t) {
		return this.jcfxKczzKhfzDao.selectEntity(t);
	}

	public Long getJcfxKczzKhfzCount(JcfxKczzKhfz t) {
		return this.jcfxKczzKhfzDao.selectEntityCount(t);
	}

	public List<JcfxKczzKhfz> getJcfxKczzKhfzList(JcfxKczzKhfz t) {
		return this.jcfxKczzKhfzDao.selectEntityList(t);
	}

	public int modifyJcfxKczzKhfz(JcfxKczzKhfz t) {
		return this.jcfxKczzKhfzDao.updateEntity(t);
	}

	public int removeJcfxKczzKhfz(JcfxKczzKhfz t) {
		return this.jcfxKczzKhfzDao.deleteEntity(t);
	}

	public List<JcfxKczzKhfz> getJcfxKczzKhfzPaginatedList(JcfxKczzKhfz t) {
		return this.jcfxKczzKhfzDao.selectEntityPaginatedList(t);
	}
	
    /**
     * 添加连表添加分组信息  级联操作 分组和客户信息
     */
	@Override
	public Long createJcfxKczzKhfzLB(JcfxKczzKhfz t) {
		Long id=this.jcfxKczzKhfzDao.insertEntity(t);
		List <JcfxKczzKhfzLink> linkList=t.getLinkList(); 
		for (JcfxKczzKhfzLink jcfxKczzKhfzLink : linkList) {
			jcfxKczzKhfzLink.setTitle(t.getTitle());//管理分组名
			jcfxKczzKhfzLink.setKhfz_id(id);//管理分组id
			jcfxKczzKhfzLinkDao.insertEntity(jcfxKczzKhfzLink);
		}
		return id;
	}
	
	/**
     * 修改 连表添加分组信息  级联操作 分组和客户信息
     */
	@Override
	public int modifyJcfxKczzKhfzLB(JcfxKczzKhfz t) {
		int id=this.jcfxKczzKhfzDao.updateEntity(t);
		//根据分组id删除之前分配的客户
		JcfxKczzKhfzLink entityLink=new JcfxKczzKhfzLink();
		entityLink.setKhfz_id(t.getId());
		List <JcfxKczzKhfzLink> linkList=this.jcfxKczzKhfzLinkDao.selectEntityList(entityLink);
		List<Long> linkIdList=new ArrayList<Long>();
        for (int i = 0; i < linkList.size(); i++) {
        	linkIdList.add(linkList.get(i).getId());
		}
        if (null!=linkIdList&&linkIdList.size()>0) {
			entityLink = new JcfxKczzKhfzLink();
			entityLink.getMap().put("pks", linkIdList);
			this.jcfxKczzKhfzLinkDao.deleteEntity(entityLink);
		}
		//新增分配客户
        linkList=t.getLinkList();
		if (null!=linkList&&linkList.size()>0) {
			for (JcfxKczzKhfzLink jcfxKczzKhfzLink : linkList) {
				jcfxKczzKhfzLink.setTitle(t.getTitle());//管理分组名
				jcfxKczzKhfzLink.setKhfz_id(t.getId());//管理分组id
				jcfxKczzKhfzLinkDao.insertEntity(jcfxKczzKhfzLink);
			}
		}
		return Integer.valueOf(""+t.getId());
	}

	
	@Override
	public Long getJcfxKczzKhfzLBCount(JcfxKczzKhfz v) {
		return this.jcfxKczzKhfzDao.selectJcfxKczzKhfzLBCount(v);
	}

	@Override
	public List<JcfxKczzKhfz> getJcfxKczzKhfzLBPaginatedList(JcfxKczzKhfz v) {
		return this.jcfxKczzKhfzDao.selectJcfxKczzKhfzLBPaginatedList(v);
	}
   
}
