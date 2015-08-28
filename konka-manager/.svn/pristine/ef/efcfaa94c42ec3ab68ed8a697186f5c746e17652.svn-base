package com.ebiz.mmt.web.servlet;

import java.awt.image.BufferedImage;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class QSCodeServlet extends HttpServlet {

	protected static final Logger logger = LoggerFactory.getLogger(QRCodeServlet.class);

	private static final int BLACK = 0x00000000;

	private static final int WHITE = 0xffffffff;
	//private static final int WHITE = 0xffffff00;
	
	private static final long serialVersionUID = -3981794330055840248L;

	private String textAttribute = "text";

	private String width = "480";

	public void init(ServletConfig config) throws ServletException {
		this.textAttribute = config.getInitParameter("textAttribute");
		this.width = config.getInitParameter("width");
	}

	public void destroy() {
		this.textAttribute = null;
		this.width = null;
		super.destroy();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			java.io.IOException {

		response.addHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
		response.setHeader("Cache-Control", "pre-check=0,post-check=0");
		response.setDateHeader("Expires", 0);

		String text = (String) request.getParameter(textAttribute);
		String str= new String(text.getBytes("iso-8859-1"),"UTF-8");
		
		int i_width = Integer.valueOf(width);

		try {
			BitMatrix bitMatrix = new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE, i_width, i_width);
			BufferedImage image = this.toBufferedImage(bitMatrix);

			ServletOutputStream sos = response.getOutputStream();
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(sos);
			encoder.encode(image);
		} catch (WriterException e) {
			e.printStackTrace();
		}
	}

	public BufferedImage toBufferedImage(BitMatrix matrix) {
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, matrix.get(x, y) == true ? BLACK : WHITE);
			}
		}
		return image;
	}

	public String getTextAttribute() {
		return textAttribute;
	}

	public void setTextAttribute(String textAttribute) {
		this.textAttribute = textAttribute;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

}
