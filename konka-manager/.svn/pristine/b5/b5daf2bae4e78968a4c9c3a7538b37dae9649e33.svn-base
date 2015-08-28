package com.ebiz.ssi.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A Wrapper Class for Commons Net's FTPClient class to ease the ftp operations.<br>
 * 
 * @author Darren.Wang, XingXiuDong
 * @since 1.0 (2005-06-13)
 * @since 1.1 (2010-06-23) by Xing,XiuDong
 */
public class FTPClientTemplate {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private FTPClientConfig ftpClientConfig;// optional

	private String server;// required

	private String username; // required

	private String password; // required

	private int port = FTP.DEFAULT_PORT;// optional

	private static final String DEFAULT_ENCODER = "UTF-8";

	public FTPClientTemplate() {
	}

	public FTPClientTemplate(String server, String user, String pwd, int port) {
		this.server = server;
		this.username = user;
		this.password = pwd;
		this.port = port;
	}

	public FTPClientTemplate(String server, String user, String pwd) {
		this(server, user, pwd, 21);
	}

	/**
	 * the common interface method for ftp operations, only refer to this method
	 * if other methods don't meed your need.<br>
	 * with your own FtpTransferCallback implementation, you can do almost
	 * everything that you can do with FTPClient.<br>
	 * 
	 * @param callback
	 *            The FtpTransferCallback instance
	 * @throws IOException
	 *             some thing goes wrong while ftp operations.
	 */
	public void execute(FTPClientOperCallback callback) {
		FTPClient ftp = new FTPClient();
		try {
			if (this.getFtpClientConfig() != null)
				ftp.configure(this.getFtpClientConfig());

			ftp.setControlEncoding(DEFAULT_ENCODER);
			ftp.connect(server, port);
			// check whether the connection to server is confirmed
			int reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				throw new IOException("failed to connect to the FTP Server:" + server);
			}
			// login
			ftp.login(this.getUsername(), this.getPassword());

			// set file type to binary
			ftp.setFileType(FTPClient.BINARY_FILE_TYPE);

			// do your ftp operations in call back method
			callback.doWithFTPClient(ftp);

			// logout
			ftp.logout();
		} catch (SocketException e) {
			logger.error("FTP SocketException:{}", e.getMessage());
		} catch (IOException e) {
			logger.error("FTP IOException:{}", e.getMessage());
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException e) {

				}
			}
		}
	}

	// User Defined
	// upload files to server
	public void storeFile(final String remote, final File file) throws IOException {
		this.storeFile(remote, new File[] { file });
	}

	public void storeFile(final String remote, final File[] files) throws IOException {
		InputStream[] is = new InputStream[files.length];

		for (int i = 0; i < files.length; i++) {
			is[i] = new FileInputStream(files[i]);
		}

		storeFile(remote, is);
	}

	public void storeFile(final String remote, final InputStream is) throws IOException {
		this.storeFile(remote, new InputStream[] { is });
	}

	public void storeFile(final String remote, final InputStream[] is) throws IOException {
		this.execute(new FTPClientOperCallback() {
			@Override
			public void doWithFTPClient(FTPClient ftp) throws IOException {
				String ftp_remote = remote.replace(File.separator, "/");
				String fileName = StringUtils.substringAfterLast(ftp_remote, "/");
				String dir = StringUtils.substringBeforeLast(ftp_remote, "/");

				if (!ftp.changeWorkingDirectory(dir)) {
					forceMkdir(dir, ftp);
					ftp.changeWorkingDirectory(dir);
				}

				for (InputStream s : is) {
					ftp.storeFile(fileName, s);
				}
			}
		});
	}

	public boolean forceMkdir(String remote, FTPClient ftpClient) throws IOException {
		if (!StringUtils.endsWith(remote, "/")) {
			remote += "/";
		}
		String directory = remote.substring(0, remote.lastIndexOf("/") + 1);
		if (!directory.equalsIgnoreCase("/") && !ftpClient.changeWorkingDirectory(directory)) {
			int start = 0;
			int end = 0;
			if (directory.startsWith("/")) {
				start = 1;
			} else {
				start = 0;
			}
			end = directory.indexOf("/", start);
			while (true) {
				String subDirectory = remote.substring(start, end);
				if (!ftpClient.changeWorkingDirectory(subDirectory)) {
					if (ftpClient.makeDirectory(subDirectory)) {
						ftpClient.changeWorkingDirectory(subDirectory);
					} else {
						return false;
					}
				}

				start = end + 1;
				end = directory.indexOf("/", start);

				// 检查所有目录是否创建完毕
				if (end <= start) {
					break;
				}
			}
		}
		return true;
	}

	// Setters and getters
	public FTPClientConfig getFtpClientConfig() {
		return ftpClientConfig;
	}

	public void setFtpClientConfig(FTPClientConfig ftpClientConfig) {
		this.ftpClientConfig = ftpClientConfig;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
}
