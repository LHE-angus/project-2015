package com.ebiz.mmt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ebiz.mmt.dao.FileReportTypeDao;
import com.ebiz.mmt.domain.FileReportType;
import com.ebiz.mmt.service.FileReportTypeService;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-02-12 16:10:13
 */
@Service
public class FileReportTypeServiceImpl implements FileReportTypeService {

	@Resource
	private FileReportTypeDao fileReportTypeDao;
	

	public Long createFileReportType(FileReportType t) {
		return this.fileReportTypeDao.insertEntity(t);
	}

	public FileReportType getFileReportType(FileReportType t) {
		return this.fileReportTypeDao.selectEntity(t);
	}

	public Long getFileReportTypeCount(FileReportType t) {
		return this.fileReportTypeDao.selectEntityCount(t);
	}

	public List<FileReportType> getFileReportTypeList(FileReportType t) {
		return this.fileReportTypeDao.selectEntityList(t);
	}

	public int modifyFileReportType(FileReportType t) {
		return this.fileReportTypeDao.updateEntity(t);
	}

	public int removeFileReportType(FileReportType t) {
		return this.fileReportTypeDao.deleteEntity(t);
	}

	public List<FileReportType> getFileReportTypePaginatedList(FileReportType t) {
		return this.fileReportTypeDao.selectEntityPaginatedList(t);
	}

}
