package cn.eshore.mismp.common;

import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.ActionInvocation;
import com.opensymphony.xwork.Result;

/**
 * <p>Description: 把chart文件流换成是通过HttpServletResponse 
 * 象获取到的输出流在浏览器中输出</p> 
 * @author: helin 
 */
@SuppressWarnings("serial")
public class ChartResult implements Result {
	JFreeChart chart;

	boolean chartSet = false;

	private int height;

	private int width;

	public void setChart(JFreeChart chart) {
		this.chart = chart;
		chartSet = true;
	}

	// 设置图片的长度
	public void setHeight(int height) {
		this.height = height;
	}

	// 设置图片的宽度
	public void setWidth(int width) {
		this.width = width;
	}

	public void execute(ActionInvocation invocation) throws Exception {
		JFreeChart chart = null;

		if (chartSet) {
			chart = this.chart;
		} else {
			chart = (JFreeChart) invocation.getStack().findValue("chart");
		}

		if (chart == null) {
			throw new NullPointerException("No chart found");
		}

		// 把文件流换成是通过HttpServletResponse对象获取到的输出流
		HttpServletResponse response = ServletActionContext.getResponse();
		OutputStream os = response.getOutputStream();
		ChartUtilities.writeChartAsPNG(os, chart, width, height);
		os.flush();
	}
}
