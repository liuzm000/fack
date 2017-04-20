
package cn.eshore.mismp.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;

/**
 * 文件读写类
 * @author lusonglin
 * Created on Dec 23, 2008 11:17:18 AM
 */
public class FileReadAndWrite {

    public static void main(String[] args) {
    	char[] mych=FileReadAndWrite.ReadF("E:\\test.php");
    	String content="";
    	if(mych!=null){
    		for (int i = 0; i < mych.length; i++) {
    			content+=mych[i];
    		}
    	}
    	System.out.println(content);
		FileReadAndWrite.writeFile("E:\\test3.php",content);
    }
    
    /**
     * 生成指定文件名和内容的文件（DataStream方式）
     * String filePath, String msg
     */
    public static void FileStreamWrite(String filePath,String msg) {
        try {
        	File f=new File(filePath);
        	if(f.exists()){
    			f.delete();
    			f.createNewFile();
    		}else{
    			f.createNewFile();
    		}
        	FileOutputStream fos = new FileOutputStream(filePath);
    		DataOutputStream dos = new DataOutputStream(fos);
    		dos.write(msg.getBytes());
    		dos.close();
        } catch (IOException e) {
            System.out.println("FileSreamWrite file error!");
            e.printStackTrace();
        }
    }
    
    /**
     * 读取指定文件的内容（DataStream方式）
     * String filePath
     */
    public static String FileStreamRead(String filePath) {
    	String msg = "";
        try {
        	File f=new File(filePath);
    		if(!f.exists()){
    			return msg;
    		}
        	FileInputStream fis = new FileInputStream(filePath);
    		DataInputStream dis = new DataInputStream(fis);
    		int i;
    		while((i = dis.read()) != -1){
    			msg+=(char)i;
    			System.out.print((char)i);
    		}
        } catch (IOException e) {
        	System.out.println("FileStreamRead error!");
            e.printStackTrace();
        }
        return msg;
    }

    /**
     * 生成指定文件名和内容的文件（去排版格式）
     * @param filePath 路径
     * @param msg 内容 
     */
    public static void writeFile(String filePath,String msg) {
        try {
        	writeFile(filePath,msg,"");
        } catch (Exception e) {
            System.out.println("writeFile error!");
            e.printStackTrace();
        }
    }
    
    /**
     * 生成指定文件名和内容的文件(指定编码)
     * @param filePath 路径
     * @param msg 内容 
     * @param enc 编码和文件格式
     */
    public static void writeFile(String filePath,String msg,String enc) {
        try {
        	File f=new File(filePath);
        	if(f.exists()){
    			f.delete();
    			f.createNewFile();
    		}else{
    			f.createNewFile();
    		}
        	PrintWriter out = null;
            if(enc==null||enc.length()==0){
            	out = new PrintWriter(f);
            }else{
            	out = new PrintWriter(f,enc);
            }
            out.print(msg);
            out.close();
        } catch (IOException e) {
            System.out.println("writeFile error!");
            e.printStackTrace();
        }
    }
    
    /**
     * 读取指定文件的内容（去排版格式）
     * @param filePath 路径
     * @return
     */
    public static String readFile(String filePath) {
        String msg = "";
        try {
        	File f=new File(filePath);
    		if(!f.exists()){
    			return msg;
    		}
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            String record = null;
            while ((record = br.readLine()) != null) {
            	msg+=record;
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            System.out.println("readFile error!");
            e.printStackTrace();
        }
        return msg;
    }

    /**
     * 按文本的编码类型进行读取，并存放到String字符串中
     * @param filePath
     * @param encodingType
     * @return
     */
    public static String readFile(String filePath,String encodingType) {
    	String msg = "";
    	try {
    		File f=new File(filePath);
    		if(!f.exists()){
    			return msg;
    		}
    		 BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath),encodingType));
    		String record = null;
    		while ((record = br.readLine()) != null) {
    			msg+=record;
    		}
    		br.close();
    	} catch (IOException e) {
    		System.out.println("readFile error!");
    		e.printStackTrace();
    	}
    	return msg;
    }

    /**
     * 生成指定文件名和内容的文件（保留排版格式）
     * @param filePath
     * @param msg
     */
    public static void WriteF(String filePath,String msg) {
        try {
        	File f=new File(filePath);
    		if(f.exists()){
    			f.delete();
    			f.createNewFile();
    		}else{
    			f.createNewFile();
    		}
    		
    		Writer wtr = new FileWriter(f);
			char[] ch = msg.toCharArray();
			wtr.write(ch);
			wtr.close();            
        } catch (IOException e) {
            System.out.println("WriteF error!");
            e.printStackTrace();
        }
    }

    /**
     * 读取指定文件的内容（保留排版格式）
     * @param filePath
     * @return
     */
    public static char[] ReadF(String filePath) {
    	char[] ch=null;;
		try {
			File file = new File(filePath);
			if(!file.exists()){
    			return ch;
    		}
			Reader rdr = new FileReader(file);

			long sz = file.length();
			ch = new char[(int) sz];

			rdr.read(ch);
			rdr.close();
		} catch (IOException e) {
			System.out.println("ReadF error!");
            e.printStackTrace();
		}
		return ch;
	}

}

