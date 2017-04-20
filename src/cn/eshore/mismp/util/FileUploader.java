package cn.eshore.mismp.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;


/**
 * 文件上传类
 * @author helin
 */
public class FileUploader {
	protected  final static  Logger log = Logger.getLogger(FileUploader.class);
	private static final int BUFFER_SIZE=8192;
	/**
	 * 上传文件 返回正数表示成功上传文件的大小，负数表示错误代码
	 * @param file 要上传的文件
	 * @param savePath 保存路径(磁盘上的实际路径)，如果不存在，则创建
	 * @param fileName 文件名称（带后缀，例如：qq.exe）
	 * @param cover 如果要保存的路径下存在相同文件名的文件，是否覆盖，true表示覆盖，false表示不覆盖
	 * @return 上传结果<br> 正数表示成功上传的文件的大小;<br>-1表示参数错误;<br>-2表示系统异常;<br>-3表示不允许覆盖时文件已存在
	 * @author helin
	 */
	public static long upload(File file,String savePath, String fileName,boolean cover) {
		if(file==null || StringUtils.isEmpty(savePath) ||StringUtils.isEmpty(fileName)){
			return -1;
		}
		long fileSize=0;//文件大小
		FileOutputStream out =null;
		FileInputStream inn=null;
		try {
			File saveDirectory = new File(savePath);
			// 如果上传文件的存放路径文件夹不存在，则创建
			if (!saveDirectory.isDirectory()) {
				saveDirectory.mkdirs();
				log.info("创建" +saveDirectory + "目录");
			}
			String fileFullPath=savePath + File.separator + fileName;
			File checkExistFile=new File(fileFullPath);
			if(checkExistFile.exists()&& !cover){
				log.info("文件"+fileFullPath+"已存在，上传失败");
				return -3;
			}
			out = new FileOutputStream(fileFullPath);
			byte[] buffer = new byte[BUFFER_SIZE];
			inn= new FileInputStream(file);
//			读数据流时不能写成以下注释的形式，否则会因为最后一个read buffer多出一些字节而导致上传的手机软件不能在手机上运行
//			while (inn.read(buffer) != -1) {
//			os.write(buffer, 0, buffer.length);
//			}
			while (true) {
				int len=inn.read(buffer);
				if(len==-1){
					break;
				}else{
					fileSize+=len;
					out.write(buffer, 0, len);
				}
			}
			log.info("上传成功，保存地址为："+fileFullPath+"，文件大小为："+fileSize);
			return fileSize;
		} catch (Exception e) {
			log.error("上传文件"+savePath + File.separator + fileName+"时出现异常，错误信息："+e.toString());
			return -2;
		}finally{
			try {
				if(out!=null){
					out.close();
				}
				if(inn!=null){
					inn.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
public static void main(String[] args) {
	File file=new File("c:\\0.rar");
	FileUploader.upload(file, "d:\\aa\\heh", "bcbbd.rar",false);
}
}
