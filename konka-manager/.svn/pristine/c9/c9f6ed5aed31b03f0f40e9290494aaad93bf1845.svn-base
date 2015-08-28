package com.ebiz.mmt.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataAccessException;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.ebiz.mmt.domain.EcUser;

/**
 * Coder AutoGenerator generate.
 * 
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2013-09-09 10:06:25
 */
public interface EcUserService {

	Long createEcUser(EcUser t);

	int modifyEcUser(EcUser t);

	int removeEcUser(EcUser t);

	EcUser getEcUser(EcUser t);

	List<EcUser> getEcUserList(EcUser t);

	Long getEcUserCount(EcUser t);

	List<EcUser> getEcUserPaginatedList(EcUser t);

	List<EcUser> getEcUserWithPositionNameAndFullDeptNamePaginatedList(EcUser t);

	Long getEcUserForHydjCount(EcUser t);

	List<EcUser> getEcUserPaginatedForHydjList(EcUser t);

	int modifyEcUserWithMultiRoleUser(EcUser t);

	int createEcUserAndSendEmail2(EcUser entity, JavaMailSenderImpl mailSender, String mailForm, String senderName,
	        HttpServletRequest request);

	int createEcUserAndSendEmail5(EcUser entity, JavaMailSenderImpl mailSender, String mailForm, String senderName,
	        HttpServletRequest request);

	Long createEcUserAndSendEmail(EcUser entity, JavaMailSenderImpl mailSender, String mailForm, String senderName,
	        HttpServletRequest request);

	Long createEcUserAndSendEmail3(EcUser entity, JavaMailSenderImpl mailSender, String mailForm, String senderName,
	        HttpServletRequest request);

	Long createEcUserAndSendEmail4(EcUser entity, JavaMailSenderImpl mailSender, String mailForm, String senderName,
	        HttpServletRequest request);

	int modifyEcUserAndEid(EcUser t);

	Long getSubEcUserByUserNameCount(EcUser t);

	List<EcUser> getSubEcUserByUserNameList(EcUser t);

	Long createEcUserForFgs(EcUser t);

	public List<EcUser> getEcUserNewPaginatedList(EcUser t) throws DataAccessException;

	public Long getEcUserNewCount(EcUser t) throws DataAccessException;

	Long createEcUserForZc(EcUser t);

	public Long getEcUserNo(EcUser t) throws DataAccessException;

}