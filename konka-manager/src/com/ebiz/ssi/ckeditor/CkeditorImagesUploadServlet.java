package com.ebiz.ssi.ckeditor;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.naming.SizeLimitExceededException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Jiang,JiaYong
 * @version 2011-11-11
 */
public class CkeditorImagesUploadServlet extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(CkeditorImagesUploadServlet.class);

	private static final long serialVersionUID = 1L;

	private static final String ENCODING = "UTF-8";
	
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding(PropertiesLoader.getProperty("ckeditor.encoding"));

		String CKEditorFuncNum = request.getParameter("CKEditorFuncNum");
		String fileSavePath = "";

		// 判断请求是否是文件请求
		if (!ServletFileUpload.isMultipartContent(request)) {
			String alert_msg = PropertiesLoader.getProperty("message.request.general.form");
			if(StringUtils.isBlank(alert_msg)){
				alert_msg = "Request is the general form.";
			}
			response.getWriter().println("<script type=\"text/javascript\">alert(\"" + alert_msg + "\");history.back();</script>");
			return;
		}

		// 从配置文件中读取上传文件的目录
		String uploadDir = PropertiesLoader.getProperty("ckeditor.userFilesPath");
		if (StringUtils.isBlank(uploadDir)) {
			uploadDir = StringUtils.join(new String[] { "files", "ckeditor", "" }, File.separator);
		} else {
			uploadDir = StringUtils.substring(uploadDir, 1);
			uploadDir = StringUtils.replace(uploadDir, "/", File.separator);
		}

		// 处理上传图片的目录以时间为基准建立目录
		String[] folderPatterns = new String[] { "yyyy", "MM", "dd", "" };
		String autoCreatedDateDir = DateFormatUtils.format(new Date(), StringUtils.join(folderPatterns, File.separator));

		String ctxDir = this.getServletContext().getRealPath(File.separator);
		logger.debug("===> ctxDir is: {}", ctxDir);
		if (!ctxDir.endsWith(File.separator)) {
			ctxDir = ctxDir + File.separator;
		}
		File savePath = new File(ctxDir + uploadDir + autoCreatedDateDir);
		logger.debug("===> save path is: {}", savePath);
		if (!savePath.exists()) {
			savePath.mkdirs();
		}

		// 上传文件处理
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			String threshold = PropertiesLoader.getProperty("ckeditor.size.threshold");
			if(StringUtils.isBlank(threshold)){
				threshold = "1048576"; // default: 1*1024*1024
			}
			factory.setSizeThreshold(Integer.valueOf(threshold)); // 设置临时文件大小
			factory.setRepository(new File(ctxDir + "temp")); // 设置临时保存文件夹

			ServletFileUpload sfu = new ServletFileUpload(factory);
			String one_file_max = PropertiesLoader.getProperty("ckeditor.file.size.max");
			if(StringUtils.isBlank(one_file_max)){
				one_file_max = "52428800"; // default: 5*1024*1024
			}
			sfu.setFileSizeMax(Long.valueOf(one_file_max)); // 设置单个文件允许大小
			String all_file_max = PropertiesLoader.getProperty("ckeditor.size.max");
			if(StringUtils.isBlank(all_file_max)){
				all_file_max = "10485760"; // default: 10*1024*1024
			}
			sfu.setSizeMax(Long.valueOf(all_file_max)); // 设置总表单文件总和允许大小
			String e_c = PropertiesLoader.getProperty("ckeditor.encoding");
			if(StringUtils.isBlank(e_c)){
				e_c = ENCODING;
			}
			sfu.setHeaderEncoding(e_c); // 设置编码
			List<FileItem> fileItems;

			fileItems = sfu.parseRequest(request);
			int leng = fileItems.size();
			for (int n = 0; n < leng; n++) {
				FileItem item = fileItems.get(n); // 从集合中获得一个文件流
				// 如果是普通表单字段
				if (item.isFormField()) {
					String name = item.getFieldName(); // 获得该字段名称
					String value = item.getString(e_c); // 获得该字段值，编码:UTF-8
					logger.debug("===> name is: {}", name);
					logger.debug("===> value is: {}", value);
				} else if (item.getName().length() > 0) { // 如果为文件域
					String iname = item.getName().substring(item.getName().lastIndexOf("."));

					// 上传的文件类型判断(这里判断是否是图片类型)
					if (PropertiesLoader.getProperty("ckeditor.resourceType.media.extensions.allowed").indexOf(iname) == -1) {
						String alert_msg = PropertiesLoader.getProperty("message.request.not.contain.images");
						response.getWriter().println("<script type=\"text/javascript\">alert(\"" + alert_msg + "\");history.back();</script>");
						return;
					}

					// 文件名生成规则UUID
					String fileSaveName = StringUtils.join(new String[] { UUID.randomUUID().toString(), iname });
					logger.debug("===> fileSaveName is: {}", fileSaveName);
					File file = new File(savePath, fileSaveName);
					// 写入文件
					item.write(file);

					fileSavePath = uploadDir + autoCreatedDateDir + fileSaveName;
					logger.debug("===> fileSavePath is: {}", fileSavePath);
					
					// 使用FTP伤处文件
					if ("true".equalsIgnoreCase(PropertiesLoader.getProperty("ftp.upload"))) {
						// 初始化FTP信息
						String server = PropertiesLoader.getProperty("ftp.server");
						String user_name = PropertiesLoader.getProperty("ftp.user_name");
						String password = PropertiesLoader.getProperty("ftp.password");
						Integer port = Integer.valueOf(PropertiesLoader.getProperty("ftp.port"));
						FTPClientTemplate ftpClientTemplate = FTPClientTemplate.getInstance(server, user_name, password, port);

						ftpClientTemplate.storeFile(fileSavePath, new File(ctxDir + fileSavePath));
						
						//是否压缩上传的图片
						if ("true".equalsIgnoreCase(PropertiesLoader.getProperty("image.resize"))) {
							Integer resizeType = Integer.valueOf(PropertiesLoader.getProperty("image.resize.type")); 
							String[] resize_sizes = StringUtils.split(PropertiesLoader.getProperty("image.resize.size"), ",");
							switch (resizeType) {
							case 0: // resizeByMaxSize
								for (String str : resize_sizes) {
									FtpImageUtils.resizeByMaxSize(ftpClientTemplate, fileSavePath, ctxDir + fileSavePath, null, Integer.valueOf(str));
								}
								break;
							case 1:// resizeByRatio
								for (String str : resize_sizes) {
									FtpImageUtils.resizeByRatio(ftpClientTemplate, fileSavePath, ctxDir + fileSavePath, null,
											Integer.valueOf(str));
								}
								break;
							case 2:// resizeByFixedWidth
								for (String str : resize_sizes) {
									FtpImageUtils.resizeByFixedWidth(ftpClientTemplate, fileSavePath, ctxDir + fileSavePath,
											null, Integer.valueOf(str));
								}
								break;
							case 3:// resizeByFixedHeight
								for (String str : resize_sizes) {
									FtpImageUtils.resizeByFixedHeight(ftpClientTemplate, fileSavePath, ctxDir + fileSavePath,
											null, Integer.valueOf(str));
								}
								break;
							}
						}
					}
				}
			}
		} catch (FileSizeLimitExceededException f) {
			String alert_msg = PropertiesLoader.getProperty("message.request.file.max");
			if(StringUtils.isBlank(alert_msg)){
				alert_msg = "One file is too large.";
			}
			response.getWriter().println("<script type=\"text/javascript\">alert(\"" + alert_msg + "\");history.back();</script>");
			return;
		} catch (SizeLimitExceededException s) {
			String alert_msg = PropertiesLoader.getProperty("message.request.all.file.max");
			if(StringUtils.isBlank(alert_msg)){
				alert_msg = "All files is too large.";
			}
			response.getWriter().println("<script type=\"text/javascript\">alert(\"" + alert_msg + "\");history.back();</script>");
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 处理请求的地址
		StringBuffer ctx = new StringBuffer();
		ctx.append(request.getScheme()).append("://");
		ctx.append(request.getServerName()).append(":").append(request.getServerPort());
		ctx.append(request.getContextPath());
		logger.debug("===> ctx is: {}", ctx);

		// 上传成功处理
		String alt_msg = "";
		fileSavePath = StringUtils.replace(fileSavePath, File.separator, "/"); // 处理路径否则在浏览器不能正常显示
		String return_img_url = StringUtils.join(new String[] { ctx.toString(), "/", fileSavePath });
		logger.debug("===> return_img_url is: {}", return_img_url);
		response.getWriter().println("<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction(" + CKEditorFuncNum + ", '"
						+ return_img_url + "' , '" + alt_msg + "');</script>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		this.doGet(request, response);
	}
}
