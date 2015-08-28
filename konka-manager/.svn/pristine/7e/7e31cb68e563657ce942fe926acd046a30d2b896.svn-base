package com.ebiz.mmt.web.util;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;

/**
 * @author Cheng,Bing
 * @version 2012-02-09
 */
public class CrcUtil {
    private static final int RAND_MAX = 10;
    
	public static boolean checkNum(Integer arr[], int num) {
		for (int i = 0; i < arr.length; i++)
			if (new Integer(num).equals(arr[i]))
				return false;

		return true;
	}

	public static Integer[] getRand(int count) {
		Integer arr[] = new Integer[count];
		int i = 0;
		while (i < count) {
			int x = new Random().nextInt(RAND_MAX);
			if (checkNum(arr, x)) arr[i++] = x;
		}
		return arr;
	}

	public static Integer[] transArr(Integer arr[]) {
		Integer trArr[] = new Integer[arr.length];
		int index = 0;
		int len = 0;
		Integer num = RAND_MAX;
		while (len < arr.length) {
			for (int i = 0; i < arr.length; i++) {
				if (trArr[i] == null && arr[i] < num) {
					num = arr[i];
					index = i;
				}
			}
			trArr[index] = len;
			len++;
			num = RAND_MAX;
		}
		return trArr;
	}

	public static String getCRC32Code(String srcString) throws Exception {

		ByteArrayInputStream bais = new ByteArrayInputStream(srcString.getBytes("UTF-8"));
		CRC32 crc32 = new CRC32();
		CheckedInputStream cis = new CheckedInputStream(bais, crc32);
		byte[] buf = new byte[128];
		while (cis.read(buf) >= 0){}
		String crcCode = Long.toHexString(crc32.getValue());
		String fillStr = "00000000";
		return fillStr.substring(crcCode.length()) + crcCode;
	}

	/**
	 * @param tiemstr
	 *            传入日期数据 返回 /yyyy-MM-dd HH:mm:ss/
	 */
	public static String convertTimeToStr(Date time, String format)
			throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(time);
	}

	/* 
	 * 获取校验码
	 * no 加密的手机号码
	 * x 加密后的经度
	 * y 加密后的经度
	 * z 手机时间不加密，格式如 yyyy-MM-dd HH:mm:ss
	 */
	public static String getVerifyCode(String no, String x, String y, String z , Integer tranArr[]) throws Exception{
		String arrs[] = { no, x, y, z };
		
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < tranArr.length;i++)
		    sb.append(arrs[tranArr[i]]);

		return getCRC32Code(sb.toString());
	}
	
	/* 
	 * 核对校验码  函数不作null检查
	 * no 加密的手机号码
	 * x 加密后的经度
	 * y 加密后的经度
	 * z 手机时间不加密，格式如 yyyy-MM-dd HH:mm:ss
	 */
	public static boolean CheckVerifyCode(String no, String x, String y, String z , String idc) throws Exception{

		if(z.length() != 23 || idc.length() != 9) return false;
		
		String randStr = z.substring(20) + idc.substring(0,1);
		String crc = idc.substring(1);
		
		Integer trArr[] = new Integer[randStr.length()];
		for(int i = 0; i < randStr.length(); i++)
			trArr[i] = Character.getNumericValue(randStr.charAt(i));
		
		// 转换成序列
		Integer tranArr[] = transArr(trArr);

		if(crc.equals(getVerifyCode(no, x, y, z.substring(0, 19), tranArr))) return true;
		else return false;
	}
	
	public static void main(String[] args) throws Exception {
		// 加密过程
		// 原始数据
		String no = "13012345678";
		String x = "117.312311023";
		String y = "32.4780913";
		String z = convertTimeToStr(new Date(), "yyyy-MM-dd HH:mm:ss");
		
		// //System.out.println("加密前：no = " + no);
		// //System.out.println("加密前：x = " + x);
		// //System.out.println("加密前：y = " + y);
		// //System.out.println("手机发送时间：z=" + z);
		// //System.out.println("----------------------");
		
		String _no = Encrypt.encrypt(no, null);
		String _x = Encrypt.encrypt(x, null);
		String _y = Encrypt.encrypt(y, null);
		
		// //System.out.println("加密后：no = " + _no);
		// //System.out.println("加密后：x = " + _x);
		// //System.out.println("加密后：y = " + _y);
		// //System.out.println("----------------------");
		
        // 获取0-9之间4个不重复的随机数的数组
		Integer rans[] = getRand(4);
		// 转换成序列
		Integer tranArr[] = transArr(rans);

		// 测试随机数
		// //System.out.println(rans[0] + "" + rans[1] + "" + rans[2] + "" + rans[3]);
		// //System.out.println(tranArr[0] + "" + tranArr[1] + "" + tranArr[2] + "" + tranArr[3]);
		// 根据随机序列生成校验码
		String crc = getVerifyCode(_no, _x, _y, z, tranArr);
		// //System.out.println("验证码：crc = " + crc);
		// 手机发送的数据相当于以下
		StringBuffer sb = new StringBuffer();
		sb.append("no=").append(_no).append("&x=").append(_x).append("&y=").append(_y).append("&z=").append(z);
		sb.append(".").append(rans[0]).append(rans[1]).append(rans[2]).append("&idc=").append(rans[3]).append(crc);

		// //System.out.println(sb.toString());
		// //System.out.println("----------------------");
		// 解密过程
		String t_no = _no;
		String t_x = _x;		
		String t_y = _y;
		String t_z = z + "." + rans[0] + "" + rans[1] + "" + rans[2];
		String t_idc = rans[3] + crc;
		
		// 1.核对校验码  
		if(CheckVerifyCode(t_no, t_x, t_y, t_z, t_idc)){
			// 2.解密数据
			// //System.out.println("解密后：no=" + Encrypt.decrypt(t_no, null));
			// //System.out.println("解密后：x=" + Encrypt.decrypt(t_x, null));
			// //System.out.println("解密后：y=" + Encrypt.decrypt(t_y, null));
			// //System.out.println("手机发送时间：z=" + t_z.substring(0,19));
		}else{
			// 违法数据拒绝处理
			// //System.out.println("没有通过校验！");
		}		
	}
}
