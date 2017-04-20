package cn.eshore.mismp.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
//import java.util.zip.ZipOutputStream;
import org.apache.tools.zip.ZipOutputStream;


public class DirectoryZip {

	public void zip(String inputFileName, String outputFileName) throws Exception {
        //String zipFileName = "c: \\test.zip"; //打包后文件名字
        zip(outputFileName, new File(inputFileName));
    }

    private void zip(String zipFileName, File inputFile) throws Exception {
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName));
        zip(out, inputFile, "");
        out.close();
    }

    private void zip(ZipOutputStream out, File f, String base) throws Exception {
        if (f.isDirectory()) {
           File[] fl = f.listFiles();
           out.putNextEntry(new org.apache.tools.zip.ZipEntry(base + "/"));
           base = base.length() == 0 ? "" : base + "/";
           for (int i = 0; i < fl.length; i++) {
           zip(out, fl[i], base + fl[i].getName());
         }
        }else {
           out.putNextEntry(new org.apache.tools.zip.ZipEntry(base));
           FileInputStream in = new FileInputStream(f);
           int b;
           while ( (b = in.read()) != -1) {
            out.write(b);
         }
         in.close();
       }
    }


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String userDir = System.getProperty("user.dir");//�����û���ǰ���ڵ�Ŀ¼  
		DirectoryZip zip = new DirectoryZip();
	     try {
	       //manager.releaseZipToFile("c:\\test.zip","c:\\test");
	    	 String srcDir = userDir + "\\resource";
	    	 zip.zip(srcDir, "dest.zip");	    
	     }
	     catch (Exception e) {}
	     	System.out.println("over");
	   }	


}
