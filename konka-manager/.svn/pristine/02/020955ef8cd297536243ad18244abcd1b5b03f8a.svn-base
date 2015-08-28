package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaPePublicScopeDao;
import com.ebiz.mmt.dao.PeShopMsgDao;
import com.ebiz.mmt.domain.KonkaPePublicScope;
import com.ebiz.mmt.domain.PeShopMsg;
import com.ebiz.mmt.service.PeShopMsgService;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Xing,XiuDong
 * @date 2011-05-16 15:54:19
 */
@Service
public class PeShopMsgServiceImpl implements PeShopMsgService {

	@Resource
	private PeShopMsgDao peShopMsgDao;
	
	/*@Resource
	private KonkaBranchCategoryDao konkaBranchCategoryDao;*/

	@Resource
	private KonkaPePublicScopeDao konkaPePublicScopeDao;
	
	public Long createPeShopMsg(PeShopMsg t) {
		Long id = this.peShopMsgDao.insertEntity(t);
		List<KonkaPePublicScope> konkaPePublicScopeList = t.getKonkaPePublicScopeList();
		if (null != konkaPePublicScopeList) {
			for (KonkaPePublicScope kps : konkaPePublicScopeList) {
				kps.setArticle_id(id);
				this.konkaPePublicScopeDao.insertEntity(kps);
			}
		}

		return id;
	}

	public PeShopMsg getPeShopMsg(PeShopMsg t) {
		return this.peShopMsgDao.selectEntity(t);
	}

	public Long getPeShopMsgCount(PeShopMsg t) {
		return this.peShopMsgDao.selectEntityCount(t);
	}

	public List<PeShopMsg> getPeShopMsgList(PeShopMsg t) {
		return this.peShopMsgDao.selectEntityList(t);
	}
	
	public int modifyPeShopMsg(PeShopMsg t) {
		KonkaPePublicScope _kps = new KonkaPePublicScope();
		_kps.setArticle_id(t.getId());
		this.konkaPePublicScopeDao.deleteEntity(_kps);

		List<KonkaPePublicScope> konkaPePublicScopeList = t.getKonkaPePublicScopeList();
		if (null != konkaPePublicScopeList && konkaPePublicScopeList.size() >0) {
			for (KonkaPePublicScope kps : konkaPePublicScopeList) {
				kps.setArticle_id(t.getId());
				this.konkaPePublicScopeDao.insertEntity(kps);
			}
		}

		return this.peShopMsgDao.updateEntity(t);
	}

	public int removePeShopMsg(PeShopMsg t) {
		return this.peShopMsgDao.deleteEntity(t);
	}

	public List<PeShopMsg> getPeShopMsgPaginatedList(PeShopMsg t) {
		return this.peShopMsgDao.selectEntityPaginatedList(t);
	}

	/*
	 * (non-Javadoc)
	 * @see com.ebiz.mmt.service.PeShopMsgService#getPeShopMsgWithPar(com.ebiz.mmt.domain.PeShopMsg)
	 */
	@Override
	public List<PeShopMsg> getPeShopMsgWithPar(PeShopMsg entity) {
		// 已查看
		if (entity.getState() != null) {
			entity.setState(2);
			this.peShopMsgDao.updateEntity(entity);
		}
		return this.peShopMsgDao.selectPeShopMsgWithPar(entity);
	}

	/*
	 * (non-Javadoc)
	 * @see com.ebiz.mmt.service.PeShopMsgService#createPeShopMsgWithUpdateReply(com.ebiz.mmt.domain.PeShopMsg)
	 */
	@Override
	public Long createPeShopMsgWithUpdateReply(PeShopMsg entity) {
		Long result = null;
		String receive_msg_id = (String) entity.getMap().get("receive_msg_id");
		// 如果是回复信件，则修改原信件的状态，并将本信件的设置父信件ID
		if (StringUtils.isNotEmpty(receive_msg_id)) {
			PeShopMsg peShopMsg = new PeShopMsg();
			peShopMsg.setId(Long.valueOf(receive_msg_id));
			// 3-已回复
			peShopMsg.setState(3);
			this.peShopMsgDao.updateEntity(peShopMsg);
			peShopMsg = this.peShopMsgDao.selectEntity(peShopMsg);
			entity.setPar_id(peShopMsg.getPar_id() == 0 ? peShopMsg.getId() : peShopMsg.getPar_id());
		}
		String receive_user_ids = (String) entity.getMap().get("receive_user_ids");
		String receive_user_names = (String) entity.getMap().get("receive_user_names");
		if (StringUtils.isNotEmpty(receive_user_ids)) {
			String[] idArr = receive_user_ids.split(",");
			String[] nameArr = receive_user_names.split(",");
			for (int i = 0; i < idArr.length; i++) {
				PeShopMsg msg = new PeShopMsg();
				msg.setReceive_user_id(Long.valueOf(idArr[i]));
				msg.setReceive_user_name(nameArr[i]);
				msg.setSend_user_id(entity.getSend_user_id());
				msg.setSend_user_name(entity.getSend_user_name());
				msg.setSend_user_type(entity.getSend_user_type());
				msg.setSend_ip(entity.getSend_ip());

				msg.setContent(entity.getContent());
				msg.setTitle(entity.getTitle());
				msg.setPar_id(entity.getPar_id());
				msg.setSend_date(entity.getSend_date());
				msg.setReceive_user_type(entity.getReceive_user_type());
				msg.setState(entity.getState());
				result = this.peShopMsgDao.insertEntity(msg);
			}
		} else {
			result = this.peShopMsgDao.insertEntity(entity);
		}
		return result;
	}

	public Long getPeShopMsgCountForReceive(PeShopMsg t) {
		return this.peShopMsgDao.selectPeShopMsgCountForReceive(t);
	}

	@Override
	public List<PeShopMsg> getPeShopMsgPaginatedListForReceive(PeShopMsg t) {
		return this.peShopMsgDao.selectPeShopMsgPaginatedListForReceive(t);
	}
	
	/**
	 * @author Li,Ka
	 * @version 2011-12-23 15:15:09
	 * @desc 客户端回复站内信
	 */
	public Long createPeShopMsgReply(PeShopMsg t) {
		PeShopMsg parPeShopMsg = (PeShopMsg) t.getMap().get("parMsg");
		if (null != parPeShopMsg) {
			this.peShopMsgDao.updateEntity(parPeShopMsg);//更新原件为已回复
		}
		
		Long id = this.peShopMsgDao.insertEntity(t);//插入回复的信件
		
		KonkaPePublicScope konkaPePublicScope = (KonkaPePublicScope) t.getMap().get("konkaPePublicScope");
		konkaPePublicScope.setArticle_id(id);
		
		this.konkaPePublicScopeDao.insertEntity(konkaPePublicScope);//插入回复信息的投放范围
		return id;
	}
	
	/*
	 * 原始的修改，用于群删
	 * @see com.ebiz.mmt.service.PeShopMsgService#modifyPeShopMsg(com.ebiz.mmt.domain.PeShopMsg)
	 */
	public int preModifyPeShopMsg(PeShopMsg t) {
		return this.peShopMsgDao.updateEntity(t);
	}
}
