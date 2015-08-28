package com.ebiz.mmt.domain;

import java.io.Serializable;

import org.apache.commons.io.FilenameUtils;

public class UploadFile implements Serializable {

	private static final long serialVersionUID = -4782112363263786411L;

	public static void main(String[] args) {
		UploadFile uploadFile = new UploadFile();
		uploadFile.setFileName("javaeye\\b8a0b8cc-2755-4e48-8220-52677e1dba29.jpg");
		// //System.out.println(uploadFile.getExtension());
	}

	/** contentType */
	private String contentType;

	/** fileSize */
	private int fileSize;

	/** fileName */
	private String fileName;

	/** saveFileName */
	private String fileSaveName;

	/** fileSavePath */
	private String fileSavePath;

	/** form name */
	private String formName;

	public UploadFile() {
	}

	public String getContentType() {
		return contentType;
	}

	public String getExtension() {
		return FilenameUtils.getExtension(this.fileName);
	}

	public String getFileName() {
		return fileName;
	}

	public String getFileSaveName() {
		return fileSaveName;
	}

	public String getFileSavePath() {
		return fileSavePath;
	}

	public int getFileSize() {
		return fileSize;
	}

	public String getFormName() {
		return formName;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setFileSaveName(String fileSaveName) {
		this.fileSaveName = fileSaveName;
	}

	public void setFileSavePath(String fileSavePath) {
		this.fileSavePath = fileSavePath;
	}

	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[");
		buffer.append("fileName:").append(this.getFileName()).append("|");
		buffer.append("contentType:").append(this.getContentType()).append("|");
		buffer.append("fileSize:").append(this.getFileSize()).append("|");
		buffer.append("fileSaveName:").append(this.getFileSaveName()).append("|");
		buffer.append("fileSavePath:").append(this.getFileSavePath());
		buffer.append("]");
		return buffer.toString();
	}
}
