package com.ebiz.mmt.dao.ibatis;

import org.springframework.stereotype.Repository;

import com.ebiz.mmt.dao.AttachmentDao;
import com.ebiz.mmt.domain.Attachment;
import com.ebiz.ssi.dao.ibatis.EntityDaoSqlMapImpl;

/**
 * @author Hui,Gang
 */
@Repository
public class AttachmentDaoSqlMapImpl extends EntityDaoSqlMapImpl<Attachment> implements AttachmentDao {
}
