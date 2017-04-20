/**
 * Created on 2007-3-17 ����10:14:05
 */
package cn.eshore.mismp.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: poson</p>
 * <p><a href="InitPropLoader.java.html"><i>View Source</i></a></p>
 * @author <a href="Yusm@gsta.com">Yu Songming</a>
 * @version 1.0
 * Modified at:
 * Modified by:
 */
public class InitPropLoader {
	
	public Properties getProperties(String propName){
		Properties prop = new Properties();
		InputStream in = null;
		try {
			in = (InitPropLoader.class).getResourceAsStream("/"+propName);
			prop.load(in);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e) {
			}
		}
		return prop;
	}
	
}
