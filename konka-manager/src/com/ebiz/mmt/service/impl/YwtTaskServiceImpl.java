package com.ebiz.mmt.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.AttachmentDao;
import com.ebiz.mmt.dao.YwtTaskDao;
import com.ebiz.mmt.dao.YwtTaskReceiveDao;
import com.ebiz.mmt.domain.Attachment;
import com.ebiz.mmt.domain.YwtTask;
import com.ebiz.mmt.domain.YwtTaskReceive;
import com.ebiz.mmt.service.YwtTaskService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-10-20 10:01:47
 */
@Service
public class YwtTaskServiceImpl implements YwtTaskService {

	@Resource
	private YwtTaskDao ywtTaskDao;
	
	@Resource
	private YwtTaskReceiveDao ywtTaskReceiveDao;

	@Resource
	private AttachmentDao attachmentDao;

	public Long createYwtTask(YwtTask t) {
		return this.ywtTaskDao.insertEntity(t);
	}

	public YwtTask getYwtTask(YwtTask t) {
		return this.ywtTaskDao.selectEntity(t);
	}

	public Long getYwtTaskCount(YwtTask t) {
		return this.ywtTaskDao.selectEntityCount(t);
	}

	public List<YwtTask> getYwtTaskList(YwtTask t) {
		return this.ywtTaskDao.selectEntityList(t);
	}

	public int modifyYwtTask(YwtTask t) {
		return this.ywtTaskDao.updateEntity(t);
	}

	public int removeYwtTask(YwtTask t) {
		return this.ywtTaskDao.deleteEntity(t);
	}

	public List<YwtTask> getYwtTaskPaginatedList(YwtTask t) {
		return this.ywtTaskDao.selectEntityPaginatedList(t);
	}

	@Override
	public Long getYwtTaskLBCount(YwtTask v) {
		return this.ywtTaskDao.selectYwtTaskLBCount(v);
	}

	@Override
	public List<YwtTask> getYwtTaskLBPaginatedList(YwtTask v) {
		return this.ywtTaskDao.selectYwtTaskLBPaginatedList(v);
	}
	
	@Override
	public List<YwtTask> selectxiaji(YwtTask v) {
		return this.ywtTaskDao.selectxiaji(v);
	}
    //新增任务联动 接受者数据一起插入
	@Override
	public int createYwtTask_YwtTaskReceive(YwtTask t) {
		Long id=this.ywtTaskDao.insertEntity(t);
		YwtTaskReceive delywtTaskReceive=new YwtTaskReceive();
		delywtTaskReceive.setTask_id(id);
		
		List<YwtTaskReceive> receivelist=t.getYwtTaskReceive();
		if (null!=receivelist) {
			for (YwtTaskReceive ywtTaskReceive : receivelist) {
				ywtTaskReceive.setTask_id(id);
				//System.out.println("==================="+ywtTaskReceive.getUser_id());
				this.ywtTaskReceiveDao.insertEntity(ywtTaskReceive);
			}
		}
		//添加附件
		List<Attachment> attachmentlist=t.getAttachment();
		if (null!=attachmentlist) {
			for (Attachment attachment : attachmentlist) {
				attachment.setLink_id(id);
				this.attachmentDao.insertEntity(attachment);
			}
		}
		return Integer.valueOf(""+id);
	}
    //修改任务删除之前接收者然后重新插入数据
	@Override
	public int modifyYwtTask_YwtTaskReceive(YwtTask t) {
		int id=this.ywtTaskDao.updateEntity(t);
		YwtTaskReceive delywtTaskReceive=new YwtTaskReceive();
		delywtTaskReceive.setTask_id(t.getId());
		//根据task_id删除任务接收人
		this.ywtTaskReceiveDao.deleteEntity(delywtTaskReceive);
		List<YwtTaskReceive> receivelist=t.getYwtTaskReceive();
		if (null!=receivelist) {
			//重新插入
			for (YwtTaskReceive ywtTaskReceive : receivelist) {
				ywtTaskReceive.setTask_id(t.getId());
				//System.out.println("================"+ywtTaskReceive.getUser_id());
				this.ywtTaskReceiveDao.insertEntity(ywtTaskReceive);
			}
		}
		//添加附件
		List<Attachment> attachmentlist=t.getAttachment();
		if (null!=attachmentlist) {
			for (Attachment attachment : attachmentlist) {
				attachment.setLink_id(t.getId());
				this.attachmentDao.insertEntity(attachment);
			}
		}
		return id;
	}
	
    /**
     * 根据单个或多个部门 模糊搜索用户待选分配下级人员列表
     */
	@Override
	public List<Map<String, String>> getChooseList(YwtTask v) {
		return this.ywtTaskDao.selectChooseList(v);
	}
	
    /**
     * 查找接收人信息列表
     */
	@Override
	public List<Map<String, String>> getNoChooseList(YwtTask v) {
		return this.ywtTaskDao.selectNoChooseList(v);
	}
}
