package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.ArticleImg;

/**
 * @author Xing,XiuDong
 */
public interface ArticleImgService {

	Long createArticleImg(ArticleImg t);

	int modifyArticleImg(ArticleImg t);

	int removeArticleImg(ArticleImg t);

	ArticleImg getArticleImg(ArticleImg t);

	List<ArticleImg> getArticleImgList(ArticleImg t);

	Long getArticleImgCount(ArticleImg t);

	List<ArticleImg> getArticleImgPaginatedList(ArticleImg t);

}
