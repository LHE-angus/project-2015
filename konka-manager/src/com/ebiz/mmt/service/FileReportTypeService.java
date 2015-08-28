package com.ebiz.mmt.service;

import java.util.List;

import com.ebiz.mmt.domain.FileReportType;

/**
 * Coder AutoGenerator generate.
 *
 * @author Coder AutoGenerator by Jiang,JiaYong
 * @date 2014-02-12 16:10:13
 */
public interface FileReportTypeService {

	Long createFileReportType(FileReportType t);

	int modifyFileReportType(FileReportType t);

	int removeFileReportType(FileReportType t);

	FileReportType getFileReportType(FileReportType t);

	List<FileReportType> getFileReportTypeList(FileReportType t);

	Long getFileReportTypeCount(FileReportType t);

	List<FileReportType> getFileReportTypePaginatedList(FileReportType t);

}