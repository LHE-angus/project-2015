package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.KonkaExpenseClaimsDao;
import com.ebiz.mmt.dao.KonkaPeAttachmentsDao;
import com.ebiz.mmt.dao.KonkaoaDocInfoDao;
import com.ebiz.mmt.dao.KonkaoaFilesAuditNodeDao;
import com.ebiz.mmt.dao.KonkaoaFilesContentDao;
import com.ebiz.mmt.dao.KonkaoaFilesDao;
import com.ebiz.mmt.dao.KonkaoaFilesPropertyDao;
import com.ebiz.mmt.dao.KonkaoaFilesRecipientDao;
import com.ebiz.mmt.domain.KonkaExpenseClaims;
import com.ebiz.mmt.domain.KonkaPeAttachments;
import com.ebiz.mmt.domain.KonkaoaDocInfo;
import com.ebiz.mmt.domain.KonkaoaFiles;
import com.ebiz.mmt.domain.KonkaoaFilesAuditNode;
import com.ebiz.mmt.domain.KonkaoaFilesContent;
import com.ebiz.mmt.domain.KonkaoaFilesProperty;
import com.ebiz.mmt.domain.KonkaoaFilesRecipient;
import com.ebiz.mmt.domain.KonkaoaSsuedDocument;
import com.ebiz.mmt.service.KonkaoaFilesService;

/**
 * @author Hui,Gang
 * @version Build 2010-12-13 14:49:33
 */

/**
 * 
 * 20150519代码审查
 * 
 */

@Service
public class KonkaoaFilesServiceImpl implements KonkaoaFilesService {

    @Resource
    private KonkaoaDocInfoDao docInfoDao;

    @Resource
    private KonkaoaFilesDao filesDao;

    @Resource
    private KonkaExpenseClaimsDao expenseClaimsDao;

    @Resource
    private KonkaoaFilesContentDao filesContentDao;

    @Resource
    private KonkaPeAttachmentsDao KonkaPeAttachmentsDao;

    @Resource
    private KonkaoaFilesRecipientDao filesRecipientDao;

    @Resource
    private KonkaoaFilesPropertyDao filesPropertyDao;

    @Resource
    private KonkaoaFilesAuditNodeDao filesAuditNodeDao;

    public Long createKonkaoaFiles(KonkaoaFiles t) {
        Long id = this.filesDao.insertEntity(t);

        List<KonkaoaFilesProperty> filesPropertyList = t.getFilesPropertyList();
        if (null != filesPropertyList) {
            for (KonkaoaFilesProperty fp : filesPropertyList) {
                fp.setLink_id(id);
                filesPropertyDao.insertEntity(fp);
            }
        }

        List<KonkaoaFilesRecipient> filesRecipientList = t.getFilesRecipientList();
        if (null != filesRecipientList) {
            for (KonkaoaFilesRecipient fr : filesRecipientList) {
                fr.setLink_id(id);
                filesRecipientDao.insertEntity(fr);
            }
        }

        // 文件内容,单独的表来存储
        KonkaoaFilesContent filesContent = new KonkaoaFilesContent();
        filesContent.setLink_id(id);
        filesContent.setContent(t.getContent());
        this.filesContentDao.insertEntity(filesContent);

        List<KonkaPeAttachments> KonkaPeAttachmentsList = t.getAttachmentList();
        if (null != KonkaPeAttachmentsList) {
            for (KonkaPeAttachments KonkaPeAttachments : KonkaPeAttachmentsList) {
                KonkaPeAttachments.setLink_id(id);
                KonkaPeAttachments.setIs_del(0l);
                KonkaPeAttachments.setAdd_user_id(t.getSubmit_user_id());
                this.KonkaPeAttachmentsDao.insertEntity(KonkaPeAttachments);
            }
        }

        // 生成审批节点
        List<KonkaoaFilesAuditNode> filesAuditNodeList = t.getFilesAuditNodeList();
        if (null != filesAuditNodeList) {
            for (KonkaoaFilesAuditNode fau : filesAuditNodeList) {
                fau.setLink_id(id);
                this.filesAuditNodeDao.insertEntity(fau);
            }
        }

        return id;
    }

    public KonkaoaFiles getKonkaoaFiles(KonkaoaFiles t) {
        return this.filesDao.selectEntity(t);
    }

    public Long getKonkaoaFilesCount(KonkaoaFiles t) {
        return this.filesDao.selectEntityCount(t);
    }

    public List<KonkaoaFiles> getKonkaoaFilesList(KonkaoaFiles t) {
        return this.filesDao.selectEntityList(t);
    }

    public int modifyKonkaoaFiles(KonkaoaFiles t) {
        int count = this.filesDao.updateEntity(t);

        KonkaoaFilesProperty _fp = new KonkaoaFilesProperty();
        _fp.setLink_id(t.getId());
        filesPropertyDao.deleteEntity(_fp);
        List<KonkaoaFilesProperty> filesPropertyList = t.getFilesPropertyList();
        if (null != filesPropertyList) {
            for (KonkaoaFilesProperty fp : filesPropertyList) {
                fp.setLink_id(t.getId());
                filesPropertyDao.insertEntity(fp);
            }
        }

        KonkaoaFilesRecipient _fr = new KonkaoaFilesRecipient();
        _fr.setLink_id(t.getId());
        filesRecipientDao.deleteEntity(_fr);
        List<KonkaoaFilesRecipient> filesRecipientList = t.getFilesRecipientList();
        if (null != filesRecipientList) {
            for (KonkaoaFilesRecipient fr : filesRecipientList) {
                fr.setLink_id(t.getId());
                filesRecipientDao.insertEntity(fr);
            }
        }

        KonkaoaFilesAuditNode _fan = new KonkaoaFilesAuditNode();
        _fan.setLink_id(t.getId());
        // 有可能是删除不干净
        _fan.setAudit_type(t.getAudit_type());
        filesAuditNodeDao.deleteEntity(_fan);
        List<KonkaoaFilesAuditNode> filesAuditNodeList = t.getFilesAuditNodeList();
        if (null != filesAuditNodeList) {
            for (KonkaoaFilesAuditNode fau : filesAuditNodeList) {
                fau.setLink_id(t.getId());
                this.filesAuditNodeDao.insertEntity(fau);
            }
        }

        if (null != t.getContent()) {
            KonkaoaFilesContent filesContent = new KonkaoaFilesContent();
            filesContent.setLink_id(t.getId());// 主表的ID
            filesContent.setContent(t.getContent());
            this.filesContentDao.updateEntity(filesContent);
        }

        List<KonkaPeAttachments> KonkaPeAttachmentsList = t.getAttachmentList();
        if (null != KonkaPeAttachmentsList) {
            for (KonkaPeAttachments KonkaPeAttachments : KonkaPeAttachmentsList) {
                KonkaPeAttachments.setLink_id(t.getId());
                KonkaPeAttachments.setIs_del(0l);
                this.KonkaPeAttachmentsDao.insertEntity(KonkaPeAttachments);
            }
        }

        return count;
    }

    public int removeKonkaoaFiles(KonkaoaFiles t) {
        return this.filesDao.deleteEntity(t);
    }

    public List<KonkaoaFiles> getKonkaoaFilesPaginatedList(KonkaoaFiles t) {
        return this.filesDao.selectEntityPaginatedList(t);
    }

    public void auditFiles(KonkaoaFiles t) {

        this.expenseClaimsDao.updateEntity(t.getExpenseClaims());

        this.filesAuditNodeDao.updateEntity(t.getFilesAuditNode());

        if (null != t.getFilesRecipient()) {
            this.filesRecipientDao.updateEntity(t.getFilesRecipient());
        }

        // TODO 是不是中途被插入
        // 没有流程的审批,会在审批过程中指定下一个审批人
        List<KonkaoaFilesAuditNode> filesAuditNodeList = t.getFilesAuditNodeList();
        if (t.getFile_status() != 2) {
            if (null!=t.getIs_node() && t.getIs_node() != 1) {// 没有流程的提交
                if (null != filesAuditNodeList) {
                    for (KonkaoaFilesAuditNode fau : filesAuditNodeList) {
                        fau.setLink_id(t.getId());
                        this.filesAuditNodeDao.insertEntity(fau);// 是插入流程节点.进一步延长流程
                    }
                }
            }
        }

        List<KonkaoaFilesProperty> filesPropertyList = t.getFilesPropertyList();
        if (null != filesPropertyList) {
            if (filesPropertyList.size() > 0) {
                for (KonkaoaFilesProperty fp : filesPropertyList) {
                    this.filesPropertyDao.insertEntity(fp);
                }
            }
        }

        this.filesDao.updateEntity(t);

        if (null != t.getContent()) {
            KonkaoaFilesContent filesContent = new KonkaoaFilesContent();
            filesContent.setLink_id(t.getId());
            filesContent.setContent(t.getContent());
            this.filesContentDao.updateEntity(filesContent);
        }
    }

    public List<KonkaoaFiles> getKonkaoaFilesPaginatedListForAuditIng(KonkaoaFiles t) {
        return this.filesDao.selectKonkaoaFilesPaginatedListForAuditIng(t);
    }

    public List<KonkaoaFiles> getKonkaoaFilesPaginatedListFirst(KonkaoaFiles t) {
        return this.filesDao.selectKonkaoaFilesPaginatedListFirst(t);
    }

    public List<KonkaoaFiles> getKonkaoaFilesListForArchive(KonkaoaFiles t) {
        return this.filesDao.selectKonkaoaFilesListForArchive(t);
    }

    public Long getKonkaoaFilesListForArchiveCount(KonkaoaFiles t) {
        return this.filesDao.selectKonkaoaFilesListForArchiveCount(t);
    }

    public Long getKonkaoaFilesListForAuditIngCount(KonkaoaFiles t) {
        return this.filesDao.selectKonkaoaFilesListForAuditIngCount(t);
    }

    public List<KonkaoaFiles> getKonkaoaFilesPaginatedListForAudit(KonkaoaFiles t) {
        return this.filesDao.selectKonkaoaFilesPaginatedListForAudit(t);
    }

    public Long getKonkaoaFilesCountForAudit(KonkaoaFiles t) {
        return this.filesDao.selectKonkaoaFilesCountForAudit(t);
    }

    public Long getKonkaoaFilesCountForPaginatedList(KonkaoaFiles t) {
        return this.filesDao.selectKonkaoaFilesCountForPaginatedList(t);
    }

    @Override
    public int modifyKonkaoaFilesProperty(KonkaoaFiles t) {

        // 先删除下发人,再重新插入
        KonkaoaFilesRecipient _fr = new KonkaoaFilesRecipient();
        _fr.setLink_id(t.getId());
        filesRecipientDao.deleteEntity(_fr);
        List<KonkaoaFilesRecipient> filesRecipientList = t.getFilesRecipientList();
        if (null != filesRecipientList) {
            for (KonkaoaFilesRecipient fr : filesRecipientList) {
                fr.setLink_id(t.getId());
                filesRecipientDao.insertEntity(fr);
            }
        }

        return 0;
    }

    @Override
    public Long getKonkaoaSsuedDocumentCount(KonkaoaSsuedDocument t) {
        return this.filesDao.selectKonkaoaSsuedDocumentCount(t);
    }

    @Override
    public List<KonkaoaSsuedDocument> getKonkaoaSsuedDocumentPaginatedList(KonkaoaSsuedDocument t) {
        return this.filesDao.selectKonkaoaSsuedDocumentPaginatedList(t);
    }

    @Override
    public Long getKonkaoaFilesArchiveCount(KonkaoaSsuedDocument files) {
        return this.filesDao.selectKonkaoaFilesArchiveCount(files);
    }

    @Override
    public List<KonkaoaSsuedDocument> getKonkaoaFilesArchivePaginatedList(KonkaoaSsuedDocument files) {
        return this.filesDao.selectKonkaoaFilesArchivePaginatedList(files);
    }

    @Override
    public Long getKonkaoaFilesNoMax(KonkaoaFiles t) {
        return this.filesDao.selectKonkaoaFilesNoMax(t);
    }

    public KonkaoaFiles getKonkaoaFilesForExpenseClaims(KonkaoaFiles t) {
        return this.filesDao.selectKonkaoaFilesForExpenseClaims(t);
    }

    public void removeKonkaFilesArchiveFiles(KonkaoaFiles t) {
        String[] pks = (String[]) t.getMap().get("pks");
        String notice_ids = new String();
        String expense_ids = new String();
        String file_ids = new String();
        for (int i = 0; i < pks.length; i++) {
            String[] pk = pks[i].split("_");
            if ("notice".equals(pk[1])) {
                notice_ids = notice_ids + "," + pk[0];
            } else if ("file".equals(pk[1]) || "expense".equals(pk[1])) {
                file_ids = file_ids + "," + pk[0];
            }
            if ("expense".equals(pk[1])) {
                expense_ids = expense_ids + "," + pk[0];
            }
        }
        if (StringUtils.isNotBlank(notice_ids)) {
            String[] n_ids = notice_ids.substring(1, notice_ids.length()).split(",");
            KonkaoaDocInfo docInfo = new KonkaoaDocInfo();
            docInfo.getMap().put("pks", n_ids);
            docInfo.setIs_del(1);
            this.docInfoDao.updateEntity(docInfo);
        }
        if (StringUtils.isNotBlank(file_ids)) {
            String[] f_ids = file_ids.substring(1, file_ids.length()).split(",");
            KonkaoaFiles file = new KonkaoaFiles();
            file.getMap().put("pks", f_ids);
            this.removeKonkaoaFiles(file);
        }
        if (StringUtils.isNotBlank(expense_ids)) {
            String[] e_ids = expense_ids.substring(1, expense_ids.length()).split(",");
            KonkaExpenseClaims claims = new KonkaExpenseClaims();
            claims.getMap().put("pks", e_ids);
            this.expenseClaimsDao.deleteEntity(claims);
        }
    }

    /**
     * @author Hu,Hao
     * @version 2013-08-09
     */
    public List<KonkaoaFiles> getBaseKonkaoaFilesPaginatedList(KonkaoaFiles t) {
        return this.filesDao.selectBaseKonkaoaFilesPaginatedList(t);
    }

    public int modifyKonkaoaFilesOnly(KonkaoaFiles t) {
        return this.filesDao.updateEntity(t);
    }

    @Override
    public List<KonkaoaFiles> getMyKonkaoaFilesOfAuditPaginatedList(KonkaoaFiles t) {
        return this.filesDao.selectMyAuditOfKonkaoaFilesPaginatedList(t);
    }

    @Override
    public Long getMyKonkaoaFilesOfAuditCount(KonkaoaFiles t) {
        return this.filesDao.selectMyAuditOfKonkaoaFilesCount(t);
    }
}
