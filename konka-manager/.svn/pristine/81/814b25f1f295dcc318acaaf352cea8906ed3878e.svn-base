package com.ebiz.mmt.web.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageProducer;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ebiz.ssi.ftp.FTPClientTemplate;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.sun.jimi.core.Jimi;
import com.sun.jimi.core.JimiException;
import com.sun.jimi.core.JimiWriter;
import com.sun.jimi.core.options.JPGOptions;

/**
 * utils of resize image
 * 
 * @author Xing,XiuDong
 * @version 2010.06.24
 */
public class FtpImageUtils {

	private static final Logger logger = LoggerFactory.getLogger(FtpImageUtils.class);

	public static void resize(FTPClientTemplate ftpClient, String uploadFile, String source, String desc, int width,
			int height) throws IOException {
		// resize
		FtpImageUtils.resize(source, desc, width, height);

		// ftp store
		ftpClient.storeFile(uploadFile.replace(File.separator, "/"), new File(desc));
	}

	public static void resize(String source, String desc, int width, int height) throws IOException {
		Image sourceImage = ImageIO.read(new File(source));
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		bi.getGraphics().drawImage(sourceImage.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);

		FileOutputStream out = new FileOutputStream(desc);
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		encoder.encode(bi);
		bi.flush();
		out.close();
	}

	public static void resize(FTPClientTemplate ftpClient, String uploadFile, final InputStream source, int width,
			int height) throws IOException {
		uploadFile = uploadFile.replace(File.separator, "/");

		Image sourceImage = ImageIO.read(source);

		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		bi.getGraphics().drawImage(sourceImage.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);

		ByteArrayOutputStream bs = new ByteArrayOutputStream();
		ImageOutputStream ios = ImageIO.createImageOutputStream(bs);
		ImageIO.write(bi, "jpg", ios); // scaledImage1为BufferedImage，jpg为图像的类型
		InputStream is = new ByteArrayInputStream(bs.toByteArray());

		// ftp store
		ftpClient.storeFile(uploadFile, is);
	}

	public static void resizeByFixedHeight(FTPClientTemplate ftpClient, String uploadFile, InputStream source,
			int fixedHeight) throws JimiException, IOException {
		logger.debug("source image file is:{}" + source);

		Image sourceImage = ImageIO.read(source);

		double sourceImageWidth = sourceImage.getWidth(null);
		double sourceImageHeight = sourceImage.getHeight(null);

		int resizedWidth = (int) ((sourceImageWidth / sourceImageHeight) * fixedHeight);

		if (StringUtils.isNotBlank(uploadFile)) {
			uploadFile = StringUtils.substringBeforeLast(uploadFile, "_") + "_"
					+ StringUtils.leftPad(String.valueOf(fixedHeight), 3, "0") + ".jpg";
		}

		FtpImageUtils.resize(ftpClient, uploadFile, source, resizedWidth, fixedHeight);

	}

	public static void resizeByFixedWidth(FTPClientTemplate ftpClient, String uploadFile, InputStream source,
			int fixedWidth) throws JimiException, IOException {

		Image sourceImage = ImageIO.read(source);

		double sourceImageWidth = sourceImage.getWidth(null);
		double sourceImageHeight = sourceImage.getHeight(null);

		int resizedHeight = (int) ((sourceImageHeight / sourceImageWidth) * fixedWidth);

		if (StringUtils.isBlank(uploadFile)) {
			uploadFile = StringUtils.substringBeforeLast(uploadFile, "_") + "_"
					+ StringUtils.leftPad(String.valueOf(fixedWidth), 3, "0") + ".jpg";
		}

		FtpImageUtils.resize(ftpClient, uploadFile, source, fixedWidth, resizedHeight);
	}

	public static void resizeByMaxSize(FTPClientTemplate ftpClient, String uploadFile, InputStream source, int maxSize)
			throws JimiException, IOException {
		double ratio = 0.0d;

		Image sourceImage = ImageIO.read(source);

		double sourceImageWidth = sourceImage.getWidth(null);
		double sourceImageHeight = sourceImage.getHeight(null);

		if (sourceImageHeight > sourceImageWidth) {
			ratio = maxSize / sourceImageHeight;
		} else {
			ratio = maxSize / sourceImageWidth;
		}

		int resizedWidth = (int) (sourceImageWidth * ratio);
		int resizedHeight = (int) (sourceImageHeight * ratio);

		if (StringUtils.isNotBlank(uploadFile)) {
			uploadFile = StringUtils.substringBeforeLast(uploadFile, ".") + "_"
					+ StringUtils.leftPad(String.valueOf(maxSize), 3, "0") + ".jpg";
		}

		FtpImageUtils.resize(ftpClient, uploadFile, source, resizedWidth, resizedHeight);
	}

	public static void resizeByRatio(FTPClientTemplate ftpClient, String uploadFile, InputStream source, double ratio)
			throws JimiException, IOException {

		Image sourceImage = ImageIO.read(source);

		double sourceImageWidth = sourceImage.getWidth(null);
		double sourceImageHeight = sourceImage.getHeight(null);

		int resizedWidth = (int) (sourceImageWidth * ratio);
		int resizedHeight = (int) (sourceImageHeight * ratio);

		if (StringUtils.isNotBlank(uploadFile)) {
			uploadFile = StringUtils.substringBeforeLast(uploadFile, ".") + "_"
					+ StringUtils.leftPad(String.valueOf((ratio * 100)), 3, "0") + ".jpg";
		}

		FtpImageUtils.resize(ftpClient, uploadFile, source, resizedWidth, resizedHeight);
	}

	public static void resizeByFixedHeight(FTPClientTemplate ftpClient, String uploadFile, String source, String desc,
			int fixedHeight) throws JimiException, IOException {
		if (StringUtils.isBlank(desc)) {
			desc = StringUtils.substringBeforeLast(source, "_") + "_"
					+ StringUtils.leftPad(String.valueOf(fixedHeight), 3, "0") + ".jpg";
		}
		logger.debug("source image file is:{}" + source);
		logger.debug("desc image file is:{}" + desc);

		String jpgSource = toJPG(source, null, 100);
		logger.debug("source jpg file is:{}" + jpgSource);

		Image sourceImage = ImageIO.read(new File(jpgSource));

		double sourceImageWidth = sourceImage.getWidth(null);
		double sourceImageHeight = sourceImage.getHeight(null);

		int resizedWidth = (int) ((sourceImageWidth / sourceImageHeight) * fixedHeight);

		if (StringUtils.isNotBlank(uploadFile)) {
			uploadFile = StringUtils.substringBeforeLast(uploadFile, "_") + "_"
					+ StringUtils.leftPad(String.valueOf(fixedHeight), 3, "0") + ".jpg";
		}

		FtpImageUtils.resize(ftpClient, uploadFile, jpgSource, desc, resizedWidth, fixedHeight);

	}

	public static void resizeByFixedWidth(FTPClientTemplate ftpClient, String uploadFile, String source, String desc,
			int fixedWidth) throws JimiException, IOException {
		if (StringUtils.isBlank(desc)) {
			desc = StringUtils.substringBeforeLast(source, "_") + "_"
					+ StringUtils.leftPad(String.valueOf(fixedWidth), 3, "0") + ".jpg";
		}
		logger.debug("source image file is:{}" + source);
		logger.debug("desc image file is:{}" + desc);

		String jpgSource = toJPG(source, null, 100);
		logger.debug("source jpg file is:{}" + jpgSource);

		Image sourceImage = ImageIO.read(new File(jpgSource));

		double sourceImageWidth = sourceImage.getWidth(null);
		double sourceImageHeight = sourceImage.getHeight(null);

		int resizedHeight = (int) ((sourceImageHeight / sourceImageWidth) * fixedWidth);

		if (StringUtils.isBlank(uploadFile)) {
			uploadFile = StringUtils.substringBeforeLast(uploadFile, "_") + "_"
					+ StringUtils.leftPad(String.valueOf(fixedWidth), 3, "0") + ".jpg";
		}

		FtpImageUtils.resize(ftpClient, uploadFile, jpgSource, desc, fixedWidth, resizedHeight);
	}

	public static void resizeByMaxSize(FTPClientTemplate ftpClient, String uploadFile, String source, String desc,
			int maxSize) throws JimiException, IOException {
		if (StringUtils.isBlank(desc)) {
			desc = StringUtils.substringBeforeLast(source, ".") + "_"
					+ StringUtils.leftPad(String.valueOf(maxSize), 3, "0") + ".jpg";
		}
		logger.debug("source image file is:{}" + source);
		logger.debug("desc image file is:{}" + desc);

		String jpgSource = toJPG(source, null, 100);
		logger.debug("source jpg file is:{}" + jpgSource);

		double ratio = 0.0d;

		Image sourceImage = ImageIO.read(new File(jpgSource));

		double sourceImageWidth = sourceImage.getWidth(null);
		double sourceImageHeight = sourceImage.getHeight(null);

		if (sourceImageHeight > sourceImageWidth) {
			ratio = maxSize / sourceImageHeight;
		} else {
			ratio = maxSize / sourceImageWidth;
		}

		int resizedWidth = (int) (sourceImageWidth * ratio);
		int resizedHeight = (int) (sourceImageHeight * ratio);

		if (StringUtils.isNotBlank(uploadFile)) {
			uploadFile = StringUtils.substringBeforeLast(uploadFile, ".") + "_"
					+ StringUtils.leftPad(String.valueOf(maxSize), 3, "0") + ".jpg";
		}

		FtpImageUtils.resize(ftpClient, uploadFile, jpgSource, desc, resizedWidth, resizedHeight);
	}

	public static void resizeByRatio(FTPClientTemplate ftpClient, String uploadFile, String source, String desc,
			double ratio) throws JimiException, IOException {
		if (StringUtils.isBlank(desc)) {
			desc = StringUtils.substringBeforeLast(source, ".") + "_"
					+ StringUtils.leftPad(String.valueOf((ratio * 100)), 3, "0") + ".jpg";
		}
		logger.debug("source image file is:{}" + source);
		logger.debug("desc image file is:{}" + desc);

		String jpgSource = toJPG(source, null, 100);
		logger.debug("source jpg file is:{}" + jpgSource);

		String jpgSourceFile = toJPG(source, null, 100);
		Image sourceImage = ImageIO.read(new File(jpgSource));

		double sourceImageWidth = sourceImage.getWidth(null);
		double sourceImageHeight = sourceImage.getHeight(null);

		int resizedWidth = (int) (sourceImageWidth * ratio);
		int resizedHeight = (int) (sourceImageHeight * ratio);

		if (StringUtils.isNotBlank(uploadFile)) {
			uploadFile = StringUtils.substringBeforeLast(uploadFile, ".") + "_"
					+ StringUtils.leftPad(String.valueOf((ratio * 100)), 3, "0") + ".jpg";
		}

		FtpImageUtils.resize(ftpClient, uploadFile, jpgSourceFile, desc, resizedWidth, resizedHeight);
	}

	public static String toJPG(String source, String dest, int quality) throws JimiException {
		if ((dest == null) || dest.trim().equals("")) {
			dest = source;
		}
		if (!dest.toLowerCase().trim().endsWith(".jpg")) {
			dest += ".jpg";

			if ((quality < 0) || (quality > 100) || ((quality + "") == null) || (quality + "").equals("")) {
				quality = 75;
			}
			JPGOptions options = new JPGOptions();
			options.setQuality(quality);
			ImageProducer image = Jimi.getImageProducer(source);
			JimiWriter writer = Jimi.createJimiWriter(dest);
			writer.setSource(image);
			// add options here if necessary
			writer.setOptions(options);
			writer.putImage(dest);
		}
		return dest;
	}
}
