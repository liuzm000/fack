package cn.eshore.mismp.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.log4j.Logger;

/**
 * 文件存储结构如下： 项目 占用字节 文件（目录）名长度：2 文件（目录）名 ：变长 文件属性 ：2 （0表示文件，1标识目录） 下级文件数目 ：2
 * 所在目录名长度 ：2 所在目录名 ：变长 文件长度 ：4 文件内容 ：变长
 */
public class BrewPack {
	public static final int FILE_COUNT_SIZE = 2;

	public static final int NAME_LEN_SIZE = 2;

	public static final int FILE_LEN_SIZE = 4;

	public static final int FILE_ATTR_SIZE = 2;

	public static final int SUB_ITEM_COUNT = 2;

	public static final int BUF_SIZE = 1024 * 50;

	protected static final Logger log = Logger.getLogger(BrewPack.class);

	public static void pack(String basePath, String[] fileNames, String packFile)
			throws IOException {
		String path = "";
		if (basePath.trim().equals(""))
			path = basePath;
		else
			path = basePath + "/";
		if (fileNames == null || packFile == null) {
			log.debug("input error!");
			return;
		}
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		writeInt(byteOut, fileNames.length, FILE_COUNT_SIZE);
		for (int fileIdx = 0; fileIdx < fileNames.length; fileIdx++) {
			File file = new File(path + fileNames[fileIdx]);
			if (file.isFile()) {
				writeFile(byteOut, fileNames[fileIdx], "", path);
			} else if (file.isDirectory()) {
				writeDir(byteOut, fileNames[fileIdx], "", path);
			}
		}
		FileOutputStream fileOut = new FileOutputStream(packFile);
		fileOut.write(byteOut.toByteArray());
		fileOut.close();
	}

	public static void writeDir(OutputStream out, String strDirName,
			String strParentDir, String basePath) throws IOException {
		String compFileName;
		if (strParentDir.equals(""))
			compFileName = strDirName;
		else
			compFileName = strParentDir + "/" + strDirName;

		File dir = new File(basePath + compFileName);
		if (!dir.isDirectory()) {
			log.debug(dir + " is not a directory");
			return;
		}

		// 写文件名长度
		int nameLen = strDirName.length();
		writeInt(out, nameLen, NAME_LEN_SIZE);
		// 写文件名
		for (int ich = 0; ich < nameLen; ich++) {
			out.write(strDirName.charAt(ich));
		}
		// 父目录的长度
		writeInt(out, strParentDir.length(), NAME_LEN_SIZE);
		// 父目录
		log.debug(strParentDir + ":" + strDirName + ":"
				+ strParentDir.length());

		for (int ich = 0; ich < strParentDir.length(); ich++) {
			out.write(strParentDir.charAt(ich));
		}

		// 写文件属性
		writeInt(out, 1, FILE_ATTR_SIZE);

		String[] files = dir.list();
		// 本目录下文件数目
		writeInt(out, files.length, SUB_ITEM_COUNT);

		for (int fileIdx = 0; fileIdx < files.length; fileIdx++) {
			String tempCompFileName = compFileName + "/" + files[fileIdx];
			log.debug(tempCompFileName);
			File file = new File(basePath + tempCompFileName);
			if (file.isFile()) {
				writeFile(out, files[fileIdx], compFileName, basePath);
			} else if (file.isDirectory()) {
				writeDir(out, files[fileIdx], compFileName, basePath);
			}
		}
	}

	public static void writeFile(OutputStream out, String strFileName,
			String strParentDir, String basePath) throws IOException {
		String compFileName = "";
		if (strParentDir.equals(""))
			compFileName = strFileName;
		else
			compFileName = strParentDir + "/" + strFileName;
		// 写文件名长度
		int len = strFileName.length();
		writeInt(out, len, NAME_LEN_SIZE);
		// 写文件名
		for (int ich = 0; ich < len; ich++)
			out.write(strFileName.charAt(ich));

		// 父目录的长度
		writeInt(out, strParentDir.length(), NAME_LEN_SIZE);
		log.debug("parent dir len:" + strParentDir + "|"
				+ strParentDir.length());
		// 父目录
		for (int ich = 0; ich < strParentDir.length(); ich++) {
			out.write(strParentDir.charAt(ich));
		}

		// 写文件属性
		writeInt(out, 0, FILE_ATTR_SIZE);

		// 写文件长度
		File file = new File(basePath + compFileName);
		long fileLen = file.length();
		writeLong(out, fileLen, FILE_LEN_SIZE);
		// 写文件
		FileInputStream fileIn = new FileInputStream(basePath + compFileName);
		byte buf[] = new byte[BUF_SIZE];
		int readed = fileIn.read(buf);
		while (readed > 0) {
			out.write(buf, 0, readed);
			readed = fileIn.read(buf);
		}
		fileIn.close();
	}

	private static void writeInt(OutputStream out, int val, int width)
			throws IOException {
		for (int ib = 0; ib < width; ib++) {
			int curByte = (val >> (ib * 8)) & 0xFF;
			out.write(curByte);
		}
	}

	private static void writeLong(OutputStream out, long val, int width)
			throws IOException {
		for (int ib = 0; ib < width; ib++) {
			int curByte = (int) ((val >> (ib * 8)) & 0xFF);
			out.write(curByte);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] fileList = new String[] {
		// "1.png",
		"d2"
		// "1.xls",
		// "4.png"
		};

		String outFile = "D:/pack.dat";
		try {
			pack("D:\\workspace\\BrewPack\\test", fileList, outFile);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}

