package com.ebiz.ssi.ftp;

import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;

/**
 * this interface is generic for ftp operations.<br>
 * you can do what you want to do with supplied FTPClient instance. <br>
 * 
 * @author DarrenWang
 * @since 2007-2-13
 */
public interface FTPClientOperCallback {
	/**
	 * you can use the passed-in FTPClient instance to retrieve files from
	 * server,<br>
	 * or store files to server,even rename files on the server.<br>
	 * 
	 * @param ftp
	 *            FTPClient instance to use
	 * @throws IOException
	 *             if something goes wrong when operations is processing
	 */
	void doWithFTPClient(FTPClient ftp) throws IOException;
}