package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaXxActMessageDao;
import com.ebiz.mmt.dao.KonkaXxMessageDao;
import com.ebiz.mmt.dao.KonkaXxNoticeContentDao;
import com.ebiz.mmt.dao.KonkaXxNoticeDao;
import com.ebiz.mmt.dao.KonkaXxZmdDao;
import com.ebiz.mmt.dao.PeProdUserDao;
import com.ebiz.mmt.domain.KonkaXxActMessage;
import com.ebiz.mmt.domain.KonkaXxMessage;
import com.ebiz.mmt.domain.KonkaXxNotice;
import com.ebiz.mmt.domain.KonkaXxNoticeContent;
import com.ebiz.mmt.domain.KonkaXxZmd;
import com.ebiz.mmt.domain.PeProdUser;
import com.ebiz.mmt.service.KonkaXxNoticeService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2012-03-19 09:15:51
 */
@Service
public class KonkaXxNoticeServiceImpl implements KonkaXxNoticeService {

	@Resource
	private KonkaXxNoticeDao konkaXxNoticeDao;

	@Resource
	private KonkaXxNoticeContentDao konkaXxNoticeContentDao;

	@Resource
	private KonkaXxActMessageDao konkaXxActMessageDao;

	@Resource
	private KonkaXxMessageDao konkaXxMessageDao;

	@Resource
	private PeProdUserDao peProdUserDao;

	@Resource
	private KonkaXxZmdDao konkaXxZmdDao;

	public Long createKonkaXxNotice(KonkaXxNotice t) {
		return this.konkaXxNoticeDao.insertEntity(t);
	}

	public KonkaXxNotice getKonkaXxNotice(KonkaXxNotice t) {
		return this.konkaXxNoticeDao.selectEntity(t);
	}

	public Long getKonkaXxNoticeCount(KonkaXxNotice t) {
		return this.konkaXxNoticeDao.selectEntityCount(t);
	}

	public List<KonkaXxNotice> getKonkaXxNoticeList(KonkaXxNotice t) {
		return this.konkaXxNoticeDao.selectEntityList(t);
	}

	public int modifyKonkaXxNotice(KonkaXxNotice t) {
		return this.konkaXxNoticeDao.updateEntity(t);
	}

	public int removeKonkaXxNotice(KonkaXxNotice t) {
		return this.konkaXxNoticeDao.deleteEntity(t);
	}

	public List<KonkaXxNotice> getKonkaXxNoticePaginatedList(KonkaXxNotice t) {
		return this.konkaXxNoticeDao.selectEntityPaginatedList(t);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2012-03-19
	 */
	public Long createKonkaXxNoticeAndContent(KonkaXxNotice t) {
		Long id = this.konkaXxNoticeDao.insertEntity(t);

		KonkaXxNoticeContent content = new KonkaXxNoticeContent();
		content.setNotice_id(id);
		content.setNotice_content(t.getMap().get("content").toString());
		this.konkaXxNoticeContentDao.insertEntity(content);

		return id;
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2012-03-19
	 */
	public int modifyKonkaXxNoticeAndContent(KonkaXxNotice t) {
		int count = this.konkaXxNoticeDao.updateEntity(t);

		KonkaXxNoticeContent content = new KonkaXxNoticeContent();
		content.setNotice_id(t.getId());
		List<KonkaXxNoticeContent> contents = this.konkaXxNoticeContentDao.selectEntityList(content);

		if (null != contents && contents.size() > 0) {
			content.setId(contents.get(0).getId());
			content.setNotice_content(t.getMap().get("content").toString());
			this.konkaXxNoticeContentDao.updateEntity(content);
		}

		return count;
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2012-03-19
	 */
	public void publishKonkaXxNotice(KonkaXxNotice t) {
		this.konkaXxNoticeDao.updateEntity(t);

		// 获取下级部门/专卖店
		PeProdUser user = new PeProdUser();
		user.setId(t.getAdd_user_id());
		user.setIs_del(0);
		user = this.peProdUserDao.selectEntity(user);

		if (null != user) {
			Long par_dept_id = user.getPar_dept_id();
			Long dept_id = user.getDept_id();

			if (par_dept_id == 0L) { // 新兴渠道总部
				PeProdUser dept_user = new PeProdUser();
				dept_user.setPar_dept_id(dept_id);

				List<PeProdUser> dept_user_List = this.peProdUserDao.selectEntityList(dept_user);

				if (null != dept_user_List && dept_user_List.size() > 0) {
					for (PeProdUser temp : dept_user_List) {
						// 循环插入活动消息表和消息盒子
						KonkaXxActMessage act_msg = new KonkaXxActMessage();
						act_msg.setId(t.getId());
						act_msg.setMsg_title(t.getNotice_title());
						act_msg.setMsg_content(t.getMap().get("content").toString());
						act_msg.setMsg_type(1); // 0，消息；1，通知公告
						act_msg.setSender_id(0L); // 0-系统；
						act_msg.setRec_user_id(temp.getId());
						this.konkaXxActMessageDao.insertKonkaXxActMessageForNotice(act_msg);

						KonkaXxMessage msg = new KonkaXxMessage();
						msg.setOut_id(t.getId());
						msg.setMsg_title(t.getNotice_title());
						msg.setMsg_content(t.getMap().get("content").toString());
						msg.setMsg_type(1); // 0，消息；1，通知公告
						msg.setSender_id(0L); // 0-系统；
						msg.setRec_user_id(temp.getId());
						msg.setState(0);
						this.konkaXxMessageDao.insertEntity(msg);
					}
				}
			} else { // 分公司
				KonkaXxZmd zmd = new KonkaXxZmd();
				zmd.setDept_id(dept_id);
				zmd.setIs_del(0);

				List<KonkaXxZmd> zmdList = this.konkaXxZmdDao.selectKonkaXxZmdAndManagerList(zmd);

				if (null != zmdList && zmdList.size() > 0) {
					for (KonkaXxZmd temp : zmdList) {
						// 循环插入活动消息表和消息盒子
						KonkaXxActMessage act_msg = new KonkaXxActMessage();
						act_msg.setId(t.getId());
						act_msg.setMsg_title(t.getNotice_title());
						act_msg.setMsg_content(t.getMap().get("content").toString());
						act_msg.setMsg_type(1); // 0，消息；1，通知公告
						act_msg.setSender_id(0L); // 0-系统；
						act_msg.setRec_user_id(Long.valueOf(temp.getMap().get("manager_id").toString()));
						this.konkaXxActMessageDao.insertKonkaXxActMessageForNotice(act_msg);

						KonkaXxMessage msg = new KonkaXxMessage();
						msg.setOut_id(t.getId());
						msg.setMsg_title(t.getNotice_title());
						msg.setMsg_content(t.getMap().get("content").toString());
						msg.setMsg_type(1); // 0，消息；1，通知公告
						msg.setSender_id(0L); // 0-系统；
						msg.setRec_user_id(Long.valueOf(temp.getMap().get("manager_id").toString()));
						msg.setState(0);
						this.konkaXxMessageDao.insertEntity(msg);
					}
				}
			}

		}
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2012-03-19
	 */
	public Long getKonkaXxNoticeAndContentCount(KonkaXxNotice t) {
		return this.konkaXxNoticeDao.selectKonkaXxNoticeAndContentCount(t);
	}

	/**
	 * @author Wu,ShangLong
	 * @version 2012-03-19
	 */
	public List<KonkaXxNotice> getKonkaXxNoticeAndContentPaginatedList(KonkaXxNotice t) {
		return this.konkaXxNoticeDao.selectKonkaXxNoticeAndContentPaginatedList(t);
	}

}
