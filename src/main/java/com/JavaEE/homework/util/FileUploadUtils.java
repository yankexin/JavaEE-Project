package com.JavaEE.homework.util;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class FileUploadUtils {

	/**
	 * @description: 生成一个唯一的ID
	 * @param
	 * @return: java.lang.String
	 */
	public static String createUUID () {
		 String s = UUID.randomUUID().toString(); 
	     String s2 = s.substring(24).replace("-","");
	     return s2.toUpperCase();
	}
	
	/**
	 * @description: 得到一个保证不重复的临时文件名
	 * @return: java.lang.String
	 */
	public static String createTmpFileName(String suffix) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
		String datestr = sdf.format(new Date());
		String name = datestr + "-" + createUUID() + "." + suffix;
		return name;
	}

    /**
     * @description: 得到文件的后缀名
     * @param fileName 原始文件名
     * @return: java.lang.String
     */
	public static String fileSuffix(String fileName) {
		int p = fileName.lastIndexOf('.');
		if(p >= 0)
		{
			return fileName.substring(p+1).toLowerCase();
		}
		return "";
	}

	public static void deleteDir(String dirPath) {
		File file = new File(dirPath);
		if (file.isFile()) {
			file.delete();
		} else {
			File[] files = file.listFiles();
			if (files == null) {
				file.delete();
			} else {
				for (int i = 0; i < files.length; i++) {
					deleteDir(files[i].getAbsolutePath());
				}
				file.delete();
			}
		}
	}

	public static byte[] getFileByteArray(File file) {
		long fileSize = file.length();
		if (fileSize > Integer.MAX_VALUE) {
			System.out.println("file too big...");
			return null;
		}
		byte[] buffer = null;
		try (FileInputStream fi = new FileInputStream(file)) {
			buffer = new byte[(int) fileSize];
			int offset = 0;
			int numRead = 0;
			while (offset < buffer.length
					&& (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {
				offset += numRead;
			}
			// 确保所有数据均被读取
			if (offset != buffer.length) {
				throw new IOException("Could not completely read file"
						+ file.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer;
	}

	public static void writeBytesToFile(byte[] bs, String file) throws IOException {
		OutputStream out = new FileOutputStream(file);
		InputStream is = new ByteArrayInputStream(bs);
		byte[] buff = new byte[1024];
		int len = 0;
		while((len=is.read(buff))!=-1){
			out.write(buff, 0, len);
		}
		is.close();
		out.close();
	}

}
