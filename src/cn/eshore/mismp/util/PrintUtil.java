package cn.eshore.mismp.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <p>Title: </p> 
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: poson</p>
 * @author Yu Songming
 * @version 1.0
 */
public class PrintUtil {
	
	public static PrintWriter getPrintWriter(String outputFileName) throws IOException{
		PrintWriter writer;
		try {
			writer = new PrintWriter(new FileWriter(outputFileName,true),true);
            // Can't open the requested file. Open the default file.
        }
        catch (IOException e1) {
            System.err.println("Warning: Could not open \""
                + outputFileName + "\" to write anything to. Make sure that your Java " +
                "process has permission to write to the file and that the directory exists."
            );
            try {
            	writer = new PrintWriter(new FileWriter("Stat_" +
                    System.currentTimeMillis() + ".log",true), true
                );
            }
            catch (IOException e2) {
                throw new IOException("Can't open any file");
            }
        }
        return writer;
	}
}
