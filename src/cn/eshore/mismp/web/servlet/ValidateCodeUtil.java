

/**
 * 用法：
 * 1.页面中加：
 * 			<td align="right" >验证码：</td>
            <td ><input name="validateCode" type="text" class=forminput dataType="Require" msg="请输入验证码！"> 
            <img alt="" src="<%=basePath%>ValidateCode"   width="60" height="20"/  >*
 * 2.form中加：private String validateCode;
 * 3.action中加
 *  	String validateCode=mainIndexForm.getValidateCode();
		String sessionCode=(String) request.getSession().getAttribute("validateCode");
		if(validateCode==null||"".equals(validateCode)||!(validateCode.equals(sessionCode))){
			request.setAttribute("error_message","验证码输入有误，请重新输入！");
			return mapping.findForward("error");
		}
 * 
 */
package cn.eshore.mismp.web.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ValidateCodeUtil extends HttpServlet {
	/*public static Color getRandColor(int fc,int bc){//给定范围获得随机颜色    
		Random random = new Random();     
		if(fc>255) fc=255;       
		if(bc>255) bc=255;       
		int r=fc+random.nextInt(bc-fc);      
		int g=fc+random.nextInt(bc-fc);      
		int b=fc+random.nextInt(bc-fc);      
		return new Color(r,g,b); 		
	}*/
	public void doGet(HttpServletRequest request,
			HttpServletResponse response){
		//Image image1 = new Image();
//		设置页面不缓存
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
		// 在内存中创建图象
		int width=45, height=16;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		// 获取图形上下文
		Graphics g = image.getGraphics();
		//生成随机类
		Random random = new Random();
		// 设定背景色
		Color bgcolor=new Color(10000536);
		g.setColor(bgcolor);
		//g.setColor(getRandColor(200,250));
		g.fillRect(0, 0, width, height);
		//设定字体
		g.setFont(new Font("Times New Roman",Font.PLAIN,12));
		//画边框//g.setColor(new Color());//
		g.drawRect(0,0,width-1,height-1);
		// 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
		/*g.setColor(getRandColor(160,200));
		
		for (int i=0;i<155;i++){ 
			int x = random.nextInt(width); 
			int y = random.nextInt(height);      
			int xl = random.nextInt(12);       
			int yl = random.nextInt(12); 
			g.drawLine(x,y,x+xl,y+yl);
		}*/// 取随机产生的认证码(4位数字)
		String sRand="";for (int i=0;i<4;i++){
			String rand=String.valueOf(random.nextInt(10));   
			sRand+=rand;    
			// 将认证码显示到图象中   
			//g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));
			g.setColor(Color.WHITE);
			//调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成    
			g.drawString(rand,8*i+8,13);
		}
		// 将认证码存入
		request.getSession().setAttribute("validateCode",sRand);
		/*// 图象生效
		g.dispose();
		//输出图象到页面
		try {
			ImageIO.write(image, "JPEG", response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		try {
		      //使用JPEG编码，输出到response的输出流
		      JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(response.
		      getOutputStream());
		      JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(image);
		      param.setQuality(1.0f, false);
		      encoder.setJPEGEncodeParam(param);
		      encoder.encode(image);
		    }
		    catch (Exception ex) {
		    	
		    }

		
	}

}
