/*
 * <p>Title: ProjectInfoDocManagerImpl.java</p>
 * <p>Date : 2007-7-11</p>
 * <p>Copyright: Copyright (c) 2006 </p>
 * <p>Company: GuangDong Eshore Technology Co.,Ltd</p>
 * <p>Description: 亿迅科技信息化平台</p>
 */
package cn.eshore.mismp.version.common;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.upload.FormFile;

/**
 * @version 1.0 2007-7-11
 * @author Ray Wang
 */
public class FileUpload {

	public static String uploadFile(FormFile file,String filePath)
			throws Exception {

		boolean blState = false;
		String exception = "";
		if (StringUtils.isNotBlank(file.getFileName())) {
			if (file.getFileSize() <= 20 * 1024 * 1024) {
				blState = createUploadFile(file,filePath);
				if (blState == false) {
					exception = file.getFileName() + "文件上传失败";
				}
			} else {
				blState = false;
				exception = file.getFileName() + "文件大于20M";
			}
		} else {
			exception = "未获取到文件名";
		}
		return exception;
	}

	public static boolean createUploadFile(FormFile file,String filePath)
			throws Exception {
		boolean bFlag = false;
		if (file == null) {
			return bFlag;
		}
		if (file == null) {
			return bFlag;
		}
		String sFileName = file.getFileName();
		InputStream in = file.getInputStream();
		/* =====END===== */

		if (StringUtils.isNotEmpty(sFileName)) {
			// 判断目录是否存在，不存在则创建
			File uploadDir = new File(filePath);
			if (!uploadDir.exists()) {
				uploadDir.mkdirs();
			}
			// 构造的服务器端文件对象
			File upFile = new File(filePath + "/" + sFileName);
			if (upFile.exists()) {// 文件已存在覆盖原文件以及更新数据库
				bFlag = upload(in, upFile);
			} else {// 文件不存在,新上传文件,以及新增数据
				bFlag = upload(in, upFile);
			}
		}
		return bFlag;

	}

	/**
	 * 上传方法.
	 * 
	 * @param in
	 * @param fileToUpload
	 * @return boolean -true:success;false:failture
	 * @throws Exception
	 */
	private static boolean upload(InputStream in, File fileToUpload) throws Exception {
		boolean bFlag = false;
		OutputStream out = new BufferedOutputStream(new FileOutputStream(
				fileToUpload));
		int bytesRead = 0;
		byte[] buffer = new byte[8192];
		while ((bytesRead = in.read(buffer, 0, 8192)) != -1) {
			out.write(buffer, 0, bytesRead);
		}
		if (out != null)
			bFlag = true;
		out.close();
		return bFlag;
	}

}
