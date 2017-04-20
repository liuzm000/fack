package cn.eshore.mismp.wqs.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.eshore.mismp.util.MobileGlobals;
import cn.eshore.mismp.web.servlet.BaseHttpServlet;
/**
 * <p> 文件下载servlet<p>
 * <p> @author jianghuan <p>
 * <p> 时间：2012-12-18下午03:08:34<p>
 * <p> CopyRight 2012 <p>
 */
public class WqsDownloadServlet extends BaseHttpServlet {

	private static final long serialVersionUID = 1L;
	protected static final Logger log = Logger.getLogger(WqsDownloadServlet.class);
	String PLAT_BASE_PATH = MobileGlobals.getProperty("plat.base.path");

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	    String FILE_UPLOAD_PATH = MobileGlobals.getProperty("file.upload.path");
	    String path = request.getParameter("path");
	    if( path ==null || path.equals("")){
	    	return ;
	    }
		
			String filePath = path;
			String fileName = filePath.substring(filePath.lastIndexOf("/")+1,filePath.length());
			String text = FILE_UPLOAD_PATH + path;
			log.info("【万顷沙】 请求下载的文件路径："+text);
			File file = new File(text);
			if (file.exists()) {
				String filename = "";
				try {
					filename = URLEncoder.encode(fileName, "utf-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				response.reset();
				response.setContentType("APPLICATION/DOWNLOAD");
				response.addHeader("Content-Disposition","attachment; filename=\"" + filename + "\"");
				int fileLength = (int) file.length();
				response.setContentLength(fileLength);
				/*如果文件长度大于0*/
				if (fileLength != 0) {
					/*创建输入流*/
					InputStream inStream = null;
					try {
						inStream = new FileInputStream(file);
					} catch (FileNotFoundException e) {

					}
					byte[] buf = new byte[4096];
					/*创建输出流*/
					ServletOutputStream servletOS = null;
					try {
						servletOS = response.getOutputStream();

						int readLength;
						try {
							while (((readLength = inStream.read(buf)) != -1)) {
								servletOS.write(buf, 0, readLength);
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
						inStream.close();
						servletOS.flush();
						servletOS.close();
						

					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} else {
				log.info("在服务器上找不到该文件！"+text);
				request.setAttribute("tips", "文件不存在,请联系管理员");
				request.setAttribute("forward", "wqstravel/getTownVideoList.action");
				request.getRequestDispatcher("tips.jsp").forward(request, response);
			}

	}


}
