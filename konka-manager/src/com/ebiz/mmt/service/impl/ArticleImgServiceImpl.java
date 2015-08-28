package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.ArticleImgDao;
import com.ebiz.mmt.domain.ArticleImg;
import com.ebiz.mmt.service.ArticleImgService;

/**
 * @author Xing,XiuDong
 */
@Service
public class ArticleImgServiceImpl implements ArticleImgService {

	@Resource
	private ArticleImgDao articleImgDao;

	public void setArticleImgDao(ArticleImgDao articleImgDao) {
		this.articleImgDao = articleImgDao;
	}

	public Long createArticleImg(ArticleImg t) {
		return this.articleImgDao.insertEntity(t);
	}

	public ArticleImg getArticleImg(ArticleImg t) {
		return this.articleImgDao.selectEntity(t);
	}

	public Long getArticleImgCount(ArticleImg t) {
		return this.articleImgDao.selectEntityCount(t);
	}

	public List<ArticleImg> getArticleImgList(ArticleImg t) {
		return this.articleImgDao.selectEntityList(t);
	}

	public int modifyArticleImg(ArticleImg t) {
		return this.articleImgDao.updateEntity(t);
	}

	public int removeArticleImg(ArticleImg t) {
		return this.articleImgDao.deleteEntity(t);
	}

	public List<ArticleImg> getArticleImgPaginatedList(ArticleImg t) {
		return this.articleImgDao.selectEntityPaginatedList(t);
	}

}
