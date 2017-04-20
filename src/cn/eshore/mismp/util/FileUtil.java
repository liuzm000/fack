package cn.eshore.mismp.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>Title: FileUtil</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: poson</p>
 * <p><a href="FileUtil.java.html"><i>View Source</i></a></p>
 * @author <a href="Yusm@gsta.com">Yu Songming</a>
 * @version 1.0
 * Modified at:
 * Modified by:
 */
public class FileUtil {

	private static final Log LOG = LogFactory.getLog(FileUtil.class);

	public boolean write(String path, String content, String fileName, boolean append) {
		boolean writeok = false;
		FileOutputStream fp = null;
		Writer out = null;
		try {
			String file_path = path + File.separator + fileName;
			fp = new FileOutputStream(file_path, append);
			out = new BufferedWriter(new OutputStreamWriter(fp, "UTF-8"));
			// out = new BufferedWriter(new OutputStreamWriter(fp,"GB2312"));
			out.write(content);
			writeok = true;
		} catch (IOException e) {
			LOG.debug(this.getClass().getName() + e);
			writeok = false;
		} finally {
			try {
				if (out != null)
					out.close();
				out = null;
			} catch (IOException e) {
			}
			try {
				if (fp != null)
					fp.close();
				fp = null;
			} catch (IOException e) {
			}
		}
		return writeok;
	}
	
	public String read(String path, String fileName) {
		BufferedReader br = null;
		InputStreamReader reader = null;
		FileInputStream input = null;
		StringBuffer sb = new StringBuffer();
		try {
			input = new FileInputStream(path + File.separator + fileName);
			reader = new InputStreamReader(input, "UTF-8");
			br = new BufferedReader(reader);
			String s1 = "";
			while ((s1 = br.readLine()) != null) {
				sb.append(s1).append("\n");
			}
		} catch (FileNotFoundException e) {
			LOG.error(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			LOG.error(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if(br != null){
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if(reader != null){
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
}
