package cn.eshore.mismp.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***
 *** @version 实现Html到txt文件转换
 *** @inheritDoc 实现Html到txt文件转换类
 *** 实现功能：实现Html到txt文件转换
 *** @author jsingfly 2009-04-09
 ***/

public class Html2Txt {

	public static String HtmlStrToText(String inputString) {
		String htmlStr = inputString; //含html标签的字符串 
		String textStr = "";
		
		Pattern p_annoce;
		Matcher m_annoce;
		Pattern p_script;
		Matcher m_script;
		Pattern p_style;
		Matcher m_style;
		Pattern p_html;
		Matcher m_html;
		Pattern p_spe;
		Matcher m_spe;
		Pattern p_blank;
		Matcher m_blank;
		Pattern p_table;
		Matcher m_table;
		Pattern p_enter;
		Matcher m_enter;

		try {
			String regEx_annoce = "<!\\-\\-[^/]*\\-\\->";
			//定义html注释标签的正则表达式
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
			//定义script的正则表达式.
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";
			//定义style的正则表达式. 
			String regEx_html = "<[^>]+>";
			//定义HTML标签的正则表达式 
			String regEx_spe = "\\&[^;]+;";
			//定义特殊符号的正则表达式
			String regEx_blank = " +";
			//定义多个空格的正则表达式
			String regEx_table = "\t+";
			//定义多个制表符的正则表达式
			String regEx_enter = "\n+";
			//定义多个回车的正则表达式
			
			p_annoce = Pattern.compile(regEx_annoce, Pattern.CASE_INSENSITIVE);
			m_annoce = p_annoce.matcher(htmlStr);
			htmlStr = m_annoce.replaceAll(""); //过滤html注释
			
			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); //过滤script标签

			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); //过滤style标签 

			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); //过滤html标签 

			p_spe = Pattern.compile(regEx_spe, Pattern.CASE_INSENSITIVE);
			m_spe = p_spe.matcher(htmlStr);
			htmlStr = m_spe.replaceAll(""); //过滤特殊符号 

			p_blank = Pattern.compile(regEx_blank, Pattern.CASE_INSENSITIVE);
			m_blank = p_blank.matcher(htmlStr);
			htmlStr = m_blank.replaceAll(" "); //过滤过多的空格

			p_table = Pattern.compile(regEx_table, Pattern.CASE_INSENSITIVE);
			m_table = p_table.matcher(htmlStr);
			htmlStr = m_table.replaceAll(" "); //过滤过多的制表符

			p_enter = Pattern.compile(regEx_enter, Pattern.CASE_INSENSITIVE);
			m_enter = p_enter.matcher(htmlStr);
			htmlStr = m_enter.replaceAll(" "); //过滤过多的制表符

			textStr = htmlStr;

		} catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}

		return textStr;//返回文本字符串 
	}

	public static String HtmlFileToText(String inputHtmlFile, String charSetName)
			throws Exception {
		FileReaderHelp fileReader = new FileReaderHelp(inputHtmlFile,
				charSetName);
		BufferedReader htmlBufferedReader = new BufferedReader((fileReader));
		StringBuffer htmlBuf = new StringBuffer();
		String html = "";
		while ((html = htmlBufferedReader.readLine()) != null) {
			htmlBuf.append(html);

		}
		String htmlStr = htmlBuf.toString(); //含html标签的字符串 
		String textStr = "";
		
		Pattern p_annoce;
		Matcher m_annoce;
		Pattern p_script;
		Matcher m_script;
		Pattern p_style;
		Matcher m_style;
		Pattern p_html;
		Matcher m_html;
		Pattern p_spe;
		Matcher m_spe;
		Pattern p_blank;
		Matcher m_blank;
		Pattern p_table;
		Matcher m_table;
		Pattern p_enter;
		Matcher m_enter;

		try {
			String regEx_annoce = "<!\\-\\-[^/]*\\-\\->";
			//定义html注释标签的正则表达式 
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
			//定义script的正则表达式.
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";
			//定义style的正则表达式. 
			String regEx_html = "<[^>]+>";
			//定义HTML标签的正则表达式 
			String regEx_spe = "\\&[^;]+;";
			//定义特殊符号的正则表达式
			String regEx_blank = " +";
			//定义多个空格的正则表达式
			String regEx_table = "\t+";
			//定义多个制表符的正则表达式
			String regEx_enter = "\n+";
			//定义多个回车的正则表达式
			
			p_annoce = Pattern.compile(regEx_annoce, Pattern.CASE_INSENSITIVE);
			m_annoce = p_annoce.matcher(htmlStr);
			htmlStr = m_annoce.replaceAll(""); //过滤html注释
			
			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); //过滤script标签

			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); //过滤style标签 

			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); //过滤html标签 

			p_spe = Pattern.compile(regEx_spe, Pattern.CASE_INSENSITIVE);
			m_spe = p_spe.matcher(htmlStr);
			htmlStr = m_spe.replaceAll(""); //过滤特殊符号 

			p_blank = Pattern.compile(regEx_blank, Pattern.CASE_INSENSITIVE);
			m_blank = p_blank.matcher(htmlStr);
			htmlStr = m_blank.replaceAll(" "); //过滤过多的空格

			p_table = Pattern.compile(regEx_table, Pattern.CASE_INSENSITIVE);
			m_table = p_table.matcher(htmlStr);
			htmlStr = m_table.replaceAll(" "); //过滤过多的制表符

			p_enter = Pattern.compile(regEx_enter, Pattern.CASE_INSENSITIVE);
			m_enter = p_enter.matcher(htmlStr);
			htmlStr = m_enter.replaceAll(" "); //过滤过多的制表符
			
			textStr = htmlStr;

		} catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}

		return textStr;//返回文本字符串 

	}

	/***
	 *** @version 实现FileReader具有指定编码能力
	 *** @inheritDoc 实现FileReader具有指定编码能力类
	 *** 实现功能：实现实现FileReader具有指定编码能力
	 *** @author jsingfly 2009-04-09
	 ***/
	private static class FileReaderHelp extends InputStreamReader {
		public FileReaderHelp(String fileName, String charSetName)
				throws FileNotFoundException, UnsupportedEncodingException {
			super(new FileInputStream(fileName), charSetName);
		}

	}

	public static void main(String args[]) throws Exception {
		String txt = Html2Txt.HtmlFileToText(
				"E:/kingfee/workplace/移动号百/集团移百/集团接口资料/接口资料/信息同步接口/120592.txt",
				"UTF-8");
		System.out.println(txt);
		/*String txtArray[] = txt.split(" ");
		for (String mmf : txtArray) {
			System.out.println(mmf);
		}*/
		/*FileReader fileReader = new FileReader("E:/kingfee/workplace/移动号百/集团移百/集团接口资料/接口资料/信息同步接口/120592.txt");
		 BufferedReader a = new BufferedReader((fileReader));
		 StringBuffer html2txtBuf = new StringBuffer();
		 String html = "";
		 while((html = a.readLine())!=null){
		 html2txtBuf.append(html);
		 }
		 System.out.println(html2txtBuf.toString());*/
	}

}
